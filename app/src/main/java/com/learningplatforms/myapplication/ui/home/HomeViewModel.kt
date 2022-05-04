package com.learningplatforms.myapplication.ui.home

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch
import java.lang.Exception

class HomeViewModel : ViewModel() {
    val error: MutableLiveData<String>?=null
    private val _subject: MutableLiveData<ArrayList<Subjects>> = MutableLiveData()
    val subject: LiveData<ArrayList<Subjects>> get() = _subject

    // Write a message to the database
    val database = Firebase.database

    val myRef = database.reference.child("users").child("subject")



    fun getSubjects(){
        viewModelScope.launch {
            try {



            // Read from the database
            myRef.addValueEventListener(object: ValueEventListener {
                val list:ArrayList<Subjects> = ArrayList()
                override fun onDataChange(snapshot: DataSnapshot) {
                    snapshot.children.forEach {
                    val one= it.getValue<Subjects>()
                        one?.let { it1 -> list.add(it1) }
                    }
                    Log.d("list",list.toString())
                    _subject.postValue(list )

                    Log.d("list",list.toString())
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.w(TAG, "Failed to read value.", error.toException())
                }

            })
            }catch (e:Exception){

            }

        }


    }
}