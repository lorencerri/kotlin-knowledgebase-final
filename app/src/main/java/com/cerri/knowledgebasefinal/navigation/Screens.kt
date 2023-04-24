package com.cerri.knowledgebasefinal.navigation

sealed class Screens(val route: String) {
    object DocumentsScreen : Screens(route = "Documents_Screen")
}
