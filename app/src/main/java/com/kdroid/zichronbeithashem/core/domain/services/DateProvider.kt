package com.kdroid.zichronbeithashem.core.domain.services

import com.kdroid.zichronbeithashem.core.domain.model.TimeInterval
import com.kosherjava.zmanim.hebrewcalendar.JewishDate
import java.util.Date
import java.util.concurrent.TimeUnit

interface DateProvider {
    fun getCurrentJewishDate(): JewishDate

    fun getSecondTempleDestructionDate(): JewishDate

    fun getTodayJerusalemSunset(): Date

    fun getCurrentTimeMillis() : Long

    fun calculateDaysSinceTempleDestruction(): Long {
        val diffInMillis =
            getCurrentJewishDate().gregorianCalendar.timeInMillis - getSecondTempleDestructionDate().gregorianCalendar.timeInMillis
        return TimeUnit.MILLISECONDS.toDays(diffInMillis)
    }

    fun convertDaysToHebrewYearsMonthsDays(): TimeInterval {
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

        return TimeInterval(years, months, daysOfMonth)
    }
}

