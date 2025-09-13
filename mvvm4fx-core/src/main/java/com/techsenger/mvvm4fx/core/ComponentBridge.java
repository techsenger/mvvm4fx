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
 * This interface is used by the ParentViewModel to add or remove child components (such as tabs, panes, dialogs, etc.).
 * It provides a bridge for managing these components. The component bridge is created in the View and has a reference
 * to the View, but it does not provide a direct reference to the ViewModel.
 *
 * <p>For example, to show a dialog, the bridge implementation can include a method like the following:</p>
 *
 * <pre>
 * void openDialog(DialogViewModel dialogViewModel) {
 *     //alternatively, this logic can be handled within the View itself
 *     DialogView dialogView = new DialogView(dialogViewModel);
 *     dialogView.initialize();
 *     getView().openDialog(dialogView);
 * }
 * </pre>
 *
 * @author Pavel Castornii
 */
public interface ComponentBridge {

}
