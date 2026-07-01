package com.mada.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mada.ui.theme.*

// ── توب بار بسيط لشاشات (USB / FTP / PS4) ──────────────
@Composable
fun MadaScreenHeader(
    title   : String,
    subtitle: String? = null,
    trailing: (@Composable () -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text  = title,
                style = MaterialTheme.typography.titleLarge,
                color = MadaText
            )
            if (subtitle != null) {
                Text(
                    text  = subtitle,
                    style = MaterialTheme.typography.bodySmall,
                    color = MadaText3,
                    fontFamily = FontFamily.Monospace
                )
            }
        }
        trailing?.invoke()
    }
}

// ── بادج حالة الاتصال (متصل/غير متصل) ───────────────────
@Composable
fun StatusBadge(connected: Boolean, modifier: Modifier = Modifier) {
    val color = if (connected) MadaTeal else MadaText3
    val bg    = if (connected) MadaTealDim else MadaSurface3
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(99.dp))
            .background(bg)
            .padding(horizontal = 10.dp, vertical = 5.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Box(
            modifier = Modifier
                .size(6.dp)
                .clip(CircleShape)
                .background(color)
        )
        Text(
            text  = if (connected) "متصل" else "غير متصل",
            fontSize   = 11.sp,
            fontWeight = FontWeight.SemiBold,
            color = color,
            fontFamily = FontFamily.Monospace
        )
    }
}

// ── شريط التخزين ─────────────────────────────────────────
@Composable
fun StorageCard(
    label    : String,
    usedBytes: Long,
    totalBytes: Long,
    accent   : Color = MadaPurple,
    modifier : Modifier = Modifier
) {
    val fraction = if (totalBytes > 0) (usedBytes.toFloat() / totalBytes).coerceIn(0f, 1f) else 0f
    val animated by animateFloatAsState(
        targetValue = fraction,
        animationSpec = tween(700, easing = EaseOutCubic),
        label = "storage_bar"
    )

    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .background(MadaSurface)
            .padding(14.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(label, fontSize = 12.5.sp, fontWeight = FontWeight.Medium, color = MadaText2)
            Text(
                text = "${usedBytes.toGbString()} / ${totalBytes.toGbString()}",
                fontSize = 12.sp,
                color = MadaText3,
                fontFamily = FontFamily.Monospace
            )
        }
        Spacer(Modifier.height(10.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(5.dp)
                .clip(RoundedCornerShape(99.dp))
                .background(MadaSurface3)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(animated)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(99.dp))
                    .background(
                        Brush.horizontalGradient(listOf(accent, accent.copy(alpha = .75f)))
                    )
            )
        }
    }
}

private fun Long.toGbString(): String {
    val gb = this / 1024.0 / 1024.0 / 1024.0
    return "%.1f GB".format(gb)
}

// ── شرائح الفلترة ────────────────────────────────────────
@Composable
fun FilterChips(
    options : List<Pair<String, String>>, // label to icon
    selected: String,
    onSelect: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState())
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        options.forEach { (label, icon) ->
            val isActive = label == selected
            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(99.dp))
                    .background(if (isActive) MadaPurpleDim else MadaSurface2)
                    .clickable { onSelect(label) }
                    .padding(horizontal = 12.dp, vertical = 7.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                if (icon.isNotEmpty()) Text(icon, fontSize = 12.sp)
                Text(
                    text  = label,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    color = if (isActive) MadaPurple else MadaText2
                )
            }
        }
    }
}
