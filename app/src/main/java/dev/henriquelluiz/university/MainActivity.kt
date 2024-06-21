package dev.henriquelluiz.university

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.henriquelluiz.university.ui.theme.UniversityTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UniversityTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    HomeScreenContent(
                        modifier = Modifier
                            .fillMaxSize()
                            .wrapContentSize()
                            .padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun HomeScreenContent(modifier: Modifier = Modifier) {
    var userValue by remember { mutableStateOf("") }
    var hasChanged by remember { mutableStateOf(false) }

    Box(modifier) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            AnimatedVisibility(
                visible = hasChanged,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Olá, $userValue!",
                    style = MaterialTheme.typography.headlineSmall,
                    textAlign = TextAlign.Center,
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = userValue,
                onValueChange = {
                    userValue = it
                    if (userValue.isBlank()) {
                        hasChanged = false
                    }
                },
                placeholder = { Text(stringResource(R.string.text_field)) },
                keyboardActions = KeyboardActions(onDone = { hasChanged = true }),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    hasChanged = true
                }
            ) {
                Text(text = stringResource(R.string.button_text))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppPreview() {
    UniversityTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            HomeScreenContent(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize()
                    .padding(innerPadding)
            )
        }
    }
}