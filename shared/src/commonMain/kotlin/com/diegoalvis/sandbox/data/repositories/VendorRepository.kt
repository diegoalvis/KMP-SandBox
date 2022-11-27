package com.diegoalvis.sandbox.data.repositories

import com.diegoalvis.sandbox.data.remote.api.AuthService
import com.diegoalvis.sandbox.domain.Resource
import com.diegoalvis.sandbox.domain.entities.Vendor
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class VendorRepository(
    private val authService: AuthService
) {

    suspend fun saveVendor(): Flow<Resource<Unit>> {
        return flow {
            emit(Resource.Loading())
            emit(Resource.Success(Unit))
        }.catch {
            // TODO handle errors
            emit(Resource.Failure(it))
        }
    }
}