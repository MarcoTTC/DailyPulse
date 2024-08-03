package br.com.marcottc.dailypulse.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.marcottc.dailypulse.articles.application.Article
import br.com.marcottc.dailypulse.articles.presentation.ArticleViewModel
import br.com.marcottc.dailypulse.ui.screens.elements.ErrorMessage
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import org.koin.compose.koinInject
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.koin.core.Koin

class ArticleScreen(val koin: Koin): Screen {
    @Composable
    override fun Content() {
        ArticleScreenContent(koin)
    }
}

@Composable
fun ArticleScreenContent(
    koin: Koin,
    articleViewModel: ArticleViewModel = koin.get()
) {
    val articleState = articleViewModel.articleState.collectAsState()

    Column {
        AppBar(koin)

        if (articleState.value.error != null) {
            ErrorMessage(articleState.value.error!!)
        }
        if (articleState.value.articleList.isNotEmpty()) {
            ArticleListView(articleViewModel)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AppBar(koin: Koin) {
    val navigator = LocalNavigator.currentOrThrow

    TopAppBar(
        title = { Text(text = "Articles") },
        actions = {
            IconButton(onClick = {
                navigator.push(SourceScreen(koin))
            }) {
                Icon(
                    imageVector = Icons.Outlined.List,
                    contentDescription = "Sources Button"
                )
            }
            IconButton(onClick = {
                navigator.push(AboutScreen())
            }) {
                Icon(
                    imageVector = Icons.Outlined.Info,
                    contentDescription = "About Device Button"
                )
            }
        }
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ArticleListView(viewModel: ArticleViewModel) {

    val state = rememberPullRefreshState(
        refreshing = viewModel.articleState.value.loading,
        onRefresh = { viewModel.getArticles(forceFetch = true) }
    )

    Box(
        modifier = Modifier.pullRefresh(state = state)
    ) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(viewModel.articleState.value.articleList.size) { index ->
                ArticleItemView(article = viewModel.articleState.value.articleList[index])
            }
        }
        PullRefreshIndicator(
            refreshing = viewModel.articleState.value.loading,
            state = state,
            modifier = Modifier.align(Alignment.TopCenter)
        )
    }
}

@Composable
fun ArticleItemView(article: Article) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        KamelImage(
            resource = asyncPainterResource(data = article.imageUrl),
            contentDescription = "Article Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.height(200.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = article.title,
            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 22.sp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = article.desc)
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = article.date,
            style = TextStyle(color = Color.Gray),
            modifier = Modifier.align(Alignment.End)
        )
        Spacer(modifier = Modifier.height(4.dp))
    }
}