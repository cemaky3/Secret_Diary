package org.hyperskill.secretdiary

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

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSave.setOnClickListener {
            if (binding.etNewWriting.text.trim().isNotEmpty()) {
                var date = Clock.System.now().toLocalDateTime(currentSystemDefault()).toString().replace("T"," ").substringBefore(".")
                binding.tvDiary.text = "${date}\n${binding.etNewWriting.editableText}${
                    if (binding.tvDiary.text.isEmpty()) "" else "\n\n${binding.tvDiary.text}"
                }"
                binding.etNewWriting.text.clear()
            } else {
                Toast.makeText(this, "Empty or blank input cannot be saved", Toast.LENGTH_SHORT).show()
            }
        }
    }

}