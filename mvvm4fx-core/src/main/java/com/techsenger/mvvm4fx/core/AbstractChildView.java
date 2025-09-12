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

import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Pavel Castornii
 */
public abstract class AbstractChildView<T extends AbstractChildViewModel> extends AbstractParentView<T>
        implements ChildView<T> {

    private static final Logger logger = LoggerFactory.getLogger(AbstractChildView.class);

    private final ReadOnlyObjectWrapper<ParentView<?>> parent = new ReadOnlyObjectWrapper<>();

    public AbstractChildView(T viewModel) {
        super(viewModel);
    }

    @Override
    public ReadOnlyObjectProperty<ParentView<?>> parentProperty() {
        return this.parent.getReadOnlyProperty();
    }

    @Override
    public ParentView<?> getParent() {
        return this.parent.get();
    }

    @Override
    protected void addListeners(T viewModel) {
        super.addListeners(viewModel);
        parent.addListener((ov, oldV, newV) -> {
            if (newV == null) {
                viewModel.parentWrapper().set(null);
            } else {
                viewModel.parentWrapper().set(newV.getViewModel());
            }
        });
    }

    /**
     * Sets the parent component for this component.
     * <p>
     * This method is normally called automatically when the component is added as a child to another component.
     * It can also be used explicitly when only the child-to-parent relationship needs to be established, without
     * adding the component to the parent's list of children.
     *
     * @param parent the parent component to set
     */
    protected void setParent(ParentView<?> parent) {
        this.parent.set(parent);
    }
}
