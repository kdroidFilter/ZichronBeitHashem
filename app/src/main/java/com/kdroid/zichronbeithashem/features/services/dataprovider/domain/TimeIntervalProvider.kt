package com.kdroid.zichronbeithashem.features.services.dataprovider.domain

import com.kosherjava.zmanim.hebrewcalendar.JewishDate
import java.util.Date
import java.util.concurrent.TimeUnit

/**
 * Interface for providing date-related functionalities.
 */

interface TimeIntervalProvider {
    fun getCurrentJewishDate(): JewishDate

    fun getSecondTempleDestructionDate(): JewishDate

    fun getTodayJerusalemSunset(): Date

    fun getYesterdayJerusalemSunset(): Date

    fun getCurrentTimeMillis() : Long

    fun calculateDaysSinceTempleDestruction(): Long {
        val diffInMillis =
            getCurrentJewishDate().gregorianCalendar.timeInMillis - getSecondTempleDestructionDate().gregorianCalendar.timeInMillis
        return TimeUnit.MILLISECONDS.toDays(diffInMillis)
    }

    /**
     * Converts the number of days since the destruction of the Second Temple into Hebrew years, months, and days.
     *
     * @return A [TimeInterval] representing the difference in years, months, and days.
     */
    fun calculateTimeIntervalSinceTempleDestruction(): TimeInterval {
        val currentDate = getCurrentJewishDate()
        val templeDestructionDate = getSecondTempleDestructionDate()

        var years = currentDate.jewishYear - templeDestructionDate.jewishYear //  Calculate the difference in years between the current date and the temple Destruction date
        var months = currentDate.jewishMonth - templeDestructionDate.jewishMonth  // Calculate the difference in months between the current date and the temple Destruction date
        var daysOfMonth = currentDate.jewishDayOfMonth - templeDestructionDate.jewishDayOfMonth // Calculate the difference in days of the month between the current date and the temple Destruction date

        val isLastDayOfMonth = currentDate.daysInJewishMonth == currentDate.jewishDayOfMonth

        val isSunsetPassed = getTodayJerusalemSunset().time < getCurrentTimeMillis()

        if (isSunsetPassed) {
            if (isLastDayOfMonth) {
                // If it's the last day of the month and sunset has passed, move to the next month
                months += 1
                daysOfMonth = 1
            } else {
                // If sunset has passed but it's not the last day of the month, increment the day
                daysOfMonth += 1
            }
        }

        // Adjust days if the current day is before the start day
        if (daysOfMonth < 0) {
            // Move to the previous month and adjust days accordingly
            months -= 1
            val tempDate = JewishDate(currentDate.gregorianCalendar.time)
            tempDate.jewishMonth -= 1
            daysOfMonth += tempDate.daysInJewishMonth
        }

        // Adjust months if the current month is before the start month
        if (months < 0) {
            // Move to the previous year and adjust months accordingly
            years -= 1
            months += 12
        }

        /* Calculates the time difference between the current time and sunset
         If the current time is after today's sunset, calculates the difference with today's sunset.
         Otherwise, calculates the difference with yesterday's sunset. */
        val todaySunsetTime = getTodayJerusalemSunset().time
        val yesterdaySunsetTime = getYesterdayJerusalemSunset().time
        val currentTime = getCurrentTimeMillis()

        // Determines whether the difference should be calculated relative to today's or yesterday's sunset
        val diffInMillis = if (currentTime > todaySunsetTime) currentTime - todaySunsetTime else currentTime - yesterdaySunsetTime

        // Convert the difference to hours, minutes and seconds
        val hours = TimeUnit.MILLISECONDS.toHours(diffInMillis).toInt()
        val minutes = (TimeUnit.MILLISECONDS.toMinutes(diffInMillis) % 60).toInt()
        val seconds = (TimeUnit.MILLISECONDS.toSeconds(diffInMillis) % 60).toInt()

        return TimeInterval(years, months, daysOfMonth, hours, minutes, seconds)
    }
}

