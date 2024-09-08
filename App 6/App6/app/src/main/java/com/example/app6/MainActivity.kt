package com.example.app6

import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.TranslateAnimation
import android.widget.Button
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout

class MainActivity : AppCompatActivity()
{
    private var zoom_counter = 0
    private var start = false
    private var imageView : ImageView? = null
    private var fadeOutMyViewComplete = false
    private var fadeOutCompleteViewComplete = false
    private var repeatCount = 1

    private var origtouchedRectangle = Rect(0,0,0,0)
    private var moveCompleted1 = false
    private var moveCompleted2 = false


    companion object
    {
        private var instance : MainActivity? = null
        public fun getInstance() : MainActivity
        {
            return instance!!
        }
    }


    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        instance = this
        setContentView(R.layout.activity_main)

        //Set the imageView once
        imageView = ImageView(this)
        //Add event handlers
        var fade_out = findViewById<Button>(R.id.fade_out)
        var fade_in = findViewById<Button>(R.id.fade_in)
        var dissolve = findViewById<Button>(R.id.dissolve)
        var zoom = findViewById<Button>(R.id.zoom)
        var move = findViewById<Button>(R.id.move)
        var rotate = findViewById<Button>(R.id.rotate)
        var lion0 = findViewById<ImageView>(R.id.lion0)
        var handler = Handler()
        fade_out.setOnClickListener(handler)
        fade_in.setOnClickListener(handler)
        dissolve.setOnClickListener(handler)
        zoom.setOnClickListener(handler)
        move.setOnClickListener(handler)
        rotate.setOnClickListener(handler)


        //Set the imageView once
        imageView = ImageView(this)

    }

    inner class Handler : View.OnClickListener
    {
        override fun onClick(v: View?)
        {
            var text = (v as Button).getText()
            if (text == "Disappear Cobra")
            {
                var myFadeOut = AnimationUtils.loadAnimation(MainActivity.getInstance(), R.anim.fade_out)
                var animHandler = FadeOutHandler()
                myFadeOut.setAnimationListener(animHandler)
                var untouched = findViewById<ImageView>(R.id.cobra)
                untouched.startAnimation(myFadeOut)

            }
            else if (text == "Disappear Cobra")
            {
                var myFadeIn = AnimationUtils.loadAnimation(MainActivity.getInstance(), R.anim.fade_in)
                var animHandler1 = FadeInHandler()
                myFadeIn.setAnimationListener(animHandler1)
                var untouched = findViewById<ImageView>(R.id.cobra)
                untouched.startAnimation(myFadeIn)

            }
            else if (text == "Make Lion Angry!")
            {
                //Fade out the target
                var myFadeOut = AnimationUtils.loadAnimation(MainActivity.getInstance(), R.anim.fade_out)
                var animHandler1 = DissolveHandler()
                myFadeOut.setAnimationListener(animHandler1)
                var untouched = findViewById<ImageView>(R.id.lion0)
                untouched.startAnimation(myFadeOut)

            }
            else if (text == "Hide Title")
            {
                var myZoom = AnimationUtils.loadAnimation(MainActivity.getInstance(),R.anim.zoom_in)
                var zoomHandler = ZoomHandler()
                myZoom.setAnimationListener(zoomHandler)
                var touched = MainActivity.getInstance().findViewById<ImageView>(R.id.title)
                touched.startAnimation(myZoom)
            }
            else if (text == "Flip Rabbit")
            {
                var myRotate = AnimationUtils.loadAnimation(MainActivity.getInstance(),R.anim.rotate)
                var rotateHandler = RotateHandler()
                myRotate.setAnimationListener(rotateHandler)
                var touched = findViewById<ImageView>(R.id.rabbit)
                touched.startAnimation(myRotate)

            }
            else if (text == "Jump Rabbit")
            {
                var untouched = findViewById<ImageView>(R.id.title)
                var touched = findViewById<ImageView>(R.id.title)

                origtouchedRectangle = Rect(touched.left,touched.top,touched.right, touched.bottom)

                //Compute the deltas
                var delta_x =  (untouched.left - touched.left).toFloat()
                var delta_y =  (untouched.top - touched.top).toFloat()

                var moveAnim = TranslateAnimation(0f, delta_x, 0f, delta_y)
                moveAnim.duration = 500
                moveAnim.fillAfter = true

                var handler = MoveHandler()
                moveAnim.setAnimationListener(handler)
                touched.startAnimation(moveAnim)


            }
        }
    }
    inner class FadeOutHandler : Animation.AnimationListener
    {
        override fun onAnimationRepeat(animation: Animation?)
        {
            println("repeat")
        }
        override fun onAnimationEnd(animation: Animation?)
        {
            println("Fade out finshied")
            var myFadeIn = AnimationUtils.loadAnimation(MainActivity.getInstance(), R.anim.fade_in)
            var animHandler1 = FadeInHandler()
            myFadeIn.setAnimationListener(animHandler1)
            var untouched = findViewById<ImageView>(R.id.cobra)
            untouched.startAnimation(myFadeIn)


        }
        override fun onAnimationStart(animation: Animation?)
        {
            println("Fade out start")

        }
    }
    //FadeIn handler
    inner class FadeInHandler : Animation.AnimationListener
    {
        override fun onAnimationRepeat(animation: Animation?)
        {
            println("repeat")
        }
        override fun onAnimationEnd(animation: Animation?)
        {
            println("Fade in finished")

        }
        override fun onAnimationStart(animation: Animation?)
        {

            println("Fade in start")
        }
    }

    /***********************************Dissolve Handler**********************************/
    inner class DissolveHandler : Animation.AnimationListener
    {
        override fun onAnimationRepeat(animation: Animation?)
        {
            println("repeat")
        }
        override fun onAnimationEnd(animation: Animation?)
        {
            println("dissolve finshied")

            var untouched = findViewById<ImageView>(R.id.title)
            untouched.setAlpha(1.0f)
            imageView?.setAlpha(0.0f)
        }
        override fun onAnimationStart(animation: Animation?)
        {
            println("dissolve start")
            imageView?.setImageResource(R.drawable.lion1)
            var untouched = findViewById<ImageView>(R.id.jungle)
            imageView?.layoutParams = untouched.layoutParams
            //Be sure to add only once!
            if (!start)
            {
                var cl = findViewById<ConstraintLayout>(R.id.cl)
                cl.addView(imageView)
                start = true
            }
            var myFadeIn = AnimationUtils.loadAnimation(MainActivity.getInstance(), R.anim.fade_in )
            var animHandler2 = FadeInHandler()
            myFadeIn.setAnimationListener(animHandler2)
            imageView?.startAnimation(myFadeIn)
        }
    }
    /*************************************************************************************************/
    inner class ZoomHandler : Animation.AnimationListener
    {
        override fun onAnimationRepeat(animation: Animation?)
        {
            println("repeat")
        }
        override fun onAnimationEnd(animation: Animation?)
        {
            zoom_counter++
            if(zoom_counter >= repeatCount)
                return
            else
            {
                var myZoom = AnimationUtils.loadAnimation(MainActivity.getInstance(),R.anim.zoom_in)
                var zoomHandler = ZoomHandler()
                myZoom.setAnimationListener(zoomHandler)
                var touched = MainActivity.getInstance().findViewById<ImageView>(R.id.title)

                touched.startAnimation(myZoom)
            }
            //Restart the animation for 7 times
            println("End animation")
        }
        override fun onAnimationStart(animation: Animation?)
        {
            println("zoom start")
        }

    }
    /******************Rotate Handler*****************************/
    inner class RotateHandler : Animation.AnimationListener
    {
        override fun onAnimationRepeat(animation: Animation?)
        {
            println("repeat")
        }
        override fun onAnimationEnd(animation: Animation?)
        {
            println("rotate finshied")

        }
        override fun onAnimationStart(animation: Animation?)
        {
            println("rotate start")
        }
    }
    /**************************************************************/

    /*********************Move Handler**********************************/
    inner class MoveHandler : Animation.AnimationListener
    {
        override fun onAnimationRepeat(animation: Animation?)
        {
            println("repeat")
        }
        override fun onAnimationEnd(animation: Animation?)
        {
            var untouched = findViewById<ImageView>(R.id.jungle)
            var touched = findViewById<ImageView>(R.id.rabbit)
            var lion0 = findViewById<ImageView>(R.id.jungle)

            if (!moveCompleted1)
            {
                //Reset the untouched coordinates
                touched.left = untouched.left
                touched.top = untouched.top
                touched.bottom = untouched.bottom
                touched.right = untouched.right
                moveCompleted1 = true
                //Compute the deltas
                var delta_x = (lion0.left - untouched.left).toFloat()
                var delta_y = (lion0.top - untouched.top).toFloat()
                var moveAnim = TranslateAnimation(0f, delta_x, 0f, delta_y)
                moveAnim.duration = 500
                moveAnim.fillAfter = true
                var handler = MoveHandler()
                moveAnim.setAnimationListener(handler)
                touched.startAnimation(moveAnim)
            }
            else if (!moveCompleted2)
            {
                touched.left = lion0.left
                touched.top = lion0.top
                touched.right = lion0.right
                touched.bottom = lion0.bottom
                moveCompleted2 = true

                //Compute the deltas back to the original
                var delta_x = (origtouchedRectangle.left - lion0.left).toFloat()
                var delta_y = (origtouchedRectangle.top - lion0.top).toFloat()
                var moveAnim = TranslateAnimation(0f, delta_x, 0f, delta_y)
                moveAnim.duration = 500
                moveAnim.fillAfter = true
                var handler = MoveHandler()
                moveAnim.setAnimationListener(handler)
                touched.startAnimation(moveAnim)
            }

            println("move finshied")
        }
        override fun onAnimationStart(animation: Animation?)
        {
            println("move start")
        }
    }
    /****************************************************************/


}