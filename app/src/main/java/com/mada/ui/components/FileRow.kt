package com.mada.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mada.data.*
import com.mada.ui.theme.*

@Composable
fun FileRow(
    file    : MadaFile,
    maxSize : Long,
    onClick : () -> Unit,
    modifier: Modifier = Modifier
) {
    val accent = file.type.accentColor()
    val sizeFraction = if (maxSize > 0) (file.size.toFloat() / maxSize).coerceIn(0.05f, 1f) else 0f

    // انيميشن لشريط الحجم
    val animatedFraction by animateFloatAsState(
        targetValue   = sizeFraction,
        animationSpec = tween(durationMillis = 600, easing = EaseOutCubic),
        label         = "size_bar"
    )

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .clickable(onClick = onClick)
            .padding(horizontal = 10.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // ── أيقونة الملف ────────────────────────────
        Box(
            modifier = Modifier
                .size(44.dp)
                .clip(RoundedCornerShape(11.dp))
                .background(file.type.iconBg()),
            contentAlignment = Alignment.Center
        ) {
            Text(text = file.type.icon(), fontSize = 20.sp)
        }

        // ── الاسم والتفاصيل ─────────────────────────
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text     = file.name,
                style    = MaterialTheme.typography.bodyMedium,
                color    = MadaText,
                fontWeight = FontWeight.Medium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(Modifier.height(4.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                // امتداد الملف badge
                if (file.extension.isNotEmpty() && file.type != FileType.FOLDER) {
                    ExtBadge(ext = file.extension, color = accent, bg = file.type.iconBg())
                }
                // الحجم أو عدد العناصر
                Text(
                    text  = if (file.type == FileType.FOLDER)
                                "${file.itemCount} ملف · ${file.size.toReadableSize()}"
                            else
                                file.size.toReadableSize(),
                    style = MaterialTheme.typography.bodySmall,
                    color = MadaText3,
                    fontFamily = FontFamily.Monospace
                )
            }
        }

        // ── شريط الحجم البصري ───────────────────────
        Column(
            modifier         = Modifier.width(36.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(3.dp)
        ) {
            Box(
                modifier = Modifier
                    .width(3.dp)
                    .height(30.dp)
                    .clip(RoundedCornerShape(99.dp))
                    .background(MadaSurface3),
                contentAlignment = Alignment.BottomCenter
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(animatedFraction)
                        .clip(RoundedCornerShape(99.dp))
                        .background(accent)
                )
            }
            Text(
                text   = "${(sizeFraction * 100).toInt()}%",
                style  = MaterialTheme.typography.labelSmall,
                color  = MadaText3,
                fontFamily = FontFamily.Monospace,
                fontSize   = 9.sp
            )
        }
    }
}

@Composable
fun ExtBadge(ext: String, color: Color, bg: Color) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(4.dp))
            .background(bg)
            .padding(horizontal = 6.dp, vertical = 2.dp)
    ) {
        Text(
            text       = ext.uppercase(),
            fontSize   = 10.sp,
            fontWeight = FontWeight.SemiBold,
            color      = color,
            fontFamily = FontFamily.Monospace
        )
    }
}
