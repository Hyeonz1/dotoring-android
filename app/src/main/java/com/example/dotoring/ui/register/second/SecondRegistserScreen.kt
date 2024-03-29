package com.example.dotoring.ui.register.second

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.core.net.toFile
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.dotoring.R
import com.example.dotoring.navigation.AuthScreen
import com.example.dotoring.ui.register.MentoInformation
import com.example.dotoring.ui.register.util.RegisterScreenNextButton
import com.example.dotoring.ui.register.util.RegisterScreenTop
import com.example.dotoring.ui.theme.DotoringTheme
import de.charlex.compose.HtmlText
import java.io.File

@Composable
private fun ImageUploadButton(
    registerSecondViewModel: RegisterSecondViewModel = viewModel(),
    uploadEmploymentFile: Boolean
) {

/*    var selectedImageUri by remember {
        mutableStateOf<Uri?>(null)
    }

    val singlePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = {
                uri ->
            selectedImageUri = uri
            val file = registerSecondViewModel.uriToFile(uri)

            if (uploadEmploymentFile) {
                registerSecondViewModel.uploadEmploymentFile()
                registerSecondViewModel.updateEmploymentCertification(file)

            } else {
                registerSecondViewModel.uploadGraduationFile()
                registerSecondViewModel.updateGraduationCertification(file)
            }
        }
    )*/

    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) {
            selectedUri ->
        if (selectedUri != null) {
            Log.d("uri", "selectedUri: file selected $selectedUri")
            Log.d("uri", "selectedUri: file path ${selectedUri.path}")
            if (uploadEmploymentFile) {
                registerSecondViewModel.uploadEmploymentFile()
                registerSecondViewModel.updateEmploymentCertification(selectedUri)

            } else {
                registerSecondViewModel.uploadGraduationFile()
                registerSecondViewModel.updateGraduationCertification(selectedUri)
            }

        } else {
            Log.d("uri", "selectedUri: No file was selected.")
        }
    }

    Button(
        onClick = {
/*            singlePhotoPickerLauncher.launch(
            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
        )*/
                  launcher.launch("*/*")

                  },
        modifier = Modifier.size(width = 300.dp, height = 80.dp),
        border = BorderStroke(width = 0.5.dp, color = colorResource(id = R.color.grey_200)),
        colors = ButtonDefaults.buttonColors(
            contentColor = colorResource(id = R.color.black),
            backgroundColor = colorResource(id = R.color.white),
            disabledBackgroundColor = colorResource(id = R.color.white),
            disabledContentColor = colorResource(id = R.color.black)
        ),
        shape = RoundedCornerShape(20.dp)
    ) {
        Text(text = stringResource(id = R.string.register2_upload_extension))
    }
}

@Composable
fun SecondRegisterScreen(
    navController: NavHostController,
    registerSecondViewModel: RegisterSecondViewModel = viewModel(),
    mentoInformation: MentoInformation
) {
    val registerSecondUiState by registerSecondViewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .padding(top = 50.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        RegisterScreenTop(screenNumber = 2, question = R.string.register2_q2)

        Spacer(modifier = Modifier.weight(1f))

        Column {
            HtmlText(
                textId = R.string.register2_upload_certificate_of_employment,
                fontSize = 18.sp)

            Spacer(modifier = Modifier.size(10.dp))

            ImageUploadButton(uploadEmploymentFile = true)

            Spacer(modifier = Modifier.size(50.dp))

            Row(verticalAlignment = Alignment.Bottom) {

                HtmlText(
                    textId = R.string.register2_upload_certificate_of_graduate,
                    fontSize = 18.sp)
                Spacer(modifier = Modifier.size(5.dp))
                Text(
                    text = stringResource(id = R.string.register2_upload_choice),
                    fontSize = 13.sp)
            }

            Spacer(modifier = Modifier.size(10.dp))

            ImageUploadButton(uploadEmploymentFile = false)

            Spacer(modifier = Modifier.size(60.dp))

            RegisterScreenNextButton(
                onClick = {
                    val mentoInfo = MentoInformation(
                        company = mentoInformation.company,
                        careerLevel = mentoInformation.careerLevel,
                        job = mentoInformation.job,
                        major = mentoInformation.major,
                        employmentCertification = registerSecondUiState.employmentCertification,
                        graduateCertification = registerSecondUiState.graduationCertification
                    )
                    navController.currentBackStackEntry?.savedStateHandle?.set(
                        key = "mentoInfo",
                        value = mentoInfo
                    )
                    navController.navigate(AuthScreen.Register3.route)
                          },
                enabled = true )
        }

        Spacer(modifier = Modifier.weight(3f))

    }
}

@Preview(showSystemUi = true)
@Composable
private fun RegisterScreenPreview() {
    DotoringTheme {
        SecondRegisterScreen(navController = rememberNavController(), mentoInformation = MentoInformation())
    }
}