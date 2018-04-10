# TheMovieDB

In order for the app to work, add an *apikeys.gradle* file in the project root folder with the next content:

```
buildscript {
  ext.THE_MOVIE_DB_API_KEY = "YOUR_API_KEY_HERE"
}
```

### Features
  - Show popular tv shows
  - Show basic details for those
  
### Libraries
  - Android AppCompat, Design, RecyclerView, CardView, ConstraintLayout
  - Dagger 2 for Dependency Injection
  - RxAndroid for Reactive extensions (Observer pattern)
  - Retrofit for Network handling
  - Stetho for Debug
  - Picasso for image handling
  - LeakCanary to detect memory leaks
  - Mockito for Unit Testing
  
### Information
Tried to follow Clean Code principles and modularize the app per feature. 
Using DI for the different components allow to Unit Test them easily as well as keeping the different features decoupled.

### Things to improve
  - Hanlding configuration changes
  - Add DB and a Repository layer to persist data
  - Improve UI
  - Add more features
  - Add reporting tool (Fabric)
