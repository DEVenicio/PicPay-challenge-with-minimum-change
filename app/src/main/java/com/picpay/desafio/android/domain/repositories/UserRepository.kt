package com.picpay.desafio.android.domain.repositories

import com.picpay.desafio.android.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {

   fun fetchUsers() : Flow<List<User>>
}


