import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import br.com.marcottc.dailypulse.di.initKoin
import br.com.marcottc.dailypulse.ui.App

fun main() {
    initKoin()

    application {
        val windowState = rememberWindowState()

        Window(
            onCloseRequest = ::exitApplication,
            state = windowState,
            title = "Daily Pulse"
        ) {
            Surface(modifier = Modifier.fillMaxSize()) {
                App()
            }
        }
    }
}