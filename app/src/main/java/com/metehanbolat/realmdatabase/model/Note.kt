package com.metehanbolat.realmdatabase.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

open class Note(
    var noteTitle: String = "",
    var noteDescription: String = "",
    var notePriority: String = "",
    @PrimaryKey
    var id: String = UUID.randomUUID().toString()
) : RealmObject()