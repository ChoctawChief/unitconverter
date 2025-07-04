package com.example.unitconverter.data

interface ConversionRepository {
    fun convert(value: Double, fromUnit: String, toUnit: String): Double
}
