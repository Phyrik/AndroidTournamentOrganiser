package com.example.buchanan.tournamentorganiser

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import java.io.File

class NewTournamentPart1 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_tournament_part1)
        setSupportActionBar(findViewById(R.id.my_toolbar))

        val titleEditText = findViewById<EditText>(R.id.title_edittext)
        val nextButton = findViewById<Button>(R.id.next_button)
        nextButton.setOnClickListener {
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

        val participantLinearLayout = findViewById<LinearLayout>(R.id.participants_linearlayout)
        val addParticipantButton = findViewById<Button>(R.id.add_participant_button)
        var numberOfParticipants = 1
        addParticipantButton.setOnClickListener {
            numberOfParticipants++
            val participantEditText = EditText(this)
            participantEditText.hint = getString(R.string.participant)
            participantEditText.id = View.generateViewId()
            participantLinearLayout.addView(participantEditText)
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
