package com.ramprasad.charviewer.repository

import com.ramprasad.charviewer.commons.StateOfResponse
import com.ramprasad.charviewer.network.RetrofitClient
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Created by Ramprasad on 7/5/23.
 */
class CharactersRepositoryImpl @Inject constructor(
    private val retrofitClient: RetrofitClient
) : CharactersRepository {
    override suspend fun getData() = flow {
        emit(StateOfResponse.Loading(true))
        val response = retrofitClient.getCharacterResponseData()
        emit(StateOfResponse.Success(response))
    }.catch { e ->
        emit(StateOfResponse.Error(e.message ?: "Unknown Error"))
    }
}