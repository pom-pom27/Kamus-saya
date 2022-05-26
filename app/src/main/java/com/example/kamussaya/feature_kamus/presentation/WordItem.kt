package com.example.kamussaya.feature_kamus.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kamussaya.feature_kamus.domain.models.Word

@Composable
fun WordItem(
    wordInfo: Word,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = wordInfo.word,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Text(
            text = wordInfo.lema,
            fontWeight = FontWeight.Light
        )

        Spacer(modifier = Modifier.height(16.dp))

        wordInfo.arti.forEachIndexed { idx, meaning ->
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "${idx + 1}. ${meaning.kelas_kata.ifBlank { "-" }}",
                fontWeight = FontWeight.Bold
            )
            Text(text = meaning.deskripsi)
        }
    }
}
