package com.iagoaf.plannerjetpack.src.home.presentation


import AppTypography
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Sell
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.iagoaf.plannerjetpack.core.services.database.AppDatabase
import com.iagoaf.plannerjetpack.core.ui.theme.lime300
import com.iagoaf.plannerjetpack.core.ui.theme.zinc100
import com.iagoaf.plannerjetpack.core.ui.theme.zinc400
import com.iagoaf.plannerjetpack.core.ui.theme.zinc50
import com.iagoaf.plannerjetpack.core.ui.theme.zinc800
import com.iagoaf.plannerjetpack.core.ui.theme.zinc950
import com.iagoaf.plannerjetpack.core.utils.convertMillisToDate
import com.iagoaf.plannerjetpack.src.home.infra.repository.ActivityRepositoryImpl
import com.iagoaf.plannerjetpack.src.home.presentation.viewmodel.HeadFormSaveActivityViewModel
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeadFormSaveActivity() {

    val context = LocalContext.current
    val viewModel = remember {
        HeadFormSaveActivityViewModel(
            activityRepository = ActivityRepositoryImpl(
                activityDao = AppDatabase.getDatabase(context).activityDao()
            )
        )
    }

    val state = viewModel.state.collectAsState()


    val currentDate = Calendar.getInstance()
    val datePickerState = rememberDatePickerState(

    )
    val timePickerState = rememberTimePickerState(
        initialHour = currentDate.get(Calendar.HOUR_OF_DAY),
        initialMinute = currentDate.get(Calendar.MINUTE),
        is24Hour = true,
    )
    val showDatePicker = remember { mutableStateOf(false) }
    val showTimePicker = remember { mutableStateOf(false) }
    val dateState = remember { mutableStateOf("") }
    val timeState = remember { mutableStateOf("") }

    val btnSaveActivityEnabled = remember {
        mutableStateOf(false)
    }
    Column(
        Modifier
            .fillMaxSize()
            .background(zinc950)
            .padding(horizontal = 20.dp, vertical = 20.dp)
    ) {
        val activityValue = remember {
            mutableStateOf("")
        }
        OutlinedTextField(
            value = activityValue.value,
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp)),
            shape = RoundedCornerShape(8.dp),
            enabled = state.value !is HeadFormSaveActivityState.Loading,
            onValueChange = {
                activityValue.value = it
                btnSaveActivityEnabled.value =
                    activityValue.value.isNotEmpty() && dateState.value.isNotEmpty() && timeState.value.isNotEmpty()
            },
            placeholder = {
                Text(
                    "Qual a atividade?",
                    style = AppTypography.bodyMd.copy(zinc400)
                )
            },
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = zinc800,
                focusedBorderColor = lime300,
                unfocusedTextColor = zinc400,
                focusedTextColor = zinc400,
            ),
            prefix = {
                Icon(
                    Icons.Default.Sell, contentDescription = "Atividade",
                    modifier = Modifier.padding(end = 6.dp)
                )
            }
        )
        Spacer(Modifier.height(8.dp))
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(zinc950)
                    .border(
                        width = 1.dp,
                        color = zinc800,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(16.dp)
                    .clickable {
                        if (state.value !is HeadFormSaveActivityState.Loading) {
                            showDatePicker.value = true
                        }
                    }
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Default.DateRange,
                        contentDescription = "Date",
                        tint = zinc400
                    )
                    Spacer(Modifier.width(8.dp))
                    if (dateState.value.isEmpty()) {
                        Text(
                            "Data",
                            style = AppTypography.bodyMd.copy(zinc400)
                        )
                    } else {
                        Text(
                            text = dateState.value,
                            color = zinc100
                        )
                    }
                }

            }
            if (showDatePicker.value) {
                DatePickerDialog(
                    onDismissRequest = { showDatePicker.value = false },
                    confirmButton = {
                        Button(
                            onClick = {
                                showDatePicker.value = false
                                dateState.value =
                                    convertMillisToDate(datePickerState.selectedDateMillis!!)
                                btnSaveActivityEnabled.value =
                                    activityValue.value.isNotEmpty() && dateState.value.isNotEmpty() && timeState.value.isNotEmpty()

                            }
                        ) {
                            Text(text = "OK")
                        }
                    },
                    dismissButton = {
                        Button(
                            onClick = { showDatePicker.value = false }
                        ) {
                            Text(text = "Cancel")
                        }
                    },
                ) {
                    DatePicker(
                        state = datePickerState,
                        showModeToggle = true,
                    )
                }

            }
        }
        Spacer(Modifier.height(8.dp))
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(zinc950)
                    .border(
                        width = 1.dp,
                        color = zinc800,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(16.dp)
                    .clickable {
                        if (state.value !is HeadFormSaveActivityState.Loading) {
                            showTimePicker.value = true
                        }
                    }
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Default.AccessTime,
                        contentDescription = "Time",
                        tint = zinc400
                    )
                    Spacer(Modifier.width(8.dp))
                    if (dateState.value.isEmpty()) {
                        Text(
                            "Horário",
                            style = AppTypography.bodyMd.copy(zinc400)
                        )
                    } else {
                        Text(
                            text = timeState.value,
                            color = zinc100
                        )
                    }
                }
            }
            if (showTimePicker.value) {
                AlertDialog(
                    onDismissRequest = { showTimePicker.value = false },
                    confirmButton = {
                        TextButton(
                            onClick = {
                                timeState.value =
                                    "${timePickerState.hour}:${timePickerState.minute}"
                                showTimePicker.value = false
                                btnSaveActivityEnabled.value =
                                    activityValue.value.isNotEmpty() && dateState.value.isNotEmpty() && timeState.value.isNotEmpty()
                            }
                        ) {
                            Text(
                                "OK",
                                style = AppTypography.bodyMd.copy(
                                    zinc50,
                                    fontWeight = FontWeight.Bold,
                                )
                            )
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = { showTimePicker.value = false }) {
                            Text(
                                "Cancel",
                                style = AppTypography.bodyMd.copy(
                                    zinc50,
                                )
                            )
                        }
                    },
                    text = {
                        TimePicker(state = timePickerState)
                    },
                    containerColor = zinc800,
                    textContentColor = zinc50,
                    titleContentColor = zinc50,
                    iconContentColor = zinc50,
                )
            }
        }
        Spacer(Modifier.height(12.dp))
        Button(
            colors = ButtonDefaults.buttonColors(
                containerColor = lime300,
            ),
            shape = RoundedCornerShape(8.dp),
            enabled = btnSaveActivityEnabled.value,
            onClick = {
                viewModel.saveActivity(
                    activityValue.value,
                    dateState.value,
                    timeState.value
                )
            },
            modifier = Modifier
                .fillMaxWidth()

        ) {
            if (state.value is HeadFormSaveActivityState.Loading) {
                CircularProgressIndicator(
                    color = zinc950,
                    modifier = Modifier.size(24.dp)
                )
            } else {
                Text(
                    "Salvar atividade",
                    style = AppTypography.button.copy(
                        color = if (btnSaveActivityEnabled.value) zinc950 else zinc400,
                    ),
                )
            }
        }
    }
}

@Preview
@Composable
private fun PreviewHeadFormSaveActivity() {
    HeadFormSaveActivity()
}