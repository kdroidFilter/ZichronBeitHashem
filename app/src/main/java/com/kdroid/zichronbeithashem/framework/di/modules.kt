package com.kdroid.zichronbeithashem.framework.di

import com.kdroid.zichronbeithashem.core.data.DateProviderImpl
import com.kdroid.zichronbeithashem.core.domain.services.DateProvider
import com.kdroid.zichronbeithashem.core.domain.services.JewishDateIntervalCalculator
import org.koin.dsl.module

val appModule = module {
    single<DateProvider> { DateProviderImpl() }
    single { JewishDateIntervalCalculator(dateProvider = get()) }
}