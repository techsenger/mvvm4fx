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
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.util.converter.IntegerStringConverter;

/**
 *
 * @author Pavel Castornii
 */
public class PersonDialogView extends AbstractParentView<PersonDialogViewModel> {

    private final Label firstNameLabel = new Label("First Name");

    private final TextField firstNameTextField = new TextField();

    private final Label lastNameLabel = new Label("Last Name");

    private final TextField lastNameTextField = new TextField();

    private final TextField ageTextField = new TextField();

    private final Label ageLabel = new Label("Age");

    private final HBox hBox = new HBox(firstNameLabel, firstNameTextField, lastNameLabel, lastNameTextField,
                ageLabel, ageTextField);

    private final Dialog<Person> dialog = new Dialog<>();

    private Button okButton;

    public PersonDialogView(PersonDialogViewModel viewModel) {
        super(viewModel);
    }

    public Dialog<Person> getDialog() {
        return this.dialog;
    }

    @Override
    protected void build(PersonDialogViewModel viewModel) {
        super.build(viewModel);
        firstNameLabel.setMinWidth(Region.USE_PREF_SIZE);
        HBox.setHgrow(firstNameTextField, Priority.ALWAYS);
        lastNameLabel.setMinWidth(Region.USE_PREF_SIZE);
        HBox.setHgrow(lastNameTextField, Priority.ALWAYS);
        ageLabel.setMinWidth(Region.USE_PREF_SIZE);
        HBox.setHgrow(ageTextField, Priority.ALWAYS);
        hBox.setSpacing(10);
        hBox.setAlignment(Pos.CENTER_LEFT);

        dialog.getDialogPane().setContent(hBox);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.CANCEL, ButtonType.OK);
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == ButtonType.OK) {
                return viewModel.createPerson();
            }
            return null;
        });

        this.okButton = (Button) dialog.getDialogPane().lookupButton(ButtonType.OK);
    }

    @Override
    protected void bind(PersonDialogViewModel viewModel) {
        super.bind(viewModel);
        dialog.titleProperty().bind(viewModel.titleProperty());

        firstNameTextField.textProperty().bindBidirectional(viewModel.getPerson().firstNameProperty());
        bindValid(firstNameTextField, viewModel.firstNameValidProperty());
        lastNameTextField.textProperty().bindBidirectional(viewModel.getPerson().lastNameProperty());
        bindValid(lastNameTextField, viewModel.lastNameValidProperty());
        ageTextField.textProperty().bindBidirectional(viewModel.getPerson().ageProperty(),
                new IntegerStringConverter());
        bindValid(ageTextField, viewModel.ageValidProperty());
    }

    @Override
    protected void addHandlers(PersonDialogViewModel viewModel) {
        super.addHandlers(viewModel);
        okButton.addEventFilter(ActionEvent.ACTION, event -> {
            if (!viewModel.isPersonValid()) {
                event.consume();
            }
        });
    }

    private void bindValid(TextField textField, BooleanProperty validProperty) {
        textField.styleProperty().bind(Bindings.when(validProperty)
                .then("")
                .otherwise("-fx-background-color: red, white; -fx-background-insets: 0, 1"));
    }

}
