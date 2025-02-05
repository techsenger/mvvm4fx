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

import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.scene.Scene;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Pavel Castornii
 */
public abstract class AbstractChildView<T extends AbstractChildViewModel> extends AbstractParentView<T>
        implements ChildView<T> {

    private static final Logger logger = LoggerFactory.getLogger(AbstractChildView.class);

    private ChangeListener<? super Scene> sceneListener;

    private Runnable preLayoutPulseListener;

    private Runnable postLayoutPulseListener;

    private final List<LayoutPulseListener> preLayoutPulseListeners = new ArrayList<>();

    private final List<LayoutPulseListener> postLayoutPulseListeners = new ArrayList<>();

    public AbstractChildView(T viewModel) {
        super(viewModel);
    }

    protected void addLayoutPulseListener(PulseListenerTiming timing, LayoutPulseListener listener) {
        //if we have scene, we add pulse listener, otherwise we add scene listener
        var scene = sceneProperty().get();
        switch (timing) {
            case BEFORE:
                if (this.preLayoutPulseListeners.isEmpty()) {
                    if (this.sceneListener == null) {
                        if (scene == null) {
                            addSceneListener();
                        } else {
                            addPreLayoutPulseListener(scene);
                        }
                    }
                }
                this.preLayoutPulseListeners.add(listener);
                break;
            case AFTER:
                if (this.postLayoutPulseListeners.isEmpty()) {
                    if (this.sceneListener == null) {
                        if (scene == null) {
                            addSceneListener();
                        } else {
                            addPostLayoutPulseListener(scene);
                        }
                    }
                }
                this.postLayoutPulseListeners.add(listener);
                break;
            default:
                throw new AssertionError();
        }
    }

    protected void removeLayoutPulseListener(PulseListenerTiming timing, LayoutPulseListener listener) {
        var scene = sceneProperty().get();
        switch (timing) {
            case BEFORE:
                this.preLayoutPulseListeners.remove(listener);
                if (this.sceneListener == null) {
                    checkPreLayoutPulseListener(scene);
                } else {
                    checkSceneListener();
                }
                break;
            case AFTER:
                this.postLayoutPulseListeners.remove(listener);
                if (this.sceneListener == null) {
                    checkPostLayoutPulseListener(scene);
                } else {
                    checkSceneListener();
                }
                break;
            default:
                throw new AssertionError();
        }
    }

    /**
     * Returns the scene property for getting scene that will be used for setting layout pulse listener.
     *
     * @return
     */
    protected abstract ReadOnlyObjectProperty<Scene> sceneProperty();

    private void addSceneListener() {
        this.sceneListener = (ov, oldV, newV) -> {
            if (newV != null) {
                if (!this.preLayoutPulseListeners.isEmpty()) {
                    addPreLayoutPulseListener(newV);
                }
                if (!this.postLayoutPulseListeners.isEmpty()) {
                    addPostLayoutPulseListener(newV);
                }
                removeSceneListener();
            }
        };
        sceneProperty().addListener(this.sceneListener);
        logger.debug("Added scene listener for {}", getViewModel().getKey());
    }

    private void checkSceneListener() {
        if (this.preLayoutPulseListeners.isEmpty() && this.postLayoutPulseListeners.isEmpty()) {
            removeSceneListener();
        }
    }

    private void removeSceneListener() {
        if (this.sceneListener != null) {
            sceneProperty().removeListener(this.sceneListener);
            this.sceneListener = null;
            logger.debug("Removed scene listener for {}", getViewModel().getKey());
        }
    }

    private void addPreLayoutPulseListener(Scene scene) {
        this.preLayoutPulseListener = () -> {
            callListeners(this.preLayoutPulseListeners);
            checkPreLayoutPulseListener(scene);
        };
        scene.addPreLayoutPulseListener(this.preLayoutPulseListener);
        logger.debug("Added pre layout pulse listener for {}", getViewModel().getKey());
    }

    private void checkPreLayoutPulseListener(Scene scene) {
        if (this.preLayoutPulseListeners.isEmpty()) {
            removePreLayoutPulseListener(scene);
        }
    }

    private void removePreLayoutPulseListener(Scene scene) {
        if (this.preLayoutPulseListener != null) {
            scene.removePreLayoutPulseListener(this.preLayoutPulseListener);
            this.preLayoutPulseListener = null;
            logger.debug("Removed pre layout pulse listener for {}", getViewModel().getKey());
        }
    }

    private void addPostLayoutPulseListener(Scene scene) {
        this.postLayoutPulseListener = () -> {
            callListeners(this.postLayoutPulseListeners);
            checkPostLayoutPulseListener(scene);
        };
        scene.addPostLayoutPulseListener(this.postLayoutPulseListener);
        logger.debug("Added post layout pulse listener for {}", getViewModel().getKey());
    }

    private void checkPostLayoutPulseListener(Scene scene) {
        if (this.postLayoutPulseListeners.isEmpty()) {
            removePostLayoutPulseListener(scene);
        }
    }

    private void removePostLayoutPulseListener(Scene scene) {
        if (this.postLayoutPulseListener != null) {
            scene.removePostLayoutPulseListener(this.postLayoutPulseListener);
            this.postLayoutPulseListener = null;
            logger.debug("Removed post layout pulse listener for {}", getViewModel().getKey());
        }
    }

    private void callListeners(List<LayoutPulseListener> listeners) {
        var iterator = listeners.iterator();
        while (iterator.hasNext()) {
           var listener = iterator.next();
           if (!listener.onLayoutPulse()) {
               iterator.remove();
           }
       }
    }
}
