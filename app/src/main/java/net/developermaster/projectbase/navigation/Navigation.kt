package net.developermaster.projectbase.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.scaleIn
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import net.developermaster.projectbase.screens.HomeScreen
import net.developermaster.projectbase.screens.LoginScreen
import net.developermaster.projectbase.screens.RealTimeScreen
import net.developermaster.projectbase.screens.SplashScreen
import net.developermaster.projectbase.estudoUI.estudoUiShop.UiScreenShopEstudo1
import net.developermaster.projectbase.estudoUI.estudoUiShop.UiScreenShopEstudo2
import net.developermaster.projectbase.estudoUI.estudoUiShop.UiScreenShopEstudo3
import net.developermaster.projectbase.viewModel.ViewModelProjectBase


@Composable
fun NavigationNavController() {

    //hook que retorna um navController
    val navController = rememberNavController()

    //controlador de navegação que recebe o navController para a rota inicial
    NavHost(
        navController =
        navController,
        startDestination =
        ScreensObject.UiScreenShopEstudo1.route

    ) {

        //rota home
        composable(ScreensObject.HomeScreensObjectObject.route) {
            HomeScreen(navController)
        }

        //rota login
        composable(ScreensObject.LoginScreenObject.route) {
            LoginScreen(autenticacao = FirebaseAuth.getInstance(), navigateToHome = {

                navController.navigate(ScreensObject.HomeScreensObjectObject.route)

            })
        }

        //rota splash
        composable(ScreensObject.SplashScreenObject.route) {
            SplashScreen(navController)
        }

        //rota real time
        composable(ScreensObject.RealTimeScreenObject.route) {
            RealTimeScreen(ViewModelProjectBase(), navController)
        }

        //rota ui screen estudo shop 1
        composable(ScreensObject.UiScreenShopEstudo1.route,

            //transições de entrada
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    tween(500)
                ) + fadeIn() + scaleIn(tween(500))
            },

            //transições de saída
        )

        {
            UiScreenShopEstudo1(navController)
        }

        //rota ui screen estudo shop 2
        composable(ScreensObject.UiScreenShopEstudo2.route) {
            UiScreenShopEstudo2(navController)
        }

        //rota ui screen estudo shop 3
        composable(ScreensObject.UiScreenShopEstudo3.route,

            //transições de entrada
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    tween(500)
//                ) + slideInHorizontally(tween(200)) + fadeIn()
//                ) + scaleIn(tween(200)) + fadeIn()
                ) + fadeIn() + scaleIn(tween(500))
            },

            //transições de saída
        )

        {
            UiScreenShopEstudo3(navController)
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
