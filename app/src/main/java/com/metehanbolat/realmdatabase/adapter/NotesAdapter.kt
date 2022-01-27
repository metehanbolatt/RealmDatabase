package com.metehanbolat.realmdatabase.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.metehanbolat.realmdatabase.AddNoteActivity
import com.metehanbolat.realmdatabase.Database
import com.metehanbolat.realmdatabase.MainActivity
import com.metehanbolat.realmdatabase.R
import com.metehanbolat.realmdatabase.databinding.NoteRecyclerRowBinding
import com.metehanbolat.realmdatabase.model.Note
import kotlin.collections.ArrayList

class NotesAdapter(private var noteList : ArrayList<Note>, private val database : Database) : RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {
    class NotesViewHolder(val binding: NoteRecyclerRowBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val binding = NoteRecyclerRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NotesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        holder.binding.apply {
            noteTitle.text = noteList[position].noteTitle
            noteDescription.text = noteList[position].noteDescription

            when(noteList[position].notePriority) {
                "low" -> noteTitle.setTextColor(ContextCompat.getColor(holder.binding.root.context, R.color.green))
                "medium" -> noteTitle.setTextColor(ContextCompat.getColor(holder.binding.root.context, R.color.yellow))
                "high" -> noteTitle.setTextColor(ContextCompat.getColor(holder.binding.root.context, R.color.red))
            }

            closeButton.setOnClickListener { view ->

                Snackbar.make(view, "${noteList[position].noteTitle} will be deleted. Are you sure?", Snackbar.LENGTH_LONG).setAction("Delete") {
                    database.deleteNote(noteList[position].id)
                    noteList.removeAt(position)
                    notifyDataSetChanged()
                }.show()
            }

            rowLinear.setOnClickListener {
                Intent(it.context, AddNoteActivity::class.java).apply {
                    putExtra("control", "update")
                    putExtra("position", position)
                    putExtra("id", noteList[position].id)
                    putExtra("title", noteList[position].noteTitle)
                    putExtra("description", noteList[position].noteDescription)
                    putExtra("priority", noteList[position].notePriority)
                    it.context.startActivity(this)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return noteList.size
    }
}