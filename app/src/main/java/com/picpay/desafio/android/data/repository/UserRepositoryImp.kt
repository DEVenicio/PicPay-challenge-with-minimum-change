package com.picpay.desafio.android.data.repository

import com.picpay.desafio.android.data.mapper.toDomain
import com.picpay.desafio.android.data.services.PicPayService
import com.picpay.desafio.android.domain.model.User
import com.picpay.desafio.android.domain.repositories.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class UserRepositoryImp(private val service: PicPayService) : UserRepository {
    override fun fetchUsers(): Flow<List<User>> = flow {
        val userDtoList  = service.getUsers()
       val users = userDtoList.map { it.toDomain() }
        emit(users)
    }.flowOn(Dispatchers.IO)

}