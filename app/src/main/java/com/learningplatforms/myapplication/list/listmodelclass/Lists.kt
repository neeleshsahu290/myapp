package com.learningplatforms.myapplication.list.listmodelclass

data class Lists(
    val listid: String?=null,
    val listfield:Int?=null,
    val listitem: ArrayList<Listitem>?=null,
    val listtitles: ArrayList<String>?=null
)
