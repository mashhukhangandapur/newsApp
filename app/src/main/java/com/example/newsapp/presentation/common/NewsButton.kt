package com.example.newsapp.presentation.common

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.newsapp.presentation.Dimens.MediumPadding1
import com.example.newsapp.presentation.Dimens.MediumPadding2

@Composable
fun NewsButton(
    text : String,
    onClick :() -> Unit
){
    Button(onClick = onClick,
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.White,
                containerColor = MaterialTheme.colorScheme.primary
            ),
            shape = RoundedCornerShape(size = MediumPadding2)
            ) {
        Text(
            text= text,
            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold)
        )
    }
}

@Composable
fun NewsTextButton(
    text: String,
    onClick: () -> Unit
){
    TextButton(onClick = onClick) {
        Text(
            text= text,
            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold),
            color =  MaterialTheme.colorScheme.onBackground // ✅ Auto-switches per theme
        )
    }
}