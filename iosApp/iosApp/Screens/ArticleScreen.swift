import SwiftUI
import shared

extension ArticleScreen {

    @MainActor
    class ArticleViewModelWrapper: ObservableObject {
        let articleViewModel: ArticleViewModel

        init() {
            articleViewModel = ArticleInjector().articleViewModel
            articleState = articleViewModel.articleState.value
        }

        @Published var articleState: ArticleState

        func startObserving() {
            Task {
                for await articleS in articleViewModel.articleState {
                    self.articleState = articleS
                }
            }
        }
    }
}

struct ArticleScreen: View {

    @ObservedObject private(set) var viewModel: ArticleViewModelWeapper

    var body: some View {
        VStack {
            AppBar()

            if viewModel.articleState.loading {
                Loader()
            }

            if let error = viewModel.articleState.error {
                ErrorMessage(message: error)
            }

            if (!viewModel.articleState.articleList.isEmpty) {
                ScrollView {
                    LazyVStack(spacing: 10) {
                        ForEach(viewModel.articleState.articleList, id: \.self) { article in
                            ArticleItemView(article: article)
                        }
                    }
                }
            }
        }.onAppear {
            self.viewModel.startObserving()
        }
    }
}

struct AppBar: View {
    var body: some View {
        Text("Articles")
            .font(.largeTitle)
            .fontWeight(.bold)
    }
}

struct ArticleItemView: View {
    var article: Article

    var body: some View {
        VStack(alignment: .leading, spacing: 8) {
            AsyncImage(url: URL(string: article.imageUrl)) { phase in
                if phase.image != nil {
                    phase.image!
                        .resizable()
                        .aspectRatio(contentMode: .fit)
                } else if phase.error != nil {
                    Text("Image Load Error")
                } else {
                    ProgressView()
                }
            }
            Text(article.title)
                .font(.title)
                .fontWeight(.bold)
            Text(article.desc)
            Text(article.date).frame(maxWidth: .infinity, alignment: .trailing).foregroundStyle(.gray)
        }
        .padding(16)
    }
}

struct Loader: View {
    var body: some View {
        ProgressView()
    }
}

struct ErrorMessage: View {
    var message: String

    var body: some View {
        Text(message)
            .font(.title)
    }
}