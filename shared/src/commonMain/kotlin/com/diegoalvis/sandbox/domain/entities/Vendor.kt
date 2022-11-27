package com.diegoalvis.sandbox.domain.entities

enum class BusinessUnit { DMART, OTHER }

data class Vendor(
    val id: String,
    val name: String,
    val address: String?,
    val latitude: Double?,
    val longitude: Double?,
    val imageUrl: String?,
    val children: List<String>,
    val globalEntityId: String,
    val stores: List<Store>?,
    val config: VendorConfig,
    val warehouseId: String?,
    val businessUnit: BusinessUnit,
) {
    override fun equals(other: Any?) = other != null && other is Vendor && other.id == id

    override fun hashCode(): Int = id.hashCode()

    data class Store(
        val globalEntityId: String,
        val platformVendorId: String
    )

    data class VendorConfig(
        val chatEnabled: Boolean,
        val cycleCountsEnabled: Boolean,
        val maxOrdersInProgress: Int,
        val assignmentFcfsEnabled: Boolean,
    )

}

fun Vendor.isDmart() = businessUnit == BusinessUnit.DMART

fun Vendor.isNotDmart() = !isDmart()

fun Vendor.isSingleStore(): Boolean = stores?.size == 1

fun Vendor.getPlatformVendorIds(): List<String> = stores?.map(Vendor.Store::platformVendorId) ?: listOf()

fun Vendor.isPurchaseOrdersEnabled(country: Country): Boolean {
    val feature = country.config.featurePurchaseOrders ?: return false
    return isDmart() && feature.enabled
}

fun Vendor.isCycleCountsEnabled(country: Country): Boolean {
    val featureEnabled = country.config.featureCycleCount?.enabled ?: false
    return (isDmart() && featureEnabled) || config.cycleCountsEnabled
}

fun Vendor.isProductLocationEnabled(country: Country): Boolean {
    val feature = country.config.featureProductLocation ?: return false
    return isDmart() && feature.enabled
}

fun Vendor.isStoreTransferEnabled(country: Country): Boolean {
    val feature = country.config.featureStoreTransfer ?: return false
    return isDmart() && feature.enabled
}

fun Vendor.isProductAvailabilityEnabled(country: Country, isPickerRider: Boolean): Boolean {
    val feature = country.config.featureProductAvailability ?: return false
    return isNotDmart() && isSingleStore() && feature.enabled && !isPickerRider
}

fun Vendor.isVendorAvailabilityEnabled(country: Country, isPickerRider: Boolean): Boolean {
    val feature = country.config.featureVendorAvailability ?: return false
    return isNotDmart() && isSingleStore() && feature.enabled && !isPickerRider
}

fun Vendor.isPutawayEnabled(country: Country): Boolean {
    val feature = country.config.featurePutaway ?: return false
    return isDmart() && feature.enabled
}

fun Vendor.getSingleStorePlatformId(): String? = when {
    isSingleStore() -> stores?.first()?.platformVendorId
    else -> null
}
