package com.learningplatforms.myapplication.ui.subject_details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.learningplatforms.myapplication.Extras
import com.learningplatforms.myapplication.databinding.ActivityListBinding
import com.learningplatforms.myapplication.databinding.FragmentSubjectDetailsBinding
import com.learningplatforms.myapplication.list.listmodelclass.ListViewmodel
import com.learningplatforms.myapplication.ui.home.Itemlist
import kotlinx.android.synthetic.main.activity_quiz.*

class Subject_DetailsActivity : AppCompatActivity() {
    lateinit var binding: FragmentSubjectDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentSubjectDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        Extras().setStatusBarGradiant(this@Subject_DetailsActivity)


        val b = intent.extras
    //    val q:List<X1> = b?.get("list") as List<X1>
        val name = b?.getString("name")
        Log.d("name",name.toString())
        supportActionBar?.title = name.toString()


        val imp= b?.get("amount")
        Log.d("msggg",imp.toString())
        var ArrayList:ArrayList<Itemlist> = ArrayList()
        ArrayList = imp as ArrayList<Itemlist>


        binding.detailsrecycler.apply {
            adapter = SubjectDetailsAdapter(ArrayList, this@Subject_DetailsActivity)
            layoutManager= LinearLayoutManager(this@Subject_DetailsActivity)
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}