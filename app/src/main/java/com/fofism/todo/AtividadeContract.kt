package com.fofism.todo.db

import android.provider.BaseColumns

class AtividadeContract {
    companion object {
        val DB_NAME = "com.fofism.todo.db"
        val DB_VERSION = 1
    }

    class Atividade : BaseColumns {

        companion object {
            val A_TABLE = "Atividades"
            val A_COL_ATIVIDADE_TITLE = "title_Atividade"
            val A_ID = BaseColumns._ID
        }
    }

}