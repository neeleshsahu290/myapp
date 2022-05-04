package com.learningplatforms.myapplication.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.learningplatforms.myapplication.R
import com.learningplatforms.myapplication.databinding.HomeCardViewBinding
import com.learningplatforms.myapplication.ui.subject_details.Subject_DetailsActivity


class HomeAdapter(var itemlist: ArrayList<Subjects>?=null , var context:Context):RecyclerView.Adapter<HomeAdapter.Homeviewholder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Homeviewholder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.home_card_view,parent,false)
        return Homeviewholder(view)
    }

    override fun onBindViewHolder(holder: Homeviewholder, position: Int) {

        val subjects = itemlist?.get(position)
        holder.binding.SubText.text= subjects?.name
        val list= subjects?.itemlist   as ArrayList<Itemlist>

        val url = subjects?.image

        Glide
            .with(context)
            .load(url)
            .fitCenter()
            .placeholder(R.drawable.paceholder)
            .into(holder.binding.subjectImg)
        holder.itemView.setOnClickListener {
            val intent = Intent(context,Subject_DetailsActivity::class.java)
            intent.putExtra("name",subjects?.name)
            intent.putExtra("amount", list )

            context.startActivity(intent)
            //val bundle = Bundle()
            //bundle.putString("link", "http://yourlink.com/policy")

          /*  val bundle = bundleOf("amount" to list)



            Navigation.findNavController(it)
                .navigate(R.id.action_navigation_home_to_subjectDetailsFragment,bundle)*/
        }

    }

    override fun getItemCount(): Int {
        return itemlist?.size?:0
    }


    inner class Homeviewholder(itemview: View):RecyclerView.ViewHolder(itemview) {
        val binding: HomeCardViewBinding = HomeCardViewBinding.bind(itemView)

    }

}

