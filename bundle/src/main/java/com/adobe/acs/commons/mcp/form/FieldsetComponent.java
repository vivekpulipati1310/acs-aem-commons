/*
 * Copyright 2019 Adobe.
 *
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
 */
package com.adobe.acs.commons.mcp.form;

import org.apache.sling.api.resource.Resource;

/**
 * Wrap a field set (section) component
 */
public class FieldsetComponent extends AbstractContainerComponent {
    private static final String JCR_TITLE = "jcr:title";
    private static final String CLASS = "class";

    private String title;
    private String cssClass;

    public FieldsetComponent() {
        setResourceType("granite/ui/components/coral/foundation/form/fieldset");
    }

    @Override
    public void init() {
        super.init();
        if (getTitle() == null) {
            getOption(JCR_TITLE).ifPresent(this::setTitle);
        }
        getOption(CLASS).ifPresent(this::setCssClass);
    }

    @Override
    public Resource buildComponentResource() {
        getComponentMetadata().put(JCR_TITLE, getTitle());
        getComponentMetadata().put(CLASS, getClass());
        AbstractResourceImpl res = new AbstractResourceImpl(getPath(), getResourceType(), getResourceSuperType(), getComponentMetadata());
        if (sling != null) {
            res.setResourceResolver(sling.getRequest().getResourceResolver());
        }
        res.addChild(generateItemsResource(getPath() + "/items"));
        return res;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the cssClass
     */
    public String getCssClass() {
        return cssClass;
    }

    /**
     * @param cssClass the cssClass to set
     */
    public void setCssClass(String cssClass) {
        this.cssClass = cssClass;
    }
}
