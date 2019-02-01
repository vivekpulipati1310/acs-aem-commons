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
 * Represent a form
 */
public class FormComponent extends AbstractContainerComponent {

    private String method = "post";
    private String action = "";
    private String enctype = "";
    private String target = "";
    private String autocomplete = "";
    private boolean novalidate = true;
    private String dataPath = "./";
    private String nameNotFoundMode = "ignore-freshness";
    private boolean autosubmitForm = false;
    private boolean margin = true;
    private boolean maximized = false;
    private boolean foundationForm = true;
    private boolean async = false;
    private boolean loadingMask = true;
    private String ui = "";
    private boolean successresponse = false;

    public FormComponent() {
        setResourceType("granite/ui/components/coral/foundation/form");
        getOption(METHOD).ifPresent(this::setMethod);
        getOption(ACTION).ifPresent(this::setAction);
        getOption(ENCTYPE).ifPresent(this::setEnctype);
        getOption(TARGET).ifPresent(this::setTarget);
        getOption(AUTOCOMPLETE).ifPresent(this::setAutocomplete);
        getOption(DATA_PATH).ifPresent(this::setDataPath);
        getOption(NAME_NOT_FOUND_MODE).ifPresent(this::setNameNotFoundMode);
        getOption(UI).ifPresent(this::setUi);
        getOption(NOVALIDATE).map(Boolean::parseBoolean).ifPresent(this::setNovalidate);
        getOption(AUTOSUBMIT_FORM).map(Boolean::parseBoolean).ifPresent(this::setAutosubmitForm);
        getOption(MARGIN).map(Boolean::parseBoolean).ifPresent(this::setMargin);
        getOption(MAXIMIZED).map(Boolean::parseBoolean).ifPresent(this::setMaximized);
        getOption(FOUNDATION_FORM).map(Boolean::parseBoolean).ifPresent(this::setFoundationForm);
        getOption(ASYNC).map(Boolean::parseBoolean).ifPresent(this::setAsync);
        getOption(LOADING_MASK).map(Boolean::parseBoolean).ifPresent(this::setLoadingMask);
        getOption(SUCCESSRESPONSE).map(Boolean::parseBoolean).ifPresent(this::setSuccessresponse);
    }
    private static final String ACTION = "action";
    private static final String ASYNC = "async";
    private static final String AUTOCOMPLETE = "autocomplete";
    private static final String AUTOSUBMIT_FORM = "autosubmitForm";
    private static final String DATA_PATH = "dataPath";
    private static final String ENCTYPE = "enctype";
    private static final String FOUNDATION_FORM = "foundationForm";
    private static final String LOADING_MASK = "loadingMask";
    private static final String MARGIN = "margin";
    private static final String MAXIMIZED = "maximized";
    private static final String METHOD = "method";
    private static final String NAME_NOT_FOUND_MODE = "nameNotFoundMode";
    private static final String NOVALIDATE = "novalidate";
    private static final String SUCCESSRESPONSE = "successresponse";
    private static final String TARGET = "target";
    private static final String UI = "ui";

    @Override
    public Resource buildComponentResource() {
        getComponentMetadata().put(ACTION, getAction());
        getComponentMetadata().put(ASYNC, isAsync());
        getComponentMetadata().put(AUTOCOMPLETE, getAutocomplete());
        getComponentMetadata().put(AUTOSUBMIT_FORM, isAutosubmitForm());
        getComponentMetadata().put(DATA_PATH, getDataPath());
        getComponentMetadata().put(ENCTYPE, getEnctype());
        getComponentMetadata().put(FOUNDATION_FORM, isFoundationForm());
        getComponentMetadata().put(LOADING_MASK, isLoadingMask());
        getComponentMetadata().put(MARGIN, isMargin());
        getComponentMetadata().put(MAXIMIZED, isMaximized());
        getComponentMetadata().put(METHOD, getMethod());
        getComponentMetadata().put(NAME_NOT_FOUND_MODE, getNameNotFoundMode());
        getComponentMetadata().put(NOVALIDATE, isNovalidate());
        getComponentMetadata().put(SUCCESSRESPONSE, isSuccessresponse());
        getComponentMetadata().put(TARGET, getTarget());
        getComponentMetadata().put(UI, getUi());
        purgeEmptyMetadata();

        AbstractResourceImpl res = new AbstractResourceImpl(getPath(), getResourceType(), getResourceSuperType(), getComponentMetadata());
        if (sling != null) {
            res.setResourceResolver(sling.getRequest().getResourceResolver());
        }

        AbstractResourceImpl items = generateItemsResource(getPath() + "/field");
        res.addChild(items);
        return res;
    }

    /**
     * @return the method
     */
    public String getMethod() {
        return method;
    }

    /**
     * @param method the method to set
     */
    final public void setMethod(String method) {
        this.method = method;
    }

    /**
     * @return the action
     */
    public String getAction() {
        return action;
    }

    /**
     * @param action the action to set
     */
    final public void setAction(String action) {
        this.action = action;
    }

    /**
     * @return the enctype
     */
    public String getEnctype() {
        return enctype;
    }

    /**
     * @param enctype the enctype to set
     */
    final public void setEnctype(String enctype) {
        this.enctype = enctype;
    }

    /**
     * @return the target
     */
    public String getTarget() {
        return target;
    }

    /**
     * @param target the target to set
     */
    final public void setTarget(String target) {
        this.target = target;
    }

    /**
     * @return the autocomplete
     */
    public String getAutocomplete() {
        return autocomplete;
    }

    /**
     * @param autocomplete the autocomplete to set
     */
    final public void setAutocomplete(String autocomplete) {
        this.autocomplete = autocomplete;
    }

    /**
     * @return the novalidate
     */
    public boolean isNovalidate() {
        return novalidate;
    }

    /**
     * @param novalidate the novalidate to set
     */
    final public void setNovalidate(boolean novalidate) {
        this.novalidate = novalidate;
    }

    /**
     * @return the dataPath
     */
    public String getDataPath() {
        return dataPath;
    }

    /**
     * @param dataPath the dataPath to set
     */
    final public void setDataPath(String dataPath) {
        this.dataPath = dataPath;
    }

    /**
     * @return the nameNotFoundMode
     */
    public String getNameNotFoundMode() {
        return nameNotFoundMode;
    }

    /**
     * @param nameNotFoundMode the nameNotFoundMode to set
     */
    final public void setNameNotFoundMode(String nameNotFoundMode) {
        this.nameNotFoundMode = nameNotFoundMode;
    }

    /**
     * @return the autosubmitForm
     */
    public boolean isAutosubmitForm() {
        return autosubmitForm;
    }

    /**
     * @param autosubmitForm the autosubmitForm to set
     */
    final public void setAutosubmitForm(boolean autosubmitForm) {
        this.autosubmitForm = autosubmitForm;
    }

    /**
     * @return the margin
     */
    public boolean isMargin() {
        return margin;
    }

    /**
     * @param margin the margin to set
     */
    final public void setMargin(boolean margin) {
        this.margin = margin;
    }

    /**
     * @return the maximized
     */
    public boolean isMaximized() {
        return maximized;
    }

    /**
     * @param maximized the maximized to set
     */
    final public void setMaximized(boolean maximized) {
        this.maximized = maximized;
    }

    /**
     * @return the foundationForm
     */
    public boolean isFoundationForm() {
        return foundationForm;
    }

    /**
     * @param foundationForm the foundationForm to set
     */
    final public void setFoundationForm(boolean foundationForm) {
        this.foundationForm = foundationForm;
    }

    /**
     * @return the async
     */
    public boolean isAsync() {
        return async;
    }

    /**
     * @param async the async to set
     */
    final public void setAsync(boolean async) {
        this.async = async;
    }

    /**
     * @return the loadingMask
     */
    public boolean isLoadingMask() {
        return loadingMask;
    }

    /**
     * @param loadingMask the loadingMask to set
     */
    final public void setLoadingMask(boolean loadingMask) {
        this.loadingMask = loadingMask;
    }

    /**
     * @return the ui
     */
    public String getUi() {
        return ui;
    }

    /**
     * @param ui the ui to set
     */
    final public void setUi(String ui) {
        this.ui = ui;
    }

    /**
     * @return the successresponse
     */
    public boolean isSuccessresponse() {
        return successresponse;
    }

    /**
     * @param successresponse the successresponse to set
     */
    final public void setSuccessresponse(boolean successresponse) {
        this.successresponse = successresponse;
    }

}
