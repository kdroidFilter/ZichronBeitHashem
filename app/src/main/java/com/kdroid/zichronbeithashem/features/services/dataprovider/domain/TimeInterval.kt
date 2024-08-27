package com.kdroid.zichronbeithashem.features.services.dataprovider.domain

import kotlinx.serialization.Serializable

@Serializable
data class TimeInterval(
    val years: Int,
    val months: Int,
    val days: Int,
    val hours : Int = 0,
    val minutes : Int = 0,
    val secondes : Int = 0
)
