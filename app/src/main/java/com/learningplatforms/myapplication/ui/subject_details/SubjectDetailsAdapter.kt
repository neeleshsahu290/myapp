package com.learningplatforms.myapplication.ui.subject_details

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.learningplatforms.myapplication.R
import com.learningplatforms.myapplication.databinding.DetailsCardViewBinding
import com.learningplatforms.myapplication.databinding.HomeCardViewBinding
import com.learningplatforms.myapplication.list.ListActivity
import com.learningplatforms.myapplication.quiz.QuizActivity
import com.learningplatforms.myapplication.ui.home.Itemlist
import com.learningplatforms.myapplication.ui.home.Subjects


class SubjectDetailsAdapter(var itemlist: ArrayList<Itemlist>?=null, var context:Context):RecyclerView.Adapter<SubjectDetailsAdapter.SubjectDetailsviewholder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectDetailsviewholder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.details_card_view,parent,false)
        return SubjectDetailsviewholder(view)
    }

    override fun onBindViewHolder(holder: SubjectDetailsviewholder, position: Int) {

        val subjects = itemlist?.get(position)
        holder.binding.txtdetails.text= subjects?.quizname
        if ( subjects?.quizid != "" ){
            holder.binding.imgdetails.setOnClickListener {
                val intent= Intent(context,QuizActivity::class.java)
                intent.putExtra("name",subjects?.quizname)
                intent.putExtra("quizid",subjects?.quizid)
                context.startActivity(intent)

            }
            Log.d("msgres","true")
        }else{
            Log.d("msgres","false")

        }
        if ( subjects?.listid != "" ){
            holder.itemView.setOnClickListener {
                val intent= Intent(context,ListActivity::class.java)
                intent.putExtra("listid",subjects?.listid)
                intent.putExtra("name",subjects?.quizname)
                context.startActivity(intent)

            }
            Log.d("msgres","true")
        }



    }

    override fun getItemCount(): Int {
        return itemlist?.size?:0
    }


    inner class SubjectDetailsviewholder(itemview: View):RecyclerView.ViewHolder(itemview) {
        val binding = DetailsCardViewBinding.bind(itemView)

    }

}

