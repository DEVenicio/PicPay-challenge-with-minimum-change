package com.picpay.desafio.android.presentation.ui

sealed class UiState<out T> {

    class Loading() : UiState<Nothing>()
    class Success<out T>(val data: T) : UiState<T>()
    class Error<out T>(val message: String) : UiState<T>()
}