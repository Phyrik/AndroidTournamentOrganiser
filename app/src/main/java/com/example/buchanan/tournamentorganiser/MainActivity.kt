package com.example.buchanan.tournamentorganiser

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import java.io.File

class MainActivity : AppCompatActivity() {

    var tournamentsArray = arrayOf<File>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.my_toolbar))

        val button = findViewById<Button>(R.id.new_tournament_button)

        button.setOnClickListener {
            val intent = Intent(this, NewTournamentPart1::class.java)
            startActivity(intent)
        }

        val tournamentListView = findViewById<ListView>(R.id.tournament_list)
        tournamentListView.adapter = TournamentListViewAdapter(this)

        File(this.filesDir, "").walk().forEach {
            tournamentsArray += it
        }
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

    private inner class TournamentListViewAdapter(context: Context) : BaseAdapter() {

        private val mContext: Context

        init {
            mContext = context
        }

        //responsible for number of rows in list
        override fun getCount(): Int {
            return 5
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getItem(position: Int): Any {
            return "TEST STRING"
        }

        //renders each row
        override fun getView(position: Int, convertView: View?, viewGroup: ViewGroup?): View {
            val layoutInflater = LayoutInflater.from(mContext)
            val rowMain = layoutInflater.inflate(R.layout.row_main, viewGroup, false)

            val mainTextView = rowMain.findViewById<TextView>(R.id.textView2)
            try {
                mainTextView.text = tournamentsArray[position].toString()
            } catch (e: Exception) {
                println(e)
            }

            val positionTextView = rowMain.findViewById<TextView>(R.id.position_textview)
            positionTextView.text = "Tournament number: $position"

            return rowMain
        }
    }
}
