package com.mada.data

object SampleData {
    val homeFiles = listOf(
        MadaFile("الأفلام العربية", "/storage/movies", 15_254_152_396, FileType.FOLDER, itemCount = 34),
        MadaFile("GTA_V_PS4", "/dl/gta5.pkg", 52_311_897_400, FileType.PKG, "pkg"),
        MadaFile("playlist_final", "/music/p.mp3", 8_808_038, FileType.AUDIO, "mp3"),
        MadaFile("backup_2025", "/dl/backup.zip", 2_254_857_830, FileType.ARCHIVE, "zip"),
        MadaFile("clip_edit_final_v3", "/dl/clip.mp4", 356_515_840, FileType.VIDEO, "mp4"),
        MadaFile("التنزيلات", "/storage/downloads", 4_512_998_120, FileType.FOLDER, itemCount = 18),
    )

    val usbFiles = listOf(
        MadaFile("FW_10.01_PS4UPDATE", "/usb/fw.pup", 1_310_720_000, FileType.OTHER, "pup"),
        MadaFile("Minecraft_Addons", "/usb/mc", 891_456_200, FileType.FOLDER, itemCount = 12),
        MadaFile("game_save_backup", "/usb/save.zip", 45_120_300, FileType.ARCHIVE, "zip"),
    )

    val ftpHistory = listOf(
        MadaFile("PS4_screenshots", "/data/screens", 612_450_000, FileType.FOLDER, itemCount = 56),
        MadaFile("save-data-0012", "/data/saves/0012.bin", 2_145_000, FileType.OTHER, "bin"),
    )
}
