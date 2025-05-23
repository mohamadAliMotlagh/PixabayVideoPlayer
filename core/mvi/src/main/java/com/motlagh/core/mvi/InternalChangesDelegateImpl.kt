package com.motlagh.core.mvi

import com.motlagh.core.utils.coroutine.flatMapConcurrently
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.onSubscription

internal class InternalChangesDelegateImpl<PARTIAL_UI_STATE> : InternalChangesDelegate<PARTIAL_UI_STATE> {

    private val internalChangesPartialStateFlowListenerStarted = CompletableDeferred<Unit>()
    private val internalChangesPartialStateFlow = MutableSharedFlow<PARTIAL_UI_STATE>()

    override fun getInternalChanges(): Flow<PARTIAL_UI_STATE> = internalChangesPartialStateFlow
        .onSubscription { internalChangesPartialStateFlowListenerStarted.complete(Unit) }

    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun clearChanges() {
        internalChangesPartialStateFlow
    }

    override suspend fun setInternalChanges(vararg internalChangesFlows: Flow<PARTIAL_UI_STATE>) {
        internalChangesPartialStateFlowListenerStarted.await()
        internalChangesPartialStateFlow.emitAll(
            // to flatten Flow with queue behaviour like in userIntents() Flow but without ::mapIntents
            internalChangesFlows.asFlow().flatMapConcurrently {
                it
            },
        )
    }
}
