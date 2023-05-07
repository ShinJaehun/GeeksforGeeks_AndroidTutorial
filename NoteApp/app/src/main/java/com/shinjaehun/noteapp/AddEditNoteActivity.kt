package com.shinjaehun.noteapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import java.text.SimpleDateFormat
import java.util.*

class AddEditNoteActivity : AppCompatActivity() {

    lateinit var noteTitleEdt: EditText
    lateinit var noteDescriptionEdt: EditText
    lateinit var addUpdateBtn: Button
    lateinit var viewModel: NoteViewModel
    var noteId = -1;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit_note)

        noteTitleEdt = findViewById(R.id.etNoteTitle)
        noteDescriptionEdt = findViewById(R.id.etNoteDescription)
        addUpdateBtn = findViewById(R.id.btnAddUpdate)

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(NoteViewModel::class.java)

        val noteType = intent.getStringExtra("noteType")
        if (noteType.equals("Edit")) {
            val noteTitle = intent.getStringExtra("noteTitle")
            val noteDesc = intent.getStringExtra("noteDescription")
            noteId = intent.getIntExtra("noteId", -1)
            addUpdateBtn.setText("Update Note")
            noteTitleEdt.setText(noteTitle)
            noteDescriptionEdt.setText(noteDesc)
        } else {
            addUpdateBtn.setText("Save Note")
        }

        addUpdateBtn.setOnClickListener {
            val noteTitle = noteTitleEdt.text.toString()
            val noteDesc = noteDescriptionEdt.text.toString()
            if (noteType.equals("Edit")) {
                if (noteTitle.isNotEmpty() && noteDesc.isNotEmpty()) {
                    val sdf = SimpleDateFormat("yyyy MMM dd - HH:mm")
                    val currentDate:String = sdf.format(Date())
                    val updateNote = Note(noteTitle, noteDesc, currentDate)
                    updateNote.id = noteId
                    viewModel.updateNote(updateNote)
                    Toast.makeText(this, "Note updated...", Toast.LENGTH_LONG).show()
                }
            } else {
                if (noteTitle.isNotEmpty() && noteDesc.isNotEmpty()) {
                    val sdf = SimpleDateFormat("yyyy MMM dd - HH:mm")
                    val currentDate:String = sdf.format(Date())
                    viewModel.addNote(Note(noteTitle, noteDesc, currentDate))
                    Toast.makeText(this, "Note added...", Toast.LENGTH_LONG).show()
                }
            }
            startActivity(Intent(applicationContext, MainActivity::class.java))
            this.finish()
        }
    }
}