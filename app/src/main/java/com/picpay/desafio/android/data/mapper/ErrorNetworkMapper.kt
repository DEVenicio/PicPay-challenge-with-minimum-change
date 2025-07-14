package com.picpay.desafio.android.data.mapper

import okio.IOException
import retrofit2.HttpException
import java.net.SocketTimeoutException

fun Throwable.toUserMessageError(): String {
    return when (this) {
        is SocketTimeoutException -> "Tempo de resposta esgotado."
        is IOException -> "Sem conexão com a internet. Verifique sua rede."
        is HttpException -> { this.httpMessage()}
        else -> "Erro inesperado: ${this.message ?: "Sem detalhes"}"
    }
}


fun HttpException.httpMessage(): String = when (this.code()) {
    401 -> "Sessão expirada."
    403 -> "Acesso negado."
    404 -> "Recurso não encontrado."
    in 500..599 -> "Erro no servidor."
    else -> "Erro HTTP: ${this.code()}"
}

