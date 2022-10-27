package edu.temple.basicbrowser

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.EditText
import android.widget.ImageButton

class MainActivity : AppCompatActivity() {

    lateinit var urlEditText: EditText
    lateinit var goButton: ImageButton
    lateinit var webView: WebView

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        urlEditText = findViewById(R.id.urlEditText)
        goButton = findViewById(R.id.goButton)
        webView = findViewById(R.id.webView)
        webView.settings.javaScriptEnabled=true
        // Allow your browser to intercept hyperlink clicks
        webView.webViewClient = object: WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                title = view?.title
            }
        }
        goButton.setOnClickListener{
            webView.loadUrl( wrapURL( urlEditText.text.toString() ) )
        }

    }


    fun wrapURL(_url: String) : String{

        val url = _url.trim()

        if (url.indexOf(" ") > 0){
            url.replace(" ","%")

            return "https://google.com/search?q=$url"

        }


        if ( url.indexOf("http://") ==0 || url.indexOf("https://") == 0)
            return _url
        return "https://$_url"
    }
 }