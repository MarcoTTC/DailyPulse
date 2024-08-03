package br.com.marcottc.dailypulse

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.CanvasBasedWindow
import br.com.marcottc.dailypulse.di.initKoin
import br.com.marcottc.dailypulse.ui.App

val koin = initKoin()

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    CanvasBasedWindow("Daily Pulse") {
        Surface(modifier = Modifier.fillMaxSize()) {
            App(koin)
        }
    }
}