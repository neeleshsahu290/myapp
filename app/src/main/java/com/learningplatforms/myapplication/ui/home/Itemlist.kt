package com.learningplatforms.myapplication.ui.home

import android.os.Parcelable
import java.io.Serializable

data class Itemlist(
    val listid: String?=null,
    val quizid: String?=null,
    val quizname: String?=null
):Serializable