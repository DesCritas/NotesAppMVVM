package com.descritas.notesappmvvm.database.firebase

import android.util.Log
import androidx.lifecycle.LiveData
import com.descritas.notesappmvvm.model.Note
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class AllNotesLiveData: LiveData<List<Note>>() {

    private val url = "https://notes-app-105d5-default-rtdb.europe-west1.firebasedatabase.app/"

    private val mAuth = FirebaseAuth.getInstance()
    private val database = Firebase.database(url).reference
        .child(mAuth.currentUser?.uid.toString())

    private val listener = object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            val notes = mutableListOf<Note>()
            snapshot.children.map{
                notes.add(it.getValue(Note::class.java) ?: Note() )
            }
            value = notes
        }

        override fun onCancelled(error: DatabaseError) {
            Log.d("checkData", "${error.message}")

        }

    }

    override fun onActive() {
        database.addValueEventListener(listener)
        super.onActive()
    }

    override fun onInactive() {
        database.removeEventListener(listener)
        super.onInactive()
    }
}
