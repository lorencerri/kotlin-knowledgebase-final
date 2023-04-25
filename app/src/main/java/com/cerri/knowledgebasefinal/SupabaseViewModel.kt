package com.cerri.knowledgebasefinal

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.GoTrue
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.realtime.Realtime
import kotlinx.coroutines.launch

open class SupabaseViewModel : ViewModel() {
    val documents = mutableStateOf<List<Document>>(emptyList())
    val client: SupabaseClient = getClient()

    init {
        getDocuments()
    }

    suspend fun createDocument(title: String, description: String) {
        client.postgrest["documents"].insert(
            Document(
                title = title,
                description = description
            )
        )
    }


    private fun getDocuments() {
        viewModelScope.launch {
            kotlin.runCatching {
                val supabaseResponse = client.postgrest["documents"].select()
                documents.value = supabaseResponse.decodeList()
            }.onFailure {
                it.printStackTrace()
            }
        }


    }
}


private fun getClient(): SupabaseClient {
    return createSupabaseClient(
        supabaseUrl = "https://tjrnedvrgxxmdekzwnyq.supabase.co",
        supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InRqcm5lZHZyZ3h4bWRla3p3bnlxIiwicm9sZSI6ImFub24iLCJpYXQiOjE2Nzg3NjEzNDMsImV4cCI6MTk5NDMzNzM0M30.ZuEn58H1sqf5-MxE4dcNdjCyR95eSh7XgN146Ssur9M"
    ) {
        install(Postgrest)
        install(Realtime)
        install(GoTrue)
    }
}