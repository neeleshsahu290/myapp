package com.learningplatforms.myapplication.list

import android.graphics.Typeface
import android.os.Bundle
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.learningplatforms.myapplication.Extras
import com.learningplatforms.myapplication.R
import com.learningplatforms.myapplication.databinding.ActivityListBinding
import com.learningplatforms.myapplication.list.listmodelclass.ListViewmodel
import kotlinx.android.synthetic.main.activity_quiz.*


class ListActivity : AppCompatActivity() {
    lateinit var binding: ActivityListBinding
    lateinit var viewmodel: ListViewmodel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        Extras().setStatusBarGradiant(this@ListActivity)
        val name = intent.getStringExtra("name")
        supportActionBar?.title= name.toString()
        val listid=intent.getStringExtra("listid")
        binding.recyclerview.layoutManager= LinearLayoutManager(this)
        viewmodel= ViewModelProvider(this)[ListViewmodel::class.java]
        listid?.let { viewmodel.getLists(it) }
        viewmodel.listss.observe(this){
            if (it.listtitles != null && it.listtitles.size > 0){
                    addview(it.listtitles)
            }
            if (it.listitem!=null && it.listitem.size>0 && it.listfield !=null){
                binding.recyclerview.adapter= listAdapter(it.listitem,it.listfield)
            }
        }

        }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }




    fun addview(list:ArrayList<String>) {

        for (i in 0 until list.size) {
            val tv = TextView(this).apply {
                layoutParams = LinearLayout.LayoutParams(
                    0,
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    1F
                ).apply {
                    setMargins(0, 0, 0, 0)
                    setPadding(2, 2, 2, 2)
                    setGravity(Gravity.CENTER or Gravity.CENTER_VERTICAL)
                }
                text = list[i]
                val background = ContextCompat.getDrawable(this@ListActivity, R.drawable.listitems)
                setBackgroundDrawable(background)

                setTextColor(ContextCompat.getColor(this@ListActivity, R.color.black))
                textSize = 16.0f
                typeface = Typeface.defaultFromStyle(Typeface.BOLD)
            }


            binding.linearLayout.addView(tv)
        }
    }

}