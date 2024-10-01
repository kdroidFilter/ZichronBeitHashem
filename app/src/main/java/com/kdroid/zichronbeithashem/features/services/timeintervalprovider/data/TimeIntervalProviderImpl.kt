package com.kdroid.zichronbeithashem.features.services.timeintervalprovider.data

import com.kdroid.zichronbeithashem.features.services.timeintervalprovider.domain.TimeIntervalProvider
import com.kosherjava.zmanim.ComplexZmanimCalendar
import com.kosherjava.zmanim.hebrewcalendar.JewishDate
import com.kosherjava.zmanim.util.GeoLocation
import java.util.Calendar
import java.util.Date
import java.util.TimeZone

class TimeIntervalProviderImpl : TimeIntervalProvider {
    override fun getCurrentJewishDate(): JewishDate {
        return JewishDate()
    }

    override fun getSecondTempleDestructionDate(): JewishDate {
        return JewishDate(3830, JewishDate.AV, 9)
    }

     fun getJerusalemSunset(dayOffset: Int): Date {

        // Jerusalem coordinates
        val latitude = 31.7683
        val longitude = 35.2137
        val elevation = 800.0 // in meters, approximate

        val timeZone = TimeZone.getTimeZone("Asia/Jerusalem")

        val jerusalem = GeoLocation("Jerusalem", latitude, longitude, elevation, timeZone)

        val calendar = ComplexZmanimCalendar(jerusalem)

        // Apply the day offset
        calendar.calendar.add(Calendar.DAY_OF_YEAR, dayOffset)

        return calendar.sunset
    }

   override fun getTodayJerusalemSunset(): Date {
        return getJerusalemSunset(0)
    }

    override fun getYesterdayJerusalemSunset(): Date {
        return getJerusalemSunset(-1)
    }

    override fun getCurrentTimeMillis(): Long {
        return System.currentTimeMillis()
    }

}

fun main() {
   println( TimeIntervalProviderImpl().getJerusalemSunset(0).time)
    println(TimeIntervalProviderImpl().getYesterdayJerusalemSunset().time)
}