package com.learningplatforms.myapplication.quiz

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.learningplatforms.myapplication.Constaints
import com.learningplatforms.myapplication.Extras
import com.learningplatforms.myapplication.R
import kotlinx.android.synthetic.main.activity_quiz.*


class QuizActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var viewmodel:QuizViewModel

    var mCurrentposition:Int =1
    var mquestionlist : ArrayList<Question>? = null
    var mselectedoptionposition : Int = 0
    var mcorrectanswer: Int = 0
    var musername : String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)
        //val toolbar= findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        Extras().setStatusBarGradiant(this@QuizActivity)
        val name = intent.getStringExtra("name")
        supportActionBar?.title= name.toString()
        val quizid = intent.getStringExtra("quizid")
        val plyt=findViewById<ConstraintLayout>(R.id.progresslyt)
        viewmodel= ViewModelProvider(this)[QuizViewModel::class.java]
        if (quizid != null) {
            viewmodel.getQuizes(Quizid = quizid)
        }
        title="Quiz"
        musername = intent.getStringExtra(Constaints.User_name)
        viewmodel.subject.observe(this){
            mquestionlist=it
            if (mquestionlist!=null ) {
                if (mquestionlist!!.size > 0) {
                    setQuestion()
                    txtoptionone.setOnClickListener(this)
                    txtoptiontwo.setOnClickListener(this)
                    txtoptionthree.setOnClickListener(this)
                    txtoptionfour.setOnClickListener(this)
                    btnsubmit.setOnClickListener(this)
                    try {
                        plyt.visibility = View.GONE
                        quizlyt.visibility = View.VISIBLE
                    } catch (e: Exception) {
                        Log.d("error123", e.toString())
                    }

                }
            }
        }




    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    fun setQuestion(){
        val question = mquestionlist?.get(mCurrentposition-1)
        defaultoptionsview()
        if (mCurrentposition==mquestionlist?.size){
            btnsubmit.text = "Finish"
        }else{
            btnsubmit.text="Submit"
        }


        progressbar.progress = mCurrentposition
        progressbar.max = mquestionlist?.size?:0
        tv_progress.text = "$mCurrentposition"+ "/"+ mquestionlist?.size
        tv_question.text= question?.Question
        txtoptionone.text= question?.Optionone
        txtoptiontwo.text= question?.Optiontwo
        txtoptionthree.text= question?.OPtionthree
        txtoptionfour.text=question?.OPtionfour
    }
    fun defaultoptionsview(){
        val options = ArrayList<TextView>()
        options.add(0,txtoptionone)
        options.add(1,txtoptiontwo)
        options.add(2,txtoptionthree)
        options.add(3,txtoptionfour)
        for (option in options){
            option.setTextColor(Color.parseColor("#7A8686"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(this,
                R.drawable.default_option_border_bg
            )
        }
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.txtoptionone ->{
                selectedoptionview(txtoptionone,1)
            }
            R.id.txtoptiontwo ->{
                selectedoptionview(txtoptiontwo,2)
            }
            R.id.txtoptionthree ->{
                selectedoptionview(txtoptionthree,3)
            }
            R.id.txtoptionfour ->{
                selectedoptionview(txtoptionfour,4)
            }
            R.id.btnsubmit ->{
                if (mselectedoptionposition==0){
                    mCurrentposition++
                    when{
                        mCurrentposition<=mquestionlist!!.size->{
                            setQuestion()
                        }else->{
                        val intent = Intent(this, ResultActivity::class.java)
                        intent.putExtra(Constaints.User_name,musername)
                        intent.putExtra(Constaints.correct_answers,mcorrectanswer)
                        intent.putExtra(Constaints.total_question,mquestionlist?.size)
                        startActivity(intent)
                        finish()
                    }
                    }
                }else{
                    val question= mquestionlist?.get(mCurrentposition-1)
                    if (question!!.Correctoption != mselectedoptionposition){
                        answerview(mselectedoptionposition,
                            R.drawable.wrong_option_border_bg
                        )
                    }
                    else{
                        mcorrectanswer++
                    }
                    question .Correctoption?.let {
                        answerview(
                            it,
                            R.drawable.correct_option_border_bg
                        )
                    }

                    if (mCurrentposition==mquestionlist!!.size){
                        btnsubmit.text = "Finish"
                    }else{
                        btnsubmit.text="Go to Next Question"
                    }
                    mselectedoptionposition = 0
                }

            }
        }

    }
    fun selectedoptionview(txt: TextView, selectedoptionNum : Int){
        defaultoptionsview()
        mselectedoptionposition= selectedoptionNum
        txt.setTextColor(Color.parseColor("#363A43"))
        txt.setTypeface(txt.typeface, Typeface.BOLD)
        txt.background = ContextCompat.getDrawable(this,
            R.drawable.selected_option_border_bg
        )
    }
    fun answerview(answer:Int,drawableview:Int){
        when(answer){
            1->{
                txtoptionone.background= ContextCompat.getDrawable(
                    this,drawableview
                )
            }
            2->{
                txtoptiontwo.background= ContextCompat.getDrawable(
                    this,drawableview
                )
            }
            3->{
                txtoptionthree.background= ContextCompat.getDrawable(
                    this,drawableview
                )
            }
            4->{
                txtoptionfour.background= ContextCompat.getDrawable(
                    this,drawableview
                )
            }
        }
    }
}