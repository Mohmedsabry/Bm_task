package com.example.bmapp.ui.theme

import android.content.Intent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Task9() {
    Column(Modifier.fillMaxSize()) {
        var text by remember {
            mutableStateOf("")
        }
        val context = LocalContext.current
        OutlinedTextField(value =text , onValueChange ={
            text = it
        } )
        Button(onClick = {
            val intent = Intent("MY_ACTION")
            intent.putExtra("my data",text)
            context.startActivity(intent)
        }) {
            Text(text = "do Action")
        }
    }
}