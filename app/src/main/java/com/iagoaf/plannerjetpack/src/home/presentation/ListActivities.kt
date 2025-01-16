package com.iagoaf.plannerjetpack.src.home.presentation

import AppTypography
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.iagoaf.plannerjetpack.core.services.database.AppDatabase
import com.iagoaf.plannerjetpack.core.ui.theme.zinc50
import com.iagoaf.plannerjetpack.src.home.infra.repository.ActivityRepositoryImpl
import com.iagoaf.plannerjetpack.src.home.presentation.states.ListActivitiesState
import com.iagoaf.plannerjetpack.src.home.presentation.viewmodel.ListActivitiesViewModel
import com.iagoaf.plannerjetpack.src.home.presentation.viewmodel.ListActivitiesViewModelFactory

@Composable
fun ListActivities(modifier: Modifier = Modifier) {

    val context = LocalContext.current
    val viewModel: ListActivitiesViewModel = viewModel(
        factory = ListActivitiesViewModelFactory(
            activityRepository = ActivityRepositoryImpl(
                activityDao = AppDatabase.getDatabase(context).activityDao()
            )
        )
    )
    val state = viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getActivities()
    }

    Column {
        Text(
            "Atividades",
            style = AppTypography.headingSm.copy(
                color = zinc50,
            ),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(Modifier.height(24.dp))
        when (state.value) {
            is ListActivitiesState.Idle -> {}

            is ListActivitiesState.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }

            is ListActivitiesState.Success -> {
                val successState = state.value as ListActivitiesState.Success
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(successState.listActivities) { activity ->
                        ItemListActivities(activity)
                    }
                }
            }

            is ListActivitiesState.Error -> {}
        }
    }
}

@Preview
@Composable
private fun PreviewListActivities() {
    ListActivities()
}