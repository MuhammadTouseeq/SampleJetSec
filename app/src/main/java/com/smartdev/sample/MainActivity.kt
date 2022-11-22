package com.smartdev.sample

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.security.crypto.MasterKey
import com.smartdev.sample.App.Companion.encryptedPrefs
import com.smartdev.sample.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setupListeners()
    }



    private fun setupListeners() {
        binding.apply {
            btnSave.setOnClickListener { saveDataToEncryptedPrefs() }
            btnShow.setOnClickListener { txtReadData.text=getDataFromEncryptedPrefs() }

        }
    }



    private fun saveDataToEncryptedPrefs() {
        closeKeyboard()
        binding.apply {

            if (isInputValid(edtText.text.toString())) {
                encryptedPrefs.saveData(edtText.text.toString())
                edtText.text?.clear()
                showToast("Data saved")
            } else {
                showToast("Data is required")
            }
        }

    }



    private fun getDataFromEncryptedPrefs(): String {
        return encryptedPrefs.getData()
    }

    private fun isInputValid(input: String): Boolean {
        return input.isNotBlank()
    }

    private fun showToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(this, message, duration).show()
    }

    private fun closeKeyboard() {
        val inputManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(currentFocus?.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
    }
}

const val EMPTY_STRING = ""