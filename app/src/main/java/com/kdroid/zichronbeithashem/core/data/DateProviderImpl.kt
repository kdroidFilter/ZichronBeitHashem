package com.kdroid.zichronbeithashem.core.data

import com.kdroid.zichronbeithashem.core.domain.services.DateProvider
import com.kosherjava.zmanim.hebrewcalendar.JewishDate

class DateProviderImpl : DateProvider {
    override fun getCurrentJewishDate(): JewishDate {
        return JewishDate()
    }

    override fun getSecondTempleDestructionDate(): JewishDate {
        return JewishDate(3830, JewishDate.AV, 9)
    }

}