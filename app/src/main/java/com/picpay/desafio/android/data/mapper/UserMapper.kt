package com.picpay.desafio.android.data.mapper

import com.picpay.desafio.android.data.model.UserDTO
import com.picpay.desafio.android.domain.model.User

fun UserDTO.toDomain() : User {
    return User(
        id = this.id,
        name = this.name,
        image = this.img,
        userName = this.username
    )
}