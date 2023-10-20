package com.example.mt1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Game()
        }
    }
}

// main application
@Composable
fun Game() {
    var targetVal by remember { mutableStateOf(Random.nextInt(0, 100)) }
    var sliderVal by remember { mutableStateOf(50f) }
    var score by remember { mutableStateOf(0) }
    var roundNum by remember { mutableStateOf(1) }
    var message by remember { mutableStateOf("") }

    // all the calculations are done here
    fun getTheScore() {
        val guess = sliderVal.toInt()
        val difference = kotlin.math.abs(targetVal - guess)
        when {
            difference <= 4 -> {
                score += 6
                message = "You got everything!"
            }
            difference <= 7 -> {
                score += 1
                message = "Almost there but not quite. Try again bro"
            }
            else -> {
                message = "Nvm try more"
            }
        }

        targetVal = Random.nextInt(0, 100)
    }

    // the overall structure
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Bull's Eye Game",
            style = TextStyle(fontSize = 22.sp, fontWeight = FontWeight.ExtraBold)
        )
        Spacer(modifier = Modifier.height(50.dp))
        Text(
            text = "Move the slider as close as you can to: $targetVal",
            style = TextStyle(fontSize = 16.sp)
        )
        Slider(
            value = sliderVal,
            onValueChange = { newValue ->
                sliderVal = newValue
            },
            valueRange = 0f..100f,
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        )
        Button(
            onClick = {
                getTheScore()
                roundNum++
                sliderVal = 50f
            },
            modifier = Modifier.padding(18.dp)
        ) {
            Text(text = "Hit Me!")
        }
        Text(
            text = "Your score is: $score",
            style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.Blue),
            modifier = Modifier.padding(16.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = message,
            style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.Black),
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewGame() {
    Game()
}