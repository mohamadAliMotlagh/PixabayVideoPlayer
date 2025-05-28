# Pixabay Video Player

A modern Android application for searching, playing, and bookmarking videos from Pixabay. Built with **Jetpack Compose** and **MVI** architecture, featuring robust **state management**, **configuration change** support, and following **clean architecture** principles.

## 🎥 Recomposition Demo

<img src="docs/assets/gif_2.gif" width="60%" alt="App Demo">

*Zero recompositions achieved through optimized Compose implementation*

## 📥 Download

[Download APK](https://drive.google.com/file/d/1yQOqWPiazq5wucqtAzTiMUZVpgSBybzi/view?usp=sharing)

*Version 1.0.0*

## 🎯 Features

- **Video Search**: Search and browse videos from Pixabay
- **Video Playback**: Watch videos with a custom player supporting both portrait and landscape modes
- **Bookmarking**: Save your favorite videos for offline access
- **Responsive UI**: Beautiful Material 3 design with smooth animations
- **Offline First**: Instant local results with async remote updates when online, seamless offline access when disconnected

## 🎯 Tech Stack

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
    classDef module fill:#E3F2FD,stroke:#1976D2,stroke-width:2px,color:#0D47A1
    classDef core fill:#E8F5E9,stroke:#2E7D32,stroke-width:2px,color:#1B5E20
    classDef domain fill:#FFF3E0,stroke:#E65100,stroke-width:2px,color:#BF360C
    classDef feature fill:#F3E5F5,stroke:#6A1B9A,stroke-width:2px,color:#4A148C
    
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

## 🔑 API Key

To run the app, you need to:
1. Get an API key from [Pixabay](https://pixabay.com/api/docs/)
2. Add it to `local.properties`:
```properties
PIXABAY_API_KEY=your_api_key_here
```