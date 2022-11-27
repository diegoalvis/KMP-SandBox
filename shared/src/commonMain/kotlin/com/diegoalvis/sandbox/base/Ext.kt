package com.diegoalvis.sandbox.base


fun <T> List<T>.toCommaSeparated() = joinToString(",")

fun <T> toSlashSeparated(vararg value: T) = value.toList().joinToString("/")

fun <T> Collection<T>.isEqualToWithoutOrder(list: Collection<T>): Boolean = when {
    this.size != list.size -> false
    else -> this.containsAll(list) && list.containsAll(this)
}

fun String.wrapInQuotes(): String = "\"$this\""

inline fun <T1 : Any, T2 : Any, R : Any> notNull(p1: T1?, p2: T2?, block: (T1, T2) -> R?): R? {
    return if (p1 != null && p2 != null) block(p1, p2) else null
}

inline fun <reified T : Enum<T>> enumContains(name: String): Boolean = enumValues<T>().any { it.name == name }

inline fun <reified T : Enum<T>> String.toEnumOrNull(): T? = enumValues<T>().find { it.name.equals(this, true) }

inline fun <reified T : Enum<T>> String.toEnumOrDefault(default: T): T = enumValues<T>().find { it.name.equals(this, true) } ?: default

inline fun <reified T : Enum<T>> enumNames(): List<String> = enumValues<T>().map { it.name }