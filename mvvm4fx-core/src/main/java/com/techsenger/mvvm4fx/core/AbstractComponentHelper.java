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
public abstract class AbstractComponentHelper<T extends ParentView<?>> implements ComponentHelper<T> {

    private final T view;

    public AbstractComponentHelper(T view) {
        this.view = view;
    }

    protected final T getView() {
        return view;
    }
}
