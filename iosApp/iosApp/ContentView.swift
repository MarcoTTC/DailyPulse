import SwiftUI
import shared

struct ContentView: View {

    @State private var shouldOpenAbout = false
    @State private var shouldOpenSources = false

	var body: some View {
	    let articleScreen = ArticleScreen(viewModel: .init())

	    NavigationStack {
		    articleScreen
	            .toolbar {
	                ToolbarItem {
	                    Button {
	                        shouldOpenSources = true
	                    } label: {
	                        Label("Sources", systemIamge: "list.bullet.rectangle")
	                            .labelStyle(.titleAndIcon)
	                    }
	                    .popover(isPresented: $shouldOpenSources) {
	                        SourcesScreen(viewModel: .init())
	                    }
	                }
	                ToolbarItem {
                        Button {
                            shouldOpenAbout = true
                        } label: {
                            Label("About", systemIamge: "info.circle")
                                .labelStyle(.titleAndIcon)
                        }
                        .popover(isPresented: $shouldOpenAbout) {
                            AboutScreen()
                        }
                    }
	            }
	    }.refreshable {
	        articleScreen.viewModel.articleViewModel.getArticle(forceFetch: true)
	    }
    }
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}