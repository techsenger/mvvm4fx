# Techsenger MVVM4FX

| Support the Project! |
|:-------------|
| This project is open-source and free to use, both commercially and non-commercially, which is why we need your help in its development. If you like it, please give it a star ⭐ on GitHub — it helps others discover the project and increases its visibility. You can also contribute, for example, by fixing bugs 🐛 or suggesting improvements 💡 — see [Contributing](#contributing). If you can, financial support 💰 is always appreciated — see [Support Us](#support-us). Thank you for your support! |

## Table of Contents
* [Overview](#overview)
* [Features](#features)
* [MVVM](#mvvm)
    * [What is MVVM?](#what-is-mvvm)
    * [MVVM Advantages](#mvvm-advantages)
* [Requirements](#requirements)
* [Dependencies](#dependencies)
* [Code building](#code-building)
* [Running Sampler](#sampler)
* [License](#license)
* [Contributing](#contributing)
* [Support Us](#support-us)

## Overview <a name="overview"></a>

Techsenger MVVM4FX is a tiny framework for developing JavaFX applications using the MVVM pattern. It provides all
the necessary interfaces and base class implementations for creating components, which serve as the units of the MVVM
pattern. Examples of components include tabs, dialog windows, toolbars, image viewers, help pages, and more.

Components can be both parent and child, forming a tree structure that can change dynamically. The library
provides a mechanism for dynamically creating and removing components but does not include logic for maintaining the
relationships between them. This is intentional, as there are various options that can be easily implemented
independently.

Each component has template methods initialize() and deinitialize(), which manage its lifecycle. This simplifies the
control of initialization processes, dependency setup, and resource cleanup when the component is removed.

## Features <a name="features"></a>

Key features include:

* Support for the component lifecycle.
* Organization of core tasks within the view.
* Component inheritance.
* Ability to preserve component history.
* Designed without considering FXML support.
* Detailed documentation and sample code.

## MVVM <a name="mvvm"></a>

### What is MVVM? <a name="what-is-mvvm"></a>

MVVM (Model-View-ViewModel) is an architectural pattern that divides an application's logic into three main parts:
Model, View, and ViewModel.

Model — encapsulates the data and business logic of the application. Models represent an abstraction that stores and
processes the application’s data, including all business logic rules and data validation logic. Models do not interact
with the UI and do not know about View or ViewModel. Instead, they provide data and perform actions related to the
business logic. Model can include:

* Data (for example, entities from a database or objects obtained from external sources).
* Business logic (such as data processing rules, calculations, data manipulation).
* Validation logic (for example, checks that are performed before saving data).

View — represents the user interface that displays the data. The View's task is to contain UI elements and bind their
state to the ViewModel. View is responsible for displaying data and interacting with the user, but it should not
contain logic for managing the state of these elements. Because it is the responsibility of the ViewModel to control
this state without knowing about specific controls in the View. For example, if the ViewModel indicates that a button
should be active or inactive, the View will update the control, but the View will not manage the logic that determines
when the button should be enabled or disabled.

ViewModel — manages the state of UI elements without needing to know the implementation details of the user interface.
ViewModel can also serve as a layer between the View and Model, obtaining data from the Model and preparing it for
display in the View. It can transform the data from the model into a format suitable for UI presentation.

### MVVM Advantages <a name="mvvm-advantages"></a>

* Separation of concerns. MVVM helps to clearly separate the presentation logic (View), business logic and data (Model),
and interaction logic (ViewModel). This simplifies code maintenance and makes it more readable.

* Testability. The ViewModel can be tested independently of the user interface (UI) because it is not tied to specific
visual elements. This makes it easy to write unit tests for business logic.

* Two-way data binding. In MVVM, data is automatically synchronized between the View and ViewModel, which reduces the
amount of code required for managing UI state and simplifies updates.

* Simplification of complex UIs. When an application has complex UIs with dynamic data, MVVM helps make the code more
understandable and structured, easing management of UI element states.

* UI updates without direct manipulation. The ViewModel manages updates to the View via data binding, avoiding direct
manipulation of UI elements. This makes the code more flexible and scalable.

## Requirements <a name="requirements"></a>

Java 11+ and JavaFX 11+.

## Dependencies <a name="dependencies"></a>

The project will be added to the Maven Central repository in a few days.

## Code Building <a name="code-building"></a>

To build the library use standard Git and Maven commands:

    git clone https://github.com/techsenger/mvvm4fx
    cd mvvm4fx
    mvn clean install

## Running Sampler <a name="sampler"></a>

To run the sampler execute the following commands in the root of the project:

    cd mvvm4fx-sampler
    mvn javafx:run

Please note, that debugger settings are in `mvvm4fx-sampler/pom.xml` file.

## License <a name="license"></a>

Techsenger MVVM4FX is licensed under the Apache License, Version 2.0.

## Contributing <a name="contributing"></a>

We welcome all contributions. You can help by reporting bugs, suggesting improvements, or submitting pull requests
with fixes and new features.

## Support Us <a name="support-us"></a>

You can support us financially through [GitHub Sponsors](https://github.com/sponsors/techsenger). Your
contribution directly helps us keep our open-source projects active, improve their features, and offer ongoing support.
Besides, we offer multiple sponsorship tiers, with different rewards.
