# MyBrightMind

MyBrightMind is an Android application designed to make learning fun and engaging. It offers interactive courses, captivating stories, and a rewards system to motivate users to explore and learn in an enjoyable way. While some features are still under development, they will be completed in future updates.

## Features

- **Interactive Courses**: Explore various topics with engaging lessons.
- **Story World**: Dive into stories that make learning exciting.
- **Rewards System**: Earn rewards as you complete courses and lessons.
- **User Profiles**: Sign up as a student, parent, or teacher to personalize your experience.
- **Navigation**: Intuitive bottom navigation for seamless app usage.

## Project Structure

```
MyBrightMind/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/example/mybrightmind/  # Java source files
│   │   │   ├── res/                            # Resources (layouts, drawables, etc.)
│   │   │   ├── assets/                         # JSON data for courses and stories
│   │   │   ├── AndroidManifest.xml             # App manifest
│   │   ├── test/                               # Unit tests
│   │   ├── androidTest/                        # Instrumented tests
│   ├── build.gradle.kts                        # Module-level Gradle configuration
├── build.gradle.kts                            # Project-level Gradle configuration
├── settings.gradle.kts                         # Gradle settings
├── gradle/                                     # Gradle wrapper files
├── gradlew                                     # Gradle wrapper script (Unix)
├── gradlew.bat                                 # Gradle wrapper script (Windows)
├── README.md                                   # Project documentation
```

## Getting Started

### Prerequisites

- Android Studio (latest version recommended)
- Java 11 or higher
- Gradle 8.10.2 (configured via Gradle wrapper)

### Installation

1. Clone the repository:
   ```sh
   git clone https://github.com/your-username/MyBrightMind.git
   ```
2. Open the project in Android Studio.
3. Sync the Gradle files.

### Running the App

1. Connect an Android device or start an emulator.
2. Build and run the app from Android Studio.

### Testing

- **Unit Tests**: Located in `app/src/test/java`.
- **Instrumented Tests**: Located in `app/src/androidTest/java`.

Run tests using the following Gradle command:
```sh
./gradlew testDebugUnitTest
```

## Dependencies

This project uses the following libraries:

- [AndroidX AppCompat](https://developer.android.com/jetpack/androidx/releases/appcompat)
- [Material Components](https://material.io/develop/android)
- [ConstraintLayout](https://developer.android.com/reference/androidx/constraintlayout/widget/ConstraintLayout)
- [JUnit](https://junit.org/junit4/)
- [Espresso](https://developer.android.com/training/testing/espresso)

## License

This project is licensed under the Apache License 2.0. See the [LICENSE](LICENSE) file for details.

## Contributing

Contributions are welcome! Please follow these steps:

1. Fork the repository.
2. Create a new branch for your feature or bug fix.
3. Commit your changes and push them to your fork.
4. Submit a pull request.

 
