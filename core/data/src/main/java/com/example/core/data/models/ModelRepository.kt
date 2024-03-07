package com.example.core.data.models

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

interface ModelRepository {

    val myModels: Flow<List<String>>

    suspend fun add(text: String)
}

class ExampleModelRepository @Inject constructor() : ModelRepository  {

    private val _myModels: MutableStateFlow<List<String>> = MutableStateFlow(listOf("Android", "Kotlin", "Multimodule"))

    override val myModels: Flow<List<String>> = _myModels

    override suspend fun add(text: String) {
        _myModels.tryEmit(_myModels.value.plus(text))
    }
}
