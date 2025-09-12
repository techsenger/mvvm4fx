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

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;

/**
 *
 * @author Pavel Castornii
 */
public interface ComponentViewModel {

    /**
     * Returns the key of the component.
     *
     * @return
     */
    ComponentKey getKey();

    /**
     * Returns the state of the component.
     * @return
     */
    ComponentState getState();

    /**
     * Returns the state property of the component.
     *
     * @return
     */
    ReadOnlyObjectProperty<ComponentState> stateProperty();

    /**
     * Returns the property for the history policy.
     *
     * @return
     */
    ObjectProperty<HistoryPolicy> historyPolicyProperty();

    /**
     * Returns the history policy.
     *
     * @return
     */
    HistoryPolicy getHistoryPolicy();

    /**
     * Sets the history policy to the specified value.
     *
     * @param policy the history policy to set.
     */
    void setHistoryPolicy(HistoryPolicy policy);

}
