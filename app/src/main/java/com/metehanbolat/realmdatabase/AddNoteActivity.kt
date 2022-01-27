package com.metehanbolat.realmdatabase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import androidx.core.content.ContextCompat
import androidx.core.view.get
import com.google.android.material.snackbar.Snackbar
import com.metehanbolat.realmdatabase.adapter.NotesAdapter
import com.metehanbolat.realmdatabase.databinding.ActivityAddNoteBinding
import com.metehanbolat.realmdatabase.model.Note
import kotlin.math.floor
import kotlin.math.round

class AddNoteActivity : AppCompatActivity() {

    private lateinit var binding : ActivityAddNoteBinding

    var priority = ""
    var control = ""
    var position = 0
    var id = ""
    var title = ""
    var description = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val database = Database(this)

        intent.extras?.let {
            control = it["control"] as String
            position = it["position"] as Int
            id = it["id"] as String
            title = it["title"] as String
            description = it["description"] as String
            priority = it["priority"] as String

        }

        binding.noteTitle.setText(title)
        binding.noteDescription.setText(description)

        if (control == "update"){
            binding.addNoteButton.text = "Update"
            binding.note.text = "Update Note"
            when(priority) {
                "low" -> binding.low.isChecked = true
                "medium" -> binding.medium.isChecked = true
                "high" -> binding.high.isChecked = true
            }
        }

        binding.addNoteButton.setOnClickListener {

            val title = binding.noteTitle.text.toString()
            val description = binding.noteDescription.text.toString()

            if (title.isEmpty()){
                binding.noteTitle.background = ContextCompat.getDrawable(this, R.drawable.false_edit_text_background)
            }else if (description.isEmpty()){
                binding.noteDescription.background = ContextCompat.getDrawable(this, R.drawable.false_edit_text_background)
            }else if (!(binding.low.isChecked || binding.medium.isChecked || binding.high.isChecked)){
                object : CountDownTimer(1200, 400) {
                    override fun onTick(p0: Long) {
                        when(round(p0.toDouble()/100).toInt()) {
                            12 -> binding.low.isChecked = true
                            8 -> binding.medium.isChecked = true
                            4 -> binding.high.isChecked = true
                        }
                    }
                    override fun onFinish() {
                        binding.low.isChecked = false
                        binding.medium.isChecked = false
                        binding.high.isChecked = false
                    }
                }.start()
            }else{
                if (control == "update"){
                    database.deleteNote(id)
                }

                when {
                    binding.low.isChecked -> priority = "low"
                    binding.medium.isChecked -> priority = "medium"
                    binding.high.isChecked -> priority = "high"
                }

                database.noteAdd(title, description, priority)
                Intent(this, MainActivity::class.java).apply {
                    startActivity(this)
                    finish()
                }
            }

        }

        binding.noteTitle.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(p0: Editable?) {
                if (p0.isNullOrEmpty()){
                    binding.noteTitle.background = ContextCompat.getDrawable(this@AddNoteActivity, R.drawable.false_edit_text_background)
                }else {
                    binding.noteTitle.background = ContextCompat.getDrawable(this@AddNoteActivity, R.drawable.true_edit_text_background)
                }
            }

        })

        binding.noteDescription.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(p0: Editable?) {
                if (p0.isNullOrEmpty()){
                    binding.noteDescription.background = ContextCompat.getDrawable(this@AddNoteActivity, R.drawable.false_edit_text_background)
                }else {
                    binding.noteDescription.background = ContextCompat.getDrawable(this@AddNoteActivity, R.drawable.true_edit_text_background)
                }

            }

        })

    }
}