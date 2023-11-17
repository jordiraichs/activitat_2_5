package com.dam_m8.bd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import android.content.ContentValues
import android.content.Intent
import android.widget.Button

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnNavigate: Button = findViewById(R.id.btnNavigate)
        btnNavigate.setOnClickListener {
            // Creamos un Intent para iniciar SecondActivity
            val intent = Intent(this@MainActivity, MainActivity2::class.java)
            startActivity(intent)
        }


    }

    fun inserirProducte(v: View) {
        val admin = DatabaseHandler(this,"tenda.db",null,1)
        val db = admin.writableDatabase

        val codi = findViewById<EditText>(R.id.et1)
        val producte = findViewById<EditText>(R.id.et2)

        val registre = ContentValues()
        registre.put("codi",codi.text.toString())
        registre.put("nom",producte.text.toString())

        try {
            if (db.insert("productes", null, registre).toInt() > 0) {
                Toast.makeText(this, "S'ha inserit el producte", Toast.LENGTH_SHORT).show()
            } else Toast.makeText(this, "Ja existeix el producte", Toast.LENGTH_SHORT).show()
        } catch (e: Exception){
            Toast.makeText(this, "Excepció al inserir" + e.message , Toast.LENGTH_SHORT).show()
        }

        db.close()

        codi.setText("")
        producte.setText("")
    }

    fun consultarProducte(v: View) {
        val admin = DatabaseHandler(this,"tenda.db",null,1)
        val db = admin.readableDatabase

        val codi = findViewById<EditText>(R.id.et3)
        val txt = findViewById<TextView>(R.id.txt1)
        txt.setText("")

        val resultat = db.rawQuery("SELECT *  FROM productes WHERE codi ='" + codi.text.toString() + "'",null)
        if (resultat.moveToFirst()) {
            txt.text = resultat.getString(0) + " -- " + resultat.getString(1)
        } else {
            Toast.makeText(this, "No existeix l'article",  Toast.LENGTH_SHORT).show()
        }

        db.close()
        resultat.close() // alliberem el cursor despres de tancar BD com indica ajuda

        codi.setText("")
    }

    fun eliminarProducte(v: View){
        val admin = DatabaseHandler(this,"tenda.db",null,1)
        val db = admin.writableDatabase

        val codi = findViewById<EditText>(R.id.et3)

        if(db.delete("productes","codi = ${codi.text.toString()}",null)>0){
            Toast.makeText(this, "S'ha eliminat l'article",  Toast.LENGTH_SHORT).show()
        } else Toast.makeText(this, "No existeix l'article",  Toast.LENGTH_SHORT).show()

        db.close()

        codi.setText("")
    }

}

