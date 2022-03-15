package com.example.hellokittyquiz

import android.app.Activity
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

// Edited by Santiago
@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var cheatButton: Button
    private lateinit var nextButton: ImageButton
    private lateinit var previousButton: ImageButton
    private lateinit var questionTextView: TextView

    private val quizViewModel: QuizViewModel by lazy {
        ViewModelProviders.of(this).get(QuizViewModel::class.java)
    }

    private var isAnswered = BooleanArray(4) { true }
    private val TAG = "MainActivity"
    private val REQUEST_CODE_CHEAT = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate(Bundle?) called")
        setContentView(R.layout.activity_main)

        var correct = 0
        trueButton = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)
        nextButton = findViewById(R.id.next_button)
        previousButton = findViewById(R.id.previous_button)
        questionTextView = findViewById(R.id.question_text_view)
        cheatButton = findViewById(R.id.cheat_button)

        fun checkAnswer(userAnswer: Boolean){
            val correctAnswer = quizViewModel.currentQuestionAnswer
            isAnswered[quizViewModel.currentIndex] = false
            trueButton.isEnabled = false
            falseButton.isEnabled = false


            when {
                quizViewModel.isCheater -> {
                    quizViewModel.isCheater = false
                    var judgement = Toast.makeText(this, R.string.judgment_toast, Toast.LENGTH_LONG)
                    judgement.setGravity(Gravity.BOTTOM,0,0)
                    judgement.show()
                }
                userAnswer == correctAnswer -> {
                    correct += 1
                    var toast = Toast.makeText(this, R.string.correct_toast, Toast.LENGTH_LONG)
                    toast.setGravity(Gravity.TOP,0,0)
                    toast.show()
                }
                else -> {
                    var toast = Toast.makeText(this, R.string.incorrect_toast, Toast.LENGTH_LONG)
                    toast.setGravity(Gravity.TOP,0,0)
                    toast.show()
                }
            }
        }

        fun calcPercentage(){
            var percentage = (correct.toDouble()/quizViewModel.QuestionBank.size)*100
            var toast = Toast.makeText(this, "$percentage%", Toast.LENGTH_LONG)
            toast.setGravity(Gravity.CENTER,0,0)
            toast.show()
        }

        trueButton.setOnClickListener {
            //Does something when you click the button
            checkAnswer(true)
            if(quizViewModel.currentIndex == (quizViewModel.QuestionBank.size-1)){
                calcPercentage()
            }
        }
        falseButton.setOnClickListener {
            //Does something when you click the button
            checkAnswer(false)
            if(quizViewModel.currentIndex == (quizViewModel.QuestionBank.size-1)){
                calcPercentage()
            }
        }

        cheatButton.setOnClickListener {
            // If this button is pressed, then I want to go to the second activity
            // wrap second activity into an intent
            val answerIsTrue = quizViewModel.currentQuestionAnswer
            val intent = CheatActivity.newIntent(this@MainActivity, answerIsTrue)
            startActivity(intent)
            quizViewModel.isCheater = true
        }

        // Updates question when button clicked
        fun updateQuestions(){
            trueButton.isEnabled = isAnswered[quizViewModel.currentIndex]
            falseButton.isEnabled = isAnswered[quizViewModel.currentIndex]

            val questionTextResId = quizViewModel.currentQuestionText
            questionTextView.setText(questionTextResId)
        }

        updateQuestions()

        nextButton.setOnClickListener {
            quizViewModel.moveToNext()
            updateQuestions()
        }

        updateQuestions()

        previousButton.setOnClickListener {
            quizViewModel.moveToPrevious()
            updateQuestions()
        }
        //When TextView is clicked, it goes to the next question
        questionTextView.setOnClickListener{
            quizViewModel.moveToNext()
            updateQuestions()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode != Activity.RESULT_OK) {
            return
        }
        if (requestCode == REQUEST_CODE_CHEAT) {
            quizViewModel.isCheater = data?.getBooleanExtra(EXTRA_ANSWER_SHOWN, false) ?: false
        }
    }


    // *************** New, after create, log process *****************

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart() is called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause() is called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume() is called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop() is called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy() is called")
    }


}