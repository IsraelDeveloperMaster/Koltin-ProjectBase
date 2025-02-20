package net.developermaster.projectbase.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import net.developermaster.projectbase.screens.HomeScreen
import net.developermaster.projectbase.screens.LoginScreen
import net.developermaster.projectbase.screens.RealTimeScreen
import net.developermaster.projectbase.screens.SplashScreen
import net.developermaster.projectbase.screens.EstudoUI.EstudoUi1.UiScreenEstudo1_1
import net.developermaster.projectbase.screens.EstudoUI.EstudoUi1.UiScreenEstudo2_1
import net.developermaster.projectbase.screens.EstudoUI.EstudoUi1.UiScreenEstudo3_1
import net.developermaster.projectbase.screens.EstudoUI.EstudoUi2.UiScreenEstudo3_2
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
        ScreensObject.UiScreenEstudo1.route

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
            RealTimeScreen(ViewModelProjectBase(),  navController)
        }

        //rota ui screen estudo 1
        composable(ScreensObject.UiScreenEstudo1.route,

            //transições de entrada
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    tween(200)
                ) + fadeIn()
            },

            //transições de saída
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    tween(200)
                ) + fadeOut()
            })

        {
            UiScreenEstudo1_1(navController)
        }

        //rota ui screen estudo 2
        composable(ScreensObject.UiScreenEstudo2.route) {
            UiScreenEstudo2_1(navController)
        }

        //rota ui screen estudo 3
        composable(ScreensObject.UiScreenEstudo3.route,

            //transições de entrada
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    tween(200)
//                ) + slideInHorizontally(tween(200)) + fadeIn()
                ) + scaleIn(tween(200)) + fadeIn()
//                )  + fadeIn()
            },

            //transições de saída
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    tween(200)
//                ) + slideOutHorizontally(tween(200)) + fadeOut()
                ) + scaleOut(tween(200)) + fadeOut()
//                ) + fadeOut()
            })

        {
            UiScreenEstudo3_2(navController)
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
