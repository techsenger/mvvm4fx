/*
 * Copyright 2024-2025 Pavel Castornii.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.techsenger.mvvm4fx.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Pavel Castornii
 */
public abstract class AbstractView<T extends AbstractViewModel> implements View<T> {

    private static final Logger logger = LoggerFactory.getLogger(AbstractView.class);

    private T viewModel;

    public AbstractView(T viewModel) {
        this.viewModel = viewModel;
        this.viewModel.stateWrapper().set(ComponentState.CONSTRUCTED);
    }

    @Override
    public T getViewModel() {
        return viewModel;
    }

    /**
     * Initializes view.
     */
    @Override
    public final void initialize() {
        try {
            if (this.viewModel.stateWrapper().get() != ComponentState.CONSTRUCTED) {
                throw new IllegalStateException("Unexpected state of the component");
            }
            preInitialize(viewModel);
            build(viewModel);
            bind(viewModel);
            addListeners(viewModel);
            addHandlers(viewModel);
            this.viewModel.stateWrapper().set(ComponentState.INITIALIZED);
            logger.debug("Initialized component: {}", viewModel.getKey().toString());
            postInitialize(viewModel);
        } catch (Exception ex) {
            logger.error("Error initializing {} - {}", this.getClass().getName(),
                Integer.toHexString(this.hashCode()), ex);
        }
    }

    /**
     * Deinitializes view.
     */
    @Override
    public final void deinitialize() {
        try {
            if (this.viewModel.stateWrapper().get() != ComponentState.INITIALIZED) {
                throw new IllegalStateException("Unexpected state of the component");
            }
            preDeinitialize(viewModel);
            removeHandlers(viewModel);
            removeListeners(viewModel);
            unbind(viewModel);
            unbuild(viewModel);
            this.viewModel.stateWrapper().set(ComponentState.DEINITIALIZED);
            logger.debug("Deinitialized component: {}", viewModel.getKey().toString());
            postDeinitialize(viewModel);
        } catch (Exception ex) {
            logger.error("Error deinitializing {} - {}", this.getClass().getName(),
                Integer.toHexString(this.hashCode()), ex);
        }
    }

    /**
     * The first method called in initialization.
     */
    protected void preInitialize(T viewModel) {

    }

    /**
     * Builds view.
     */
    protected void build(T viewModel) {

    }

    /**
     * Binds view to viewModel etc.
     */
    protected void bind(T viewModel) {

    }

    /**
     * Initializes listeners to different properties etc.
     */
    protected void addListeners(T viewModel) {

    }

    /**
     * Initializes handlers for mouse, keyboard etc events.
     */
    protected void addHandlers(T viewModel) {

    }

    /**
     * The last method called in initialization.
     */
    protected void postInitialize(T viewModel) {

    }

    /**
     * The first method called in deinitialization.
     */
    protected void preDeinitialize(T viewModel) {

    }

    /**
     * Removes handlers.
     *
     * @param viewModel
     */
    protected void removeHandlers(T viewModel) {

    }

    /**
     * Removes listeners.
     *
     * @param viewModel
     */
    protected void removeListeners(T viewModel) {

    }

    /**
     * Unbinds view from viewModel etc.
     *
     * @param viewModel
     */
    protected void unbind(T viewModel) {

    }

    /**
     * Unbuilds view.
     * @param viewModel
     */
    protected void unbuild(T viewModel) {

    }

    /**
     * The last method called in deinitialization.
     */
    protected void postDeinitialize(T viewModel) {

    }
}
