package com.fofism.todo

import android.content.ContentValues
import android.content.DialogInterface
import android.database.sqlite.SQLiteDatabase
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import com.fofism.todo.db.HorarioDd
import com.fofism.todo.db.AtividadeContract
import com.fofism.todo.db.HorarioContract

class HorariosActivity : AppCompatActivity() {


    private lateinit var mHelper: HorarioDd
    private lateinit var mTaskListView: ListView
    private var mAdapter: ArrayAdapter<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_horarios)

        mTaskListView = findViewById(R.id.list_horario)

        mHelper = HorarioDd(this)
        HorariosUpdateUI()
    }
    fun deleteTask(view: View) {
        val parent = view.getParent() as View
        val taskTextView = parent.findViewById<TextView>(R.id.atividade_title)
        val task = taskTextView.text.toString()
        val db = mHelper.writableDatabase
        db.delete(HorarioContract.Horario.H_TABLE,
                HorarioContract.Horario.H_COL_HORARIO_TITLE + " = ?",
                arrayOf(task))
        db.close()
        HorariosUpdateUI()
    }

    private fun HorariosUpdateUI() {
        val taskList = ArrayList<String>()
        val db = mHelper.readableDatabase
        val cursor = db.query(HorarioContract.Horario.H_TABLE,
                arrayOf(HorarioContract.Horario.H_ID, HorarioContract.Horario.H_COL_HORARIO_TITLE), null, null, null, null, null)
        while (cursor.moveToNext()) {
            val idx = cursor.getColumnIndex(HorarioContract.Horario.H_COL_HORARIO_TITLE)
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
                        .setTitle("Adiconar Um Novo Horario")
                        .setView(taskEditText)
                        .setPositiveButton("Adiconar", DialogInterface.OnClickListener { dialog, which ->
                            val task = taskEditText.text.toString()
                            val db = mHelper.getWritableDatabase()
                            val values = ContentValues()
                            values.put(HorarioContract.Horario.H_COL_HORARIO_TITLE, task)
                            db.insertWithOnConflict(HorarioContract.Horario.H_TABLE,
                                    null,
                                    values,
                                    SQLiteDatabase.CONFLICT_REPLACE)
                            db.close()
                            HorariosUpdateUI()
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
