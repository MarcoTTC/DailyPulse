package br.com.marcottc.dailypulse.sources.presentation

import br.com.marcottc.dailypulse.BaseViewModel
import br.com.marcottc.dailypulse.sources.application.SourceUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SourceViewModel(private val useCase: SourceUseCase): BaseViewModel() {

    private val _sourceState =
        MutableStateFlow(SourceState(emptyList(), true, null))
    val sourceState: StateFlow<SourceState> get() = _sourceState

    init {
        getSources()
    }

    private fun getSources() {
        scope.launch {
            _sourceState.emit(SourceState(_sourceState.value.sourceList, true, null))

            val sourceList = useCase.getSourceList()

            _sourceState.emit(
                SourceState(sourceList)
            )
        }
    }
}