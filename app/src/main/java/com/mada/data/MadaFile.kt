package com.mada.data

import androidx.compose.ui.graphics.Color
import com.mada.ui.theme.*

enum class FileType { FOLDER, PKG, VIDEO, AUDIO, ARCHIVE, IMAGE, APK, OTHER }

data class MadaFile(
    val name     : String,
    val path     : String,
    val size     : Long,           // بالـ bytes
    val type     : FileType,
    val extension: String = "",
    val itemCount: Int    = 0,     // للمجلدات
    val modifiedAt: Long = 0L,
)

// ── مساعدات اللون حسب النوع ──────────────────────────
fun FileType.iconBg(): Color = when (this) {
    FileType.FOLDER  -> MadaPurpleDim
    FileType.PKG     -> MadaPurpleDim
    FileType.VIDEO   -> MadaVioletDim
    FileType.AUDIO   -> MadaTealDim
    FileType.ARCHIVE -> MadaYellowDim
    FileType.IMAGE   -> Color(0xFF1A2A18)
    FileType.APK     -> Color(0xFF1A2818)
    FileType.OTHER   -> Color(0xFF1A1A22)
}

fun FileType.accentColor(): Color = when (this) {
    FileType.FOLDER  -> MadaPurple
    FileType.PKG     -> MadaPurple
    FileType.VIDEO   -> MadaViolet
    FileType.AUDIO   -> MadaTeal
    FileType.ARCHIVE -> MadaYellow
    FileType.IMAGE   -> Color(0xFF6BCF7F)
    FileType.APK     -> Color(0xFF4CAF7D)
    FileType.OTHER   -> MadaText3
}

fun FileType.icon(): String = when (this) {
    FileType.FOLDER  -> "📁"
    FileType.PKG     -> "🎮"
    FileType.VIDEO   -> "🎬"
    FileType.AUDIO   -> "🎵"
    FileType.ARCHIVE -> "🗜️"
    FileType.IMAGE   -> "🖼️"
    FileType.APK     -> "📦"
    FileType.OTHER   -> "📄"
}

// ── تحويل الحجم لنص مقروء ───────────────────────────
fun Long.toReadableSize(): String {
    if (this <= 0) return "0 B"
    val units = listOf("B", "KB", "MB", "GB", "TB")
    var size = this.toDouble()
    var idx = 0
    while (size >= 1024 && idx < units.lastIndex) {
        size /= 1024; idx++
    }
    return if (size == size.toLong().toDouble())
        "${size.toLong()} ${units[idx]}"
    else
        "%.1f ${units[idx]}".format(size)
}

// ── تحديد نوع الملف من الامتداد ─────────────────────
fun String.toFileType(): FileType = when (this.lowercase()) {
    "pkg"                              -> FileType.PKG
    "mp4","mkv","avi","mov","webm"     -> FileType.VIDEO
    "mp3","flac","aac","wav","ogg"     -> FileType.AUDIO
    "zip","rar","7z","tar","gz"        -> FileType.ARCHIVE
    "jpg","jpeg","png","gif","webp"    -> FileType.IMAGE
    "apk"                              -> FileType.APK
    else                               -> FileType.OTHER
}
