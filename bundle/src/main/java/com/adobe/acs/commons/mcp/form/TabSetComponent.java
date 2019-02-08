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

import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.sling.api.resource.Resource;

/**
 * An expression of the tab layout container with convenience methods to build tabs more easily.
 * The data structures are meant to show content in the order it is added.
 */
public class TabSetComponent extends ContainerComponent {
    private final Map<String, ContainerComponent> tabs = new LinkedHashMap<>();

    public ContainerComponent getOrCreateTab(String tabName) {
        if (!tabs.containsKey(tabName)) {
            ContainerComponent tab = new ContainerComponent();
            tab.setTitle(tabName);
            tabs.put(tabName, tab);
            super.addComponent(tabName, tab);
            return tab;
        } else {
            return tabs.get(tabName);
        }

    }

    public void addComponent(String tabName, String fieldName, FieldComponent component) {
        getOrCreateTab(tabName).addComponent(fieldName, component);
    }

    @Override
    public Resource buildComponentResource() {
        getComponentMetadata().put("layout", "granite/ui/components/foundation/layouts/tabs");
        return super.buildComponentResource();
    }
}
