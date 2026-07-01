package com.mada.ui.components

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mada.ui.theme.*

enum class MadaTab(val label: String, val icon: String) {
    FILES("الملفات", "📂"),
    USB("USB", "💾"),
    FTP("FTP", "🌐"),
    PS4("PS4", "🎮"),
}

@Composable
fun MadaBottomNav(
    selected: MadaTab,
    onSelect: (MadaTab) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(MadaSurface2)
            .padding(top = 10.dp, bottom = 18.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        MadaTab.entries.forEach { tab ->
            val isActive = tab == selected
            Column(
                modifier = Modifier
                    .weight(1f)
                    .clickable { onSelect(tab) },
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .background(if (isActive) MadaPurpleDim else MadaSurface2)
                        .padding(horizontal = 12.dp, vertical = 4.dp)
                ) {
                    Text(text = tab.icon, fontSize = 18.sp)
                }
                Text(
                    text  = tab.label,
                    fontSize = 10.sp,
                    fontWeight = if (isActive) FontWeight.SemiBold else FontWeight.Medium,
                    color = if (isActive) MadaPurple else MadaText3
                )
            }
        }
    }
}
