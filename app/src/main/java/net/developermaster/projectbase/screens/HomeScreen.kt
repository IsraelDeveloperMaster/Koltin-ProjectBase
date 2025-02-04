package net.developermaster.projectbase.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import net.developermaster.projectbase.R

@Composable
fun HomeScreen(navController: NavHostController) {

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Spacer(modifier = Modifier.height(50.dp))

        Text(
            modifier = Modifier
                .padding(all = 8.dp)//todo padding top
                .border(2.dp, Color.LightGray, CutCornerShape(20)),//todo borda amarela circular
            text = "Home Screen",//todo texto
            color = Color.Blue,//todo cor vermelha
            fontSize = 30.sp,//todo tamanho da fonte
            fontFamily = FontFamily.SansSerif,//todo tipo de fonte
        )

        Image(
            painter = painterResource(id = R.drawable.kotlin_icon),
            contentDescription = "Logo",
            Modifier.size(200.dp, 200.dp)
        )
    }
}