package com.smartdev.sample

import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey

class EncryptedPrefs(masterKey: MasterKey) : EncryptedPrefsInterface {
  
  private val encryptedSharedPreferences: SharedPreferences = EncryptedSharedPreferences.create(
    App.instance,
    SHARED_PREFS_FILENAME,
    masterKey,
    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
  )
  
  override fun saveData(data: String) {
    encryptedSharedPreferences.edit().putString(DATA_KEY, data).apply()
  }
  
  override fun getData(): String {
    return encryptedSharedPreferences.getString(DATA_KEY, EMPTY_STRING) ?: EMPTY_STRING
  }
  
  override fun deleteData() {
    encryptedSharedPreferences.edit().putString(DATA_KEY, EMPTY_STRING).apply()
  }
  
  companion object {
    private const val DATA_KEY = "DATAKEY"
    private const val SHARED_PREFS_FILENAME = "sharedPrefsSecure"
  }
}