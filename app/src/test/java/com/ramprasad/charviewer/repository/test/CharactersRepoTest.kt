package com.ramprasad.charviewer.repository.test

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.ramprasad.charviewer.commons.MOCK_DATA
import com.ramprasad.charviewer.commons.MOCK_JSON
import com.ramprasad.charviewer.commons.StateOfResponse
import com.ramprasad.charviewer.commons.mockCharacter
import com.ramprasad.charviewer.data.CharactersResponse
import com.ramprasad.charviewer.network.RetrofitClient
import com.ramprasad.charviewer.repository.CharactersRepositoryImpl
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Ramprasad on 7/5/23.
 */
class CharactersRepoTest {

    private val response = MutableLiveData<StateOfResponse<CharactersResponse>>()
    private val mockWebServer = MockWebServer()

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var retrofit: Retrofit
    @Mock
    private lateinit var gson: Gson

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        mockWebServer.start(8080)
        gson = Gson()
        retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(mockWebServer.url("http://localhost:8080").toString())
            .build()
    }

    @Test
    fun testNetworkCallWithSuccessWithMockResponse() = runTest {

        mockWebServer.enqueue(MockResponse().setBody("$MOCK_JSON").setResponseCode(200))
        val service = retrofit.create(RetrofitClient::class.java)
        val result = service.getCharacterResponseData()
        Assert.assertEquals(mockCharacter, result)
    }

    @Test
    fun testNetworkCallWithFailureWithMockResponse() = runTest {
        val service = retrofit.create(RetrofitClient::class.java)
        mockWebServer.enqueue(MockResponse().setResponseCode(404))
        try {
            val result = service.getCharacterResponseData()
            assert(false)
        } catch (e: Exception) {
            assert(true)
        }
    }

    @Test
    fun testRepositorySuccessResponse() = runTest {
        mockWebServer.enqueue(MockResponse().setBody(MOCK_DATA).setResponseCode(200))
        val expectedResponse = MutableLiveData<StateOfResponse<CharactersResponse>>()
        expectedResponse.value = StateOfResponse.Success(mockCharacter)
        val service = retrofit.create(RetrofitClient::class.java)
        val repo = CharactersRepositoryImpl(service)
        repo.getData().collect {
            response.value = it
        }
        Assert.assertEquals(expectedResponse.value, response.value)
    }

    @Test
    fun testRepositoryErrorResponse() = runTest {
        mockWebServer.enqueue(MockResponse().setResponseCode(500))
        val expectedResponse = MutableLiveData<StateOfResponse<CharactersResponse>>()
        expectedResponse.value = StateOfResponse.Error(msg = "HTTP 500 Server Error")
        val service = retrofit.create(RetrofitClient::class.java)
        val repo = CharactersRepositoryImpl(service)
        repo.getData().collect {
            response.value = it
        }
        Assert.assertEquals(expectedResponse.value, response.value)
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }
}