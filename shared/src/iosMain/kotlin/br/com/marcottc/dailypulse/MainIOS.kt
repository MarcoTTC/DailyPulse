package br.com.marcottc.dailypulse

import br.com.marcottc.dailypulse.ui.App
import androidx.compose.ui.window.ComposeUIViewController
import br.com.marcottc.dailypulse.ui.screens.AboutScreen

fun MainViewController() = ComposeUIViewController {
    AboutScreen()
}