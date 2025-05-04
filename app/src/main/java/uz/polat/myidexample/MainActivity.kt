package uz.polat.myidexample

import android.R.attr.onClick
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.google.firebase.crashlytics.FirebaseCrashlytics
import uz.polat.myidexample.ui.theme.MyIdExampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyIdExampleTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {
            log("Before runtimeException")
            throw RuntimeException("This is a crash")
        }) {
            Text("RuntimeException")
        }

        Button(onClick = {
            log("Before anr")
            Thread.sleep(5_000)
        }) {
            Text("ANR")
        }

        Button(onClick = {
            try {
                val number = "abc".toInt()
            } catch (e: Exception) {
                log("Before recordException")
                logException(e)
                log("after recordException")
            }
        }) {
            Text("recordException")
        }

        var hasErrorsText by remember { mutableStateOf("") }
        Text(hasErrorsText)

        Button(onClick = {
            FirebaseCrashlytics.getInstance().checkForUnsentReports().addOnCompleteListener {
                if (it.isSuccessful && it.result) {
                    hasErrorsText = "has errors"
                } else {
                    hasErrorsText = "no errors"
                }
            }
        }) {
            Text("checkForUnsentReports")
        }

        Button(onClick = {
            FirebaseCrashlytics.getInstance().sendUnsentReports()
        }) {
            Text("sendUnsentReports")
        }


        Button(onClick = {
            FirebaseCrashlytics.getInstance().deleteUnsentReports()
        }) {
            Text("deleteUnsentReports")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyIdExampleTheme {
        Greeting("Android")
    }
}