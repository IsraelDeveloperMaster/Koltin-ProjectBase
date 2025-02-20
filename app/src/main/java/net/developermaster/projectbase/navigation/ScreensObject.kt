package net.developermaster.projectbase.navigation


sealed class ScreensObject(val route: String) {

    //rota home
    data object HomeScreensObjectObject : ScreensObject("HomeScreens")

    //rota login
    data object LoginScreenObject : ScreensObject("LoginScreen")

    //rota splash
    data object SplashScreenObject : ScreensObject("SplashScreen")

    //rota real time
    data object RealTimeScreenObject : ScreensObject("RealTimeScreen")
}