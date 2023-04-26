package com.cerri.knowledgebasefinal

import androidx.compose.runtime.mutableStateListOf

@kotlinx.serialization.Serializable
data class Document(
    val id: Int = 0,
    val created_at: String = "",
    val title: String = "",
    val description: String = "",
    val author_id: String = "",
    var components: MutableList<Component> = mutableStateListOf()
)