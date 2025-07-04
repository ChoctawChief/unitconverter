package com.example.unitconverter

import com.example.unitconverter.data.ConversionRepository
import com.example.unitconverter.data.ConversionRepositoryImpl
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Before
import org.junit.Test

class ConversionRepositoryImplTest {

    private lateinit var repository: ConversionRepository

    @Before
    fun setUp() {
        repository = ConversionRepositoryImpl()
    }

    @Test
    fun `convert meters to feet`() {
        val result = repository.convert(1.0, "Meters", "Feet")
        assertEquals(3.28084, result, 0.0001)
    }

    @Test
    fun `convert feet to meters`() {
        val result = repository.convert(3.28084, "Feet", "Meters")
        assertEquals(1.0, result, 0.0001)
    }

    @Test
    fun `convert celsius to fahrenheit`() {
        val result = repository.convert(0.0, "Celsius", "Fahrenheit")
        assertEquals(32.0, result, 0.0001)
    }

    @Test
    fun `convert fahrenheit to celsius`() {
        val result = repository.convert(32.0, "Fahrenheit", "Celsius")
        assertEquals(0.0, result, 0.0001)
    }

    @Test
    fun `convert with unsupported unit returns input unchanged`() {
        val result = repository.convert(5.0, "Inches", "Meters")
        assertEquals(5.0, result, 0.0001)
    }
}
