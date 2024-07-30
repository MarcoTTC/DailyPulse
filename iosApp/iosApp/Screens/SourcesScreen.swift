import SwiftUI
import shared

extension SourcesScreen {

    @MainActor
    class SourcesViewModelWrapper: ObservableObject {
        init() {
            viewModel = SourceInjector().sourceViewModel
            sourceState = viewModel.sourceState.value
        }

        let viewModel: SourceViewModel

        @Published var sourceState: SourceState

        func startObserving() {
            Task {
                for await sourcesS in viewModel.sourceState {
                    self.sourceState = sourceS
                }
            }
        }
    }
}

struct SourcesScreen: View {
    @Environment(\.dismiss)
    private var dismiss

    @ObservedObject private(set) var viewModel: SourcesScreen.SourcesViewModelWrapper

    var body: some View {
        NavigationStack {
            VStack {
                if let error = viewModel.sourceState.error {
                    ErrorMessage(message: error)
                }

                if viewModel.sourceState.loading {
                    Loader()
                }

                if !viewModel.sourceState.sourceList.isEmpty {
                    ScrollView {
                        LazyVStack(spacing: 10) {
                            ForEach(viewModel.sourceState.sourceList, id: \.self) { source in
                                SourceItemView(name: source.name, desc: source.desc, origin: source.origin)
                            }
                        }
                    }
                }
            }.onAppear {
                self.viewModel.startObserving()
            }
            .navigationTitle("Sources")
            .toolbar {
                ToolbarItem(placement: .primaryAction) {
                    Button {
                        dismiss()
                    } label: {
                        Text("Done")
                            .bold()
                    }
                }
            }
        }
    }
}

struct SourceItemView: View {
    let name: String
    let desc: String
    let origin: String

    var body: some View {
        VStack(alignment: .leading, spacing: 8) {
            Text(name)
                .font(.title)
                .fontWeight(.bold)
            Text(desc)
            Text(origin).frame(maxWidth: .infinity, alignment: .trailing).foregroundStyle(.gray)
        }
        .padding(16)
    }
}