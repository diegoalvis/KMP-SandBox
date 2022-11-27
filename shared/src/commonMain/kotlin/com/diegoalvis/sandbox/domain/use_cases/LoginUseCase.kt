package com.diegoalvis.sandbox.domain.use_cases

import com.diegoalvis.sandbox.data.remote.models.toProfile
import com.diegoalvis.sandbox.data.repositories.AuthRepository
import com.diegoalvis.sandbox.data.repositories.VendorRepository
import com.diegoalvis.sandbox.domain.Resource
import com.diegoalvis.sandbox.domain.entities.Profile
import kotlinx.coroutines.flow.*

class LoginUseCase(
    private val authRepository: AuthRepository,
    private val vendorRepository: VendorRepository,
) : BaseUseCase<LoginParams, Profile>() {

    override fun execute(input: LoginParams): Flow<Resource<Profile>> {
        return authRepository.submitLogin(input)
            .mapLatest {
                if (it is Resource.Success) {
                    vendorRepository.saveVendor()
                }
                when (it) {
                    is Resource.Failure -> it
                    is Resource.Loading -> it
                    is Resource.Success -> Resource.Success(it.data.profileRemote.toProfile())
                }
            }
    }
}


data class LoginParams(
    val email: String,
    val password: String,
)