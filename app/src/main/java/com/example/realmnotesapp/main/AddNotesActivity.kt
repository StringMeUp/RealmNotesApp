package com.example.realmnotesapp.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.realmnotesapp.R
import com.example.realmnotesapp.databinding.ActivityAddNotesBinding
import com.example.realmnotesapp.model.NoteModel
import io.realm.Realm

class AddNotesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddNotesBinding
    private lateinit var realm: Realm
    private lateinit var notesObject: NoteModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_notes)
        realm = Realm.getDefaultInstance()
        addNotes()
        onPressCancel()
    }

    private fun onPressCancel() {
        binding.cancelButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun addNotes() {
        binding.addButton.setOnClickListener {

            try {
                realm.beginTransaction()
                val currentIdNumber: Number? = realm.where(NoteModel::class.java).max("id")
                val defaultId: Int
                defaultId = if (currentIdNumber == null) {
                    1
                } else {
                    currentIdNumber.toInt() + 1
                }

                notesObject = NoteModel()
                notesObject.title = binding.titleEditText.text.toString()
                notesObject.description = binding.descriptionEditTest.text.toString()
                notesObject.id = defaultId
                realm.copyToRealmOrUpdate(notesObject)
                realm.commitTransaction()
                Toast.makeText(
                    this@AddNotesActivity,
                    getString(R.string.note_success), Toast.LENGTH_SHORT
                )
                    .show()

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)

            } catch (e: Exception) {
                e.printStackTrace()
                Log.d("NOTES FAILED", "${e.printStackTrace()}")
            }
        }
    }

    override fun onBackPressed() {

    }
}
