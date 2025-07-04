package com.example.unitconverter.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.example.unitconverter.data.ConversionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ConverterViewModel @Inject constructor(
    private val repository: ConversionRepository
) : ViewModel() {

    var inputValue by mutableStateOf("")
    var fromUnit by mutableStateOf("Celsius")
    var toUnit by mutableStateOf("Fahrenheit")
    var result by mutableStateOf<String?>(null)

    fun performConversion() {
        val value = inputValue.toDoubleOrNull()
        result = if (value != null) {
            val converted = repository.convert(value, fromUnit, toUnit)
            "Result: %.2f".format(converted)
        } else {
            null
        }
    }
}
