package com.iagoaf.plannerjetpack.src.home.presentation

import AppTypography
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import com.iagoaf.plannerjetpack.core.services.database.AppDatabase
import com.iagoaf.plannerjetpack.core.ui.theme.zinc100
import com.iagoaf.plannerjetpack.core.ui.theme.zinc800
import com.iagoaf.plannerjetpack.src.home.presentation.states.HeadUserInfoState
import com.iagoaf.plannerjetpack.src.home.presentation.viewmodel.HeadUserInfoViewModel
import com.iagoaf.plannerjetpack.src.home.presentation.viewmodel.HeadUserInfoViewModelFactory
import com.iagoaf.plannerjetpack.src.registerUser.infra.repository.UserRepositoryImpl

@Composable
fun HeadUserInfo() {

    val context = LocalContext.current

    val viewModel: HeadUserInfoViewModel = viewModel(
        factory = HeadUserInfoViewModelFactory(
            userRepository = UserRepositoryImpl(
                userDao = AppDatabase.getDatabase(context).userDao()
            )
        )
    )
    val state = viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadUserData()
    }

    when (state.value) {
        is HeadUserInfoState.Idle -> {}

        is HeadUserInfoState.Loading -> {
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .size(64.dp)
                    .background(zinc800)
            )
        }

        is HeadUserInfoState.Success -> {
            val successState = state.value as HeadUserInfoState.Success
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    painter = rememberImagePainter(successState.user.imagePath),
                    contentDescription = "Teste",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(64.dp)
                )
                Spacer(Modifier.width(16.dp))
                Text(
                    text = buildAnnotatedString {
                        withStyle(style = SpanStyle(color = zinc100, fontSize = 16.sp)) {
                            append("Olá, ")
                        }
                        withStyle(
                            style = SpanStyle(
                                color = zinc100,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )
                        ) {
                            append(successState.user.name)
                        }
                    },
                    style = AppTypography.bodyMd
                )
            }
        }

        is HeadUserInfoState.Error -> {
            Text(
                "ERROR",
                style = AppTypography.bodyMd.copy(
                    color = zinc100,
                )
            )
        }


    }


}