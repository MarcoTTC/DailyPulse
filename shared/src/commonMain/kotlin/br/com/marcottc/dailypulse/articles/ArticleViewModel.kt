package br.com.marcottc.dailypulse.articles

import br.com.marcottc.dailypulse.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ArticleViewModel: BaseViewModel() {

    private val _articleState: MutableStateFlow<ArticleState> = MutableStateFlow(ArticleState(loading = true))
    val articleState: StateFlow<ArticleState> get() = _articleState

    init {
        getArticles()
    }

    private fun getArticles() {
        scope.launch {
            delay(2000)

            _articleState.emit(ArticleState(error = "Something went wrong"))

            delay(2000)

            val fetchedArticleList = fetchArticles()

            _articleState.emit(ArticleState(articleList = fetchedArticleList))
        }
    }

    suspend fun fetchArticles(): List<Article> = mockArticles

    private val mockArticles = listOf(
        Article(
            "At Ukraine’s Largest Children’s Hospital, a Horrific Scene of Destruction",
            "Families and patients were not unfamiliar with the sound of missiles flying overhead. But the Russian assault on the hospital marked one of the worst days of violence against civilians in months.",
            "2024-07-09T14:38:39-04:00",
            "https://static01.nyt.com/images/2024/07/09/multimedia/09ukraine-children-01-hpvl-promo/09ukraine-children-01-hpvl-threeByTwoSmallAt2X.jpg"
        ),
        Article(
            "The Surgeon",
            "A single image captures a day of horror at a children’s hospital in Ukraine hit by a Russian missile.",
            "2024-07-08T16:58:09-04:00",
            "https://static01.nyt.com/images/2024/07/08/world/oneimageukraine/oneimageukraine-threeByTwoSmallAt2X.png"
        ),
        Article(
            "Russian Court Orders Arrest of Yulia Navalnaya, Navalny’s Widow",
            "The court accused Ms. Navalnaya, who left Russia in 2021, of “participating in an extremist community.” She would be subject to arrest if she ever returns to Russia, the court said.",
            "2024-07-09T14:08:46-04:00",
            "https://static01.nyt.com/images/2024/07/09/multimedia/09russia-Navalnaya-1-pwzg/09russia-Navalnaya-1-pwzg-threeByTwoSmallAt2X.jpg"
        )
    )
}