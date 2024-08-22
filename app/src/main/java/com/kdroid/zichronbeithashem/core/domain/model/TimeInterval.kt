package com.kdroid.zichronbeithashem.core.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class TimeInterval(
    val years: Int,
    val months: Int,
    val days: Int
)
