package com.diegoalvis.sandbox.domain.entities

import com.diegoalvis.sandbox.base.toEnumOrNull

data class CountryRemote(
    val name: String = "",
    val regionCode: String = "",
    val countryCode: String = "",
    val config: CountryConfig = CountryConfig(),
)

data class CountryLocal(
    val name: String,
    val regionCode: String,
    val countryCode: String = "",
    val config: CountryConfig = CountryConfig(),
)

fun CountryRemote.toCountry() = Country(
    name = name,
    regionCode = regionCode,
    countryCode = countryCode.toEnumOrNull<CountryCode>() ?: CountryCode.UNKNOWN,
    config = config,
)

fun CountryLocal.toCountry() = Country(
    name = name,
    regionCode = regionCode,
    countryCode = countryCode.toEnumOrNull<CountryCode>() ?: CountryCode.UNKNOWN,
    config = config,
)

data class Country(
    val name: String,
    val regionCode: String,
    val countryCode: CountryCode,
    val config: CountryConfig = CountryConfig(),
)

data class CountryConfig(
    val experimentalFeatureReplacement: Feature? = null,
    val featureOrderFulfilment: Feature? = null,
    val featureStoreTransfer: Feature? = null,
    val featureProductAvailability: Feature? = null,
    val featureVendorAvailability: Feature? = null,
    val featureProductLocation: Feature? = null,
    val featurePurchaseOrders: Feature? = null,
    val featureIncomingOrderService: Feature? = null,
    val featureKillSwitch: Feature? = null,
    val featurePutaway: Feature? = null,
    val featurePickerAuthentication: Feature? = null,
    val ignoreBatteryOptimization: Feature? = null,
    val showCustomerPhoneNumber: Feature? = null,
    val featureCycleCount: Feature? = null,
)

data class Feature(
    val url: String = "",
    val enabled: Boolean = false,
    val extras: Map<String, Any?> = mapOf()
)

fun Country.getOrderFulfilmentFcmSuffix(): String? = config.featureOrderFulfilment?.extras?.get("fcmSuffix") as? String

fun Country.getUrlOperationsPortal(globalEntityId: String?): String? {
    val frontendUrlsMap = config.featureOrderFulfilment?.extras?.get("urlOperationsPortal") as? Map<String, String>
    val entityId = globalEntityId?.split("_")?.get(0)
    return frontendUrlsMap?.get(entityId)
}

fun Country.getOrderNotificationServiceIntervalInMinutes(): Double? =
    config.featureIncomingOrderService?.extras?.get("intervalInMinutes")?.toString()?.toDoubleOrNull()

fun Country.isStoreTransferMultipleReceivingConfigured(): Boolean {
    return config.featureStoreTransfer?.extras?.get("isMultipleReceivingConfigured") as? Boolean ?: false
}

fun Country.isStoreTransferMultipleReceivingNotConfigured(): Boolean = !isStoreTransferMultipleReceivingConfigured()

fun Country.isPurchaseOrderMultipleReceivingConfigured(): Boolean {
    return config.featurePurchaseOrders?.extras?.get("isMultipleReceivingConfigured") as? Boolean ?: false
}

fun Country.isPurchaseOrderMultipleReceivingNotConfigured(): Boolean = !isPurchaseOrderMultipleReceivingConfigured()

fun Country.isOrderPollingServiceEnabled() = config.featureIncomingOrderService?.enabled == true

fun Country.isIgnoreBatteryOptimizationEnabled(): Boolean = config.ignoreBatteryOptimization?.enabled == true

fun Country.isPutawayPartialCompletionConfigured(): Boolean =
    config.featurePutaway?.extras?.get("isPartialCompletionConfigured") as? Boolean ?: false


