package net.developermaster.projectbase.screens.EstudoUI.EstudoUi1

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import net.developermaster.projectbase.R
import net.developermaster.projectbase.model.ModelUiScreenEstudo
import net.developermaster.projectbase.viewModel.ViewModelUiScreenEstudo1

/*

@Composable
fun UiScreenEstudoMain() {

    val viewModel = ViewModelUiScreenEstudo1()
    val banner = remember { mutableListOf<ModelUiScreenEstudo>() }
    var showLoading by remember { mutableStateOf(true) }

    //banner
    LaunchedEffect(Unit) {
        viewModel.loadBanner()
        viewModel.banner.observeForever {
            banner.clear()
            banner.addAll(it)
            showLoading = false
        }
    }
}
*/

@Composable
fun UiScreenEstudo3_1(navController: NavHostController) {

    val viewModel = ViewModelUiScreenEstudo1()
    val banner = remember { mutableListOf<ModelUiScreenEstudo>() }
    var showLoading by remember { mutableStateOf(true) }

    //banner
    LaunchedEffect(Unit) {
        viewModel.loadBanner()
        viewModel.banner.observeForever {
            banner.clear()
            banner.addAll(it)
            showLoading = false
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

            if (showLoading) {
                Box(
                    modifier = Modifier
                        .height(200.dp)
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            } else {

                BannerList(banner)
            }
        }
    }
}

@Composable
fun BannerList(banner: List<ModelUiScreenEstudo>) {
    AutoSlidingCarousel(modifier = Modifier, banner = banner)
}

@Composable
fun AutoSlidingCarousel(
    modifier: Modifier,
    banner: List<ModelUiScreenEstudo>,
) {

    Column(
        modifier = modifier.fillMaxSize(),

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

   DotIndicator(
        modifier = Modifier
            .padding(8.dp),
        totalDots = banner.size,
        selectedIndex = if (banner.isEmpty()) 0 else 0,
        dotSize = 8.dp
    )
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


