package com.kdroid.zichronbeithashem.core.domain.services

import com.kosherjava.zmanim.hebrewcalendar.JewishDate

interface DateProvider {
    fun getCurrentJewishDate(): JewishDate

    fun getSecondTempleDestructionDate(): JewishDate

}

