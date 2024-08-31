package com.coffee.compose

import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.coffee.compose.ui.theme.MyApplicationTheme
import com.unity3d.player.GameViewActivity

class MainActivity : GameViewActivity() {


    private var score: Int? = null


    override fun onGameFinished(score: Int) {
        this.score = score
    }

    override fun onGameStarted() {
    }

    var time: Long = 0L

    override fun setActivityContentView(playerView: View) {
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(Modifier.fillMaxSize()) {
                        Column(Modifier.weight(1f)) {
                            Greeting(
                                name = "Android full compose",
                                modifier = Modifier.padding(innerPadding)
                            )
                            Button(onClick = {
                                finishGame()
                            }) {
                                Text(text = "Finish game")
                            }
                            score?.let {
                                Text(text = "Total score: $it")
                            }
                        }
                        Box(Modifier.weight(1f).padding(10.dp)) {
                            AndroidView(factory = {
                                playerView
                            })
                        }
                    }
                }
            }
        }
    }

}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        Greeting("Android")
    }
}