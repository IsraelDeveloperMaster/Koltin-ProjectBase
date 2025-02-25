/*
 * *
 *  * Created by rael on 21/02/2025 23:28
 *  * Copyright (c) 2025 . All rights reserved.
 *  * Last modified 21/02/2025 23:28
 *
 */

package net.developermaster.projectbase.estudoUI.estudoUiShop.views

import android.widget.RatingBar
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import coil3.compose.rememberAsyncImagePainter
import net.developermaster.projectbase.R
import net.developermaster.projectbase.estudoUI.estudoUiShop.model.ModelUiScreenShopItems
import net.developermaster.projectbase.estudoUI.estudoUiShop.viewModel.ViewModelUiScreenShopEstudo

@Composable
fun UiScreenShopEstudo4(navController: NavHostController) {

    val viewModel = ViewModelUiScreenShopEstudo()

    val recommended = remember { mutableStateListOf<ModelUiScreenShopItems>() }
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

    // Show loading indicator if loading
    if (showRcomemdedLoading) {
        // You can replace this with a proper loading indicator
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {
        // Show the detail screen with the recommended items
        DetailScreen(
            recommended = recommended,
            onBackClick = {
                navController.popBackStack()
            },
            onAddToCartClick = { },

            onCartClick = { }
        )
    }
}

@Composable
fun DetailScreen(
    recommended: List<ModelUiScreenShopItems>,
    onBackClick: () -> Unit,
    onAddToCartClick: () -> Unit,
    onCartClick: () -> Unit
) {
    // Assuming you want to display the first recommended item as the main item
    val item = recommended.firstOrNull() ?: return // Return if no items are available

    var selectedImageUri by remember { mutableStateOf(item.picUrl.first()) }
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
            items(item.picUrl) { imageUrl ->
                ImageThumbnail(
                    imageUrl = imageUrl,
                    isSelected = imageUrl == selectedImageUri,
                    onClick = {
                        selectedImageUri = imageUrl
                    }
                )
            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(16.dp)
        ) {

            Text(
                text = item.title,
                fontSize = 23.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 16.dp)
                    .weight(1f),
            )

            Text(
                text = "$${item.price}",
                fontSize = 23.sp,
                modifier = Modifier
                    .padding(end = 16.dp)
            )
        }
        RatingBar(rating = item.rating)
        ModelSelector(
            models = item.model,
            selectedModelIndex = selectedImageIndex,
            onModelSelected = { index ->
                selectedImageIndex = index
            }
        )
        Text(
            text = item.description,
            fontSize = 16.sp,
            color = colorResource(R.color.black),
            modifier = Modifier
                .padding(vertical = 16.dp)
        )

        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {

            Button(
                onClick = onAddToCartClick,
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .height(50.dp)
                    .padding(end = 8.dp)
                    .weight(1f)
            ) {
                Text(text = "Buy Now", fontSize = 18.sp, color = colorResource(R.color.white))
            }
            IconButton(
                onClick = onCartClick,
                modifier = Modifier.background(
                    color = colorResource(R.color.purple),
                    shape = RoundedCornerShape(10.dp)
                ),
            ) {
                Image(
                    painter = painterResource(id = R.drawable.btn_2),
                    contentDescription = "Cart",
                    modifier = Modifier.size(30.dp)
                )
            }
        }
    }
}

@Composable
fun ModelSelector(models: List<String>, selectedModelIndex: Int, onModelSelected: (Int) -> Unit) {

    LazyRow(
        modifier = Modifier.padding(vertical = 8.dp)
    ) {
        itemsIndexed(models) { index, model ->

            Box(
                modifier = Modifier
                    .padding(8.dp)
                    .height(40.dp)
                    .then(
                        if (index == selectedModelIndex)
                            Modifier
                                .border(
                                    1.dp, colorResource(R.color.purple),
                                    shape = RoundedCornerShape(10.dp)
                                ) else Modifier
                    )

                    .background(
                        if (index == selectedModelIndex) colorResource(R.color.light_pink) else colorResource(
                            R.color.light_grey
                        ),
                        shape = RoundedCornerShape(10.dp)
                    )

                    .clickable { onModelSelected(index) }
                    .padding(horizontal = 16.dp)

            ) {
                Text(
                    text = model,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    color = if (index == selectedModelIndex) colorResource(R.color.blue) else colorResource(
                        R.color.black
                    ),
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}

@Composable
fun RatingBar(rating: Double) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(16.dp)
    ) {

        Text(
            text = "Select Model",
            fontWeight = FontWeight.Bold,
            modifier = Modifier.weight(1f)
        )

        Image(
            painter = painterResource(id = R.drawable.star),
            contentDescription = "Star",
            modifier = Modifier.padding(end = 8.dp)
        )

        Text(
            text = "$rating Rating",
            style = MaterialTheme.typography.bodyMedium,
            fontSize = 16.sp
        )
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
