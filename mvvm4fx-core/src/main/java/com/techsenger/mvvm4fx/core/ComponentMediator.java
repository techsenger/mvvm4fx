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
 * Defines a mediator interface used by the {@code ParentViewModel} to perform view-related operations,
 * such as adding or removing child components (tabs, panes, dialogs, etc.).
 *
 * <p>The {@code ComponentMediator} acts as an intermediary between the {@code ComponentViewModel} and its corresponding
 * {@code ComponentView}. It is created within the View layer and maintains a reference to the View, allowing the
 * ViewModel to request UI actions without holding a direct reference to the View itself.
 *
 * <p>For example, to display a dialog, the mediator implementation might define a method such as:
 *
 * <pre>
 * void openDialog(DialogViewModel dialogViewModel) {
 *     // Alternatively, this logic can be encapsulated inside the View itself.
 *     DialogView dialogView = new DialogView(dialogViewModel);
 *     dialogView.initialize();
 *     getView().openDialog(dialogView);
 * }
 * </pre>
 *
 * @author Pavel Castornii
 */
public interface ComponentMediator {

}
