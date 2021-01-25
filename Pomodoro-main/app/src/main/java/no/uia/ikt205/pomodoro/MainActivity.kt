package no.uia.ikt205.pomodoro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import no.uia.ikt205.pomodoro.util.millisecondsToDescriptiveTime

class MainActivity : AppCompatActivity() {

    lateinit var timer:CountDownTimer
    lateinit var startButton:Button
    lateinit var buttonOne:Button
    lateinit var buttontwo:Button
    lateinit var buttonthree:Button
    lateinit var coutdownDisplay:TextView

    var timeToCountDownInMs = 5000L
    var timeTicks = 1000L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       startButton = findViewById<Button>(R.id.startCountdownButton)
       startButton.setOnClickListener(){
           startCountDown(it)
       }

        buttonOne = findViewById<Button>(R.id.setbtone)
        buttonOne.setOnClickListener(){
            setbtone()
        }

        buttontwo = findViewById<Button>(R.id.setbttwo)
        buttonOne.setOnClickListener(){
            setbttwo()
        }

        buttonthree = findViewById<Button>(R.id.setbtthree)
        buttonOne.setOnClickListener(){
            setbthree()
        }


       coutdownDisplay = findViewById<TextView>(R.id.countDownView)

    }

    fun startCountDown(v: View){

        timer = object : CountDownTimer(timeToCountDownInMs,timeTicks) {
            override fun onFinish() {
                Toast.makeText(this@MainActivity,"Arbeidsøkt er ferdig", Toast.LENGTH_SHORT).show()
            }

            override fun onTick(millisUntilFinished: Long) {
               updateCountDownDisplay(millisUntilFinished)
            }
        }

        timer.start()
    }

    fun setbtone(){
        timeToCountDownInMs = 1800000L
    }

    fun setbttwo(){
        timeToCountDownInMs = 3600000L
    }

    fun setbthree(){
        timeToCountDownInMs = 7200000L
    }

    fun updateCountDownDisplay(timeInMs:Long){
        coutdownDisplay.text = millisecondsToDescriptiveTime(timeInMs)
    }

}