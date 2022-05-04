package com.learningplatforms.myapplication.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.learningplatforms.myapplication.R
import com.learningplatforms.myapplication.databinding.ListCardViewBinding
import com.learningplatforms.myapplication.list.listmodelclass.Listitem

class listAdapter(val itemlist:ArrayList<Listitem>?=null,val listfield:Int):RecyclerView.Adapter<listAdapter.ListviewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListviewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_card_view,parent,false)
        return ListviewHolder(view)    }

    override fun onBindViewHolder(holder: ListviewHolder, position: Int) {
        val listitem = itemlist?.get(position)

         listitem?.item1?.let { holder.binding.tv1.text=it }
        listitem?.item2?.let { holder.binding.tv2.text=it }
        listitem?.item3?.let{holder.binding.tv3.text=it}
        listitem?.item4?.let { holder.binding.tv4.text=it  }

        if (listfield==1){
            holder.binding.tv1.visibility= View.VISIBLE
        }
        if (listfield==2){
            holder.binding.tv1.visibility= View.VISIBLE
            holder.binding.tv2.visibility=View.VISIBLE
        }
        if (listfield==3){
            holder.binding.tv1.visibility= View.VISIBLE
            holder.binding.tv2.visibility=View.VISIBLE
            holder.binding.tv3.visibility=View.VISIBLE
        }
        if (listfield==4){
            holder.binding.tv1.visibility= View.VISIBLE
            holder.binding.tv2.visibility=View.VISIBLE
            holder.binding.tv3.visibility=View.VISIBLE
            holder.binding.tv4.visibility=View.VISIBLE
        }



    }

    override fun getItemCount(): Int {
        return itemlist?.size?:0
    }

    inner class ListviewHolder(itemview:View):RecyclerView.ViewHolder(itemview) {
        val binding: ListCardViewBinding = ListCardViewBinding.bind(itemView)


    }
}