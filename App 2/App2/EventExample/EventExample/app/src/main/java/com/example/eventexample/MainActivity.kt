package com.example.eventexample

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.CompoundButton
import android.widget.CompoundButton.OnCheckedChangeListener
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class StationHandler(private var fn: String) : Runnable
{
  override fun run()
  {
    var imageView = MainActivity.getInstance().findViewById<FrameLayout>(R.id.imageView2)

    var id = MainActivity.getInstance().resources.getIdentifier(fn, "drawable",
      MainActivity.getInstance().packageName
    )

  }
}

class Handler : OnClickListener, OnCheckedChangeListener, OnSeekBarChangeListener
{
  private var sliderLabel : TextView? = null
  private var logos = arrayOf("launch","wfan", "kabc", "wbap","wls","karnam", "wxyt","kurb", "wkim","wwtn","kdxe", "karnam", "klal")
  override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean)
  {
    var slider = MainActivity.getInstance().findViewById<TextView>(R.id.freq)
    var text = progress.toString()

    //AM radio stations
    if (text == "660")
    {
      slider.setText("WFAN")
      var i = StationHandler(logos[1])
      MainActivity.getInstance().runOnUiThread(i)
    }
    else if(text == "790")
    {
      slider.setText("KABC")
      var i = StationHandler(logos[2])
      MainActivity.getInstance().runOnUiThread(i)
    }
    else if(text == "820")
    {
      slider.setText("WBAP")
      var i = StationHandler(logos[3])
      MainActivity.getInstance().runOnUiThread(i)
    }
    else if(text == "890")
    {
      slider.setText("WLS")
      var i = StationHandler(logos[4])
      MainActivity.getInstance().runOnUiThread(i)
    }
    else if(text == "920")
    {
      slider.setText("KARNAM")
      var i = StationHandler(logos[5])
      MainActivity.getInstance().runOnUiThread(i)
    }
    // FM radio stations
    else if(text == "97")
    {
      slider.setText("WXYT")
      var i = StationHandler(logos[6])
      MainActivity.getInstance().runOnUiThread(i)
    }
    else if(text == "98")
    {
      slider.setText("KURB")
      var i = StationHandler(logos[7])
      MainActivity.getInstance().runOnUiThread(i)
    }
    else if(text == "99")
    {
      slider.setText("WKIM")
      var i = StationHandler(logos[8])
      MainActivity.getInstance().runOnUiThread(i)
    }
    else if(text == "100")
    {
      slider.setText("WWTN")
      var i = StationHandler(logos[9])
      MainActivity.getInstance().runOnUiThread(i)
    }
    else if(text == "101")
    {
      slider.setText("KDXE")
      var i = StationHandler(logos[10])
      MainActivity.getInstance().runOnUiThread(i)
    }
    else if(text == "102")
    {
      slider.setText("KARNFM")
      var i = StationHandler(logos[11])
      MainActivity.getInstance().runOnUiThread(i)
    }
    else if(text == "107")
    {
      slider.setText("KLAL")
      var i = StationHandler(logos[12])
      MainActivity.getInstance().runOnUiThread(i)
    }
    else
    {
      slider.setText("Now Playing")
      var i = StationHandler(logos[0])
      MainActivity.getInstance().runOnUiThread(i)
    }
  }

  override fun onStartTrackingTouch(seekBar: SeekBar?)
  {
    println("play")
  }

  override fun onStopTrackingTouch(seekBar: SeekBar?)
  {
    println("stop")
  }


  override fun onCheckedChanged(frameLayout: CompoundButton?, isChecked: Boolean)
  {
    var text = frameLayout?.getText()
    println("Is checked" + frameLayout?.isChecked())
    println(text)

    if (text == "AM")
    {
      frameLayout?.setText("FM")

    }
    else if (text == "FM")
    {
      frameLayout?.setText("AM")
    }
  }


  override fun onClick(v: View?)
  {
    //Play streaming audio

  }
}

class MainActivity : AppCompatActivity()
{

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
    setContentView(R.layout.activity_main)


    instance = this

    //Instantiate Handler
    var handler = Handler()

//Register Handler with the Buttons
    var play = findViewById<Button>(R.id.play)
    var band = findViewById<CompoundButton>(R.id.band)
    var slider = findViewById<SeekBar>(R.id.seekBar)
    play.setOnClickListener{(startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("http://playerservices.streamtheworld.com/api/livestream-redirect/KLALFM.mp3"))))}

    band.setOnClickListener(handler)
    slider.setOnSeekBarChangeListener(handler)
    slider.keyProgressIncrement

  }
}