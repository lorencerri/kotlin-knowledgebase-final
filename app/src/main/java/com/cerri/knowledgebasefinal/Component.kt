package com.cerri.knowledgebasefinal

@kotlinx.serialization.Serializable
class Component {
    var title: String = ""
    var content: String = ""
    var deleted: Boolean = false
}