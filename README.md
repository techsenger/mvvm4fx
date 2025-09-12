# Techsenger MVVM4FX

| Support the Project! |
|:-------------|
| This project is open-source and free to use, both commercially and non-commercially, which is why we need your help in its development. If you like it, please give it a star ⭐ on GitHub — it helps others discover the project and increases its visibility. You can also contribute, for example, by fixing bugs 🐛 or suggesting improvements 💡, see [Contributing](#contributing). If you can, financial support 💰 is always appreciated, see [Support Us](#support-us). Thank you! |

## Table of Contents
* [Overview](#overview)
* [Features](#features)
* [MVVM](#mvvm)
    * [What is MVVM?](#what-is-mvvm)
    * [MVVM Advantages](#mvvm-advantages)
* [Component](#component)
    * [What is a Component?](#what-is-component)
    * [Component Structure](#component-structure)
    * [Component Lifecycle](#component-lifecycle)
    * [Component Hierarchy](#component-hierarchy)
* [Requirements](#requirements)
* [Dependencies](#dependencies)
* [Code building](#code-building)
* [Running Sampler](#sampler)
* [License](#license)
* [Contributing](#contributing)
* [👉 Support Us](#support-us)

## Overview <a name="overview"></a>

Techsenger MVVM4FX is a tiny framework for developing JavaFX applications using the MVVM pattern. It provides all
the necessary interfaces and base class implementations for creating components, which serve as the units of the MVVM
pattern. Examples of components include tabs, dialog windows, toolbars, image viewers, help pages, and more.

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

## Component <a name="component"></a>

### What is a Component? <a name="what-is-component"></a>

A component is a fundamental, self-contained building block of a user interface (UI) that provides a specific
piece of functionality and enables user interaction. A component represents a higher-level abstraction than standard
UI controls, fundamentally distinguished by its compositional nature, which encompasses and organizes multiple
UI controls, its managed lifecycle, and its capacity to maintain state history. Crucially, while usually components
also encapsulate business logic, this is not a mandatory trait for all, as structural components like layout containers
demonstrate.

### Component Structure <a name="component-structure"></a>

A component always consists of at least two classes: a `ViewModel` and a `View`. A natural question might arise: why is
there no `Model` in the component, given that the pattern is called MVVM? Firstly, a component is a building block for
constructing a user interface, which might not be related to the application's business logic at all. Secondly, the
`Model` exists independently of the UI and should have no knowledge of the component's existence.

In addition to the `ViewModel` and `View`, a component may include two optional classes: `ComponentHistory` and
`ComponentHelper`.

The ComponentHistory enables the preservation of the component's state upon its destruction. Data exchange occurs
 exclusively between the `ViewModel` and the `ComponentHistory`. During component constructing, data is restored
from the `ComponentHistory` to the `ViewModel`, while during deinitialization, data from the `ViewModel` is saved to the
`ComponentHistory`.

The `ComponentHelper` is an interface that allows the `ViewModel` to request the `View` to perform specific actions.
These actions are typically related to creating or removing other components—operations that cannot be executed solely
within the `ViewModel`. It is important to emphasize that the `ViewModel` must never hold a direct reference to the
`View`, and the use of this interface does not violate this rule.

### Component Lifecycle <a name="component-lifecycle"></a>

A component has four distinct states (see `ComponentState`):

1. Unconstructed - The component has not yet been constructed (`ViewModel` exists, but `View` has not been created).

2. Constructed - Both the `ViewModel` and `View` have been created, but the component is not yet initialized.
It is important to note that when the component transitions to this state, the `ViewModel` state is restored from
the `ComponentHistory`.

3. Initialized - Both the ViewModel and View have been fully initialized and are ready for use. The component enters
this state upon completion of the `View#initialize(`) method, but before the call to the `AbstractView#postInitialize()`
method.

4. Deinitialized - The component has been deinitialized and can't be used anymore. It enters this state upon
completion of the `View#deinitialize()` method, but before the call to the `AbstractView#postDeinitialize()` method.
It is important to note that when the component transitions to this state, the `ViewModel` state is saved to the
`ComponentHistory`.

Each component features `View#initialize()` and `View#deinitialize()` methods, which initialize and deinitialize the
component, respectively, altering its state. The default implementation of these methods in `AbstractView` is achieved
through template methods that handle component building/unbuilding, binding/unbinding, adding/removing listeners,
and adding/removing handlers via corresponding protected methods. It is important to note that these protected methods
should not be considered the exclusive location for performing such tasks (e.g., adding/removing handlers) within the
component, but rather as part of the initialization/deinitialization process. Thus, adding/removing handlers may also
be performed in other methods of the component.

### Component Hierarchy <a name="component-hierarchy"></a>

Components can act as both parents and children, forming a tree structure that can change dynamically.
The library provides a mechanism for dynamically creating and removing components and includes optional logic
for managing component relationships, leaving their use to the developer's discretion.

The component tree is built according to the Unidirectional Hierarchy Rule (UHR). This rule establishes a strict
hierarchical order by explicitly prohibiting circular parent-child relationships, meaning a component cannot
simultaneously be a direct parent and a direct child of another component. The UHR is designed to maintain a clear,
acyclic structure, which prevents logical conflicts and ensures predictable behavior. Importantly, this rule does not
restrict child components from directly accessing or communicating with their parents; it solely forbids cyclical
dependencies that would compromise the architectural integrity of the hierarchy.

It is crucial to highlight the interaction between components. Consider a parent and a child component as an example.

The parent component's `ViewModel` holds a reference to the child component's `ViewModel` via its `children` field,
while the child component's `ViewModel` holds a reference to the parent component's `ViewModel` via its `parent` field.

Similarly, the parent component's `View` holds a reference to the child component's `View` through its `children` field,
and the child component's `View` holds a reference to the parent component's `View` via its `parent` field.

This dualistic linkage establishes a coherent and symmetric relationship between parent and child components at both
the View and ViewModel layers. The parent and child components are fully aware of each other's existence and state,
enabling direct coordination and communication within the hierarchy while maintaining clear separation of concerns
between the presentation (View) and logic (ViewModel) layers. This design ensures consistency and synchronization
across the component tree without violating the Unidirectional Hierarchy Rule (UHR), as the relationships are strictly
hierarchical and non-cyclic.

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
with fixes and new features. If you have any questions, feel free to reach out — we’ll be happy to assist you.

## 👉 Support Us <a name="support-us"></a>

You can support us financially through [GitHub Sponsors](https://github.com/sponsors/techsenger). Your
contribution directly helps us keep our open-source projects active, improve their features, and offer ongoing support.
Besides, we offer multiple sponsorship tiers, with different rewards.
