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

package com.techsenger.mvvm4fx.demo;

import com.techsenger.mvvm4fx.core.AbstractParentView;
import com.techsenger.mvvm4fx.demo.model.Person;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Pavel Castornii
 */
public class PersonRegistryView extends AbstractParentView<PersonRegistryViewModel> {

    private final Button addButton = new Button("Add");

    private final Button removeButton = new Button("Remove");

    private final Button refreshButton = new Button("Refresh");

    private final ToolBar toolBar = new ToolBar(addButton, removeButton, refreshButton);

    private final TableView<Person> personTable = new TableView<>();

    private final VBox content = new VBox(personTable);

    private final VBox root = new VBox(toolBar, content);

    private final Stage stage;

    public PersonRegistryView(Stage stage, PersonRegistryViewModel viewModel) {
        super(viewModel);
        this.stage = stage;
    }

    @Override
    protected void build(PersonRegistryViewModel viewModel) {
        super.build(viewModel);
        VBox.setVgrow(personTable, Priority.ALWAYS);
        personTable.setItems(viewModel.getPersons());
        personTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        var idColumn = new TableColumn<Person, Integer>("Id");
        idColumn.setCellValueFactory(data -> data.getValue().idProperty());
        var firstNameColumn = new TableColumn<Person, String>("First Name");
        firstNameColumn.setCellValueFactory(data -> data.getValue().firstNameProperty());
        var lastNameColumn = new TableColumn<Person, String>("Last Name");
        lastNameColumn.setCellValueFactory(data -> data.getValue().lastNameProperty());
        var ageColumn = new TableColumn<Person, Integer>("Age");
        ageColumn.setCellValueFactory(data -> data.getValue().ageProperty());
        personTable.getColumns().addAll(idColumn, firstNameColumn, lastNameColumn, ageColumn);

        VBox.setVgrow(content, Priority.ALWAYS);
        content.setPadding(new Insets(Style.INSET));

        stage.setScene(new Scene(root, 800, 500));
    }

    @Override
    protected void bind(PersonRegistryViewModel viewModel) {
        super.bind(viewModel);
        stage.titleProperty().bind(viewModel.titleProperty());
        viewModel.selectedPersonProperty().bind(personTable.getSelectionModel().selectedItemProperty());
        removeButton.disableProperty().bind(viewModel.removeDisabledProperty());
    }

    @Override
    protected void addHandlers(PersonRegistryViewModel viewModel) {
        super.addHandlers(viewModel);
        addButton.setOnAction(e -> {
            var dialogVM = viewModel.createDialog();
            var dialogV = new PersonDialogView(dialogVM);
            dialogV.initialize();
            var dialog = dialogV.getDialog();
            dialog.initOwner(stage);
            dialog.initModality(Modality.WINDOW_MODAL);
            var result = dialog.showAndWait();
            viewModel.add(result);
            dialogV.deinitialize();
        });
        removeButton.setOnAction(e -> viewModel.remove());
        refreshButton.setOnAction(e -> viewModel.refresh());
    }

    @Override
    protected void postInitialize(PersonRegistryViewModel viewModel) {
        super.postInitialize(viewModel);
        stage.show();
        viewModel.refresh();
    }
}
