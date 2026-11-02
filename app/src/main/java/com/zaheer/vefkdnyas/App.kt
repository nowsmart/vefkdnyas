package com.zaheer.vefkdnyas

import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.style.TextAlign
import com.zaheer.vefkdnyas.ui.components.CreateNaqoosh
import com.zaheer.vefkdnyas.ui.components.CustomOutlinedTextField
import com.zaheer.vefkdnyas.ui.components.calculateResult
import androidx.core.content.edit

//private val alphabetRegex = "^($ALPHABET)+$".toRegex()
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App(modifier: Modifier = Modifier) {

    val context = LocalContext.current
    val sharedPref = remember {
        context.getSharedPreferences("vefkdnyasPrefs", Context.MODE_PRIVATE)
    }
    val dropdownOptions =
        listOf("مُثَلَّث (Üçgen) 3×3", "مُرَبَّع (Kare) 4×4", "مُخَمَّس (Altıgen) 5×5") //١٢٣٤٥٦٧٨٩٠
    var expanded by remember { mutableStateOf(false) }
    var selectedVefk by remember {
        mutableStateOf(
            sharedPref.getInt(
                "saved_Vefk_type",
                0
            )
        )
    } // 0 means Item 1 is selected by default
    val requiredFieldCount = selectedVefk + 3
    val ebaraat = remember {
        mutableStateListOf<String>().apply {
            for (i in 0 until requiredFieldCount) {
                add(sharedPref.getString("ebaraat_$i", "") ?: "")
            }
        }
    }
    val labels = listOf(
        "Metin kutusu bir",
        "Metin kutusu iki",
        "Metin kutusu üç",
        "Metin kutusu dört",
        "Metin kutusu beş"
    )

    LaunchedEffect(requiredFieldCount) {
        while (ebaraat.size < requiredFieldCount) {
            val index = ebaraat.size
            val savedValue = sharedPref.getString("ebaraat_$index", "") ?: ""
            ebaraat.add(savedValue)
        }
        while (ebaraat.size > requiredFieldCount) {
            ebaraat.removeAt(ebaraat.size - 1)
        }
    }
    val keyboardController = LocalSoftwareKeyboardController.current
    var showNaqash by remember { mutableStateOf(false) }
    val scrollState = rememberScrollState()
    var result by remember { mutableStateOf(listOf<String>()) }
    var isArabic by remember { mutableStateOf(sharedPref.getBoolean("isArabic", false)) }
//و ا ح
    val space = 16.dp
    Column(modifier = modifier) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White.copy(alpha = 0.95f)
            ),
        ) {
            Text(
                text = stringResource(R.string.app_name),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
        Column(
            modifier = modifier
                .fillMaxWidth()
                .verticalScroll(scrollState)
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded }
            ) {
                OutlinedTextField(
                    value = dropdownOptions[selectedVefk],
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Vefk türünü seçin.") },//نقش کی قسم سلیکٹ کرو
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth()
                )
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    dropdownOptions.forEachIndexed { index, option ->
                        DropdownMenuItem(
                            text = { Text(option) },
                            onClick = {
                                selectedVefk = index
                                expanded = false
                                sharedPref.edit { putInt("saved_Vefk_type", index) }
                            }
                        )
                    }
                }

            }
            Spacer(modifier = Modifier.height(space))
            ebaraat.forEachIndexed { index, value ->
                CustomOutlinedTextField(
                    value = value,
                    onValueChange = { newValue ->
                        if (newValue.isEmpty() || newValue.all { it in ALPHABET || it == ' ' }) {
                            ebaraat[index] = newValue
                            sharedPref.edit { putString("ebaraat_$index", newValue) }
                        }
                    },
                    label = labels[index],
                    modifier = Modifier.fillMaxWidth()
                )
            }


            // 2. Arabic Numbers Toggle Row (Replaced Box with a Bordered Row)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = space),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // 1. Arabic Numbers Toggle Box (Balanced with weight 1f or 1.2f)
                Surface(
                    modifier = Modifier
                        .weight(1.2f)
                        .height(56.dp), // Match standard Material 3 button height
                    shape = RoundedCornerShape(8.dp),
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
                    color = MaterialTheme.colorScheme.surface
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 12.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Arap Rakamları",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Switch(
                            checked = isArabic,
                            onCheckedChange = {
                                isArabic = it
                                sharedPref.edit { putBoolean("isArabic", it) }
                            }
                        )
                    }
                }

                // 2. Calculate Button (Given a solid, balanced weight)
                Button(
                    onClick = {
                        result = calculateResult(ebaraat, requiredFieldCount, isArabic)
                        showNaqash = true
                        keyboardController?.hide()
                    },
                    modifier = Modifier
                        .weight(1f)
                        .height(56.dp), // Matches the toggle height
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text("Hesaplamak") // Calculate
                }
            }

            if (showNaqash && result.isNotEmpty()) {
                CreateNaqoosh(result, isArabic)
            }

        }
    }
}

