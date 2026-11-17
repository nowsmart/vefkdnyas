package com.zaheer.vefkdnyas

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun NaqshTypeSelector(
    modifier: Modifier = Modifier,
    selectedType: String,
    onTypeSelected: (String) -> Unit
) {
    Column(modifier = modifier) {
        Text(
            text ="Nakış Türü", //"Naqsh Type",
            style = MaterialTheme.typography.labelMedium
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            FilterChip(
                selected = selectedType == "Textual",
                onClick = { onTypeSelected("Textual") },
                label = { Text("Metinli") }
            )

            FilterChip(
                selected = selectedType == "Zul-Kitabat",
                onClick = { onTypeSelected("Zul-Kitabat") },
                label = { Text("Zülkitabet") }
            )
        }
    }
}