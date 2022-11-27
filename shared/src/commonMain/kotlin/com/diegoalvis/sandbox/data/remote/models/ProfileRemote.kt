package com.diegoalvis.sandbox.data.remote.models

import com.diegoalvis.sandbox.domain.entities.Profile

data class ProfileRemote(
    val userId: String,
    val firstName: String,
    val lastName: String?,
    val email: String,
    val globalEntityId: String,
    val realGlobalEntityId: String,
    val isManagedByRooster: Boolean,
    val isRider: Boolean,
    val roosterUrl: String?,
    val profileImageUrl: String?,
    val isPasswordChangeRequired: Boolean,
)

fun ProfileRemote.toProfile() = Profile(
    userId = userId,
    firstName = firstName,
    lastName = lastName,
    email = email,
    globalEntityId = globalEntityId,
    realGlobalEntityId = realGlobalEntityId,
    isManagedByRooster = isManagedByRooster,
    isRider = isRider,
    roosterUrl = roosterUrl,
    profileImageUrl = profileImageUrl,
    isPasswordChangeRequired = isPasswordChangeRequired
)