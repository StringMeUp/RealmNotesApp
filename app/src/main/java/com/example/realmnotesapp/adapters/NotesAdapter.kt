package com.example.realmnotesapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.realmnotesapp.R
import com.example.realmnotesapp.model.NoteModel
import com.example.realmnotesapp.util.MyApp

class NotesAdapter(val list: ArrayList<NoteModel>) :
    RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    class NotesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(note: NoteModel) {
            val id = itemView.findViewById<TextView>(R.id.id_text_view)
            id.typeface = ResourcesCompat.getFont(MyApp.appContext, R.font.futurabold)
            val title = itemView.findViewById<TextView>(R.id.title_text_view)
            title.typeface = ResourcesCompat.getFont(MyApp.appContext, R.font.futuraheavy)
            val description = itemView.findViewById<TextView>(R.id.description_text_view)
            description.typeface = ResourcesCompat.getFont(MyApp.appContext, R.font.futurabook)
            id.text = note.id.toString()
            title.text = note.title.toString()
            description.text = note.description.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.notes_card, parent, false)
        return NotesViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        holder.bind(list[position])
    }
}