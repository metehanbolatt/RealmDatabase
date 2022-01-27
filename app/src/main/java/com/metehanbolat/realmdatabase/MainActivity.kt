package com.metehanbolat.realmdatabase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.metehanbolat.realmdatabase.adapter.NotesAdapter
import com.metehanbolat.realmdatabase.databinding.ActivityMainBinding
import com.metehanbolat.realmdatabase.model.Note

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var noteList : ArrayList<Note>
    private lateinit var noteAdapter : NotesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val database = Database(this)
        noteList = ArrayList()

        noteList = database.getAllNotes()
        noteList.reverse()
        noteAdapter = NotesAdapter(noteList, database)
        binding.recyclerView.adapter = noteAdapter

        binding.addButton.setOnClickListener {
            Intent(this, AddNoteActivity::class.java).apply {
                putExtra("control", "add")
                putExtra("position", 0)
                putExtra("id", "")
                putExtra("title", "")
                putExtra("description", "")
                putExtra("priority", "")
                startActivity(this)
            }
        }
    }
}
