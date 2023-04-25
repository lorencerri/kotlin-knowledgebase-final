package com.cerri.knowledgebasefinal.navigation

sealed class Screens(val route: String) {
    object DocumentsScreen : Screens(route = "Documents_Screen")
    object NewDocumentScreen : Screens(route = "New_Document_Screen")
    object AccountScreen : Screens(route = "Account_Screen")
    object SignUpScreen : Screens(route = "Sign_Up_Screen")
    object SignInScreen : Screens(route = "Sign_In_Screen")
    object DocumentScreen : Screens(route = "Document_Screen?documentId={documentId}")
}
