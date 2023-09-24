# GitHub App

GitHub App is an Android application that allows users to explore GitHub repositories and user profiles. With this app, users can log in using their GitHub username or via Google Sign-In, view user profiles, and browse repositories.

## Table of Contents

- [Features](#features)
- [Components](#components)
- [Architecture](#architecture)
- [Testability](#testability)
- [Firebase Cloud Messaging (FCM)](#firebase-cloud-messaging-fcm)
- [Notification](#notification)
- [Usage](#usage)
- [Acknowledgments](#acknowledgments)

## Features

- **Login**: Users can log in to the app using either their GitHub username or Google Sign-In.
- **User Profile**: After logging in, users can view their GitHub profile information, including their avatar, name, followers, and following count.
- **Repositories**: Users can see a list of their GitHub repositories.
- **Explore Other Profiles**: Users can explore other GitHub profiles by entering their usernames.
- **Notification**: Receive notifications for specific events, such as repository updates, using Firebase Cloud Messaging (FCM).

## Components

The app consists of several components:

- **Model**: Represents the data, including user profiles and repositories.
- **View**: Represents the user interface (UI), including XML layout files.
- **Controller**: Manages user interactions and data flow between the model and view. It includes activities and controllers for different app functionalities.
- **Firebase Cloud Messaging (FCM)**: Handles notifications and pushes them to the app.

## Architecture

The project follows the Model-View-Controller (MVC) architecture pattern:

- **Model**: Represents the data (e.g., `GitHubUser` and `GitHubRepo` classes). We chose the MVC architecture to separate the data handling and manipulation from the user interface, making it easier to manage and test data-related operations independently.

- **View**: Represents the user interface (e.g., XML layout files). The view layer is responsible for displaying data to the user. By keeping the view separate from the model and controller, we ensure a clean separation of concerns and maintainability of the user interface.

- **Controller**: Manages user interactions and data flow (e.g., `LoginActivity`, `UserActivity`, `Repositories`). In the controller layer, we handle user input, make API requests, and manage the flow of data between the model and view. The MVC architecture allows us to encapsulate application logic and keep it separate from the user interface, enhancing code modularity and testability.

By adopting the MVC architecture, we achieve several benefits:

- **Separation of Concerns**: Each component (model, view, controller) has a well-defined role and responsibility, making it easier to understand and maintain the codebase.

- **Modularity**: The project is divided into smaller, reusable modules, promoting code reusability and maintainability.

- **Testability**: The separation of concerns and modularity of components facilitate unit testing. We can write tests for individual components, ensuring code reliability and reducing the risk of regressions.

- **Scalability**: The architecture can easily accommodate future changes and feature additions. New views or controllers can be added without affecting existing components.

Overall, the MVC architecture was chosen for its ability to create a clear separation of concerns, enhance code maintainability, and enable efficient unit testing of the application's various components.

## Testability

The project is designed with testability in mind. By following the MVC architecture and modularizing the code, we enable easy unit testing of individual components. You can write unit tests for models, controllers, and other classes, ensuring the reliability of the codebase.

## Firebase Cloud Messaging (FCM)

The app leverages Firebase Cloud Messaging (FCM) for handling notifications. Users can receive notifications for specific events, such as updates to their GitHub repositories. Notifications are displayed at the top of the screen.

## Notification

To show a sample notification message when receiving a Firebase Cloud Messaging (FCM) message, the app utilizes the `MyFirebaseMessagingService` class. When an FCM message is received, a custom Toast notification is displayed at the top of the screen.

## Usage

To use the GitHub App, follow these steps:

1. **Login**: Log in to the app using your GitHub username or Google Sign-In(But most users are not provided gmail in their repositories)
2. **User Profile**: After logging in, view your GitHub profile information.
3. **Repositories**: Explore your GitHub repositories.
4. **Explore Other Profiles**: Enter other users' GitHub usernames to view their profiles and repositories.

## Acknowledgments

The GitHub App was developed as a learning project to explore Android app development, API integration, and cloud messaging. It uses GitHub's public API for fetching user and repository data.

