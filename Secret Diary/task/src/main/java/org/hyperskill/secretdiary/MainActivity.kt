package org.hyperskill.secretdiary

import android.app.AlertDialog
import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import kotlinx.datetime.*
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import kotlinx.datetime.TimeZone.Companion.currentSystemDefault
import org.hyperskill.secretdiary.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var sharedPreferences: SharedPreferences
    private val keyDiary = "KEY_DIARY_TEXT"
    private val prefName = "PREF_DIARY"

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences(prefName,Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        val textList =  sharedPreferences.getString(keyDiary, null)?.split("\n\n")?.toMutableList() ?: mutableListOf()

        binding.tvDiary.text = sharedPreferences.getString(keyDiary, null)

        fun addNote() {
            val time = Clock.System.now().toLocalDateTime(currentSystemDefault()).toString().replace("T"," ").substringBefore(".")
            textList.add(0,"${time}\n${binding.etNewWriting.editableText}")
            binding.tvDiary.text = textList.joinToString("\n\n")
        }

        binding.btnSave.setOnClickListener {
            if (binding.etNewWriting.text.trim().isNotEmpty()) {
                addNote()
                editor.putString(keyDiary, binding.tvDiary.text.toString()).apply()
                binding.etNewWriting.text.clear()
            } else {
                Toast.makeText(this, "Empty or blank input cannot be saved", Toast.LENGTH_SHORT).show()
            }
        }
        binding.btnUndo.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Remove last note")
                .setMessage("Do you really want to remove the last writing? This operation cannot be undone!")
                .setPositiveButton("Yes") { _, _ ->
                    if (textList.isNotEmpty()) {
                        textList.removeFirst()
                        binding.tvDiary.text = textList.joinToString("\n\n")
                        editor.putString(keyDiary, binding.tvDiary.text.toString()).apply()
                    }
                }
                .setNegativeButton("No", null)
                .show()
        }
    }

}