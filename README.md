# mymovies-kueski

This is a simple Android app that lists popular movies from TheMovieDB database.

## Features

- Paginated list of popular movies
- Detail screen with images and info about movies.
- Search functionality
- Movies are stored locally so you can access them while offline

## Key Libraries

-   **Key libraries in this project**
    -   `Room` for database management.
    -   `Retrofit` for HTTP requests.
    -   `Dagger Hilt` for dependency injection.
    -   `Jetpack Navigation` to handle navigation between screens.
    -   `Jetpack Paging` to hable data pagination.
    -   `Coil` image loading.
    -   `JUnit` for unit testing.
    -   `MockK` for unit testing.

## Setup

Remember to add your **TheMovieDB API** key in the `gradle.properties` file. If you don't have one, get it [here](https://developer.themoviedb.org/).
```
TMDB_API_KEY=XXXXXXXXXXXXXXXXX
```