package org.hyperskill.secretdiary

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val saveBtn = findViewById<Button>(R.id.btnSave)
        val inputTxt = findViewById<EditText>(R.id.etNewWriting)
        val textTv = findViewById<TextView>(R.id.tvDiary)

        saveBtn.setOnClickListener {
            if (inputTxt.text.trim().isNotEmpty()) {
                textTv.text = inputTxt.editableText
                inputTxt.text.clear()
            } else {
                Toast.makeText(this, "Empty or blank input cannot be saved", Toast.LENGTH_SHORT).show()
            }
        }
    }

}