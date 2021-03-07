package com.webianks.nasapicturesapp.utils

import androidx.annotation.StringRes

sealed class UiState

data class Success<T>(val data: T) : UiState()
data class Error(@StringRes val resId: Int) : UiState()
object Loading : UiState()
