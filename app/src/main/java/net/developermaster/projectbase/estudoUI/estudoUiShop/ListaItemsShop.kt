/*
 * *
 *  * Created by rael on 20/02/2025 21:49
 *  * Copyright (c) 2025 . All rights reserved.
 *  * Last modified 20/02/2025 21:49
 *
 */

package net.developermaster.projectbase.estudoUI.estudoUiShop

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import net.developermaster.projectbase.R
import net.developermaster.projectbase.estudoUI.estudoUiShop.model.ModelUiScreenShopItems

@Composable
fun ListaItemsShop(items: List<ModelUiScreenShopItems>) {

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

                RecomendedItem(items, row)
            }
        }
    }
}

@Composable
fun RecomendedItem(item: List<ModelUiScreenShopItems>, pos: Int) {

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .padding(8.dp)
            .height(225.dp)

    ) {
        AsyncImage(model = item[pos].picUrl.firstOrNull(),
            contentDescription = item[pos].title,
            modifier = Modifier
                .clickable { }
                .width(175.dp)
                .padding(8.dp)
                .height(200.dp)
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
            modifier = Modifier.padding(top = 8.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Row {
                Image(
                    painter = painterResource(id = R.drawable.star),
                    contentDescription = "Rating",
                    modifier = Modifier.align(Alignment.CenterVertically)
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = item[pos].rating.toString(),
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = colorResource(id = R.color.black),
                )

                Text(
                    text = "R$ ${item[pos].price}",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = colorResource(id = R.color.purple),
                )
            }
        }
    }
}



