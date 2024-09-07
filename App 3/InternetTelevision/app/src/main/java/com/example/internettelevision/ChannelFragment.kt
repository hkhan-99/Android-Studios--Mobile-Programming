package com.example.internettelevision

import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Space
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ChannelFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

class ButtonHandler : View.OnClickListener {
  override fun onClick(p0: View?) {
    var navController = Navigation.findNavController(FourthFragment.getInstance().requireView())
    //Transition to 5th Fragment and send null Bundle
    navController.navigate(R.id.channelsToWeb, null)
  }
}

class ChannelFragment : Fragment()
{
  // TODO: Rename and change types of parameters
  private var param1: String? = null
  private var param2: String? = null

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
    return inflater.inflate(R.layout.fragment_channels, container, false)
  }

  companion object
  {
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BottomThirdFragment.
     */
    // TODO: Rename and change types and number of parameters
    @JvmStatic
    fun newInstance(param1: String, param2: String) =
      ChannelFragment().apply {
        arguments = Bundle().apply {
          putString(ARG_PARAM1, param1)
          putString(ARG_PARAM2, param2)
        }
      }
  }
}

class FourthFragment : Fragment() {

  companion object {
    private var instance: FourthFragment? = null
    public fun getInstance(): FourthFragment {
      return instance!!
    }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    instance = this
  }


  override fun onViewCreated(view: View, savedInstanceState: Bundle?)
  {
    super.onViewCreated(view, savedInstanceState)
    var handler = ButtonHandler()

    for (i in 0..15)
    {
      var button1 = Button(MainActivity.getInstance())
      button1.setBackgroundColor(Color.BLUE)
      button1.setTextColor(Color.WHITE)
      button1.setGravity(Gravity.CENTER_HORIZONTAL)
      var text = "Button" + i
      button1.setText(text)
      button1.setOnClickListener(handler)
      var space = Space(MainActivity.getInstance()) //Could also set the margin-bottom!
      space.minimumHeight = 15
      var linearlayout = MainActivity.getInstance().findViewById<LinearLayout>(R.id.linearLayout)
      linearlayout.addView(space)
      linearlayout.addView(button1)
    }

    var space: Space
    for (i in 0..15) {
      var button1 = View.inflate(MainActivity.getInstance(), R.layout.button, null) as Button

      var text = "Button " + i
      button1.setText(text)

      var handler = ButtonHandler()
      button1.setOnClickListener(handler)

      var linearLayout = MainActivity.getInstance().findViewById<LinearLayout>(R.id.linearLayout)
      linearLayout.addView(button1)

      var space = Space(MainActivity.getInstance())
      space.minimumHeight = 15

      linearLayout.addView(space)
    }

  }
}