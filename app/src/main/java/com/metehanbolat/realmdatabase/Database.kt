package com.metehanbolat.realmdatabase

import android.content.Context
import com.metehanbolat.realmdatabase.model.User
import io.realm.Realm
import io.realm.RealmConfiguration

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

    fun userInsert(name: String, surname: String) {
        val user = User(name, surname)
        config()?.executeTransaction {
            it.insert(user)
        }
    }
}