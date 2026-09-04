/*
 * Copyright 2024-2026 Pavel Castornii.
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

package com.techsenger.patternfx.core;

import com.techsenger.annotations.Nullable;
import java.util.Objects;

/**
 *
 * @author Pavel Castornii
 */
public abstract class AbstractName implements Name {

    private final @Nullable String text;

    public AbstractName(@Nullable String text) {
        this.text = text;
    }

    /**
     * Returns the human-readable name.
     *
     * @return the component name text
     */
    @Override
    public @Nullable String getText() {
        return text;
    }

    /**
     * Two names are equal when they are instances of the exact same concrete class and carry the same
     * {@link #getText()} &mdash; e.g. a {@code ComponentName} and a {@code MenuGroupName} with identical text
     * are never equal, since they identify different kinds of slots. Names are compared by value (not just
     * reference) so that a registry keyed by name matches a freshly-constructed name carrying the same text as
     * an already-registered constant, rather than silently missing it.
     */
    // Intentional getClass() check: distinct concrete subtypes (e.g. ComponentName vs. MenuGroupName) must
    // never be equal even with identical text, which an instanceof-based equals would incorrectly allow.
    @Override
    @SuppressWarnings("EqualsGetClass")
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return Objects.equals(text, ((AbstractName) obj).text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClass(), text);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "[text=" + String.valueOf(text) + "]";
    }
}
