package com.example.dotoring.ui.register

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TextFieldDefaults.indicatorLine
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dotoring.R
import com.example.dotoring.ui.register.util.RegisterScreenNextButton
import com.example.dotoring.ui.register.util.RegisterScreenTop
import com.example.dotoring.ui.theme.DotoringTheme

@Composable
fun FourthRegisterScreen() {
    Column(
        modifier = Modifier.padding(top = 50.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        RegisterScreenTop(4, R.string.register4_q4, stringResource(id = R.string.register4_guide))

        Spacer(modifier = Modifier.size(30.dp))

        Column {
            Text(text = stringResource(id = R.string.register_A))

            Spacer(modifier = Modifier.size(10.dp))

            RoundedCornerTextField(value = "", onValueChange = {})

            Spacer(modifier = Modifier.weight(5f))

            RegisterScreenNextButton()

            Spacer(modifier = Modifier.weight(4f))
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RoundedCornerTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val interactionSource = remember { MutableInteractionSource() }
    // parameters below will be passed to BasicTextField for correct behavior of the text field,
    // and to the decoration box for proper styling and sizing
    val enabled = true
    val singleLine = false

    val colors = TextFieldDefaults.textFieldColors(
        textColor = colorResource(id = R.color.black),
        focusedIndicatorColor = Color(0xffffffff),
        unfocusedIndicatorColor = Color(0xffffffff),
        backgroundColor = colorResource(id = R.color.grey_200),
        placeholderColor = colorResource(id = R.color.grey_500)
    )
    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .background(
                color = colorResource(id = R.color.grey_200),
                shape = RoundedCornerShape(15.dp)
            )
            .indicatorLine(
                enabled = enabled,
                isError = false,
                interactionSource = interactionSource,
                colors = colors
            )
            .size(width = 300.dp, height = 38.dp),
        visualTransformation = VisualTransformation.None,
        // internal implementation of the BasicTextField will dispatch focus events
        interactionSource = interactionSource,
        enabled = enabled,
        singleLine = singleLine
    ) {
        TextFieldDefaults.TextFieldDecorationBox(
            value = value,
            visualTransformation = VisualTransformation.None,
            innerTextField = it,
            singleLine = singleLine,
            enabled = enabled,
            // same interaction source as the one passed to BasicTextField to read focus state
            // for text field styling
            placeholder = {
                Text(
                    text = stringResource(id = R.string.register4_placeholder),
                    fontSize = 13.sp
                )
            },
            colors = colors,
            interactionSource = interactionSource,
            // keep vertical paddings but change the horizontal
            contentPadding = TextFieldDefaults.textFieldWithoutLabelPadding(
                start = 10.dp, top = 10.dp, end = 10.dp, bottom = 10.dp
            )
        )
    }
}

@Preview(showSystemUi = true)
@Composable
private fun RegisterScreenPreview() {
    DotoringTheme {
        FourthRegisterScreen()
    }
}