package com.example.hellokittyquiz
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import android.widget.ImageView

const val EXTRA_MESSAGE = "com.example.helloKittyQuiz.Message"

class EndPageActivity : AppCompatActivity()  {


    private lateinit var showfinishbutton: Button
    private lateinit var showFinishText: TextView
    private lateinit var scoreTextView: TextView
    private lateinit var showImage: ImageView
    private val quizViewModel: QuizViewModel by lazy {
        ViewModelProviders.of(this).get(QuizViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_endpage)
        val actionBar = supportActionBar
        actionBar!!.title = "Score"
        //actionBar.setDisplayHomeAsUpEnabled(true)
        showfinishbutton = findViewById(R.id.show_finish_button)
        showFinishText = findViewById(R.id.show_finish_text)
        scoreTextView = findViewById(R.id.score_textview)
        showImage = findViewById(R.id.score_hello_kitty_image)


        showfinishbutton.setOnClickListener {
            val scoreImage = intent.getIntExtra("Score Image", quizViewModel.kittyImage)
            showImage.setImageResource(scoreImage)
            val scoreText = intent.getIntExtra("Score Text", quizViewModel.kittyText)
            scoreTextView.setText(scoreText)
        }

    }

}
