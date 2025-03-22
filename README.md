# Sanctum: A Secure, AI-Powered Journal

Sanctum is a privacy-focused, offline-first journal designed to keep your thoughts safe and your
experience distraction-free. It combines strong local encryption, clean architecture, and a
foundation for intelligent features without compromising user control.

This isn't just another notes app. It's a quiet space for your thoughts.

## Why Sanctum?

In a landscape filled with generic note-taking apps, Sanctum is built around a few core principles:
- **Private by default:** No cloud sync, no account required.
- **Secure by design:** Notes are encrypted end-to-end before reaching local storage.
- **Offline-first architecture:** Data lives on-device and is synced only when appropriate.
- **Modular and scalable:** Built with long-term maintainability and feature isolation in mind.
- **Prepared for meaningful AI:** Future features will focus on assistance, not intrusion.

## Key Features

- End-to-end encrypted journal entries
- Offline-first storage using Room with Protobuf
- Background sync via WorkManager
- Jetpack Compose UI with Material 3 design
- Clean multi-module structure
- Dependency Injection with Hilt (KSP)
- AI-ready architecture with support for contextual assistance
- Gradle convention plugins and version catalog

## Architecture Overview

Sanctum is structured using a multi-module, feature-based layout. Core principles include:

- Separation of concerns between model, data, and feature layers
- Scalable architecture, ready for growing feature sets
- Each feature is separated into an `-api` and `-implementation` module.
- Offline-first with optional sync, fully under user control

This structure keeps features isolated, improves testability, and makes dependency management more
explicit.

## Tech Stack

| Area                 | Tech Used                           |
|----------------------|-------------------------------------|
| UI                   | Jetpack Compose, Material 3         |
| Architecture         | MVVM + Clean Multi-Module           |
| Persistence          | Room + Protobuf                     |
| Background Work      | WorkManager                         |
| Serialization        | Kotlinx Serialization               |
| Dependency Injection | Hilt (w/ KSP)                       |
| AI Foundation        | Prepared for local NLP features     |
| Gradle               | Version Catalog, Convention Plugins |
| Testing              | Robolectric, Turbine, Truth         |

## Testing Strategy

Sanctum avoids mocking libraries like MockK in favor of realistic test doubles. Tests use actual
implementations or handcrafted fakes with test-only hooks. This approach helps:
- Exercise more production code
- Avoid brittle mocks
- Encourage contract-based architecture

## Requirements

- Android Studio Hedgehog or newer
- JVM 17
- Minimum SDK: 24

## Getting Started

### Clone the repository

```
https://github.com/harrytmthy-dev/sanctum.git
```

### Run tests

```
./gradlew testDebugUnitTest
```