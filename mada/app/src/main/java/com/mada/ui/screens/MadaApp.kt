package com.mada.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.weight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.mada.ui.components.MadaBottomNav
import com.mada.ui.components.MadaTab
import com.mada.ui.theme.MadaBg

@Composable
fun MadaApp() {
    var selectedTab by remember { mutableStateOf(MadaTab.FILES) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MadaBg)
    ) {
        Column(modifier = Modifier.weight(1f)) {
            when (selectedTab) {
                MadaTab.FILES -> HomeScreen()
                MadaTab.USB   -> UsbScreen()
                MadaTab.FTP   -> FtpScreen()
                MadaTab.PS4   -> Ps4Screen()
            }
        }
        MadaBottomNav(selected = selectedTab, onSelect = { selectedTab = it })
    }
}
