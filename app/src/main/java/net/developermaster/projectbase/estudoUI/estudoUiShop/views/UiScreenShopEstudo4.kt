/*
 * *
 *  * Created by rael on 21/02/2025 23:28
 *  * Copyright (c) 2025 . All rights reserved.
 *  * Last modified 21/02/2025 23:28
 *
 */

package net.developermaster.projectbase.estudoUI.estudoUiShop.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import coil3.compose.rememberAsyncImagePainter
import net.developermaster.projectbase.R
import net.developermaster.projectbase.estudoUI.estudoUiShop.model.ModelUiScreenShopItems
import net.developermaster.projectbase.estudoUI.estudoUiShop.viewModel.ViewModelUiScreenShopEstudo

@Composable
fun UiScreenShopEstudo4(navController: NavHostController) {

    val viewModel = ViewModelUiScreenShopEstudo()

    val recommended by remember { mutableStateOf<ModelUiScreenShopItems>()
    ) }
    var showRcomemdedLoading by remember { mutableStateOf(true) }


    //recommended
    LaunchedEffect(Unit) {
        viewModel.loadRecommended()
        viewModel.recommended.observeForever {
            recommended.clear()
            recommended.addAll(it)
            showRcomemdedLoading = false
        }
    }


    DetailScreen(

        recommended,

        onBackClick = {

            navController.popBackStack()
        },

        onAddToCartClick = { },

        onCartClick = { }
    )
}

@Composable
fun DetailScreen(
    model: ModelUiScreenShopItems,
    onBackClick: () -> Unit,
    onAddToCartClick: () -> Unit,
    onCartClick: () -> Unit

) {
    var selectedImageUri by remember { mutableStateOf(model.picUrl.first()) }
    var selectedImageIndex by remember { mutableStateOf(-1) }

    Column(
        modifier = Modifier
            .background(Color.White)
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
    ) {

        ConstraintLayout(
            modifier = Modifier
                .padding(top = 36.dp, bottom = 16.dp)
                .fillMaxSize()
        ) {
            val (back, fav) = createRefs()

            Image(
                painter = painterResource(id = R.drawable.back),
                contentDescription = "Back",
                modifier = Modifier
                    .constrainAs(back) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                    }
                    .clickable {
                        onBackClick()
                    }
                    .constrainAs(fav) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                    }
            )

            Image(
                painter = painterResource(id = R.drawable.fav_icon),
                contentDescription = "Favorite",
                modifier = Modifier
                    .constrainAs(fav) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        end.linkTo(parent.end)
                    }
            )
        }

        Image(
            painter = rememberAsyncImagePainter(model = selectedImageUri),
            contentDescription = "Rating",
            modifier = Modifier
                .fillMaxWidth()
                .height(290.dp)
                .padding(16.dp)
                .background(Color.LightGray, shape = RoundedCornerShape(8))

        )

        LazyRow(
            modifier = Modifier
                .padding(16.dp)
        ) {
            items(model.picUrl) { imageUrl ->
                ImageThumbnail(
                    imageUrl = imageUrl,
                    isSelected = imageUrl == selectedImageUri,
                    onClick = {
                        selectedImageUri = imageUrl
                    }
                )
            }
        }
    }
}

@Composable
fun ImageThumbnail(
    imageUrl: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {

    val backColor =
        if (isSelected) colorResource(R.color.light_pink) else colorResource(R.color.light_grey)

    Box(
        modifier = Modifier
            .padding(4.dp)
            .size(55.dp)
            .background(backColor, shape = RoundedCornerShape(10.dp))
            .clickable(onClick = onClick)
            .then(
                if (isSelected) Modifier.border(
                    1.dp,
                    colorResource(R.color.light_pink),
                    shape = RoundedCornerShape(10.dp)
                ) else Modifier

            )
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = imageUrl),
            contentDescription = "Thumbnail",
            modifier = Modifier
                .fillMaxSize()
                .padding(4.dp)
        )
    }

}
