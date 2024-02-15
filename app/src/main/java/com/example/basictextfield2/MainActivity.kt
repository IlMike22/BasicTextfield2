package com.example.basictextfield2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text2.BasicSecureTextField
import androidx.compose.foundation.text2.BasicTextField2
import androidx.compose.foundation.text2.input.InputTransformation
import androidx.compose.foundation.text2.input.TextFieldBuffer
import androidx.compose.foundation.text2.input.TextFieldCharSequence
import androidx.compose.foundation.text2.input.rememberTextFieldState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.basictextfield2.ui.theme.BasicTextfield2Theme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)) {
                var text1 by remember {
                    mutableStateOf("")
                }
                var text2 by remember {
                    mutableStateOf("")
                }

                val modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .background(Color.LightGray)

                BasicTextField(value = text2, onValueChange = { text2 = it }, modifier = modifier)

                val state = rememberTextFieldState()

                BasicTextField2(
                    state = state,
                    modifier = modifier,
                    inputTransformation = AndroidInputTransformation,
                    scrollState = rememberScrollState()
                )

                BasicSecureTextField( // keep sensible input only as long as need in buffer
                    state = state,
                    modifier = modifier,
                    inputTransformation = AndroidInputTransformation,
                    scrollState = rememberScrollState()
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
object AndroidInputTransformation : InputTransformation {
    override fun transformInput(
        originalValue: TextFieldCharSequence,
        valueWithChanges: TextFieldBuffer
    ) {
        if (!"Android".contains(valueWithChanges.asCharSequence())) {
            valueWithChanges.revertAllChanges()
        }
    }

}