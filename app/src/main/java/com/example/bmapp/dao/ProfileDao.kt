package com.example.bmapp.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.bmapp.roomDatabases.objects.ProfileTask

@Dao
interface ProfileDao {
    @Insert
    fun insertProfile(profileTask: ProfileTask)

    @Delete
    fun deleteProfile(profileTask: ProfileTask)

    @Update
    fun updateProfile(profileTask: ProfileTask)

    @Query("select * from profile_name")
    fun getAllProfile(): List<ProfileTask>

    @Query("select * from profile_name where id = :id ")
    fun getItemByID(id: Int): ProfileTask
}