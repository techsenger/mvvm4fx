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

import com.techsenger.mvvm4fx.core.AbstractParentViewModel;
import com.techsenger.mvvm4fx.core.ComponentKey;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Pavel Castornii
 */
public class PersonViewModel extends AbstractParentViewModel {

    private final StringProperty title = new SimpleStringProperty();

    private final ObservableList<Person> persons = FXCollections.observableArrayList(
            new Person("John", "Smith", 25), new Person("Mike", "Brown", 40), new Person("Sarah", "Wilson", 34));

    private final Person person = new Person();

    private final BooleanProperty firstNameValid = new SimpleBooleanProperty(true);

    private final BooleanProperty lastNameValid = new SimpleBooleanProperty(true);

    private final BooleanProperty ageValid = new SimpleBooleanProperty(true);

    public PersonViewModel() {
        title.bind(Bindings.size(persons).asString("Person Component (%d Items)"));
    }

    @Override
    public ComponentKey getKey() {
        return ComponentKeys.PERSON;
    }

    ObservableList<Person> getPersons() {
        return persons;
    }

    Person getPerson() {
        return person;
    }

    StringProperty titleProperty() {
        return title;
    }

    BooleanProperty firstNameValidProperty() {
        return firstNameValid;
    }

    BooleanProperty lastNameValidProperty() {
        return lastNameValid;
    }

    BooleanProperty ageValidProperty() {
        return ageValid;
    }

    void addPerson() {
        var newPerson = new Person(person.getFirstName(), person.getLastName(), person.getAge());
        firstNameValid.set(person.isFirstNameValid());
        lastNameValid.set(person.isLastNameValid());
        ageValid.set(person.isAgeValid());
        if (firstNameValid.get() && lastNameValid.get() && ageValid.get()) {
            persons.add(newPerson);
            person.setFirstName(null);
            person.setLastName(null);
            person.setAge(null);
        }
    }
}
