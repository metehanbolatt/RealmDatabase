package com.metehanbolat.realmdatabase.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

open class User(
    var name : String = "",
    var surname : String = "",
    @PrimaryKey
    var uid : String = UUID.randomUUID().toString()
) : RealmObject()
