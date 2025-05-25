package com.motlagh.core.mvi

import android.os.Parcelable
import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.scan
import kotlinx.coroutines.launch

private const val SAVED_UI_STATE_KEY = "savedUiStateKey"

abstract class BaseViewModel<UI_STATE : Parcelable, PARTIAL_UI_STATE, EVENT, INTENT>(
    private val savedStateHandle: SavedStateHandle,
    private val initialState: UI_STATE,
    val savableViewModel: Boolean = false
) : ViewModel(),
    IntentDelegate<INTENT, PARTIAL_UI_STATE> by IntentDelegateImpl(),
    InternalChangesDelegate<PARTIAL_UI_STATE> by InternalChangesDelegateImpl(),
    EventDelegate<EVENT> by EventDelegateImpl() {

    private val _nonSavableState = MutableStateFlow(initialState)

    val uiState = if (savableViewModel) savedStateHandle.getStateFlow(
        key = SAVED_UI_STATE_KEY,
        initialValue = initialState,
    ) else _nonSavableState.asStateFlow()

    init {
        viewModelScope.launch {
            viewModelScope.launch {
                Log.e("BaseViewModel",uiState.first().toString())
            }

            merge(
                getIntents(::mapIntents),
                getInternalChanges(),
            )

                .scan(uiState.value, ::reduceUiState)
                .collect {
                    if (savableViewModel) {
                        savedStateHandle[SAVED_UI_STATE_KEY] = it
                    } else {
                        _nonSavableState.value = it
                    }
                }
        }
    }

    fun acceptIntent(intent: INTENT) {
        viewModelScope.launch {
            setIntent(intent)
        }
    }

    protected fun acceptChanges(vararg internalChangesFlows: Flow<PARTIAL_UI_STATE>) {
        viewModelScope.launch {
            setInternalChanges(*internalChangesFlows)
        }
    }

    protected fun clear() {
        viewModelScope.launch {
            //uiState.value = initialState
        }
    }

    protected fun acceptChangesAsync(vararg internalChangesFlows: Flow<PARTIAL_UI_STATE>) {
        internalChangesFlows.forEach {
            viewModelScope.launch {
                setInternalChanges(it)
            }
        }
    }

    protected abstract fun mapIntents(intent: INTENT): Flow<PARTIAL_UI_STATE>

    protected abstract fun reduceUiState(
        previousState: UI_STATE,
        partialState: PARTIAL_UI_STATE,
    ): UI_STATE

}