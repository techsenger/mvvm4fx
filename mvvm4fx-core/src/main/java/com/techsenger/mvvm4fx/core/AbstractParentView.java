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

/**
 *
 * @author Pavel Castornii
 */
public abstract class AbstractParentView<T extends AbstractParentViewModel> extends AbstractView<T>
        implements ParentView<T> {

    public AbstractParentView(T viewModel) {
        super(viewModel);
        viewModel.setComponentHelper(createComponentHelper());
    }

    /**
     * Creates dialog helper that then is set to view model. Default implementation provides null.
     * @return
     */
    protected ComponentHelper<?> createComponentHelper() {
        return null;
    }
}
