package com.paulomenezes.filestorage02

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.preference.PreferenceManager
import com.paulomenezes.filestorage02.utils.SharedPreferenceUtils

class MainActivity : AppCompatActivity() {
    private lateinit var sharedPreferenceUtils: SharedPreferenceUtils

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPreferenceUtils = SharedPreferenceUtils(this)

        val editTextName = findViewById<EditText>(R.id.editTextName)
        val editTextAge = findViewById<EditText>(R.id.editTextAge)

        val buttonWrite = findViewById<Button>(R.id.buttonWrite)
        val buttonRead = findViewById<Button>(R.id.buttonRead)
        val buttonClear = findViewById<Button>(R.id.buttonClear)
        val textViewResult = findViewById<TextView>(R.id.textViewResult)

        buttonWrite.setOnClickListener {
            writeOnSharedPreference(editTextName.text.toString(), editTextAge.text.toString().toInt())
        }

        buttonRead.setOnClickListener {
            val (name, age) = readOnSharedPreference()

            if (name != null && age != 0) {
                textViewResult.text = "Your name is $name and your age is $age years old"
            } else {
                textViewResult.text = "You do not set your name neither your age"
            }
        }

        buttonClear.setOnClickListener {
            clearSharedPreference()
        }

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainerView, SettingsFragment())
            .commit()
    }

    private fun writeOnSharedPreference(name: String, age: Int) {
        sharedPreferenceUtils.write(name, age)
    }

    private fun readOnSharedPreference(): Pair<String?, Int> {
        return sharedPreferenceUtils.read()
    }

    private fun clearSharedPreference() {
        sharedPreferenceUtils.clear()
    }
}