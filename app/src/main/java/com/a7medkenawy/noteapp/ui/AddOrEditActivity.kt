package com.a7medkenawy.noteapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import com.a7medkenawy.noteapp.databinding.ActivityAddOrEditBinding
import com.a7medkenawy.noteapp.model.Note
import com.a7medkenawy.noteapp.realmmananger.NoteManager

class AddOrEditActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddOrEditBinding
    val noteManager by lazy { NoteManager() }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddOrEditBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val id = System.currentTimeMillis().toString()
        val content = binding.addOrEditNoteEd.text.toString()

        saveOrUpdateNote(id, content)
    }

    private fun saveOrUpdateNote(id: String, content: String?) {
        binding.saveBtn.setOnClickListener {
            val note = Note(id, content)
            noteManager.addNote(note)
            finish()
        }

    }


}