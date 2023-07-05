package com.ramprasad.charviewer.commons

/**
 * Created by Ramprasad on 7/3/23.
 */
sealed class StateOfResponse<T> {
    data class Loading<T>(val isLoading: Boolean) : StateOfResponse<T>()
    data class Success<T>(val data: T) : StateOfResponse<T>()
    data class Error<T>(val msg: String) : StateOfResponse<T>()
}
