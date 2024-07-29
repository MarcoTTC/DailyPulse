package br.com.marcottc.dailypulse.articles.presentation

import br.com.marcottc.dailypulse.BaseViewModel
import br.com.marcottc.dailypulse.articles.application.ArticleUseCase
import br.com.marcottc.dailypulse.articles.presentation.ArticleState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ArticleViewModel(private val useCase: ArticleUseCase): BaseViewModel() {

    private val _articleState: MutableStateFlow<ArticleState> =
        MutableStateFlow(ArticleState(loading = true))
    val articleState: StateFlow<ArticleState> get() = _articleState

    init {
        getArticles()
    }

    fun getArticles(forceFetch: Boolean = false) {
        scope.launch {
            _articleState.emit(
                ArticleState(
                    loading = true,
                    articleList = _articleState.value.articleList
                )
            )

            val fetchedArticleList = useCase.getArticleList(forceFetch)

            _articleState.emit(ArticleState(articleList = fetchedArticleList))
        }
    }
}