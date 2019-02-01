/*
 * #%L
 * ACS AEM Commons Bundle
 * %%
 * Copyright (C) 2017 Adobe
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package com.adobe.acs.commons.mcp.form;

import com.adobe.acs.commons.mcp.util.AnnotatedFieldDeserializer;
import java.lang.reflect.ParameterizedType;
import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceMetadata;

/**
 * Represent a generic container component which has one or more children
 */
public class AbstractContainerComponent extends FieldComponent {

    Map<String, FieldComponent> fieldComponents = new LinkedHashMap<>();
    private boolean composite;

    public void init() {
        if (getField() != null) {
            if (getField().getType().isArray()) {
                extractFieldComponents(getField().getType().getComponentType());
            } else {
                ParameterizedType type = (ParameterizedType) getField().getGenericType();
                Class clazz = (Class) type.getActualTypeArguments()[0];
                extractFieldComponents(clazz);
            }
        }
        if (sling != null) {
            setPath(sling.getRequest().getResource().getPath());
        }
    }

    public Map<String, FieldComponent> getFieldComponents() {
        return fieldComponents;
    }

    private void extractFieldComponents(Class clazz) {
        if (clazz == String.class || clazz.isPrimitive()) {
            FieldComponent comp = new TextfieldComponent();
            FormField fieldDef = FormField.Factory.create(getName(), "", null, false, comp.getClass(), null);
            comp.setup(getName(), null, fieldDef, sling);
            comp.getComponentMetadata().put("title", getName());
            // TODO: Provide a proper mechanism for setting path when creating components
            addComponent(getName(), comp);
            composite = false;
        } else {
            AnnotatedFieldDeserializer.getFormFields(clazz, sling).forEach((name,component)->addComponent(name,component));
            composite = true;
        }
        fieldComponents.values().forEach(this::addClientLibraries);
    }

    public void addComponent(String name, FieldComponent field) {
        fieldComponents.put(name, field);
        addClientLibraries(field);
    }

    protected AbstractResourceImpl generateItemsResource(String path) {
        AbstractResourceImpl items = new AbstractResourceImpl(path + "/items" , "", "", new ResourceMetadata());
        for (FieldComponent component : fieldComponents.values()) {
            if (sling != null) {
                component.setSlingHelper(sling);
            }
            component.setPath(path + "/items/" + component.getName());
            Resource child = component.buildComponentResource();
            items.addChild(child);
        }
        if (sling != null) {
            items.setResourceResolver(sling.getRequest().getResourceResolver());
        }
        return items;
    }

    /**
     * @return the composite
     */
    public boolean isComposite() {
        return composite;
    }
}
