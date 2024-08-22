package com.kdroid.zichronbeithashem.core.data

import com.kdroid.zichronbeithashem.core.domain.services.DateProvider
import com.kosherjava.zmanim.ComplexZmanimCalendar
import com.kosherjava.zmanim.hebrewcalendar.JewishDate
import com.kosherjava.zmanim.util.GeoLocation
import java.util.Date
import java.util.TimeZone

class DateProviderImpl : DateProvider {
    override fun getCurrentJewishDate(): JewishDate {
        return JewishDate()
    }

    override fun getSecondTempleDestructionDate(): JewishDate {
        return JewishDate(3830, JewishDate.AV, 9)
    }

    override fun getTodayJerusalemSunset(): Date {

            // Jerusalem coordinates
            val latitude = 31.7683
            val longitude = 35.2137
            val elevation = 800.0 // in meters, approximate

            val timeZone = TimeZone.getTimeZone("Asia/Jerusalem")

            val jerusalem = GeoLocation("Jerusalem", latitude, longitude, elevation, timeZone)

            val calendar = ComplexZmanimCalendar(jerusalem)

            return calendar.sunset

        }

    override fun getCurrentTimeMillis(): Long {
        return System.currentTimeMillis()
    }

}