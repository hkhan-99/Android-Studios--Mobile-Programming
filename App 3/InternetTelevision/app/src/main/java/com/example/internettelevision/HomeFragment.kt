package com.example.internettelevision

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment()
{
  // TODO: Rename and change types of parameters
  private var param1: String? = null
  private var param2: String? = null

  private var noimgs = 0
  private var duration: Long = 0
  private var imageView: ImageView? = null
  private var textView: TextView? = null

  private var files = arrayOf("homeimage.png","image1.png","image2.png")

  /*constructor()
  {
    duration = 5
    noimgs = 10
    imageView = MainActivity.getInstance().findViewById(R.id.imageView)
    textView = MainActivity.getInstance().findViewById(R.id.textView)
  }*/

  fun run()
  {
    var count = 0

    while (count < noimgs)
    {

      //Displays the images
      var handler = SlidesHandler(files[count].removeSuffix(".png"), "")
      MainActivity.getInstance().runOnUiThread(handler)
      Thread.sleep(duration * 1000)

      count++

    }

  }

  class SlidesHandler : Runnable
  {
    private var fn: String = ""
    private var text: String = ""


    constructor(fn: String, text: String)
    {
      this.fn = fn
      this.text = text
    }

    override fun run()
    {
      var imageView = MainActivity.getInstance().findViewById<ImageView>(R.id.imageView)
      var textView = MainActivity.getInstance().findViewById<TextView>(R.id.textView)
      textView.setText(text)

      var id = MainActivity.getInstance().resources.getIdentifier(fn, "drawable",
        MainActivity.getInstance().packageName
      )
      imageView.setImageResource(id)
    }
  }

  override fun onCreate(savedInstanceState: Bundle?)
  {
    super.onCreate(savedInstanceState)
    arguments?.let {
      param1 = it.getString(ARG_PARAM1)
      param2 = it.getString(ARG_PARAM2)
    }
  }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View?
  {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_home, container, false)
  }

  companion object
  {
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BottomMainFragment.
     */
    // TODO: Rename and change types and number of parameters
    @JvmStatic
    fun newInstance(param1: String, param2: String) =
      HomeFragment().apply {
        arguments = Bundle().apply {
          putString(ARG_PARAM1, param1)
          putString(ARG_PARAM2, param2)
        }
      }
  }
}