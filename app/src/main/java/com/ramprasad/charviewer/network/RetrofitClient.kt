package com.ramprasad.charviewer.network

import com.ramprasad.charviewer.BuildConfig
import com.ramprasad.charviewer.data.CharactersResponse
import retrofit2.http.GET

/**
 * Created by Ramprasad on 7/4/23.
 */
interface RetrofitClient {

    @GET(BuildConfig.SEARCH_CHARACTER)
    suspend fun getCharacterResponseData(): CharactersResponse
}