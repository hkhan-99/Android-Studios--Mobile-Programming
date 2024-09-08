package com.example.targetapp

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.Timer
import java.util.TimerTask


class HelperThread : Runnable
{
    override fun run()
    {
        MainActivity.getInstance().update()
    }
}

class TimerObject : TimerTask()
{
    override fun run()
    {
        var helper = HelperThread()

        MainActivity.getInstance().runOnUiThread(helper)
    }
}

class MainActivity : AppCompatActivity() {

    //Data Fields for controlling the view
    private var speed = 5;
    private var count = 0
    private var oval_dir = 1;
    private var ball_dir = -1
    private var timer = Timer()

    public fun getTimer(): Timer {

        return timer
    }

    public fun fire() {
        timer = Timer()
        var timerTask = TimerObject()
        timer.schedule(timerTask, 3000, 25)
    }

    companion object {
        private var instance: MainActivity? = null
        public fun getInstance(): MainActivity {
            return instance!!
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        instance = this
        setContentView(R.layout.activity_main)

        var handler = Handler()
        var add1 = findViewById<Button>(R.id.add1)
        var remove = findViewById<Button>(R.id.remove)
        var slider = findViewById<SeekBar>(R.id.seekBar)
        var fire = findViewById<Button>(R.id.fire)
        add1.setOnClickListener(handler)
        remove.setOnClickListener(handler)
        fire.setOnClickListener(handler)
        slider.setOnSeekBarChangeListener(handler)
        slider.keyProgressIncrement

        var timerTask = TimerObject()
        //timer.schedule(timerTask,0,25)
    }

    public fun update() {
        var myView = findViewById<MyView>(R.id.myView)
        var ball = findViewById<ImageView>(R.id.ball)

        //Synchronize with the view getting setup
        if (myView.getWidth() > 1) {
            println(myView.getWidth())
            println(myView.getHeight())
            var circle = myView.getCircleCoords()
            circle.left += (speed * oval_dir)
            circle.right += (speed * oval_dir)
            ball.top += (speed * ball_dir)
            ball.bottom += (speed * ball_dir)

            //Check for edges
            if ((circle.left < 0) || (circle.right > myView.getWidth()))
                oval_dir *= -1
            if ((ball.top < 0) || (ball.bottom > myView.getHeight()))
                ball_dir *= -1
            myView.setCircleCoords(circle.left, circle.top, circle.right, circle.bottom)
            myView.setballCoords(ball.left, ball.top, ball.right, ball.bottom)
            myView.invalidate()
        }
    }

    inner class Handler : View.OnClickListener, SeekBar.OnSeekBarChangeListener
    {
        override fun onClick(v: View?) {
            var text = (v as Button).getText()
            if (text == "+")
                speed++
            else if (text == "-")
                speed--
            if (speed < 0)
                speed = 0

            var firebutton = getInstance().findViewById<TextView>(R.id.shots)
            if (text == "FIRE")
            {
                 count++
                firebutton.text = count.toString()
            }


    }

        override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
            var slider = getInstance().findViewById<TextView>(R.id.degree)
            var text = progress.toString()
            slider.text = text
        }

        override fun onStartTrackingTouch(seekBar: SeekBar?) {
            println("Setting angle degree")
        }

        override fun onStopTrackingTouch(seekBar: SeekBar?) {
            println("degree set!")
        }
    }
}
