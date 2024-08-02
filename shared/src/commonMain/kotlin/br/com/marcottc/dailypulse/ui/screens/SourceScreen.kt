package br.com.marcottc.dailypulse.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.unit.sp
import br.com.marcottc.dailypulse.sources.application.Source
import br.com.marcottc.dailypulse.sources.presentation.SourceViewModel
import br.com.marcottc.dailypulse.ui.screens.elements.ErrorMessage
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.koin.compose.koinInject

class SourceScreen(): Screen {
    @Composable
    override fun Content() {
        SourceScreenContent()
    }

}

@Composable
fun SourceScreenContent(
    viewModel: SourceViewModel = koinInject()
) {
    val sourceState = viewModel.sourceState.collectAsState()

    Column {
        AppBar()

        if (sourceState.value.error != null)
            ErrorMessage(sourceState.value.error!!)

        SourcesListView(viewModel)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AppBar() {
    val navigator = LocalNavigator.currentOrThrow

    TopAppBar(
        title = { Text(text = "Sources") },
        navigationIcon = {
            IconButton(onClick = {
                navigator.pop()
            }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Up Button",
                )
            }
        }
    )
}

@Composable
fun SourcesListView(viewModel: SourceViewModel) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(viewModel.sourceState.value.sourceList) { source ->
            SourceItemView(source = source)
        }
    }
}

@Composable
fun SourceItemView(source: Source) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = source.name,
            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 22.sp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = source.desc)
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = source.origin,
            style = TextStyle(color = Color.Gray),
            modifier = Modifier.align(Alignment.End)
        )
        Spacer(modifier = Modifier.height(4.dp))
    }
}