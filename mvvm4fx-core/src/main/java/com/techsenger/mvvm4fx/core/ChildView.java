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

/**
 * Nested component is any component.
 *
 * @author Pavel Castornii
 */
public interface ChildView<T extends ChildViewModel> extends ParentView<T> {

    /**
     * Returns the property representing the parent component of this component. The property holds a reference to the
     * parent if this component is currently added as a child to another component, or {@code null} if it has no parent.
     *
     * @return the property containing the parent component
     */
    ReadOnlyObjectProperty<ParentView<?>> parentProperty();

    /**
     * Returns the value of {@link #parentProperty()}.
     *
     * @return the parent component, or {@code null} if this component has no parent
     */
    ParentView<?> getParent();

    /**
     * Requests focus. Child component implements this method via selecting FX node that will request focus.
     */
    void requestFocus();

    /**
     * Returns the main node of the component. It can be Tab, Node etc.
     *
     * @return
     */
    Object getNode();
}
