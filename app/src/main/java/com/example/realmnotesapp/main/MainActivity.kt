package com.example.realmnotesapp.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.realmnotesapp.R
import com.example.realmnotesapp.adapters.NotesAdapter
import com.example.realmnotesapp.databinding.ActivityMainBinding
import com.example.realmnotesapp.model.NoteModel
import io.realm.Realm
import io.realm.RealmResults

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var realm: Realm
    private lateinit var results: RealmResults<NoteModel>
    private lateinit var notesAdapter: NotesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_main
        )
        actionMove()
        displaySavedNotes()
    }

    private fun actionMove() {
        binding.fab.setOnLongClickListener {
            val intent = Intent(this@MainActivity, AddNotesActivity::class.java)
            startActivity(intent)
            return@setOnLongClickListener true
        }
    }

    private fun displaySavedNotes() {
        try {
            realm = Realm.getDefaultInstance()
            results = realm.where(NoteModel::class.java).findAllAsync()
            results.load()

            if (results.size > 0) {
                binding.notesRecyclerView.apply {
                    layoutManager = LinearLayoutManager(this@MainActivity)
                    val notesArray = ArrayList<NoteModel>(results)
                    notesAdapter = NotesAdapter(notesArray)
                    adapter = notesAdapter
                }
            } else {
                Toast.makeText(
                    this@MainActivity,
                    "Results are empty", Toast.LENGTH_SHORT
                )
                    .show()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Log.d("NOTESNOTSAVED", "displaySavedNotes: ${e.printStackTrace()}")
        }
    }
}