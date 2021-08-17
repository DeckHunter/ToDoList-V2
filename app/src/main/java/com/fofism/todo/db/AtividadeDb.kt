package com.fofism.todo.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class AtividadeDb(context: Context) : SQLiteOpenHelper(context, AtividadeContract.DB_NAME, null, AtividadeContract.DB_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = "CREATE TABLE " + AtividadeContract.Atividade.A_TABLE + " ( " +
                AtividadeContract.Atividade.A_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                AtividadeContract.Atividade.A_COL_ATIVIDADE_TITLE + " TEXT NOT NULL);"

        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + AtividadeContract.Atividade.A_TABLE)
        onCreate(db)
    }
}