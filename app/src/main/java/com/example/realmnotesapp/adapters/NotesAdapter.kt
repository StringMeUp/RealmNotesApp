package com.example.realmnotesapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.realmnotesapp.R
import com.example.realmnotesapp.databinding.NotesCardBinding
import com.example.realmnotesapp.model.NoteModel

class NotesAdapter(
    private val list: ArrayList<NoteModel>
) :
    RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    class NotesViewHolder(private val binding: NotesCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(note: NoteModel) {
            binding.idTextView.text = note.id.toString()
            binding.titleTextView.text = note.title
            binding.descriptionTextView.text = note.description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val notesBinding = DataBindingUtil.inflate<NotesCardBinding>(
            LayoutInflater.from(parent.context),
            R.layout.notes_card, parent, false
        )
        return NotesViewHolder(notesBinding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        holder.bind(list[position])
    }
}