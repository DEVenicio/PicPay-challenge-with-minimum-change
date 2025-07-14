package com.picpay.desafio.android.data.usecases

import com.picpay.desafio.android.domain.model.User
import com.picpay.desafio.android.domain.repositories.UserRepository
import com.picpay.desafio.android.domain.usecase.UserUseCase
import kotlinx.coroutines.flow.Flow

class UserUseCaseImpl(private val userRepository: UserRepository) : UserUseCase {
    override fun execute(): Flow<List<User>> {
      return  userRepository.fetchUsers()
    }

}