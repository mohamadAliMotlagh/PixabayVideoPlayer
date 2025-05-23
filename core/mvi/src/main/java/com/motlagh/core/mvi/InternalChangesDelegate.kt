package com.motlagh.core.mvi

import kotlinx.coroutines.flow.Flow

interface InternalChangesDelegate<PARTIAL_UI_STATE> {
    fun getInternalChanges(): Flow<PARTIAL_UI_STATE>

   suspend fun clearChanges()

    suspend fun setInternalChanges(vararg internalChangesFlows: Flow<PARTIAL_UI_STATE>)
}
