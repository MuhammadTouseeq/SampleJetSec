package com.smartdev.sample

import android.app.Application
import androidx.security.crypto.MasterKey

class App: Application() {
  
  companion object {
    lateinit var instance: App

     val masterKey: MasterKey
     by lazy{ MasterKey.Builder(instance).setKeyScheme(MasterKey.KeyScheme.AES256_GCM).build() }

     val encryptedPrefs: EncryptedPrefsInterface by lazy { EncryptedPrefs(masterKey) }

  }

  override fun onCreate() {
    super.onCreate()
    instance = this

  }
}