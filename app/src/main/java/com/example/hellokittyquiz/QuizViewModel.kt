package com.example.hellokittyquiz

import Questions
import androidx.lifecycle.ViewModel

// private val TAG = "QuizViewModel"


class QuizViewModel: ViewModel() {
    // create index counter
    var currentIndex = 0
    var isCheater = false
    //load questions up by creating a list of Question objects
    val QuestionBank = listOf(
        Questions(R.string.kitty1, true),
        Questions(R.string.kitty2, false),
        Questions(R.string.kitty3, false),
        Questions(R.string.kitty4, true)
    )


    val currentQuestionAnswer: Boolean get() = QuestionBank[currentIndex].answer

    val currentQuestionText: Int get() = QuestionBank[currentIndex].textResID

    fun moveToNext() {
        currentIndex = (currentIndex + 1) % QuestionBank.size
    }

    fun moveToPrevious(){
        if (currentIndex == 0){
            currentIndex = QuestionBank.size
            currentIndex = (currentIndex - 1) % QuestionBank.size
        }

        else{
            currentIndex = (currentIndex - 1) % QuestionBank.size
        }
    }

}