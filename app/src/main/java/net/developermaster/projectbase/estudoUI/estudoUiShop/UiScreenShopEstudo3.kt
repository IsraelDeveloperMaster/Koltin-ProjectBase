package net.developermaster.projectbase.estudoUI.estudoUiShop

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import net.developermaster.projectbase.R
import net.developermaster.projectbase.estudoUI.estudoUiShop.viewModel.ViewModelUiScreenShopEstudo

@Composable
internal fun UiScreenShopEstudo3( navController: NavHostController, id: String, title: String, ) {

     val viewModel = ViewModelUiScreenShopEstudo()

    ListItemsUIShop(
        title = title,
        onBack = { },
        viewModel = viewModel,
        id = id
    )
}

@Composable
fun ListItemsUIShop(
    title: String,
    onBack: () -> Unit,
    viewModel: ViewModelUiScreenShopEstudo,
    id: String,
) {
    val items by viewModel.recommended.observeAsState(emptyList())
    var isLoading by remember { mutableStateOf(true) }

    LaunchedEffect(id) {
        viewModel.loadFiltred(id)
    }

    Column(modifier = Modifier.fillMaxSize()) {
        ConstraintLayout(
            modifier = Modifier
                .padding(top = 36.dp, start = 16.dp, end = 16.dp)
        ) {
            val (backBtn, cartTxt) = createRefs()

            Text(
                text = title,
                modifier = Modifier
                    .padding(start = 120.dp)
                    .constrainAs(cartTxt) {
                        centerTo(parent)
                    },
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp,
            )

            Image(
                painter = painterResource(id = R.drawable.back),
                contentDescription = null,
                modifier = Modifier
                    .clickable { onBack() }
                    .constrainAs(backBtn) {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
            )
        }
        if (isLoading) {

            Column(modifier = Modifier
                .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally) {
                Spacer(modifier = Modifier.height(300.dp))
                CircularProgressIndicator()
            }

        } else {

            ListaItemsShop(items)
        }
    }

    LaunchedEffect(items) {
        isLoading = items.isEmpty()
    }
}

