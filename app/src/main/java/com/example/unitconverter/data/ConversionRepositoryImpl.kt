package com.example.unitconverter.data

class ConversionRepositoryImpl : ConversionRepository {
    override fun convert(value: Double, fromUnit: String, toUnit: String): Double {
        return when {
            fromUnit == "Celsius" && toUnit == "Fahrenheit" -> (value * 9 / 5) + 32
            fromUnit == "Fahrenheit" && toUnit == "Celsius" -> (value - 32) * 5 / 9
            fromUnit == "Meters" && toUnit == "Feet" -> value * 3.28084
            fromUnit == "Feet" && toUnit == "Meters" -> value / 3.28084
            else -> value
        }
    }
}
