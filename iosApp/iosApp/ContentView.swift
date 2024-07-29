import SwiftUI
import shared

struct ContentView: View {

    @State private var shouldOpenAbout = false

	var body: some View {
	    let articleScreen = ArticleScreen(viewModel: .init())

	    NavigationStack {
		    articleScreen
	            .toolbar {
	                ToolbarItem {
	                    Button {
	                        shouldOpenAbout = true
	                    } label: {
	                        Label("About", systemIamge: "info.circle").labelStyle()
	                    }
	                    .popover(isPresented: $shouldOpenAbout) {
	                        AboutScreen()
	                    }
	                }
	            }
	    }.refreshable {
	        articleScreen.viewModel.articleViewModel.getArticle()
	    }
    }
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}