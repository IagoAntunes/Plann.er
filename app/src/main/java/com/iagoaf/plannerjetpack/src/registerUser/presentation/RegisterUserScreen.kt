package com.iagoaf.plannerjetpack.src.registerUser.presentation

import AppTypography
import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.iagoaf.plannerjetpack.core.services.camera.createImageFile
import com.iagoaf.plannerjetpack.core.services.database.AppDatabase
import com.iagoaf.plannerjetpack.core.ui.theme.lime300
import com.iagoaf.plannerjetpack.core.ui.theme.zinc400
import com.iagoaf.plannerjetpack.core.ui.theme.zinc50
import com.iagoaf.plannerjetpack.core.ui.theme.zinc800
import com.iagoaf.plannerjetpack.core.ui.theme.zinc900
import com.iagoaf.plannerjetpack.core.ui.theme.zinc950
import com.iagoaf.plannerjetpack.src.registerUser.infra.repository.UserRepositoryImpl
import java.util.Objects


private fun validateFields(name: String, email: String, phone: String): Boolean {
    return name.isNotEmpty() && email.isNotEmpty() && phone.isNotEmpty()
}


@Composable
fun RegisterUserScreen(navController: NavController) {

    val viewModel = RegisterUserViewModel(
        UserRepositoryImpl(
            AppDatabase.getDatabase(LocalContext.current).userDao()
        )
    )
    val state by viewModel.state.collectAsState()
    val context = LocalContext.current

    when (state) {
        is RegisterUserListener.IdleUser -> {
        }

        is RegisterUserListener.RegisterUserSuccess -> {
            navController.navigate("home_screen")
        }

        is RegisterUserListener.FailureUserSuccess -> {
            Toast.makeText(
                context,
                "Erro ao salvar usuário",
                Toast.LENGTH_SHORT
            ).show()
        }
    }


    val btnSaveUserEnabled = remember {
        mutableStateOf(false)
    }
    val nameValue = remember {
        mutableStateOf("")
    }
    val emailValue = remember {
        mutableStateOf("")
    }
    val phoneValue = remember {
        mutableStateOf("")
    }

    var file = context.createImageFile()
    var uri = FileProvider.getUriForFile(
        Objects.requireNonNull(context),
        context.packageName + ".provider", file
    )
    var capturedImageUri by remember {
        mutableStateOf<Uri>(Uri.EMPTY)
    }
    val cameraLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.TakePicture()
    ) { result ->

        capturedImageUri = uri
    }
    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) {
        if (it) {
            Toast.makeText(context, "Permission Granted", Toast.LENGTH_SHORT).show()
            cameraLauncher.launch(uri)
        } else {
            Toast.makeText(context, "Permission Denied", Toast.LENGTH_SHORT).show()
        }
    }
    Scaffold(
    ) { innerPadding ->
        Column(
            Modifier
                .fillMaxSize()
                .background(zinc950)
                .padding(innerPadding)
                .padding(horizontal = 20.dp, vertical = 16.dp)
        ) {
            Text(
                "Cadastro de Usuário",
                Modifier.align(Alignment.CenterHorizontally),
                style = AppTypography.headingSm.copy(
                    color = zinc50,
                ),
                textAlign = TextAlign.Center,
            )
            Spacer(Modifier.height(100.dp))
            Column(
                Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Box(
                    Modifier
                        .clip(CircleShape)
                        .background(zinc900)
                        .align(Alignment.CenterHorizontally)
                        .size(128.dp)
                        .clickable {
                            val permissionCheckResult = ContextCompat.checkSelfPermission(
                                context,
                                Manifest.permission.CAMERA
                            )

                            if (permissionCheckResult == PackageManager.PERMISSION_GRANTED) {
                                file = context.createImageFile()
                                uri = FileProvider.getUriForFile(
                                    Objects.requireNonNull(context),
                                    context.packageName + ".provider", file
                                )

                                cameraLauncher.launch(uri)
                            } else {
                                permissionLauncher.launch(Manifest.permission.CAMERA)
                            }
                        },
                    contentAlignment = Alignment.Center
                ) {
                    if (capturedImageUri.toString().isEmpty()) {
                        Icon(
                            Icons.Default.Add,
                            contentDescription = "Add user",
                            tint = zinc50,
                            modifier = Modifier.size(36.dp)
                        )
                    } else {
                        Image(
                            painter = rememberImagePainter(capturedImageUri),
                            contentDescription = "Image",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .clip(CircleShape)
                                .fillMaxSize()
                        )
                    }

                }
                Column(
                    Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                ) {


                    OutlinedTextField(
                        value = nameValue.value,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp)),
                        shape = RoundedCornerShape(8.dp),
                        onValueChange = {
                            nameValue.value = it
                            btnSaveUserEnabled.value = validateFields(
                                nameValue.value,
                                emailValue.value,
                                phoneValue.value
                            )
                        },
                        placeholder = {
                            Text(
                                "Nome",
                                style = AppTypography.bodyMd.copy(zinc400)
                            )
                        },
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedBorderColor = zinc800,
                            focusedBorderColor = lime300,
                            unfocusedTextColor = zinc400,
                            focusedTextColor = zinc400,

                            ),
                    )
                    OutlinedTextField(
                        value = emailValue.value,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp)),
                        shape = RoundedCornerShape(8.dp),
                        onValueChange = {
                            emailValue.value = it
                            btnSaveUserEnabled.value = validateFields(
                                nameValue.value,
                                emailValue.value,
                                phoneValue.value
                            )
                        },
                        placeholder = {
                            Text(
                                "Email",
                                style = AppTypography.bodyMd.copy(zinc400)
                            )
                        },
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedBorderColor = zinc800,
                            focusedBorderColor = lime300,
                            unfocusedTextColor = zinc400,
                            focusedTextColor = zinc400,
                        ),
                    )
                    OutlinedTextField(
                        value = phoneValue.value,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp)),
                        shape = RoundedCornerShape(8.dp),
                        onValueChange = {
                            phoneValue.value = it
                            btnSaveUserEnabled.value = validateFields(
                                nameValue.value,
                                emailValue.value,
                                phoneValue.value
                            )
                        },
                        placeholder = {
                            Text(
                                "Telefone",
                                style = AppTypography.bodyMd.copy(zinc400)
                            )
                        },
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedBorderColor = zinc800,
                            focusedBorderColor = lime300,
                            unfocusedTextColor = zinc400,
                            focusedTextColor = zinc400,
                        ),
                    )
                }

                Button(
                    colors = ButtonDefaults.buttonColors(
                        containerColor = lime300,
                    ),
                    shape = RoundedCornerShape(8.dp),
                    enabled = btnSaveUserEnabled.value,
                    onClick = {
                        viewModel.saveUser(
                            capturedImageUri.toString(),
                            nameValue.value,
                            emailValue.value,
                            phoneValue.value
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()

                ) {
                    Text(
                        "Salvar usuário",
                        style = AppTypography.button.copy(
                            color = if (btnSaveUserEnabled.value) zinc950 else zinc400,
                        ),
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun PreviewRegisterUserScreen() {
    RegisterUserScreen(navController = rememberNavController())
}