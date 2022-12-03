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
    }

    // create Options Menu in top bar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_delete, menu)
        return super.onCreateOptionsMenu(menu)
    }

    //click button in options menu to delete all data in the database
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.deleteAll -> {
                noteManager.deleteAllFromRealm()
                noteAdapter.notifyDataSetChanged()
            }
        }
        return true
    }

    //initialize recycler view and passing data to it
    private fun setUpRecyclerView() {
        val notes = noteManager.getAllNotes
        noteAdapter = NoteAdapter(notes, object : NoteAdapter.OnCallBack {
            //this method is  used when you click the button more in each row of recycler view to delete it from database
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
            //when you click on note row you will go to (AddOrEditActivity) to update the note
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

    //add new Note
    private fun setUpFab() {
        binding.addNoteFab.setOnClickListener {
            val intent = Intent(this, AddOrEditActivity::class.java)
            startActivity(intent)
        }
    }

    //check  if recycler view is empty or not and update the data in recycler view
    override fun onStart() {
        super.onStart()
        if(noteManager.getAllNotes.size==0){
            binding.textView.visibility=View.VISIBLE
            binding.recyclerView.visibility=View.GONE
        }else{
            binding.textView.visibility=View.GONE
            binding.recyclerView.visibility=View.VISIBLE
            setUpRecyclerView()
            noteAdapter.notifyDataSetChanged()
        }

    }

}