# TheMovieDB

In order for the app to work, add an *apikeys.gradle* file in the project root folder with the next content:

```
buildscript {
  ext.THE_MOVIE_DB_API_KEY = "YOUR_API_KEY_HERE"
}
```
The API_KEY should be the v3 auth one.

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
I've mainly focused on having a good architecture, there is plenty of things to improve the UI but I 've prefered to set a good core for the app where you can easily improve/replace the UI without affecting the bussines logic.

### Things to improve (with more time XD)
  - Hanlding configuration changes (not reloading the data)
  - Add DB and a Repository layer to persist data
  - Improve UI
  - Add more features
  - Add reporting tool (Fabric)
  - Use architecture components for better context awareness
  - Improve error handling
  - UI Testing
