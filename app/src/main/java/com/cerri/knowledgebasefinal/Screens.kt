package com.cerri.knowledgebasefinal

sealed class Screens(val route: String) {
    object DocumentsScreen : Screens(route = "Documents_Screen")
}
