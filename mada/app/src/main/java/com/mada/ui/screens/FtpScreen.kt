package com.mada.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mada.data.SampleData
import com.mada.ui.components.*
import com.mada.ui.theme.*

private data class FtpConnection(val name: String, val host: String, val port: Int, val connected: Boolean)

@Composable
fun FtpScreen(modifier: Modifier = Modifier) {
    val connections = remember {
        listOf(
            FtpConnection("PS4 — GoldHEN", "192.168.1.12", 2121, connected = true),
            FtpConnection("الراوتر — NAS", "192.168.1.1", 21, connected = false),
        )
    }

    Column(modifier = modifier.fillMaxSize()) {
        MadaScreenHeader(title = "FTP", subtitle = "${connections.size} اتصالات محفوظة")

        Column(Modifier.padding(horizontal = 20.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            connections.forEach { conn -> FtpConnectionCard(conn) }
            DashedAddCard()
        }

        Spacer(Modifier.height(18.dp))
        Text(
            text  = "آخر الملفات المنقولة",
            fontSize = 11.sp,
            fontWeight = FontWeight.SemiBold,
            color = MadaText3,
            fontFamily = FontFamily.Monospace,
            modifier = Modifier.padding(start = 20.dp, bottom = 4.dp)
        )

        val files = SampleData.ftpHistory
        val maxSize = files.maxOf { it.size }
        LazyColumn(contentPadding = PaddingValues(horizontal = 12.dp)) {
            items(files) { file ->
                FileRow(file = file, maxSize = maxSize, onClick = {})
            }
        }
    }
}

@Composable
private fun FtpConnectionCard(conn: FtpConnection) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .background(MadaSurface)
            .clickable {}
            .padding(14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(if (conn.connected) MadaTealDim else MadaSurface3),
            contentAlignment = Alignment.Center
        ) { Text("🌐", fontSize = 16.sp) }

        Spacer(Modifier.width(12.dp))

        Column(Modifier.weight(1f)) {
            Text(conn.name, fontSize = 14.sp, fontWeight = FontWeight.Medium, color = MadaText)
            Text(
                "${conn.host}:${conn.port}",
                fontSize = 11.5.sp,
                color = MadaText3,
                fontFamily = FontFamily.Monospace
            )
        }

        StatusBadge(connected = conn.connected)
    }
}

@Composable
private fun DashedAddCard() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .background(MadaSurface.copy(alpha = .4f))
            .clickable {}
            .padding(16.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text("＋", fontSize = 16.sp, color = MadaPurple)
        Spacer(Modifier.width(8.dp))
        Text("اتصال FTP جديد", fontSize = 13.sp, color = MadaPurple, fontWeight = FontWeight.SemiBold)
    }
}
