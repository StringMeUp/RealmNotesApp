package com.example.realmnotesapp.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
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
        notifyUser()
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

    private fun notifyUser() {
        binding.fab.setOnClickListener {
            val alertDialog = AlertDialog.Builder(this@MainActivity)
            alertDialog.setCancelable(true)
            alertDialog.setMessage(resources.getString((R.string.hold_button)))
            alertDialog.setTitle(getString(R.string.notification))
            alertDialog.show()
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
                val alertDialog = AlertDialog.Builder(this@MainActivity)
                alertDialog.setCancelable(true)
                alertDialog.setMessage(resources.getString(R.string.result_empty))
                alertDialog.setTitle(getString(R.string.notification))
                alertDialog.show()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Log.d("NOTES NOT SAVED", "displaySavedNotes: ${e.printStackTrace()}")
        }
    }
}