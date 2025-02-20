package net.developermaster.projectbase.screens.EstudoUI.EstudoUi2

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage

@Composable
fun UiScreenEstudo2_2(navController: NavHostController) {

    Column(
        modifier = Modifier.fillMaxSize(),

        ) {

        AsyncImage(
            model = "https://firebasestorage.googleapis.com/v0/b/project208-2.appspot.com/o/banner1.png?alt=media&token=91f65188-3bff-4ad6-8b02-31c00aeb3ca6",
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 8.dp)
                .height(150.dp)
        )
    }
}