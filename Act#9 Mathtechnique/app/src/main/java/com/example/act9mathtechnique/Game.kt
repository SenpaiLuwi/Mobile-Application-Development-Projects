package com.example.act9mathtechnique

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import android.widget.*

class Game : AppCompatActivity() {
    private lateinit var score: TextView
    private lateinit var life: TextView
    private lateinit var time: TextView
    private lateinit var question: TextView
    private lateinit var answer: EditText
    private lateinit var ok: Button
    private lateinit var next: Button

    private val randomnum = java.util.Random()
    private var num1 = 0
    private var num2 = 0
    private var userAnswer = 0
    private var correctAnswer = 0
    private var userScore = 0
    private var userLife = 3

    private lateinit var timer: CountDownTimer
    private val START_TIMER_IN_MILLIS: Long = 21000
    private var timerRunning = false
    private var timeLeftInMillis = START_TIMER_IN_MILLIS

    private var okButtonClicked = false

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        score = findViewById(R.id.txt_id_Score)
        life = findViewById(R.id.txt_id_Life)
        time = findViewById(R.id.txt_id_Time)
        question = findViewById(R.id.txt_id_Question)
        answer = findViewById(R.id.txt_id_Answer)
        ok = findViewById(R.id.btn_id_ok)
        next = findViewById(R.id.btn_id_next)

        gameContinue()

        ok.setOnClickListener {
            if (!okButtonClicked) {
                val userAnswerText = answer.text.toString().trim()

                if (userAnswerText.isEmpty()) {
                    displayMessage("Please enter your answer.")
                }
                else {
                    userAnswer = userAnswerText.toInt()
                    pauseTimer()

                    if (userAnswer == correctAnswer) {
                        userScore++
                        score.text = userScore.toString()
                        question.text = "You Are Correct"
                        displayMessage("You Are Correct")
                    } else {
                        userLife--
                        life.text = userLife.toString()
                        question.text = "You Are Wrong"
                        displayMessage("You Are Wrong")
                    }

                    okButtonClicked = true
                }
            }
            else {
                displayMessage("You have already answered.")
            }
        }

        next.setOnClickListener {
            val userAnswerText = answer.text.toString().trim()
            if (userAnswerText.isEmpty()) {
                displayMessage("Please enter your answer before moving to the next question.")
            } else {
                answer.text.clear()
                resetTimer()
                okButtonClicked = false

                if (userLife <= 0) {
                    Toast.makeText(applicationContext, "Game Over", Toast.LENGTH_SHORT).show()
                    val intentResult = Intent(this, result::class.java)
                    intentResult.putExtra("score", userScore)
                    startActivity(intentResult)
                    finish()
                } else {
                    gameContinue()
                }
            }
        }

    }

    @SuppressLint("SetTextI18n")
    private fun gameContinue() {
        num1 = randomnum.nextInt(100)
        num2 = randomnum.nextInt(100)
        val operation = intent.getStringExtra("operation")


        if (operation == "subtraction") {
            num1 = randomnum.nextInt(100)
            num2 = randomnum.nextInt(num1 + 1)
        } else {
            num1 = randomnum.nextInt(100)
            num2 = randomnum.nextInt(100)
        }

        val operatorSymbol = when (operation) {
            "addition" -> "+"
            "subtraction" -> "-"
            "multiplication" -> "*"
            else -> ""
        }

        correctAnswer = when (operation) {
            "addition" -> num1 + num2
            "subtraction" -> num1 - num2
            "multiplication" -> num1 * num2
            else -> 0
        }

        question.text = "$num1 $operatorSymbol $num2"
        answer.text.clear()
        startTimer()

    }


    private fun startTimer() {
        timer = object : CountDownTimer(timeLeftInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeLeftInMillis = millisUntilFinished
                updateText()
            }

            @SuppressLint("SetTextI18n")
            override fun onFinish() {
                timerRunning = false
                pauseTimer()
                userLife--
                life.text = userLife.toString()
                if (userLife <= 0) {
                    question.text = "Game Over"
                    Toast.makeText(applicationContext, "Game Over", Toast.LENGTH_SHORT).show()
                    val intentResult = Intent(this@Game, result::class.java)
                    intentResult.putExtra("score", userScore)
                    startActivity(intentResult)
                    finish()
                } else {
                    question.text = "Time's Up!"
                    resetTimer()
                }
            }

        }.start()

        timerRunning = true
    }

    private fun pauseTimer() {
        timer.cancel()
        timerRunning = false
    }

    private fun resetTimer() {
        timeLeftInMillis = START_TIMER_IN_MILLIS
        updateText()
    }

    private fun updateText() {
        val seconds = (timeLeftInMillis / 1000).toInt() % 60
        val timeLeft = String.format("%02d", seconds)
        time.text = timeLeft
    }

    private fun displayMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}