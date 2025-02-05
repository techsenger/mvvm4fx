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

import java.io.Serializable;

/**
 * The history of the component contains last states that a user used. For example, it can be dialog sizes etc.
 *
 * @author Pavel Castornii
 */
public interface ComponentHistory<T extends ViewModel> extends Serializable {

    /**
     * If history hasn't been created yet, it can be created automatically. In this case this method is called
     * to set default values.
     *
     */
    void setDefaultValues();

    /**
     * Method called before the component is serialized. This can be used to prepare the object's state
     * before saving it in a binary format.
     */
    void preSerialize();

    /**
     * Method called after the component is deserialized. This can be used to restore the object's state
     * after loading it from a binary format.
     */
    void postDeserialize();

    /**
     * Method copies all data from history to view model. This method is called when the component
     * becomes {@link ComponentState#CONSTRUCTED} and the policy is {@link HistoryPolicy#ALL} or
     * {@link HistoryPolicy#DATA}.
     *
     * @param viewModel
     */
    void restoreData(T viewModel);

    /**
     * Method copies all data from view model to history. This method is called when the component
     * becomes {@link ComponentState#DEINITIALIZED} and the policy is {@link HistoryPolicy#ALL} or
     * {@link HistoryPolicy#DATA}.
     *
     * @param viewModel
     */
    void saveData(T viewModel);

    /**
     * Method copies all appearance information from history to view model. This method is called when the component
     * becomes {@link ComponentState#CONSTRUCTED} and the policy is {@link HistoryPolicy#ALL} or
     * {@link HistoryPolicy#APPEARANCE}.
     *
     * @param viewModel
     */
    void restoreAppearance(T viewModel);

    /**
     * Method copies all data from view model to history. This method is called when the component
     * becomes {@link ComponentState#DEINITIALIZED} and the policy is {@link HistoryPolicy#ALL} or
     * {@link HistoryPolicy#APPEARANCE}.
     *
     * @param viewModel
     */
    void saveAppearance(T viewModel);
}
