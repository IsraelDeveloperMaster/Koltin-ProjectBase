/*
 * *
 *  * Created by rael on 20/02/2025 20:55
 *  * Copyright (c) 2025 . All rights reserved.
 *  * Last modified 20/02/2025 20:43
 *
 */

package net.developermaster.projectbase.estudoUI.estudoUiShop

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import net.developermaster.projectbase.R
import net.developermaster.projectbase.estudoUI.estudoUiShop.model.ModelUiScreenShopBanner
import net.developermaster.projectbase.estudoUI.estudoUiShop.model.ModelUiScreenShopCategoria
import net.developermaster.projectbase.estudoUI.estudoUiShop.model.ModelUiScreenShopItems
import net.developermaster.projectbase.estudoUI.estudoUiShop.viewModel.ViewModelUiScreenShopEstudo
import net.developermaster.projectbase.navigation.ScreensObject

@Composable
internal fun UiScreenShopEstudo2(navController: NavHostController) {

    val viewModel = ViewModelUiScreenShopEstudo()
    val banners = remember { mutableStateListOf<ModelUiScreenShopBanner>() }
    var showBannerLoading by remember { mutableStateOf(true) }

    val categorias = remember { mutableStateListOf<ModelUiScreenShopCategoria>() }
    var showCategoryLoading by remember { mutableStateOf(true) }

    val recommended = remember { mutableStateListOf<ModelUiScreenShopItems>() }
    var showRcomemdedLoading by remember { mutableStateOf(true) }

    //banner
    LaunchedEffect(Unit) {
        viewModel.loadBanner()
        viewModel.banner.observeForever {
            banners.clear()
            banners.addAll(it)
            showBannerLoading = false
        }
    }

    //category
    LaunchedEffect(Unit) {
        viewModel.loadCategoria()
        viewModel.categoria.observeForever {
            categorias.clear()
            categorias.addAll(it)
            showCategoryLoading = false
        }
    }

    //recommended
    LaunchedEffect(Unit) {
        viewModel.loadRecommended()
        viewModel.recommended.observeForever {
            recommended.clear()
            recommended.addAll(it)
            showRcomemdedLoading = false
        }
    }


    LazyColumn(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.surface)
            .fillMaxSize()
            .padding(16.dp)
    ) {


        item {
            Row(
                modifier = Modifier
                    .padding(top = 48.dp, start = 16.dp, end = 16.dp)
                    .fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Column {
                    Text(
                        text = "Welcome back",
                        color = colorResource(id = R.color.black),
                    )

                    Text(
                        text = "Jackie",
                        color = colorResource(id = R.color.black),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )

                }
                Row {
                    Image(
                        painter = painterResource(id = R.drawable.fav_icon),
                        contentDescription = null
                    )

                    Spacer(modifier = Modifier.width(16.dp))

                    Image(
                        painter = painterResource(id = R.drawable.search_icon),
                        contentDescription = null
                    )
                }
            }
        }
        item {

            if (showBannerLoading) {
                Box(
                    modifier = Modifier
                        .height(200.dp)
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            } else {

                ListaBanners(banners)
            }
        }
        item {

            TitleSection(
                title = "Popular Products",
                actionText = "View All"
            )
        }
        item {
            if (showCategoryLoading) {
                Box(
                    modifier = Modifier
                        .height(50.dp)
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center

                ) {
                    CircularProgressIndicator()
                }
            } else {
                ListaCategorias(navController, categorias)
            }
        }
        item {
            TitleSection(
                title = "Recommended",
                actionText = "View All"
            )
        }
        item {
            if (showRcomemdedLoading) {
                Box(
                    modifier = Modifier
                        .height(200.dp)
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center

                ) {
                    CircularProgressIndicator()
                }
            } else {

                ListaItemsShop(recommended)
            }
        }

        item { Spacer(modifier = Modifier.height(100.dp)) }
    }

    ButtomBarMenu(
        modifier = Modifier
            .fillMaxWidth(),
        onClick = {
            navController.navigate(ScreensObject.UiScreenShopEstudo1.route)
        }
    )
}

@Composable
fun ListaCategorias(navController: NavHostController, categorias: SnapshotStateList<ModelUiScreenShopCategoria>) {
    var selectedItem by remember { mutableIntStateOf(-1) }

    LazyRow(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 8.dp)

    ) {
        items(categorias.size) { index ->
            CategoriaItems(
                item = categorias[index],
                isSelected = selectedItem == index,
                onClick = {

                    selectedItem = index

                    //navegação para a tela de detalhes da categoria selecionada
                    navController.navigate(ScreensObject.UiScreenShopEstudo3.route + "/${categorias[index].id}/${categorias[index].title}")
                },
            )
        }
    }
}

@Composable
fun CategoriaItems(item: ModelUiScreenShopCategoria, isSelected: Boolean, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .padding(8.dp)
            .clickable(onClick = onClick)
            .background(
                if (isSelected) colorResource(R.color.purple)
                else Color.Transparent,
                shape = RoundedCornerShape(8.dp)
            ),

        verticalAlignment = Alignment.CenterVertically

    ) {

        AsyncImage(
            modifier = Modifier
                .size(45.dp)
                .background(
                    color = if (isSelected) Color.Transparent else colorResource(R.color.light_grey),
                    shape = RoundedCornerShape(8.dp)
                ),
            model = (item.picUrl),
            contentDescription = item.title,
            contentScale = ContentScale.Inside,
            colorFilter = if (isSelected) ColorFilter.tint(Color.White) else ColorFilter.tint(
                colorResource(R.color.black)
            )
        )
        if (isSelected) {
            Text(
                modifier = Modifier.padding(start = 8.dp),
                text = item.title,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = colorResource(R.color.white)
            )
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ListaBanners(banner: List<ModelUiScreenShopBanner>) {
    Carousel2(modifier = Modifier, banner = banner)
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Carousel2(
    modifier: Modifier,
    pagerState: PagerState = remember { PagerState() },
    banner: List<ModelUiScreenShopBanner>,

    ) {

    val isDragged by pagerState.interactionSource.collectIsDraggedAsState()

    Column(modifier = modifier.fillMaxSize()) {
        HorizontalPager(count = banner.size, state = pagerState) { page ->
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(banner[page].url)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 8.dp)
                    .height(150.dp)
            )
        }

        PointIndicator(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(8.dp),
            totalDots = banner.size,
            selectedIndex = if (isDragged) pagerState.currentPage else pagerState.currentPage,
            dotSize = 8.dp
        )
    }
}

@Composable
fun PointIndicator(
    modifier: Modifier = Modifier,
    totalDots: Int,
    selectedIndex: Int,
    selectedColor: Color = colorResource(R.color.purple),
    unSelectedColor: Color = colorResource(R.color.gray),
    dotSize: Dp
) {
    LazyRow(
        modifier = modifier
            .wrapContentWidth()
            .wrapContentHeight()

    ) {
        items(totalDots) { index ->
            IndicatorPoint(
                modifier = Modifier.padding(4.dp),
                color = if (index == selectedIndex) selectedColor else unSelectedColor,
                size = dotSize
            )
            if (index != totalDots - 1) {
                Spacer(modifier = Modifier.padding(horizontal = 2.dp))
            }
        }
    }
}

@Composable
fun IndicatorPoint(
    modifier: Modifier = Modifier,
    color: Color,
    size: Dp
) {

    Box(
        modifier = modifier
            .size(size)
            .clip(CircleShape)
            .background(color)
    )
}

@Composable
fun TitleSection(
    title: String,
    modifier: Modifier = Modifier,
    actionText: String,
) {

    Row(
        modifier = modifier
            .padding(top = 16.dp, start = 16.dp, end = 16.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween

    ) {
        Text(
            text = title,
            modifier = modifier.padding(16.dp),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = colorResource(R.color.black),
        )
        Text(
            text = actionText,
            modifier = modifier.padding(16.dp),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = colorResource(R.color.purple)
        )

    }
}

@Composable
fun ButtomBarMenu(modifier: Modifier, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 690.dp)
            .background(colorResource(id = R.color.purple)),
        horizontalArrangement = Arrangement.SpaceAround

    ) {
        ButtomMenuItem(icon = painterResource(R.drawable.btn_1), text = "Explore")
        ButtomMenuItem(icon = painterResource(R.drawable.btn_2), text = "Cart", onClick = onClick)
        ButtomMenuItem(icon = painterResource(R.drawable.btn_3), text = "Favourite")
        ButtomMenuItem(icon = painterResource(R.drawable.btn_4), text = "Orders")
        ButtomMenuItem(icon = painterResource(R.drawable.btn_5), text = "Profile")
    }
}

@Composable
fun ButtomMenuItem(icon: Painter, text: String, onClick: (() -> Unit)? = null) {
    Column(
        modifier = Modifier
            .clickable {
                if (onClick != null) {
                    onClick()
                }
            }
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ) {

        Icon(
            icon,
            contentDescription = text,
            tint = colorResource(id = R.color.white)
        )

        Text(
            text,
            color = colorResource(id = R.color.white),
            fontSize = 10.sp,
        )
    }
}