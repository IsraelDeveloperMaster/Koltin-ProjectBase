package net.developermaster.projectbase.navigation


sealed class ScreensObject(val route : String) {

    //rota home
    data object HomeScreensObjectObject : ScreensObject("HomeScreens")

    //rota login
    data object LoginScreenObject : ScreensObject("LoginScreen")

    //rota splash
    data object SplashScreenObject : ScreensObject("SplashScreen")

    //rota real time
    data object RealTimeScreenObject : ScreensObject("RealTimeScreen")

    //rota ui screen estudo 1
    data object UiScreenEstudo1 : ScreensObject("UiScreenEstudo1")

    //rota ui screen estudo 2
    data object UiScreenEstudo2 : ScreensObject("UiScreenEstudo2")

    //rota ui screen estudo 3
    data object UiScreenEstudo3 : ScreensObject("UiScreenEstudo3")
}