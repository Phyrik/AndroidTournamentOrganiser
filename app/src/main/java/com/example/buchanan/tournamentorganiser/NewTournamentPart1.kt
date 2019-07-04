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
import java.io.FileOutputStream

class NewTournamentPart1 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_tournament_part1)
        setSupportActionBar(findViewById(R.id.my_toolbar))

        val titleEditText = findViewById<EditText>(R.id.editText)
        val button2 = findViewById<Button>(R.id.nextButton)

        button2.setOnClickListener {
            val titleString = titleEditText.text.toString()
            val intent = Intent(this, ViewTournament::class.java)
            val fileName = "title"
            val titleFileFolders = File(this.filesDir, "tournaments/$titleString/")
            titleFileFolders.mkdirs()
            val titleFile = File(titleFileFolders, "$fileName.txt")
            titleFile.appendText(titleString)
            intent.putExtra("title", titleString)
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
