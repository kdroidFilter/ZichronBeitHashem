package com.kdroid.zichronbeithashem

import com.kdroid.zichronbeithashem.features.services.timeintervalprovider.data.TimeIntervalProviderImpl
import com.kosherjava.zmanim.hebrewcalendar.JewishDate
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.spy

class TimeIntervalProviderTest {

    @Test
    fun testCalculateDaysSinceTempleDestruction() {
        // Arrange
        val dateProvider = spy(TimeIntervalProviderImpl())

        // Mock the current Jewish date
        val mockCurrentDate = JewishDate(5784, JewishDate.AV, 9)
        doReturn(mockCurrentDate).`when`(dateProvider).getCurrentJewishDate()

        // Act
        val daysSinceDestruction = dateProvider.calculateDaysSinceTempleDestruction()

        // Assert
        val expectedDays = 713696
        assertEquals(expectedDays, daysSinceDestruction.toInt())
    }


}