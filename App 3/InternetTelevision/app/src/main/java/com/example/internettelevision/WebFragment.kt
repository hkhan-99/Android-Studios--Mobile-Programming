package com.example.internettelevision

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import android.net.http.SslError
import android.webkit.SslErrorHandler
import android.webkit.WebView
import android.webkit.WebResourceResponse
import android.webkit.WebResourceRequest
import android.webkit.WebResourceError
import android.graphics.Bitmap


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FifthFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class WebFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_web, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

        var webView = MainActivity.getInstance().findViewById<WebView>(R.id.webView)
        var delegate = Delegate()
//Add the settings:
        webView.webViewClient = delegate
//This will allow the tracing of links
        webView?.getSettings()?.setJavaScriptEnabled(true);
        webView?.getSettings()?.setJavaScriptCanOpenWindowsAutomatically(true);
        var url1 = "https://content.uplynk.com/channel/3324f2467c414329b3b0cc5cd987b6be.m3u8"
        webView?.loadUrl(url1)

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FifthFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            WebFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}

//Delegate class for handling
class Delegate : WebViewClient()
{
    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?)
    {
        super.onPageStarted(view, url, favicon)
        println("started")
    }
    override fun onPageFinished(view: WebView, url: String)
    {
        super.onPageFinished(view,url)
        println("finish")
    }
    override fun onReceivedError(view: WebView, request: WebResourceRequest, error: WebResourceError)
    {
        println(error.description )
    }
    override fun onReceivedHttpError(
        view: WebView, request: WebResourceRequest, errorResponse: WebResourceResponse
    )
    {
        println(errorResponse.data)
    }
    override fun onReceivedSslError(
        view: WebView, handler: SslErrorHandler,
        error: SslError
    )
    {
        println(error.primaryError)
    }
}