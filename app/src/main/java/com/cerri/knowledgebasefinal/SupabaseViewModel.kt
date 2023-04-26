package com.cerri.knowledgebasefinal

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.GoTrue
import io.github.jan.supabase.gotrue.gotrue
import io.github.jan.supabase.gotrue.user.UserInfo
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

    suspend fun getUsernameOrNull(): String? {
        runCatching {
            client.gotrue.retrieveUserForCurrentSession()
        }.onFailure {
            return null
        }.onSuccess {
            val supabaseResponse = client.postgrest["users"].select(single = true) {
                User::email eq it.email
            }
            return supabaseResponse.decodeAs<User>().username
        }
        return null
    }

    suspend fun getUserOrNull(): UserInfo? {
        runCatching {
            client.gotrue.retrieveUserForCurrentSession()
        }.onFailure {
            return null
        }.onSuccess {
            return it
        }
        return null
    }

    suspend fun updateUsername(username: String) {
        val user = client.gotrue.retrieveUserForCurrentSession()
        client.postgrest["users"].update({ User::username setTo username })
        {
            User::email eq user.email
        }
    }

    suspend fun createDocument(title: String, description: String) {
        client.postgrest["documents"].insert(
            Document(
                title = title,
                description = description
            )
        )
    }

    suspend fun getDocument(documentId: String): Document? {
        Log.d("SupabaseViewModel", documentId)
        runCatching {
            val response = client.postgrest["documents"].select(single = true) {
                Document::id eq documentId
            }
            return response.decodeAs()
        }.onFailure {
            it.printStackTrace()
            return null
        }
        return null
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

    suspend fun editDocument(documentId: String, title: String, description: String) {
        client.postgrest["documents"].update({
            Document::title setTo title
            Document::description setTo description
        }) {
            Document::id eq documentId
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