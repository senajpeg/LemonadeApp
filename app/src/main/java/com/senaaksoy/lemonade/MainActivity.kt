package com.senaaksoy.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.senaaksoy.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LemonadeSteps()
                }
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun LemonadePreview() {
    LemonadeTheme {
LemonadeSteps()
    }
}

@Composable
fun LemonadeSteps(modifier: Modifier =Modifier) {
    var result by remember { mutableIntStateOf(1) }

    var squeezeCount by remember { mutableIntStateOf(0) }

    val imageResult = when (result) {
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        else -> R.drawable.lemon_restart
    }
    val stringResult = when (result) {
        1 -> R.string.lemonTree
        2 -> R.string.Lemon
        3 -> R.string.Glassoflemonade
        else -> R.string.Emptyglass
    }
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "LEMONADE",
            modifier.fillMaxWidth()
                .background(color = Color.Yellow)
                .padding(35.dp),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp
        )
    }

    Column(
         modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Spacer(modifier.height(16.dp))
        Image(painter = painterResource(imageResult),
            contentDescription = result.toString(),
            modifier
                .clickable {
                    if (result == 1) {
                        result++
                        squeezeCount = (2..4).random()
                    } else if (result == 2) {
                        squeezeCount--
                        if (squeezeCount == 0) {
                            result++
                        }
                    } else if (result == 3) {
                        result++
                    } else {
                        result = result % 3
                    }
                }
                .background(Color(0xFFD0F7A3), shape = RoundedCornerShape(12))
                .size(250.dp)
                .border(2.dp, Color(0xFFBAE786), shape = RoundedCornerShape(12))
        )
        Text(text = stringResource(stringResult), modifier.padding(top = 20.dp))
    }
}

