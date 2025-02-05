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

package com.techsenger.mvvm4fx.sampler;

import com.techsenger.mvvm4fx.core.ComponentKey;

/**
 * Component keys should be located in the API and be accessible to other components. At the same time, the
 * component itself can be deeply hidden in the implementation packages.
 *
 * @author Pavel Castornii
 */
public final class ComponentKeys {

    public static final ComponentKey PERSON = new ComponentKey("Person component");

    private ComponentKeys() {
        //empty
    }
}
