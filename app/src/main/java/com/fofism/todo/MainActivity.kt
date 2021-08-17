package com.fofism.todo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.content.DialogInterface
import android.support.v7.app.AlertDialog
import android.widget.EditText
import android.database.sqlite.SQLiteDatabase
import com.fofism.todo.db.AtividadeContract
import android.content.ContentValues
import android.view.View
import android.widget.ListView
import com.fofism.todo.db.AtividadeDb
import android.widget.ArrayAdapter
import android.widget.TextView

class MainActivity : AppCompatActivity() {


    private lateinit var mHelper: AtividadeDb
    private lateinit var mTaskListView: ListView
    private var mAdapter: ArrayAdapter<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mTaskListView = findViewById(R.id.list_todo)

        mHelper = AtividadeDb(this)
        AtividadesUpdateUI()
    }

    fun deleteTask(view: View) {
        val parent = view.getParent() as View
        val taskTextView = parent.findViewById<TextView>(R.id.atividade_title)
        val task = taskTextView.text.toString()
        val db = mHelper.writableDatabase
        db.delete(AtividadeContract.Atividade.A_TABLE,
                AtividadeContract.Atividade.A_COL_ATIVIDADE_TITLE + " = ?",
                arrayOf(task))
        db.close()
        AtividadesUpdateUI()
    }

    private fun AtividadesUpdateUI() {
        val taskList = ArrayList<String>()
        val db = mHelper.readableDatabase
        val cursor = db.query(AtividadeContract.Atividade.A_TABLE,
                arrayOf(AtividadeContract.Atividade.A_ID, AtividadeContract.Atividade.A_COL_ATIVIDADE_TITLE), null, null, null, null, null)
        while (cursor.moveToNext()) {
            val idx = cursor.getColumnIndex(AtividadeContract.Atividade.A_COL_ATIVIDADE_TITLE)
            taskList.add(cursor.getString(idx))
        }

        if (mAdapter == null) {
            mAdapter = ArrayAdapter(this,
                    R.layout.item_todo,
                    R.id.atividade_title,
                    taskList)
            mTaskListView.adapter = mAdapter
        } else {
            mAdapter?.clear()
            mAdapter?.addAll(taskList)
            mAdapter?.notifyDataSetChanged()
        }

        cursor.close()
        db.close()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            R.id.action_add_task -> {
                val taskEditText = EditText(this)
                val dialog = AlertDialog.Builder(this)
                        .setTitle("Adiconar Uma Nova Atividade")
                        .setView(taskEditText)
                        .setPositiveButton("Adiconar", DialogInterface.OnClickListener { dialog, which ->
                            val task = taskEditText.text.toString()
                            val db = mHelper.getWritableDatabase()
                            val values = ContentValues()
                            values.put(AtividadeContract.Atividade.A_COL_ATIVIDADE_TITLE, task)
                            db.insertWithOnConflict(AtividadeContract.Atividade.A_TABLE,
                                    null,
                                    values,
                                    SQLiteDatabase.CONFLICT_REPLACE)
                            db.close()
                            AtividadesUpdateUI()
                        })
                        .setNegativeButton("Cancelar", null)
                        .create()
                dialog.show()
                return true
            }

            else -> return super.onOptionsItemSelected(item)
        }
    }
}
