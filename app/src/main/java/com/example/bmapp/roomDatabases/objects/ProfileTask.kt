package com.example.bmapp.roomDatabases.objects

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("profile_name")
data class ProfileTask(
    @PrimaryKey(autoGenerate = true)
    var id:Int =0,
    var name:String ,
    var age:Int
) {

}
