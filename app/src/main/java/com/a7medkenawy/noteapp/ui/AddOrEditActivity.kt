package com.a7medkenawy.noteapp.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.a7medkenawy.noteapp.databinding.ActivityAddOrEditBinding
import com.a7medkenawy.noteapp.model.Note
import com.a7medkenawy.noteapp.realmmananger.NoteManager

class AddOrEditActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddOrEditBinding
    private val realmManager: NoteManager by lazy { NoteManager() }
    var note: Note? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddOrEditBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val intent = intent.extras
        if (intent != null) {
            fillEditText(intent)
        }
        saveOrUpdateNote()


    }

    private fun fillEditText(intent: Bundle) {
        binding.saveBtn.text = "Update"
        note = intent.getParcelable("myNote")!!
        binding.addOrEditNoteEd.setText(note!!.content)
    }

    private fun saveOrUpdateNote() {
        binding.saveBtn.setOnClickListener {
            var id = if (intent.extras != null)
                note!!.id
            else
                System.currentTimeMillis().toString()
            val note = Note(id, binding.addOrEditNoteEd.text.trim().toString())
            realmManager.addNoteOrUpdate(note)
            finish()
        }

    }


}