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

import static com.techsenger.mvvm4fx.core.HistoryPolicy.ALL;
import static com.techsenger.mvvm4fx.core.HistoryPolicy.APPEARANCE;
import static com.techsenger.mvvm4fx.core.HistoryPolicy.DATA;
import static com.techsenger.mvvm4fx.core.HistoryPolicy.NONE;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleObjectProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Pavel Castornii
 */
public abstract class AbstractViewModel implements ViewModel {

    private static final Logger logger = LoggerFactory.getLogger(AbstractViewModel.class);

    private final ReadOnlyObjectWrapper<ComponentState> state =
            new ReadOnlyObjectWrapper<>(ComponentState.UNCONSTRUCTED);

    private final ObjectProperty<HistoryPolicy> historyPolicy = new SimpleObjectProperty<>(HistoryPolicy.NONE);

    private HistoryProvider historyProvider;

    private ComponentHistory<?> history;

    private boolean historyProvided;

    public AbstractViewModel() {
        state.addListener((ov, oldV, newV) -> {
            var policy = getHistoryPolicy();
            if (state.get() == ComponentState.CONSTRUCTED) {
                logger.debug("History policy on constucting: {}", policy);
                switch (policy) {
                    case DATA:
                        getOrRequestHistory().restoreData(this);
                        postHistoryRestore();
                        break;
                    case APPEARANCE:
                        getOrRequestHistory().restoreAppearance(this);
                        postHistoryRestore();
                        break;
                    case ALL:
                        var h = getOrRequestHistory();
                        h.restoreData(this);
                        h.restoreAppearance(this);
                        postHistoryRestore();
                        break;
                    case NONE:
                        break;
                    default:
                        throw new AssertionError();
                }
            } else if (state.get() == ComponentState.DEINITIALIZED) {
                logger.debug("History policy on deinitializing: {}", policy);
                //The data and the appearance are saved to the history during the deinitialization of the component,
                //not while the component is running, as this feature is rarely needed but significantly complicates
                //the code.
                switch (policy) {
                    case DATA:
                        preHistorySave();
                        getOrRequestHistory().saveData(this);
                        break;
                    case APPEARANCE:
                        preHistorySave();
                        getOrRequestHistory().saveAppearance(this);
                        break;
                    case ALL:
                        preHistorySave();
                        var h = getOrRequestHistory();
                        h.saveData(this);
                        h.saveAppearance(this);
                        break;
                    case NONE:
                        break;
                    default:
                        throw new AssertionError();
                }
            }
        });
    }

    @Override
    public ComponentState getState() {
        return this.state.get();
    }

    @Override
    public ReadOnlyObjectProperty<ComponentState> stateProperty() {
        return state.getReadOnlyProperty();
    }

    @Override
    public ObjectProperty<HistoryPolicy> historyPolicyProperty() {
        return historyPolicy;
    }

    @Override
    public HistoryPolicy getHistoryPolicy() {
        return historyPolicy.get();
    }

    @Override
    public void setHistoryPolicy(HistoryPolicy policy) {
        historyPolicy.set(policy);
    }

    public void setHistoryProvider(HistoryProvider historyProvider) {
        this.historyProvider = historyProvider;
    }

    public HistoryProvider getHistoryProvider() {
        return historyProvider;
    }

    protected void postHistoryRestore() {

    }

    protected void preHistorySave() {

    }

    ReadOnlyObjectWrapper<ComponentState> stateWrapper() {
        return state;
    }

    private ComponentHistory getOrRequestHistory() {
        if (!this.historyProvided) {
            if (this.historyProvider == null) {
                throw new NullPointerException("No history provider");
            }
            this.history = this.historyProvider.provide();
            this.historyProvided = true;
        }
        return this.history;
    }
}
