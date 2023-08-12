package com.example.bmapp.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.bmapp.roomDatabases.objects.User
@Dao
interface UserDao {
    @Insert
    fun insertUser(user:User)

    @Query("select * from UserTabel")
    fun getAllDate():List<User>
    @Query("Delete from UserTabel")
    fun deleteAllUser()
    @Delete
    fun deleteUser(user: User)
}