package com.diegoalvis.sandbox.data.remote.api

import com.diegoalvis.sandbox.data.remote.models.LoginResponse

interface AuthService {

    suspend fun login(email: String, password: String): LoginResponse
}