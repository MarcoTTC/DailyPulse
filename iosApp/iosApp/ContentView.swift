import SwiftUI
import shared

struct ContentView: View {

    @State private var shouldOpenAbout = false

	var body: some View {
	    NavigationStack {
		    ArticleScreen(viewModel: .init())
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
	    }
    }
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}