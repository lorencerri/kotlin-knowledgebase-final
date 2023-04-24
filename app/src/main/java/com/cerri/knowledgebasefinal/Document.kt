package com.cerri.knowledgebasefinal

@kotlinx.serialization.Serializable
data class Document(
    val id: Int = 0,
    val created_at: String = "",
    val title: String = "",
    val description: String = ""
)