package com.example.bmapp.roomDatabases.objects

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "UserTabel")

data class User(
    @PrimaryKey(autoGenerate = true)
    val id:Int = 0,
    var name:String,
    var age:Int,
    var email:String,
    var address:String,
    var pass:String,
@ColumnInfo(defaultValue = "empty string")  var myName:String,
    @ColumnInfo(defaultValue = "0")   var addColumn:Int
)
