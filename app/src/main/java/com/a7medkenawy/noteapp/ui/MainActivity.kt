package com.a7medkenawy.noteapp.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.LinearLayoutManager
import com.a7medkenawy.noteapp.R
import com.a7medkenawy.noteapp.adapter.NoteAdapter
import com.a7medkenawy.noteapp.databinding.ActivityMainBinding
import com.a7medkenawy.noteapp.model.Note
import com.a7medkenawy.noteapp.realmmananger.NoteManager


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var noteAdapter: NoteAdapter
    private val noteManager by lazy { NoteManager() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpFab()
        setUpRecyclerView()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(com.a7medkenawy.noteapp.R.menu.menu_delete, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.deleteAll -> {
                noteManager.deleteAllFromRealm()
                noteAdapter.notifyDataSetChanged()
            }
        }
        return true
    }

    private fun setUpRecyclerView() {
        val notes = noteManager.getAllNotes
        noteAdapter = NoteAdapter(notes, object : NoteAdapter.OnCallBack {
            override fun onCallBack(view: View, note: Note) {
                val popupMenu = PopupMenu(this@MainActivity, view)
                popupMenu.menuInflater.inflate(R.menu.delete_one_item, popupMenu.menu)
                popupMenu.setOnMenuItemClickListener {
                    noteManager.deleteFromRealm(note.id!!)
                    noteAdapter.notifyDataSetChanged()
                    true
                }
                popupMenu.show()
            }

            override fun onItemSelected(note: Note) {
                val intent = Intent(applicationContext, AddOrEditActivity::class.java)
                intent.putExtra("myNote", note)
                startActivity(intent)
            }

        })
        noteManager.getAllNotes.addChangeListener { notes, _ ->
            noteAdapter.setData(notes)
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = noteAdapter
    }

    private fun setUpFab() {
        binding.addNoteFab.setOnClickListener {
            val intent = Intent(this, AddOrEditActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        noteAdapter.notifyDataSetChanged()
    }

}