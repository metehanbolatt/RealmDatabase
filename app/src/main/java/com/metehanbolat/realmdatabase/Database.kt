package com.metehanbolat.realmdatabase

import android.content.Context
import com.metehanbolat.realmdatabase.model.Note
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.kotlin.where

class Database (private val context: Context) {

    private fun config() : Realm?{
        Realm.init(context)
        val conf = RealmConfiguration.Builder()
            .name("project.db")
            .schemaVersion(1)
            .allowQueriesOnUiThread(true)
            .allowWritesOnUiThread(true)
            .build()
        return Realm.getInstance(conf)
    }

    fun noteAdd(title: String, description: String, priority: String) {
        val note = Note(title, description, priority)
        config()?.executeTransaction {
            it.insert(note)
        }
    }

    fun getAllNotes() : ArrayList<Note> {
        val noteList = ArrayList<Note>()
        config()?.executeTransaction {
            it.where<Note>().findAll().forEach { note ->
                noteList.add(note)
            }
        }
        return noteList
    }

    fun deleteNote(id : String) {
        config()?.executeTransaction {
            it.where<Note>().findAll().forEach { note ->
                if (id == note.id) {
                    note.deleteFromRealm()
                }
            }
        }
    }
}