# Pixabay Video Player

A modern Android application for searching, playing, and bookmarking videos from Pixabay. Built with Jetpack Compose and following clean architecture principles.

## 🎯 Features

- **Video Search**: Search and browse videos from Pixabay
- **Video Playback**: Watch videos with a custom player supporting both portrait and landscape modes
- **Bookmarking**: Save your favorite videos for offline access
- **Responsive UI**: Beautiful Material 3 design with smooth animations
- **Offline Support**: Access bookmarked videos without internet connection

## �� Tech Stack

- **UI**: Jetpack Compose
- **Architecture**: MVI (Model-View-Intent)
- **Dependency Injection**: Hilt
- **Database**: Room
- **Networking**: Retrofit
- **Video Player**: ExoPlayer
- **Build System**: Gradle with Kotlin DSL
- **Language**: Kotlin

## 🏗 Project Structure

The project follows a modular architecture with clear separation of concerns:

```mermaid
graph LR
    %% Main Project
    Root[PixabayVideoPlayer]
    
    %% Core Modules
    CoreUI[core:ui]
    CoreDomain[core:domain]
    CoreNetwork[core:network]
    CoreDB[core:database]
    CoreDesign[core:designsystem]
    CoreMVI[core:mvi]
    CoreUtil[core:util]
    CoreTest[core:testing]
    
    %% Core Domain Modules
    DomainBookmark[core:domain:bookmarking]
    DomainVideo[core:domain:video]
    
    %% Feature Directories
    Feature[feature]
    Search[feature/search]
    Bookmark[feature/bookmark]
    Player[feature/player]
    
    %% App Module
    App[app]
    
    %% Dependencies with spacing
    Root --> App
    Root --> CoreUI
    Root --> CoreDomain
    Root --> CoreNetwork
    Root --> CoreDB
    Root --> CoreDesign
    Root --> CoreMVI
    Root --> CoreUtil
    Root --> CoreTest
    
    %% Core Domain Module Dependencies
    CoreDomain --> DomainBookmark
    CoreDomain --> DomainVideo
    
    %% Feature Directory Structure with spacing
    Feature --> Search
    Feature --> Bookmark
    Feature --> Player
    
    %% App Dependencies with spacing
    App --> Search
    App --> Bookmark
    App --> Player
    
    %% Feature Module Dependencies with spacing
    Search -.-> CoreUI
    Search -.-> DomainVideo
    Search -.-> DomainBookmark
    Search -.-> CoreNetwork
    Search -.-> CoreDesign
    
    Bookmark -.-> CoreUI
    Bookmark -.-> DomainBookmark
    Bookmark -.-> CoreDB
    Bookmark -.-> CoreDesign
    
    Player -.-> CoreUI
    Player -.-> DomainVideo
    Player -.-> DomainBookmark
    Player -.-> CoreDesign
    
    %% Style
    classDef module fill:#f9f,stroke:#333,stroke-width:2px
    classDef core fill:#bbf,stroke:#333,stroke-width:2px
    classDef domain fill:#fbb,stroke:#333,stroke-width:2px
    classDef feature fill:#bfb,stroke:#333,stroke-width:2px
    
    class Root,App module
    class CoreUI,CoreNetwork,CoreDB,CoreDesign,CoreMVI,CoreUtil,CoreTest core
    class DomainBookmark,DomainVideo domain
    class Search,Bookmark,Player feature
```

### Modules

- **app**: Main application module
- **core**: Common functionality and utilities
  - **ui**: Base UI components and common composables
  - **domain**: Business logic and use cases
    - **bookmarking**: Bookmark-related domain logic
    - **video**: Video-related domain logic
  - **network**: API clients and network utilities
  - **database**: Room database and data access
  - **designsystem**: Theme, colors, and typography
  - **mvi**: State management and ViewModel base classes
  - **util**: Common utilities and extensions
  - **testing**: Test utilities and helpers
- **feature**: Feature modules
  - **search**: Video search functionality
  - **bookmark**: Bookmark management
  - **player**: Video playback

## 🚀 Getting Started

### Prerequisites

- Android Studio Hedgehog | 2023.1.1 or later
- JDK 11
- Android SDK 34 or later

### Installation

1. Clone the repository:
```bash
git clone https://github.com/yourusername/PixabayVideoPlayer.git
```

2. Open the project in Android Studio

3. Add your Pixabay API key in `local.properties`:
```properties
PIXABAY_API_KEY=your_api_key_here
```

4. Build and run the project

## 📝 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 🤝 Contributing

Contributions, issues, and feature requests are welcome! Feel free to check the [issues page](https://github.com/yourusername/PixabayVideoPlayer/issues).

## 📫 Contact

Your Name - [@yourtwitter](https://twitter.com/yourtwitter)

Project Link: [https://github.com/yourusername/PixabayVideoPlayer](https://github.com/yourusername/PixabayVideoPlayer)
