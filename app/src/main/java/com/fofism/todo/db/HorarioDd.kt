package com.fofism.todo.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

import com.fofism.todo.db.AtividadeContract

class HorarioDd(context: Context) : SQLiteOpenHelper(context, HorarioContract.DB_NAME, null, HorarioContract.DB_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = "CREATE TABLE " + HorarioContract.Horario.H_TABLE + " ( " +
                HorarioContract.Horario.H_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                HorarioContract.Horario.H_COL_HORARIO_TITLE + " TEXT NOT NULL);"

        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + HorarioContract.Horario.H_TABLE)
        onCreate(db)
    }
}