package de.syntax_institut.taskmanager

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore("user_preferences")

class SortPreferenceRepository(private val context: Context) {

    private val SORT_ORDER_KEY = booleanPreferencesKey("sort_order")

    val sortOrderFlow: Flow<Boolean> = context.dataStore.data
        .map { preferences ->
            preferences[SORT_ORDER_KEY] ?: false // Standardwert: false (nicht alphabetisch)
        }

    suspend fun saveSortOrder(isSorted: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[SORT_ORDER_KEY] = isSorted
        }
    }
}
