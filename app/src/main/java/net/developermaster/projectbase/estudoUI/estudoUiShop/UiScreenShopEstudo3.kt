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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
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
import net.developermaster.projectbase.model.ModelUiScreenShopBanner
import net.developermaster.projectbase.model.ModelUiScreenShopCategoria
import net.developermaster.projectbase.viewModel.ViewModelUiScreenShopEstudo

@Composable
internal fun UiScreenShopEstudo3(navController: NavHostController) {

    val viewModel = ViewModelUiScreenShopEstudo()
    val banners = remember { mutableStateListOf<ModelUiScreenShopBanner>() }
    val categorias = remember { mutableStateListOf<ModelUiScreenShopCategoria>() }
    var showBannerLoading by remember { mutableStateOf(true) }
    var showCategoryLoading by remember { mutableStateOf(true) }

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

                Column() {
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

                BannerList3(banners)
            }
        }
        item {

            SelectionTitle3(
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
                CategoryList(categorias)

            }
        }
    }
}

@Composable
fun CategoryList(categorias: SnapshotStateList<ModelUiScreenShopCategoria>) {
    var selectedItem by remember { mutableStateOf(-1) }
    LazyRow(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 8.dp)

    ) {
        items(categorias.size) { index ->
            CategoryItem(
                category = categorias[index],
                isSelected = selectedItem == index,
                onClick = {
                    selectedItem = index
                },
            )
        }
    }
}

@Composable
fun CategoryItem(category: ModelUiScreenShopCategoria, onClick: () -> Unit, isSelected: Boolean) {
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
            model = ImageRequest.Builder(LocalContext.current)
                .data(category.picUrl)
                .build(),
            contentDescription = category.title,
            contentScale = ContentScale.Inside,
            colorFilter = if (isSelected) ColorFilter.tint(Color.White) else ColorFilter.tint(
                colorResource(R.color.black)
            )
        )
        if (isSelected) {
            Text(
                modifier = Modifier.padding(start = 8.dp),
                text = category.title,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = colorResource(R.color.white)
            )
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun BannerList3(banner: List<ModelUiScreenShopBanner>) {
    Carousel3(modifier = Modifier, banner = banner)
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Carousel3(
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

        DotIndicator(
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
fun DotIndicator(
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
            IndicatorDot(
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
fun IndicatorDot(
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
fun SelectionTitle3(
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
            color = colorResource(R.color.black)
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




