/*
 * *
 *  * Created by rael on 21/02/2025 23:28
 *  * Copyright (c) 2025 . All rights reserved.
 *  * Last modified 21/02/2025 23:28
 *
 */

package net.developermaster.projectbase.estudoUI.estudoUiShop.views

import android.telecom.Call.Details
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import net.developermaster.projectbase.estudoUI.estudoUiShop.Helper.ManagmentCart
import net.developermaster.projectbase.estudoUI.estudoUiShop.model.ModelUiScreenShopItems

@Composable
fun UiScreenShopEstudo4(navController: NavHostController) {

    lateinit var managmentCart: ManagmentCart
    lateinit var item: ModelUiScreenShopItems

}

@Composable
fun DetailScreen(
    Item: ModelUiScreenShopItems,
    onBackClick: () -> Unit,
    onAddToCartClick: () -> Unit,
    onCartClick: () -> Unit

) {

    var selectedImageUri by remember { mutableStateOf(Item.picUrl.first()) }

}