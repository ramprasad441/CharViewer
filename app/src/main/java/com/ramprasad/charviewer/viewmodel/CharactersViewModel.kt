package com.ramprasad.charviewer.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ramprasad.charviewer.commons.StateOfResponse
import com.ramprasad.charviewer.data.CharactersResponse
import com.ramprasad.charviewer.data.RelatedTopic
import com.ramprasad.charviewer.repository.CharactersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Ramprasad on 7/4/23.
 */
@HiltViewModel
class CharactersViewModel @Inject constructor(private val repo: CharactersRepository): ViewModel() {

    private var _characters = MutableLiveData<StateOfResponse<CharactersResponse>>()
    val characters: LiveData<StateOfResponse<CharactersResponse>> = _characters

    var characterDetails = MutableLiveData<RelatedTopic>()

    var searchQuery: String = ""

    init {
        getData()
    }

    private fun getData() {
        viewModelScope.launch {
            repo.getData().collect {
                _characters.postValue(it)
            }
        }
    }

    fun saveQuery(query: String) {
        searchQuery = query
    }

}