package com.fofism.todo

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import kotlinx.android.synthetic.main.activity_principal.*

class Principal : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal)

        var atividade = findViewById<Button>(R.id.btn_Atividades)
        var lista = findViewById<Button>(R.id.btn_Lista_De_Compras)
        var horario = findViewById<Button>(R.id.btn_Horarios)


        atividade.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java).apply {
            }
            startActivity(intent)
        }

        lista.setOnClickListener{
            val intent = Intent(this, ListaActivity::class.java).apply {
            }
            startActivity(intent)
        }

        horario.setOnClickListener{
            val intent = Intent(this, HorariosActivity::class.java).apply {
            }
            startActivity(intent)
        }

    }
}
