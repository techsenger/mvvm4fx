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
 * When ParentViewModel needs to add/remove any child component (tabs, panes, dialogs etc) then it must use this
 * component helper. Component helper is created in a view and has a reference to the view.
 *
 * @author Pavel Castornii
 */
public interface ComponentHelper<T extends ParentView<?>> {

}
