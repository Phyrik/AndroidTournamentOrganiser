package com.example.buchanan.tournamentorganiser

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import java.io.BufferedReader
import java.io.FileInputStream
import java.io.InputStreamReader

class ViewTournament : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_tournament)
        setSupportActionBar(findViewById(R.id.my_toolbar))

        val titleTextView = findViewById<TextView>(R.id.titleTextView)
        val fileInputStream = openFileInput("title")
        val inputStreamReader = InputStreamReader(fileInputStream)
        val bufferedReader = BufferedReader(inputStreamReader)
        val stringBuilder = StringBuilder()
        val text = bufferedReader.readLine()
        stringBuilder.append(text)
        titleTextView.text = stringBuilder.toString()
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
