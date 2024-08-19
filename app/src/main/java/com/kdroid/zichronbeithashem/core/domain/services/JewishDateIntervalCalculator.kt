package com.kdroid.zichronbeithashem.core.domain.services

import com.kdroid.zichronbeithashem.core.domain.model.TimeInterval
import com.kosherjava.zmanim.hebrewcalendar.JewishDate
import java.util.concurrent.TimeUnit

class JewishDateIntervalCalculator(private val currentDateProvider: CurrentDateProvider) {

    fun calculateDaysSinceTempleDestruction(): Long {
        val startDate = currentDateProvider.getSecondTempleDestructionDate()
        val currentDate = currentDateProvider.getCurrentJewishDate()

        val diffInMillis = currentDate.gregorianCalendar.timeInMillis - startDate.gregorianCalendar.timeInMillis
        return TimeUnit.MILLISECONDS.toDays(diffInMillis)
    }

    fun convertDaysToHebrewYearsMonthsDays(): TimeInterval {
        val startDate = currentDateProvider.getSecondTempleDestructionDate()
        val currentDate = currentDateProvider.getCurrentJewishDate()

        var years = currentDate.jewishYear - startDate.jewishYear
        var months = currentDate.jewishMonth - startDate.jewishMonth
        var daysOfMonth = currentDate.jewishDayOfMonth - startDate.jewishDayOfMonth

        // Ajustement des jours
        if (daysOfMonth < 0) {
            months -= 1
            val tempDate = JewishDate(currentDate.gregorianCalendar.time)
            tempDate.jewishMonth -= 1
            daysOfMonth += tempDate.daysInJewishMonth
        }

        // Ajustement des mois
        if (months < 0) {
            years -= 1
            months += 12
        }

        return TimeInterval(years, months, daysOfMonth)
    }
}