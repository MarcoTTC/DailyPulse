package br.com.marcottc.dailypulse.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import br.com.marcottc.dailypulse.ui.screens.ArticleScreen
import cafe.adriel.voyager.navigator.Navigator

@Composable
fun App() {
    MyApplicationTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Navigator(ArticleScreen())
        }
    }
}