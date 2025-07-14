package com.picpay.desafio.android.domain.usecase

import com.picpay.desafio.android.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserUseCase {

    fun execute() : Flow<List<User>>
}