package com.mada.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val MadaTypography = Typography(
    displayLarge = TextStyle(
        fontWeight   = FontWeight.Bold,
        fontSize     = 32.sp,
        lineHeight   = 40.sp,
        letterSpacing = (-1).sp
    ),
    titleLarge = TextStyle(
        fontWeight   = FontWeight.Bold,
        fontSize     = 24.sp,
        lineHeight   = 30.sp,
        letterSpacing = (-0.5).sp
    ),
    titleMedium = TextStyle(
        fontWeight   = FontWeight.SemiBold,
        fontSize     = 16.sp,
        lineHeight   = 22.sp,
        letterSpacing = (-0.2).sp
    ),
    bodyLarge = TextStyle(
        fontWeight   = FontWeight.Normal,
        fontSize     = 15.sp,
        lineHeight   = 24.sp
    ),
    bodyMedium = TextStyle(
        fontWeight   = FontWeight.Normal,
        fontSize     = 14.sp,
        lineHeight   = 22.sp
    ),
    bodySmall = TextStyle(
        fontWeight   = FontWeight.Normal,
        fontSize     = 12.sp,
        lineHeight   = 18.sp
    ),
    labelMedium = TextStyle(
        fontWeight   = FontWeight.SemiBold,
        fontSize     = 11.sp,
        lineHeight   = 16.sp,
        letterSpacing = 0.8.sp
    ),
    labelSmall = TextStyle(
        fontWeight   = FontWeight.Medium,
        fontSize     = 10.sp,
        lineHeight   = 14.sp,
        letterSpacing = 0.6.sp
    ),
)
