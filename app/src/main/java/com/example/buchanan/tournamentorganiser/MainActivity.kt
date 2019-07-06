package com.example.buchanan.tournamentorganiser

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.Button
import android.widget.TextView
import java.io.BufferedReader
import java.io.File
import java.io.FileInputStream
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {

    var tournamentsNamesArray = arrayOf<File>()
    var participantsArray = arrayOf<File>()
    var currentTitleFile: File? = null
    var currentParticipantsFile: File? = null
    var titleString: String? = null
    var participantsString: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.my_toolbar))

        val button = findViewById<Button>(R.id.new_tournament_button)

        button.setOnClickListener {
            val intent = Intent(this, NewTournamentPart1::class.java)
            startActivity(intent)
        }

        File(this.filesDir, "/tournaments/").walk().forEach {
            if (it.isFile && it.name == "title.txt") {
                tournamentsNamesArray += it
            }

            if (it.isFile && it.name == "participants.txt") {
                participantsArray += it
            }
        }

        val tournamentsRecyclerView = findViewById<RecyclerView>(R.id.tournament_recyclerview)
        tournamentsRecyclerView.layoutManager = LinearLayoutManager(this)
        tournamentsRecyclerView.adapter = TournamentsRecyclerViewAdapter()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_settings -> {
            val intent = Intent(this, Settings::class.java)
            startActivity(intent)
            true
        }

        else -> {
            super.onOptionsItemSelected(item)
        }
    }

    inner class TournamentsRecyclerViewAdapter: RecyclerView.Adapter<TournamentsRecyclerViewAdapterViewHolder>() {
        override fun getItemCount(): Int {
            println("Number of tournaments: " + tournamentsNamesArray.size)
            return tournamentsNamesArray.size
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TournamentsRecyclerViewAdapterViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val tournamentRow = layoutInflater.inflate(R.layout.tournament_row, parent, false)

            return TournamentsRecyclerViewAdapterViewHolder(tournamentRow)
        }

        override fun onBindViewHolder(holder: TournamentsRecyclerViewAdapterViewHolder, position: Int) {
            val mainTextView = holder.itemView.findViewById<TextView>(R.id.title_textview)
            val participantsTextView = holder.itemView.findViewById<TextView>(R.id.participants_textview)
            try {
                currentTitleFile = tournamentsNamesArray[position]
                val fileInputStream = FileInputStream(currentTitleFile?.path)
                val inputStreamReader = InputStreamReader(fileInputStream)
                val bufferedReader = BufferedReader(inputStreamReader)
                val stringBuilder = StringBuilder()
                val text = bufferedReader.readLine()
                stringBuilder.append(text)
                titleString = stringBuilder.toString()

                println(titleString)
                mainTextView.text = titleString

                currentParticipantsFile = participantsArray[position]
                val fileInputStream1 = FileInputStream(currentParticipantsFile?.path)
                val inputStreamReader1 = InputStreamReader(fileInputStream1)
                val bufferedReader1 = BufferedReader(inputStreamReader1)
                val stringBuilder1 = StringBuilder()
                val text1 = bufferedReader1.readLine()
                stringBuilder1.append(text1)
                participantsString = stringBuilder1.toString()
                participantsString = participantsString.toString().replace("[", "")
                participantsString = participantsString.toString().replace("]", "")

                println(participantsArray)
                participantsTextView.text = "Participants: " + participantsString
            } catch (e: Exception) {
                println(e)
            }

            val viewButton = holder.itemView.findViewById<TextView>(R.id.view_button)
            viewButton.setOnClickListener {
                val intent = Intent(holder.itemView.context, ViewTournament::class.java)
                intent.putExtra("title", titleString)
                intent.putExtra("participants", participantsString)
                startActivity(intent)
            }
        }
    }

    class TournamentsRecyclerViewAdapterViewHolder(v: View): RecyclerView.ViewHolder(v) {

    }
}
