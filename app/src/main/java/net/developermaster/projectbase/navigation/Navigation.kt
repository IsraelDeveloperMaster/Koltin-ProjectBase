package net.developermaster.projectbase.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import net.developermaster.projectbase.screens.HomeScreen
import net.developermaster.projectbase.screens.SplashScreen


@Composable
fun NavigationNavController() {

    //hook que retorna um navController
    val navController = rememberNavController()

    //controlador de navegação que recebe o navController para a rota inicial
    NavHost(
        navController = navController, startDestination =

        ScreensObject.SplashScreenObject.route

    ) {

        //rota home
        composable(ScreensObject.HomeScreensObjectObject.route) {
            HomeScreen(navController)
        }

        //rota login
        composable(ScreensObject.LoginScreenObject.route) {
//            LoginGestionScreen(navController)
        }

        //rota splash
        composable(ScreensObject.SplashScreenObject.route) {
            SplashScreen(navController)
        }


        /*
        //rota
        composable(
            ScreensObject.SelectScreenOptionsObject.route + "/{itemId}",
            arguments = listOf(navArgument("itemId") {
                type = NavType.StringType
            })

        ) {
            SelectScreenOptions(navController, it.arguments?.getString("itemId") ?: "")
        }

        composable(ScreensObject.PlantaPrincipalScreenObject.route) {
            PlantaPrincipalScreen(navController)
        }
        composable(
            ScreensObject.PlantaScreenObject.route + "/{planta}",
            arguments = listOf(navArgument("planta") {
                type = NavType.StringType
            })

        ) {
            PlantaScreen(navController, it.arguments?.getString("planta") ?: "")
        }

        */
    }
}
