# weather-now

This is a weather app that can show you the current weather info(Current tab).
It can save and show your past viewed weather info(history tab). 


This project used various libraries:
 - Retrofit
 - Hilt
 - Room
 - Glide
 - Mockk
 - Jetpack Components

## To run the application you must:
- create a top level directory and file with a name of dependencies/depencies.gradle
- inside the dependencies.gradle file create an ext block and appid variable inside
- this appid will hold the weathermap appid key(f1972c30f01df13f61f0325dbbcbabea) for you to be able to access weathermap api
- this appid key is already included in buildconfigfield so you will also be able to access it inside the app

#### example dependencies.gradle 
ext {
    appid = '"f1972c30f01df13f61f0325dbbcbabea"'
}
