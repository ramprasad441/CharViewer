package com.ramprasad.charviewer.repository

import com.ramprasad.charviewer.commons.StateOfResponse
import com.ramprasad.charviewer.data.CharactersResponse
import kotlinx.coroutines.flow.Flow

/**
 * Created by Ramprasad on 7/4/23.
 */
interface CharactersRepository {
    suspend fun getData(): Flow<StateOfResponse<CharactersResponse>>
}