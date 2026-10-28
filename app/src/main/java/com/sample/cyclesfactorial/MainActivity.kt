package com.sample.cyclesfactorial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.sample.cyclesfactorial.ui.theme.CyclesFactorialTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CyclesFactorialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    FactorialScreen()
                }
            }
        }
    }
}

@Composable
fun FactorialScreen() {
    var inputN by remember { mutableStateOf("") }
    var resultText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = inputN,
            onValueChange = { newValue ->
                if (newValue.all { it.isDigit() } && newValue.length <= 2) inputN = newValue
            },
            label = { Text("введите n (1..20)") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(16.dp))

        Button(
            onClick = {
                resultText = if (inputN.isEmpty()) "введите n!"
                else checkFactorial(inputN.toIntOrNull() ?: 0)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("проверить", fontSize = 18.sp)
        }

        Spacer(Modifier.height(24.dp))

        Text(
            text = resultText,
            fontSize = 18.sp,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

fun checkFactorial(n: Int): String {
    if (n < 1) return "введите n ≥ 1"
    if (n > 20) return "для расчёта возьмите n ≤ 20"
    var factorial = 1L

    for (i in 2..n) {
        factorial *= i
    }

    var k = 1L
    while (true) {
        val product = k * (k + 1) * (k + 2)
        when {
            product == factorial ->
                return "$n! = $factorial = $k × ${k + 1} × ${k + 2}\nможно представить"
            product > factorial ->
                return "$n! = $factorial\nнельзя представить в виде\nпроизведения трёх последовательных чисел"
        }
        k++
    }
}