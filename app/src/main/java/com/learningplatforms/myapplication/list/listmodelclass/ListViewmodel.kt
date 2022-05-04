package com.learningplatforms.myapplication.list.listmodelclass

import android.content.ContentValues
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
import com.learningplatforms.myapplication.quiz.Question
import com.learningplatforms.myapplication.quiz.quizes
import kotlinx.coroutines.launch
import java.lang.Exception

class ListViewmodel: ViewModel() {
    val error: MutableLiveData<String>?=null
    private val _listss: MutableLiveData<Lists> = MutableLiveData()
    val listss: LiveData<Lists> get() = _listss

    private val database = Firebase.database

    private val myRef = database.reference.child("users").child("list")



    fun getLists(Listid:String){

        viewModelScope.launch {
            try{
                myRef.addValueEventListener(object: ValueEventListener {
                    val list:ArrayList<Lists> = ArrayList()
                    var list2:ArrayList<Question> = ArrayList()
                    override fun onDataChange(snapshot: DataSnapshot) {
                        snapshot.children.forEach {
                            it.value
                            val one= it.getValue<Lists>()
                            one?.let { list.add(it)
                                for (i in 0 until list.size){
                                    if (list[i].listid==Listid){
                                        val l= list[i]
                                        _listss.postValue(l)
                                    }
                                }}

                            Log.d("list123", one.toString())
                        }
                    }


                    override fun onCancelled(error: DatabaseError) {
                        Log.w(ContentValues.TAG, "Failed to read value.", error.toException())
                    }

                })}
            catch (e: Exception){
                Log.e("snapshot_error",e.toString())
            }

        }


    }
}
