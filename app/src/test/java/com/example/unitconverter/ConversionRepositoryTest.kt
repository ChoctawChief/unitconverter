package com.example.unitconverter

import com.example.unitconverter.data.ConversionRepository
import com.example.unitconverter.data.ConversionRepositoryImpl
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ConversionRepositoryTest {

    private lateinit var repository: ConversionRepository

    @Before
    fun setUp() {
        repository = ConversionRepositoryImpl()
    }

    @Test
    fun testCelsiusToFahrenheit() {
        val result = repository.convert(0.0, "Celsius", "Fahrenheit")
        assertEquals(32.0, result, 0.001)
    }
}
