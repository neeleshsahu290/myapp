package com.learningplatforms.myapplication.quiz

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch
import java.lang.Exception

class QuizViewModel : ViewModel() {
    val error: MutableLiveData<String>?=null
    private val _subject: MutableLiveData<ArrayList<Question>> = MutableLiveData()
    val subject: LiveData<ArrayList<Question>> get() = _subject

   private val database = Firebase.database

    private val myRef = database.reference.child("users").child("quiz")



    fun getQuizes(Quizid:String){

        viewModelScope.launch {
            try{
            // Read from the database
            myRef.addValueEventListener(object: ValueEventListener {
                val list:ArrayList<quizes> = ArrayList()
                var list2:ArrayList<Question> = ArrayList()
                override fun onDataChange(snapshot: DataSnapshot) {
                    snapshot.children.forEach {
                        it.value
                         val one= it.getValue<quizes>()
                        one?.let { list.add(it)
                        for (i in 0 until list.size){
                            if (list[i].quizid==Quizid){
                                list2= list[i].quizlist as ArrayList<Question>
                            }
                        }}
                        /*
                        one?.let { it1 -> list.add(it1)
                        Log.d("error",it1.toString())}*/
                        Log.d("list123", one.toString())
                    }
                          _subject.postValue(list2 )
                    }


                override fun onCancelled(error: DatabaseError) {
                    Log.w(TAG, "Failed to read value.", error.toException())
                }

            })}
            catch (e:Exception){
                Log.e("snapshot_error",e.toString())
            }

        }


    }
}