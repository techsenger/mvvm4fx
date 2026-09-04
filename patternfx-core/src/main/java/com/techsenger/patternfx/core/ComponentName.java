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

/**
 * An opaque, human-readable label identifying a component or a slot it exposes &mdash; not a substitute for the
 * component's actual Java type, and not a guarantee of any particular equality contract of its own: this
 * interface declares no {@code equals()}/{@code hashCode()}, so that is entirely up to whichever concrete
 * implementation is used.
 *
 * <p>A component's name is one, shared across every element that makes it up &mdash; its presenter (or view
 * model) and its view alike &mdash; since together they form a single logical component, not the identity of
 * whichever one of those classes happens to hold the descriptor.
 *
 * @author Pavel Castornii
 */
public interface ComponentName extends Name {

}
