package com.example.dotoring.ui.register.util

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dotoring.R
import com.example.dotoring.ui.theme.DotoringTheme
import de.charlex.compose.HtmlText

@Composable
private fun ProgressBar(page: Int) {

    val space = 5.dp
    val imageModifier = Modifier
        .size(20.dp)

    Row() {

        for(i in 1..page) {
            Image(painter = painterResource(R.drawable.register_progress_done), contentDescription = "", modifier = imageModifier)
            Spacer(modifier = Modifier.size(space))
        }

        for(i in 1..6-page) {
            Image(painter = painterResource(R.drawable.register_progress_default), contentDescription = "", modifier = imageModifier)
            Spacer(modifier = Modifier.size(space))
        }

    }

}

@Composable
fun RegisterScreenTop(screenNumber: Int, question: Int, guide: String = "") {
    Row() {
        Spacer(modifier = Modifier.weight(1f))

        Column() {
            HtmlText(
                textId = R.string.register_title,
                fontSize = 15.sp)
            Spacer(modifier = Modifier.size(80.dp))

            Column() {
                ProgressBar(screenNumber)
                Spacer(modifier = Modifier.size(10.dp))
                Row() {
                    Text(
                        text = stringResource(id = R.string.register_Q),
                        fontSize= 20.sp
                    )

                    Column() {
                        HtmlText(
                            textId = question,
                            fontSize = 20.sp
                        )

                        Spacer(modifier = Modifier.size(5.dp))

                        Text(
                            text = guide,
                            color = colorResource(id = R.color.grey_500),
                            fontSize = 10.sp
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.weight(3f))
    }
}

@Composable
@Preview(showBackground = true)
private fun RegisterScreenPreview() {
    DotoringTheme {
        RegisterScreenTop(3, R.string.register3_q3, stringResource(R.string.register3_guide))
    }
}