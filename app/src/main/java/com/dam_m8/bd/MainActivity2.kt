package com.dam_m8.bd

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val btnNavigate: Button = findViewById(R.id.btinici)
        btnNavigate.setOnClickListener {
            val intent = Intent(this@MainActivity2, MainActivity::class.java)
            startActivity(intent)
        }

        val bproductes: Button = findViewById(R.id.btproductes)
        bproductes.setOnClickListener{
            val admin = DatabaseHandler(this,"tenda.db",null,1)
            val db = admin.readableDatabase
            val linearLayout: LinearLayout = findViewById(R.id.layout01)

            val registres = db.rawQuery("SELECT *  FROM productes",null)
            if (registres.moveToFirst()) {
                while(!registres.isAfterLast) {
                    val vtext = TextView(this)
                    vtext.text = registres.getString(0) + " -- " + registres.getString(1)
                    linearLayout.addView(vtext)
                    registres.moveToNext()
                }
            } else {
                Toast.makeText(this, "La base de dades és buida",  Toast.LENGTH_SHORT).show()
            }

            db.close()
            registres.close() // alliberem el cursor despres de tancar BD com indica ajuda
        }
    }
}