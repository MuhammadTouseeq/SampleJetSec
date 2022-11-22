package com.smartdev.sample

interface EncryptedPrefsInterface {
  
  fun saveData(data: String)
  
  fun getData(): String

  fun deleteData()
}