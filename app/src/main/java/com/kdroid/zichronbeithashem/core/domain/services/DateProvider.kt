package com.kdroid.zichronbeithashem.core.domain.services

import com.kosherjava.zmanim.ZmanimCalendar
import com.kosherjava.zmanim.hebrewcalendar.JewishDate

interface DateProvider {
    fun getCurrentJewishDate(): JewishDate

    fun getSecondTempleDestructionDate(): JewishDate

    fun adjustDateForSunset(jewishDate: JewishDate, zmanimCalendar: ZmanimCalendar): JewishDate


}

