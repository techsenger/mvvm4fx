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

import com.techsenger.mvvm4fx.core.AbstractParentView;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

/**
 *
 * @author Pavel Castornii
 */
public class PersonView extends AbstractParentView<PersonViewModel> {

    private final TableView<Person> personTable = new TableView<>();

    private final Label firstNameLabel = new Label("First Name");

    private final TextField firstNameTextField = new TextField();

    private final Label lastNameLabel = new Label("Last Name");

    private final TextField lastNameTextField = new TextField();

    private final TextField ageTextField = new TextField();

    private final Label ageLabel = new Label("Age");

    private final Button addButton = new Button("Add");

    private final HBox hBox = new HBox(firstNameLabel, firstNameTextField, lastNameLabel, lastNameTextField,
                ageLabel, ageTextField, addButton);

    private final VBox root = new VBox(personTable, hBox);

    private final Stage stage;

    public PersonView(Stage stage, PersonViewModel viewModel) {
        super(viewModel);
        this.stage = stage;
    }

    @Override
    protected void build(PersonViewModel viewModel) {
        super.build(viewModel);

        VBox.setVgrow(personTable, Priority.ALWAYS);
        personTable.setItems(viewModel.getPersons());
        personTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        var firstNameColumn = new TableColumn<Person, String>("First Name");
        firstNameColumn.setCellValueFactory(data -> data.getValue().firstNameProperty());
        var lastNameColumn = new TableColumn<Person, String>("Last Name");
        lastNameColumn.setCellValueFactory(data -> data.getValue().lastNameProperty());
        var ageColumn = new TableColumn<Person, Integer>("Age");
        ageColumn.setCellValueFactory(data -> data.getValue().ageProperty());
        personTable.getColumns().addAll(firstNameColumn, lastNameColumn, ageColumn);

        firstNameLabel.setMinWidth(Region.USE_PREF_SIZE);
        HBox.setHgrow(firstNameTextField, Priority.ALWAYS);
        lastNameLabel.setMinWidth(Region.USE_PREF_SIZE);
        HBox.setHgrow(lastNameTextField, Priority.ALWAYS);
        ageLabel.setMinWidth(Region.USE_PREF_SIZE);
        HBox.setHgrow(ageTextField, Priority.ALWAYS);
        hBox.setSpacing(10);
        hBox.setAlignment(Pos.CENTER_LEFT);
        addButton.setMinWidth(Region.USE_PREF_SIZE);

        root.setPadding(new Insets(10));
        root.setSpacing(10);
        stage.setScene(new Scene(root, 800, 500));
    }

    @Override
    protected void bind(PersonViewModel viewModel) {
        super.bind(viewModel);
        stage.titleProperty().bind(viewModel.titleProperty());
        firstNameTextField.textProperty().bindBidirectional(viewModel.getPerson().firstNameProperty());
        lastNameTextField.textProperty().bindBidirectional(viewModel.getPerson().lastNameProperty());
        ageTextField.textProperty().bindBidirectional(viewModel.getPerson().ageProperty(),
                new IntegerStringConverter());
    }

    @Override
    protected void addHandlers(PersonViewModel viewModel) {
        super.addHandlers(viewModel);
        addButton.setOnAction(e -> viewModel.addPerson());
    }

    @Override
    protected void postInitialize(PersonViewModel viewModel) {
        super.postInitialize(viewModel);
        stage.show();
    }
}
