package com.mada.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mada.data.SampleData
import com.mada.ui.components.*
import com.mada.ui.theme.*

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    var filter by remember { mutableStateOf("الكل") }
    val files = SampleData.homeFiles
    val maxSize = files.maxOf { it.size }

    Column(modifier = modifier.fillMaxSize()) {

        // ── هيدر + breadcrumb ────────────────────────
        Column(Modifier.padding(horizontal = 20.dp, vertical = 14.dp)) {
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text  = "مدى",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    color = MadaPurple
                )
                Text("🔍", fontSize = 17.sp)
            }
            Spacer(Modifier.height(6.dp))
            Row {
                Text("داخلي", fontSize = 12.sp, color = MadaText3, fontFamily = FontFamily.Monospace)
                Text(" / ", fontSize = 12.sp, color = MadaLine, fontFamily = FontFamily.Monospace)
                Text("التنزيلات", fontSize = 12.sp, color = MadaText2, fontFamily = FontFamily.Monospace)
            }
        }

        StorageCard(
            label = "التخزين الداخلي",
            usedBytes = 82_400_000_000,
            totalBytes = 128_000_000_000,
            modifier = Modifier.padding(horizontal = 20.dp)
        )

        Spacer(Modifier.height(14.dp))

        FilterChips(
            options = listOf("الكل" to "", "مجلدات" to "📁", "ألعاب" to "🎮", "صوتيات" to "🎵", "فيديو" to "🎬"),
            selected = filter,
            onSelect = { filter = it }
        )

        Spacer(Modifier.height(10.dp))
        Text(
            text  = "الأحدث أولاً",
            fontSize = 11.sp,
            fontWeight = FontWeight.SemiBold,
            color = MadaText3,
            fontFamily = FontFamily.Monospace,
            modifier = Modifier.padding(horizontal = 20.dp)
        )
        Spacer(Modifier.height(4.dp))

        LazyColumn(
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(horizontal = 12.dp, vertical = 4.dp)
        ) {
            items(files) { file ->
                FileRow(file = file, maxSize = maxSize, onClick = {})
            }
        }
    }
}
