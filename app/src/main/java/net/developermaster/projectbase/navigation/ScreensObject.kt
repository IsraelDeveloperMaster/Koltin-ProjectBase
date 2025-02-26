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

    //rota ui screen estudo shop 1
    data object UiScreenShopEstudo1 : ScreensObject("UiScreenShopEstudo1")

    //rota ui screen estudo shop 2
    data object UiScreenShopEstudo2 : ScreensObject("UiScreenShopEstudo2")

    //rota ui screen estudo shop 3
    data object UiScreenShopEstudo3 : ScreensObject("UiScreenShopEstudo3")

    //rota ui screen estudo shop 4
    data object UiScreenShopEstudo4 : ScreensObject("UiScreenShopEstudo4")
}