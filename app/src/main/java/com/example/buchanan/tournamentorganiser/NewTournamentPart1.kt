package com.example.buchanan.tournamentorganiser

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import java.io.File

class NewTournamentPart1 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_tournament_part1)
        setSupportActionBar(findViewById(R.id.my_toolbar))

        val titleEditText = findViewById<EditText>(R.id.editText)
        val titleString = titleEditText.text.toString()
        val button2 = findViewById<Button>(R.id.nextButton)

        button2.setOnClickListener {
            val intent = Intent(this, ViewTournament::class.java)
            //intent.putExtra("title", titleString)
            val titleFile = File(this.filesDir, "title")
            this.openFileOutput("title", Context.MODE_PRIVATE).use {
                it.write(titleString.toByteArray())
            }
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_settings -> {
            true
        }

        else -> {
            super.onOptionsItemSelected(item)
        }
    }
}
