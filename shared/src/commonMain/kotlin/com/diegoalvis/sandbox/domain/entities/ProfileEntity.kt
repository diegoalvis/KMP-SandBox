package com.diegoalvis.sandbox.domain.entities

data class Profile(
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

fun Profile.getFullName(): String = when {
    lastName.isNullOrEmpty() -> firstName
    else -> "$firstName $lastName"
}

fun Profile.getInterPlatformId(): String = "$globalEntityId-$userId"

fun Profile.getRealInterPlatformId(): String = "$realGlobalEntityId-$userId"
