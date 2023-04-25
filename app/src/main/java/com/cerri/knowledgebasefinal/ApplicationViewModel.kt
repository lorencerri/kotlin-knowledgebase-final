package com.cerri.knowledgebasefinal

import androidx.compose.runtime.mutableStateOf

open class ApplicationViewModel : SupabaseViewModel() {
    val drawerState = mutableStateOf(false)
}