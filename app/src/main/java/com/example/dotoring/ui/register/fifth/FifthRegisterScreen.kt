package com.example.dotoring.ui.register.fifth

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.dotoring.R
import com.example.dotoring.navigation.AuthScreen
import com.example.dotoring.ui.register.MentoInformation
import com.example.dotoring.ui.register.util.RegisterScreenNextButton
import com.example.dotoring.ui.register.util.RegisterScreenTop
import com.example.dotoring.ui.theme.DotoringTheme

@Composable
fun FifthRegisterScreen(
    registerFifthViewModel: RegisterFifthViewModel = viewModel(),
    navController: NavHostController,
    mentoInformation: MentoInformation
    ) {

    val registerFifthUiState by registerFifthViewModel.uiState.collectAsState()


    Column(
        modifier = Modifier
            .padding(top = 50.dp)
            .fillMaxSize()
    ) {

        RegisterScreenTop(
            screenNumber = 5,
            question = R.string.register5_q5,
            stringResource(id = R.string.register5_guide)
        )

        Spacer(modifier = Modifier.size(15.dp))

        Row() {
            Spacer(modifier = Modifier.weight(1f))

            Column() {

                Image(
                    painter = painterResource(id = R.drawable.sample3),
                    contentDescription = "약관",
                    modifier = Modifier.size(width = 284.dp, height = 103.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(text = stringResource(id = R.string.register5_accept))

                    Checkbox(
                        checked = registerFifthUiState.acceptance,
                        onCheckedChange = { registerFifthViewModel.accept() },
                        colors = CheckboxDefaults.colors(
                            checkedColor = colorResource(id = R.color.green),
                            uncheckedColor = colorResource(id = R.color.grey_500),
                            checkmarkColor = Color(0xffffffff)
                        )
                    )

                }

                Spacer(modifier = Modifier.weight(1f))

                RegisterScreenNextButton(onClick = {
                    val mentoInfo = mentoInformation

                    navController.currentBackStackEntry?.savedStateHandle?.set(
                        key = "mentoInfo",
                        value = mentoInfo
                    )

                    navController.navigate(AuthScreen.Register6.route)
                                                   }, enabled = registerFifthUiState.btnState)

                Spacer(modifier = Modifier.weight(3f))

            }

            Spacer(modifier = Modifier.weight(1f))

        }

    }
}

@Preview(showSystemUi = true)
@Composable
private fun RegisterScreenPreview() {
    DotoringTheme {
        FifthRegisterScreen(navController = rememberNavController(), mentoInformation = MentoInformation())
    }
}