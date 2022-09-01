# D2L.kt

Calling the D2L [API](https://docs.valence.desire2learn.com/reference.html), made easy.

## Obtaining User ID and User Key
```kotlin
D2LAuth.buildLoginURL("myAppID", "myAppKey", "example.com", "http://linkbackaddress.com")
```
This will create an URL, where you can log in to your account as usual.

## Calling the API
Set the values in D2LRequest

```kotlin
with(D2LRequest) {
    appID = "myAppID"
    appKey = "myAppKey"
    userID = "myUserID"
    userKey = "myUserKey"
    baseURL = "example.com"
}
```

Call the API
```kotlin
val courses = CourseRoute.getCourses()
```
