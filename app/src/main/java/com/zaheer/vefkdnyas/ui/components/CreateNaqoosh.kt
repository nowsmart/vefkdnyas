package com.zaheer.vefkdnyas.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.zaheer.vefkdnyas.calculateAbjadSum
import com.zaheer.vefkdnyas.toArabicDigits
import kotlin.random.Random

@Composable
fun CreateNaqoosh(
    numbers: List<String>,
    isArabic: Boolean
) {
    val tableColor = Color(
        red = Random.nextInt(180, 256),
        green = Random.nextInt(180, 256),
        blue = Random.nextInt(180, 256)
    )
    val foreColor = Color(
        red = Random.nextInt(0, 101),
        green = Random.nextInt(0, 101),
        blue = Random.nextInt(0, 101)
    )
    val zila = when (numbers.size) {
        9 -> 3
        16 -> 4
        else -> 5
    }
    Column(
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        for (row in 0 until zila) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                for (col in 0 until zila) {
                    val index = row * zila + col
                    val formatedNumber =
                        if (isArabic) numbers[index].toArabicDigits() else numbers[index]
                    Box(
                        modifier = Modifier
                            .weight(1f) // Distributes width evenly across 3 columns
                            .aspectRatio(1f)
                            .background(
                                color = tableColor.copy(alpha = 0.5f),// Color.White.copy(alpha = 0.18f),
                                shape = RoundedCornerShape(6.dp)
                            )
                            .border(border = BorderStroke(1.dp, foreColor)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = formatedNumber,
                            style = MaterialTheme.typography.titleLarge.copy(
                                color = foreColor,
                                textAlign = TextAlign.Center
                            )
                        )
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        HorizontalDivider()
        Spacer(modifier = Modifier.height(10.dp))
    }
}

fun calculateResult(
    ebaraat: SnapshotStateList<String>,
    selectedVefk: Int,
    isArabic: Boolean
): List<String> {
    // 1. Calculate the sums cleanly in one line
    val orderedEbaraat = ebaraat.reversed()
    val addaadList = orderedEbaraat.map { it.calculateAbjadSum() }

// 2. Initialize resultList with reversed ebaraat and add computed values based on selectedVefk
    val resultList = mutableStateListOf<String>().apply {
        addAll(orderedEbaraat)

        when (selectedVefk) {
            3 -> {
                // A helper to make adding string conversions cleaner
                val items = listOf(
                    addaadList[1] + 2, //3 ------- 3
                    addaadList[2] - 1, //5 ------- 4
                    addaadList[0] - 1, //7 ------- 5
                    addaadList[2] - 2, //4 ------- 6
                    addaadList[0] + 1, //9 ------- 7
                    addaadList[1] + 1  //2 ------- 8
                )
                addAll(items.map { it.toString() })
            }

            4 -> {
                val items = listOf(
                    addaadList[2] - 1,
                    addaadList[3] + 1,
                    addaadList[0] - 1,
                    addaadList[1] + 1,

                    addaadList[3] + 2,
                    addaadList[2] + 2,
                    addaadList[1] - 2,
                    addaadList[0] - 2,

                    addaadList[1] - 1,
                    addaadList[0] - 3,
                    addaadList[3] + 3,
                    addaadList[2] + 1,
                )
                addAll(items.map { it.toString() })
            }

            5 -> {
                val items = listOf(
                    addaadList[2] + 1,//20
                    addaadList[3] - 4,//21
                    addaadList[4] + 1,//2
                    addaadList[0] + 1,//8
                    addaadList[1] + 1,//14

                    addaadList[4] + 2,//3
                    addaadList[0] + 2,//9
                    addaadList[1] + 2,//15
                    addaadList[2] - 3,//16
                    addaadList[3] - 3,//22

                    addaadList[1] - 2,//11
                    addaadList[2] - 2,//17
                    addaadList[3] - 2,//23
                    addaadList[4] + 3,//4
                    addaadList[0] + 3,//10

                    addaadList[3] - 1,//24
                    addaadList[4] + 4,//5
                    addaadList[0] - 1,//6
                    addaadList[1] - 1,//12
                    addaadList[2] - 1,//18
                )
                addAll(items.map { it.toString() })
            }
        }

    }
    return resultList
    /*
    return if (isArabic)
        resultList.map { it.toArabicDigits() }
    else
        resultList

     */
}