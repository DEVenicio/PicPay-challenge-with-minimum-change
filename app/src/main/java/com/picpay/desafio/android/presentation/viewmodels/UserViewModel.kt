package com.picpay.desafio.android.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.data.mapper.toUserMessageError
import com.picpay.desafio.android.domain.model.User
import com.picpay.desafio.android.domain.usecase.UserUseCase
import com.picpay.desafio.android.presentation.ui.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class UserViewModel(
    private val userUseCase: UserUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<UiState<List<User>>>(UiState.Loading())
     val state = _state.asStateFlow()

    init {
        getUsers()
    }

     fun getUsers() {
        viewModelScope.launch {
            userUseCase.execute()
                .onStart { _state.value = UiState.Loading() }
                .catch { e -> _state.value = UiState.Error(e.toUserMessageError()) }
                .collect { users -> _state.value = UiState.Success(users) }
        }
    }


}