package com.dam_m8.bd

import android.database.sqlite.SQLiteOpenHelper
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteDatabase.CursorFactory

/*
* context: this
* name: nom BD
* factory: permet retornar objectes cursor
* version: versió BD exemple
 */
class DatabaseHandler(context: Context, name: String, factory: CursorFactory?, version: Int) : SQLiteOpenHelper(context, name, factory, version) {

    /*
    Called when the database is created for the first time. This is where the creation of tables and the initial population of the tables should happen.
     */
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("create table productes(codi text primary key, nom text)")
    }
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

    }
}