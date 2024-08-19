package com.kdroid.zichronbeithashem.core.domain.services

import com.kosherjava.zmanim.hebrewcalendar.JewishDate

interface CurrentDateProvider {
    fun getCurrentJewishDate(): JewishDate

    fun getSecondTempleDestructionDate(): JewishDate

}

