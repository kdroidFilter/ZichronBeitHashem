package com.kdroid.zichronbeithashem.core.data

import com.kdroid.zichronbeithashem.core.domain.services.CurrentDateProvider
import com.kosherjava.zmanim.hebrewcalendar.JewishDate

class DefaultCurrentDateProvider : CurrentDateProvider {
    override fun getCurrentJewishDate(): JewishDate {
        return JewishDate()
    }

    override fun getSecondTempleDestructionDate(): JewishDate {
        return JewishDate(3830, JewishDate.AV, 9)
    }


}