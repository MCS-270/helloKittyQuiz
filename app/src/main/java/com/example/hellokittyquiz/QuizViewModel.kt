package com.example.hellokittyquiz

import Questions
import Scores
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders

// private val TAG = "QuizViewModel"


class QuizViewModel: ViewModel() {
    // create index counter
    var currentIndex = 0
    var imageCurrentIndex = 0
    var isCheater = false
    //load questions up by creating a list of Question objects
    var correct = 0
    val QuestionBank = listOf(
        Questions(R.drawable.deardaniel, R.string.kitty1, true),
        Questions(R.drawable.hellokittycat, R.string.kitty2, false),
        Questions(R.drawable.hellokitty, R.string.kitty3, false),
        Questions(R.drawable.hellokittyapple, R.string.kitty4, true),
        Questions(R.drawable.louisyu, R.string.louis1, true)
    )

    val ScoreBank = listOf(
        Scores(R.drawable.creepyhellokitty, R.string.endkitty1),
        Scores(R.drawable.badhellokitty, R.string.endkitty2),
        Scores(R.drawable.camerakitty, R.string.endkitty3),
        Scores(R.drawable.ballonhellokitty, R.string.endkitty4),
        Scores(R.drawable.icecreamkitty, R.string.endkitty5),
        Scores(R.drawable.lovehellokitty, R.string.endkitty6)
    )


    val currentQuestionAnswer: Boolean get() = QuestionBank[currentIndex].answer
    val currentQuestionText: Int get() = QuestionBank[currentIndex].textResID
    val currentImage: Int get() = QuestionBank[currentIndex].imageResID
    val kittyImage: Int get() = ScoreBank[correct].imageResID
    val kittyText: Int get() = ScoreBank[correct].textResID


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