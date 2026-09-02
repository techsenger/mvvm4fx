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

package com.techsenger.patternfx.core;

import com.techsenger.annotations.Nullable;

/**
 * Base for a {@link Name} that is always declared once as a shared constant and referenced by every consumer
 * directly &mdash; never freshly reconstructed from a separately-known text. Equality is therefore by identity, not
 * by {@link #getText()}: {@link AbstractName} compares by value specifically to let a freshly-reconstructed name
 * (e.g. a component descriptor's name, rebuilt on every instantiation) match an already-registered one with the
 * same text, which does not apply here and would only let two unrelated constants that happen to share text (or,
 * for a name whose text is always {@code null}, any two constants at all) collide.
 *
 * @author Pavel Castornii
 */
public abstract class AbstractIdentityName implements Name {

    private final @Nullable String text;

    public AbstractIdentityName(@Nullable String text) {
        this.text = text;
    }

    @Override
    public @Nullable String getText() {
        return text;
    }

    @Override
    public final boolean equals(Object obj) {
        return this == obj;
    }

    @Override
    public final int hashCode() {
        return System.identityHashCode(this);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "[text=" + String.valueOf(text) + "]";
    }
}
