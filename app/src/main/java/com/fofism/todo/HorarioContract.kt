package com.fofism.todo.db

import android.provider.BaseColumns

class HorarioContract {
    companion object {
        val DB_NAME = "com.fofism.todo.db"
        val DB_VERSION = 1
    }

    class Horario : BaseColumns {

        companion object {
            val H_TABLE = "Horarios"
            val H_COL_HORARIO_TITLE = "title_Horario"
            val H_ID = BaseColumns._ID
        }
    }
}