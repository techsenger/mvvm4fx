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

package com.techsenger.patternfx.demo.mvp;

import com.techsenger.patternfx.demo.model.Person;
import com.techsenger.patternfx.demo.model.PersonService;
import com.techsenger.patternfx.mvp.AbstractParentPresenter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Pavel Castornii
 */
public class RegistryPresenter<V extends RegistryView> extends AbstractParentPresenter<V> {

    private final List<Person> persons = new ArrayList<>();

    private final PersonService service;

    private int selectedIndex;

    private boolean removeDisabled = true;

    private boolean reportShown;

    public RegistryPresenter(V view, RegistryParams params) {
        super(view, params);
        this.service = params.getService();
    }

    public boolean isRemoveDisabled() {
        return this.removeDisabled;
    }

    public boolean isReportShown() {
        return this.reportShown;
    }

    protected void onSelectedChanged(int value) {
        setRemoveDisabled(value < 0);
        this.selectedIndex = value;
    }

    protected void onAdd() {
        var dialog = getView().getComposer().openDialog();
        var newPerson = dialog.getResult();
        dialog.deinitialize();
        if (newPerson != null) {
            service.save(newPerson);
            persons.add(newPerson);
            getView().addPersons(List.of(newPerson));
            refreshReport();
        }
    }

    protected void onRemove() {
        if (selectedIndex < 0) {
            return;
        }
        var person = persons.get(selectedIndex);
        Objects.requireNonNull(person.getId());
        service.delete(person.getId());
        persons.remove(selectedIndex);
        getView().removePerson(selectedIndex);
        refreshReport();
    }

    protected void onRefresh() {
        this.persons.clear();
        getView().clearPersons();
        this.persons.addAll(service.readAll());
        getView().addPersons(this.persons);
        refreshReport();
    }

    protected void onReport() {
        var composer = getView().getComposer();
        if (composer.getReport() == null) {
            composer.showReport();
            refreshReport();
            setReportShown(true);
        } else {
            composer.hideReport();
            setReportShown(false);
        }
    }

    protected void onCloseRequest() {
        deinitialize();
    }

    @Override
    protected void postInitialize() {
        super.postInitialize();
        getView().show();
        onRefresh();
    }

    private void refreshReport() {
        var report = getView().getComposer().getReport();
        if (report != null) {
            report.refresh(persons);
        }
    }

    private void setRemoveDisabled(boolean removeDisabled) {
        if (this.removeDisabled == removeDisabled) {
            return;
        }
        this.removeDisabled = removeDisabled;
        getView().updateRemoveDisabled(removeDisabled);
    }

    private void setReportShown(boolean reportShown) {
        if (this.reportShown == reportShown) {
            return;
        }
        this.reportShown = reportShown;
        getView().updateReportShown(reportShown);
    }
}
