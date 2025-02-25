package com.iagoaf.plannerjetpack.src.home.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.iagoaf.plannerjetpack.R
import com.iagoaf.plannerjetpack.core.ui.theme.zinc950

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    Scaffold { innerPadding ->
        Column(
            Modifier
                .fillMaxSize()
                .background(zinc950)
                .padding(start = 20.dp, end = 20.dp, top = 20.dp)
                .padding(innerPadding)
        ) {
            Image(
                painter = painterResource(R.drawable.logo_planner),
                contentDescription = "Planner logo",
                modifier = Modifier.size(172.dp, 44.dp)
            )
            Spacer(Modifier.height(32.dp))
            HeadUserInfo()
            Spacer(Modifier.height(32.dp))
            HeadFormSaveActivity()
            Spacer(Modifier.height(24.dp))
            ListActivities()
        }
    }

}

@Preview
@Composable
private fun PreviewHomeScreen() {
    HomeScreen()
}