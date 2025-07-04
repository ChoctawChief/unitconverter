package com.example.unitconverter.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.unitconverter.viewmodel.ConverterViewModel
import androidx.compose.ui.platform.testTag

@Composable
fun ConverterScreen(
    modifier: Modifier = Modifier,
    viewModel: ConverterViewModel = hiltViewModel()
) {
    val unitOptions = listOf("Celsius", "Fahrenheit", "Meters", "Feet")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = viewModel.inputValue,
            onValueChange = { viewModel.inputValue = it },
            label = { Text("Enter Value") },
            modifier = Modifier.testTag("InputField")
        )

        UnitDropdown(
            label = "From Unit",
            selectedUnit = viewModel.fromUnit,
            onUnitSelected = { viewModel.fromUnit = it },
            options = unitOptions,
            testTag = "FromUnitDropdown"
        )

        UnitDropdown(
            label = "To Unit",
            selectedUnit = viewModel.toUnit,
            onUnitSelected = { viewModel.toUnit = it },
            options = unitOptions,
            testTag = "ToUnitDropdown"
        )

        Button(
            onClick = { viewModel.performConversion() },
            modifier = Modifier.testTag("ConvertButton")
        ) {
            Text("Convert")
        }

        viewModel.result?.let {
            Text(
                it,
                modifier = Modifier.testTag("ResultText")
            )
        }
    }

}

@Composable
fun UnitDropdown(
    label: String,
    selectedUnit: String,
    onUnitSelected: (String) -> Unit,
    options: List<String>,
    testTag: String
) {
    var expanded by remember { mutableStateOf(false) }

    Column {
        Text(text = label)
        Box {
            OutlinedButton(
                onClick = { expanded = true },
                modifier = Modifier.testTag(testTag)
            ) {
                Text(selectedUnit)
            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                options.forEach { unit ->
                    DropdownMenuItem(
                        text = { Text(unit) },
                        onClick = {
                            onUnitSelected(unit)
                            expanded = false
                        },
                        modifier = Modifier.testTag("$testTag-$unit")
                    )
                }
            }
        }
    }
}