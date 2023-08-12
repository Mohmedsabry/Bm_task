package com.example.bmapp

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.bmapp.dao.ProfileDao
import com.example.bmapp.dao.UserDao
import com.example.bmapp.roomDatabases.objects.ProfileTask
import com.example.bmapp.roomDatabases.objects.User

@Database(entities = [User::class,ProfileTask::class], version = 5, autoMigrations = [AutoMigration(from = 4, to = 5)], exportSchema = true)
abstract class Mydatabase :RoomDatabase() {
   abstract fun UserDao():UserDao
   abstract fun ProfileDao():ProfileDao
}