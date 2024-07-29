import SwiftUI
import shared

@main
struct iOSApp: App {

    init() {
        KointInitializerKt.doInitKoin()
    }

	var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
}