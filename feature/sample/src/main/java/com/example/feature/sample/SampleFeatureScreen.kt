package com.example.feature.sample

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun SampleFeatureScreen(
    modifier: Modifier = Modifier,
    viewModel: SampleFeatureViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when (val model = state) {
            is SampleFeatureUiState.Error ->
                Text(text = model.throwable.message ?: "Error")

            SampleFeatureUiState.Loading ->
                Text(text = "Loading")

            is SampleFeatureUiState.Success ->
                MyModelScreen(
                    items = model.data,
                    onSave = { text -> viewModel.addText(text) },
                    modifier = modifier
                )
        }
    }
}

@Composable
internal fun MyModelScreen(
    items: List<String>,
    onSave: (name: String) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            var text by remember { mutableStateOf("Compose") }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                TextField(
                    modifier = Modifier.weight(1f),
                    value = text,
                    onValueChange = { text = it }
                )

                Button(
                    modifier = Modifier.width(96.dp),
                    onClick = { onSave(text) }
                ) {
                    Text("Save")
                }
            }

            items.forEach {
                Text("Saved item: $it")
            }
        }
    }
}
