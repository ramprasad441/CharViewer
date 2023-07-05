package com.ramprasad.charviewer.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.ramprasad.charviewer.commons.StateOfResponse
import com.ramprasad.charviewer.commons.mockCharacter
import com.ramprasad.charviewer.data.CharactersResponse
import com.ramprasad.charviewer.network.RetrofitClient
import com.ramprasad.charviewer.repository.CharactersRepositoryImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

/**
 * Created by Ramprasad on 7/5/23.
 */
internal class CharactersViewModelTest {

    private lateinit var viewModel: CharactersViewModel
    private val testCharacters = MutableLiveData<StateOfResponse<CharactersResponse>>()
    @OptIn(ExperimentalCoroutinesApi::class)
    private val testDispatcher = UnconfinedTestDispatcher()

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineRule = CoroutineRule()

    @Captor
    private lateinit var captor: ArgumentCaptor<StateOfResponse<CharactersResponse>>

    @Mock
    private lateinit var observer: Observer<StateOfResponse<CharactersResponse>>
    @Mock
    private lateinit var apiService: RetrofitClient
    @Mock
    private lateinit var repo: CharactersRepositoryImpl


    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `Testing viewmodel success response`() = runTest(testDispatcher) {
        Mockito.`when`(apiService.getCharacterResponseData()).thenReturn(mockCharacter)
        repo = CharactersRepositoryImpl(apiService)
        viewModel = CharactersViewModel(repo)
        viewModel.characters.observeForever(observer)
        Mockito.verify(observer, Mockito.times(1)).onChanged(MockitoHelper.capture(captor))
        val values = captor.allValues
        testCharacters.value = StateOfResponse.Success(mockCharacter)
        Assert.assertEquals(testCharacters.value, values.get(0))
    }



}

object MockitoHelper {
    fun <T> capture(argumentCaptor: ArgumentCaptor<T>): T = argumentCaptor.capture()
}