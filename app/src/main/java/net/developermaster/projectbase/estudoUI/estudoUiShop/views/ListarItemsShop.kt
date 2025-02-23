/*
 * *
 *  * Created by rael on 21/02/2025 23:27
 *  * Copyright (c) 2025 . All rights reserved.
 *  * Last modified 21/02/2025 23:27
 *
 */

package net.developermaster.projectbase.estudoUI.estudoUiShop.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import net.developermaster.projectbase.R
import net.developermaster.projectbase.estudoUI.estudoUiShop.model.ModelUiScreenShopItems
import net.developermaster.projectbase.navigation.ScreensObject

@Composable
fun ListarItemsShop(navController: NavHostController, items: List<ModelUiScreenShopItems>) {

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .height(500.dp)
            .padding(start = 8.dp, end = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)

    ) {
        items(items.size) { row ->

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)

            ) {

                RecomendedItem(navController, items, row)
            }
        }
    }
}

@Composable
fun ListarItemsShopFullSize(navController: NavHostController, items: List<ModelUiScreenShopItems>) {

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 8.dp, end = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)

    ) {
        items(items.size) { row ->

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)

            ) {

                RecomendedItem(navController, items, row)
            }
        }
    }
}

@Composable
fun RecomendedItem(navController: NavHostController, item: List<ModelUiScreenShopItems>, pos: Int) {

    Column(
        modifier = Modifier
            .padding(8.dp)
            .height(225.dp)

    ) {
        AsyncImage(model = item[pos].picUrl.firstOrNull(),
            contentDescription = item[pos].title,
            modifier = Modifier
                .clickable {

                    navController.navigate(ScreensObject.UiScreenShopEstudo4.route)
                }
                .width(175.dp)
                .padding(8.dp)
                .height(120.dp)
                .background(
                    colorResource(id = R.color.light_grey), shape = RoundedCornerShape(10.dp)
                ),
            contentScale = ContentScale.Inside)


        Text(
            text = item[pos].title,
            color = colorResource(id = R.color.black),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(8.dp)
        )

        Row(
            modifier = Modifier
                .padding(start = 8.dp)
                .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Row {
                Image(
                    painter = painterResource(id = R.drawable.star),
                    contentDescription = "Rating",
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                )

                Spacer(modifier = Modifier.width(4.dp))

                Text(
                    text = item[pos].rating.toString(),
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = colorResource(id = R.color.black),
                )

                Spacer(modifier = Modifier.width(30.dp))

                Text(
                    text = "$${item[pos].price}",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = colorResource(id = R.color.purple),
                )
            }
        }
    }
}



