package com.cerri.knowledgebasefinal

@kotlinx.serialization.Serializable
data class User(
    val id: Int = 0,
    val email: String = "",
    val username: String = ""
)