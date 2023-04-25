package com.cerri.knowledgebasefinal.navigation

sealed class Screens(val route: String) {
    object DocumentsScreen : Screens(route = "Documents_Screen")
    object NewDocumentScreen : Screens(route = "New_Document_Screen")
    object AccountScreen : Screens(route = "Account_Screen")
}
