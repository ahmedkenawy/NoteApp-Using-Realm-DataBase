package com.a7medkenawy.noteapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.a7medkenawy.noteapp.R
import com.a7medkenawy.noteapp.model.Note
import io.realm.RealmResults

class NoteAdapter(var notes: RealmResults<Note>,val onCallBack: OnCallBack) :
    RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    fun setData(notes: RealmResults<Note>) {
        this.notes = notes
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.note_item, null, false)
        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note=notes[position]!!
        holder.content.text = note.content ?:"A7A"
        holder.btnMore.setOnClickListener { onCallBack.onCallBack(holder.btnMore, notes[position]!!) }
        holder.itemView.setOnClickListener { onCallBack.onItemSelected(notes[position]!!) }
    }

    override fun getItemCount(): Int = notes.size


    class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val content:TextView = itemView.findViewById(R.id.noteContent)
        val btnMore: ImageView = itemView.findViewById(R.id.btn_more)
    }

    interface OnCallBack{
        fun onCallBack(view: View,note:Note)
        fun onItemSelected(note: Note)
    }
}