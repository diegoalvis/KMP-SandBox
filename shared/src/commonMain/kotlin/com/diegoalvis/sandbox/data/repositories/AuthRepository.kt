package com.diegoalvis.sandbox.data.repositories

import com.diegoalvis.sandbox.data.remote.api.AuthService
import com.diegoalvis.sandbox.data.remote.models.LoginResponse
import com.diegoalvis.sandbox.domain.Resource
import com.diegoalvis.sandbox.domain.use_cases.LoginParams
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class AuthRepository(
    private val authService: AuthService
) {

    fun submitLogin(input: LoginParams): Flow<Resource<LoginResponse>> {
        return flow {
            emit(Resource.Loading())
            val result = authService.login(email = input.email, password = input.password)
            emit(Resource.Success(result))
        }.catch {
            // TODO handle errors
            emit(Resource.Failure(it))
        }.flowOn(Dispatchers.Default)

    }
}