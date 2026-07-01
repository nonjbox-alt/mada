package com.mada.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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

@Composable
fun UsbScreen(modifier: Modifier = Modifier) {
    // وضع تجريبي: USB متصلة. لاحقاً يربط بـ UsbManager / StorageManager الحقيقي
    var connected by remember { mutableStateOf(true) }

    Column(modifier = modifier.fillMaxSize()) {
        MadaScreenHeader(
            title = "USB",
            subtitle = if (connected) "SanDisk Ultra · exFAT" else null,
            trailing = { StatusBadge(connected = connected) }
        )

        if (!connected) {
            EmptyUsbState(onSimulateConnect = { connected = true })
        } else {
            StorageCard(
                label = "SanDisk Ultra 64GB",
                usedBytes = 41_200_000_000,
                totalBytes = 64_000_000_000,
                accent = MadaTeal,
                modifier = Modifier.padding(horizontal = 20.dp)
            )

            Spacer(Modifier.height(14.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                ActionPill(
                    label = "إخراج آمن",
                    icon  = "⏏️",
                    accent = MadaYellow,
                    accentDim = MadaYellowDim,
                    modifier = Modifier.weight(1f),
                    onClick = { connected = false }
                )
                ActionPill(
                    label = "مزامنة",
                    icon  = "🔄",
                    accent = MadaTeal,
                    accentDim = MadaTealDim,
                    modifier = Modifier.weight(1f),
                    onClick = {}
                )
            }

            Spacer(Modifier.height(8.dp))
            Text(
                text  = "ملفات USB",
                fontSize = 11.sp,
                fontWeight = FontWeight.SemiBold,
                color = MadaText3,
                fontFamily = FontFamily.Monospace,
                modifier = Modifier.padding(start = 20.dp, top = 10.dp, bottom = 4.dp)
            )

            val files = SampleData.usbFiles
            val maxSize = files.maxOf { it.size }
            LazyColumn(
                modifier = Modifier.weight(1f),
                contentPadding = PaddingValues(horizontal = 12.dp)
            ) {
                items(files) { file ->
                    FileRow(file = file, maxSize = maxSize, onClick = {})
                }
            }
        }
    }
}

@Composable
private fun EmptyUsbState(onSimulateConnect: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize().padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .size(72.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(MadaSurface),
            contentAlignment = Alignment.Center
        ) {
            Text("💾", fontSize = 30.sp)
        }
        Spacer(Modifier.height(16.dp))
        Text("ما في USB متصلة", fontSize = 15.sp, fontWeight = FontWeight.SemiBold, color = MadaText)
        Spacer(Modifier.height(6.dp))
        Text(
            "وصّل فلاشة أو قارئ USB بكيبل OTG عشان تظهر هنا تلقائياً",
            fontSize = 12.5.sp,
            color = MadaText3,
            textAlign = androidx.compose.ui.text.style.TextAlign.Center
        )
        Spacer(Modifier.height(20.dp))
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(99.dp))
                .background(MadaPurpleDim)
                .clickable(onClick = onSimulateConnect)
                .padding(horizontal = 18.dp, vertical = 10.dp)
        ) {
            Text("محاكاة اتصال (تجريبي)", fontSize = 12.sp, color = MadaPurple, fontWeight = FontWeight.SemiBold)
        }
    }
}

@Composable
fun ActionPill(
    label : String,
    icon  : String,
    accent: androidx.compose.ui.graphics.Color,
    accentDim: androidx.compose.ui.graphics.Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(accentDim)
            .clickable(onClick = onClick)
            .padding(vertical = 12.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(icon, fontSize = 14.sp)
        Spacer(Modifier.width(6.dp))
        Text(label, fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = accent)
    }
}
