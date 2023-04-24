package com.cerri.knowledgebasefinal

sealed class Screens(val route: String) {
    object DataScreen : Screens(route = "Data_Screen")
}
