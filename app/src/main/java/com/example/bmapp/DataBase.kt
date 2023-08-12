package com.example.bmapp

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

const val DBNAME="task"
const val DBTABLENAME="user"
const val ID="id"
const val NAME="name"
const val PASSWORD="password"
const val VERSION =1
class DataBase(context: Context) : SQLiteOpenHelper(context, DBNAME,null, VERSION){
    override fun onCreate(database: SQLiteDatabase?) {
        val sql = "create table $DBTABLENAME($ID INTEGER primary key autoincrement,$NAME TEXT , $PASSWORD TEXT)"
        database?.execSQL(sql)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
        onCreate(p0)
    }
    fun insertUser(userData: UserData) {
        val sqLiteOpenHelper = writableDatabase
        val contentValue = ContentValues()
        contentValue.put(NAME,userData.name)
        contentValue.put(PASSWORD,userData.password)
        sqLiteOpenHelper.insert(DBTABLENAME,null,contentValue).let {
            sqLiteOpenHelper.close()
        }
    }
    @SuppressLint("Range")
    fun getAllUser():ArrayList<UserData>{
        val arr = arrayListOf<UserData>()
        val sqLiteOpenHelper = readableDatabase
        val cursor = sqLiteOpenHelper.rawQuery("select * from $DBTABLENAME",null)
        if (cursor.moveToFirst()){
            do{
                val id = cursor.getInt(cursor.getColumnIndex(ID))
                val name = cursor.getString(cursor.getColumnIndex(NAME))
                val password = cursor.getString(cursor.getColumnIndex(PASSWORD))
                arr.add(UserData(id = id,name = name, password = password))
            }while (cursor.moveToNext())
        }
        sqLiteOpenHelper.close()
        cursor.close()
        return arr
    }

}
data class UserData(var id:Int=0,var name:String,var password:String){

}