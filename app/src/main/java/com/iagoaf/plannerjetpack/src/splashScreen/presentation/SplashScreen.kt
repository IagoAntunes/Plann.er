package com.iagoaf.plannerjetpack.src.splashScreen.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.iagoaf.plannerjetpack.R
import com.iagoaf.plannerjetpack.core.ui.theme.zinc950
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {

    LaunchedEffect(Unit) {
        delay(300)
        navController.navigate("home_screen")
    }

    Column(
        Modifier
            .fillMaxSize()
            .background(
                zinc950,
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(R.drawable.logo_planner),
            contentDescription = "Planner logo",
            Modifier.size(172.dp, 44.dp)
        )
    }
}

@Preview
@Composable
private fun PreviewSplashScreen() {
    SplashScreen(navController = rememberNavController())
}