package com.mada.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mada.ui.components.*
import com.mada.ui.theme.*

@Composable
fun Ps4Screen(modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxSize()) {
        MadaScreenHeader(
            title = "PS4 Companion",
            subtitle = "GoldHEN 2.4b18",
            trailing = { StatusBadge(connected = true) }
        )

        // ── كرت الكونسول ─────────────────────────────
        Column(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(MadaSurface)
                .padding(16.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(46.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(MadaPurpleDim),
                    contentAlignment = Alignment.Center
                ) { Text("🎮", fontSize = 20.sp) }

                Spacer(Modifier.width(12.dp))

                Column(Modifier.weight(1f)) {
                    Text("PS4-عبود", fontSize = 15.sp, fontWeight = FontWeight.SemiBold, color = MadaText)
                    Text(
                        "192.168.1.12 · FW 10.01",
                        fontSize = 11.5.sp,
                        color = MadaText3,
                        fontFamily = FontFamily.Monospace
                    )
                }
            }

            Spacer(Modifier.height(14.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(20.dp)) {
                StatChip(label = "REST API", value = "9090")
                StatChip(label = "RPI", value = "12800")
                StatChip(label = "FTP", value = "2121")
            }
        }

        Spacer(Modifier.height(18.dp))
        Text(
            text  = "إجراءات سريعة",
            fontSize = 11.sp,
            fontWeight = FontWeight.SemiBold,
            color = MadaText3,
            fontFamily = FontFamily.Monospace,
            modifier = Modifier.padding(start = 20.dp, bottom = 8.dp)
        )

        // ── شبكة الإجراءات ───────────────────────────
        Column(
            modifier = Modifier.padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                QuickActionCard("تثبيت PKG", "📦", MadaPurple, MadaPurpleDim, Modifier.weight(1f))
                QuickActionCard("تصفح FTP", "🗂️", MadaTeal, MadaTealDim, Modifier.weight(1f))
            }
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                QuickActionCard("إعادة تشغيل", "🔁", MadaYellow, MadaYellowDim, Modifier.weight(1f))
                QuickActionCard("الإشعارات", "🔔", MadaViolet, MadaVioletDim, Modifier.weight(1f))
            }
        }

        Spacer(Modifier.height(18.dp))
        Text(
            text  = "آخر تثبيت",
            fontSize = 11.sp,
            fontWeight = FontWeight.SemiBold,
            color = MadaText3,
            fontFamily = FontFamily.Monospace,
            modifier = Modifier.padding(start = 20.dp, bottom = 4.dp)
        )

        Row(
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .clickable {}
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(44.dp)
                    .clip(RoundedCornerShape(11.dp))
                    .background(MadaPurpleDim),
                contentAlignment = Alignment.Center
            ) { Text("🎮", fontSize = 18.sp) }
            Spacer(Modifier.width(12.dp))
            Column(Modifier.weight(1f)) {
                Text("GTA_V_PS4.pkg", fontSize = 14.sp, fontWeight = FontWeight.Medium, color = MadaText)
                Text("تم التثبيت · 52.3 GB", fontSize = 11.5.sp, color = MadaText3, fontFamily = FontFamily.Monospace)
            }
            StatusBadge(connected = true)
        }
    }
}

@Composable
private fun StatChip(label: String, value: String) {
    Column {
        Text(value, fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = MadaTeal, fontFamily = FontFamily.Monospace)
        Text(label, fontSize = 10.5.sp, color = MadaText3)
    }
}

@Composable
private fun QuickActionCard(
    label : String,
    icon  : String,
    accent: androidx.compose.ui.graphics.Color,
    accentDim: androidx.compose.ui.graphics.Color,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(14.dp))
            .background(MadaSurface)
            .clickable {}
            .padding(vertical = 16.dp, horizontal = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Box(
            modifier = Modifier
                .size(38.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(accentDim),
            contentAlignment = Alignment.Center
        ) { Text(icon, fontSize = 16.sp) }
        Text(label, fontSize = 12.sp, fontWeight = FontWeight.Medium, color = MadaText2)
    }
}
