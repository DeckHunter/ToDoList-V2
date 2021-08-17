package com.fofism.todo.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ListaDb(context: Context) : SQLiteOpenHelper(context, ListaContract.DB_NAME, null, ListaContract.DB_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = "CREATE TABLE " + ListaContract.Lista.L_TABLE + " ( " +
                ListaContract.Lista.L_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ListaContract.Lista.L_COL_LISTA_TITLE + " TEXT NOT NULL);"

        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + ListaContract.Lista.L_TABLE)
        onCreate(db)
    }
}