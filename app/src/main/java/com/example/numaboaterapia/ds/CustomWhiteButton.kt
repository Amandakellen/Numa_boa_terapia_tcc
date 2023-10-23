package com.example.numaboaterapia.ds

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.numaboaterapia.ui.theme.NumaBoaTheme

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CustomWhiteButton(
    text: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        backgroundColor = Color.White,
        elevation = 2.dp,
        shape = MaterialTheme.shapes.medium,
        onClick = onClick
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = text,
                fontSize = 16.sp,
                color = Color(0xFF9C27B0),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(16.dp)
            )
        }
    }
}

@Preview
@Composable
fun CustomButtonPreview() {
    NumaBoaTheme{
        CustomWhiteButton(text = "Button Text") {}
    }

}

