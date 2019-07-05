package com.example.buchanan.tournamentorganiser

import android.content.DialogInterface
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.io.File

class ViewTournament : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_tournament)
        setSupportActionBar(findViewById(R.id.my_toolbar))

        val titleTextView = findViewById<TextView>(R.id.title_textview)
        val titleString = intent.getStringExtra("title")
        titleTextView.text = titleString

        val backButton = findViewById<Button>(R.id.back_button)
        backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val deleteButton = findViewById<Button>(R.id.delete_button)
        deleteButton.setOnClickListener {
            var fileToDelete: File? = File(this.filesDir, "/tournaments/$titleString/title.txt")
            fileToDelete?.delete()
            fileToDelete = File(this.filesDir, "/tournaments/$titleString")
            fileToDelete?.delete()
            fileToDelete = null

            val intent = Intent(this, MainActivity::class.java)
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
