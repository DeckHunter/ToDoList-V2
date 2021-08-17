package com.fofism.todo.db

import android.provider.BaseColumns

class ListaContract {
    companion object {
        val DB_NAME = "com.fofism.todo.db"
        val DB_VERSION = 1
    }

    class Lista : BaseColumns {

        companion object {
            val L_TABLE = "Listas"
            val L_COL_LISTA_TITLE = "title_Lista"
            val L_ID = BaseColumns._ID
        }
    }

}