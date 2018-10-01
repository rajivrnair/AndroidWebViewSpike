package io.rajivrnair.androidwebviewspike;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.strumsoft.websocket.phonegap.WebSocketFactory;

/**
 * Based on http://foretribe.blogspot.com/2013/08/how-to-make-android-webview-support.html which in
 * turn is based on an example by the PhoneGap folks.
 *
 * Almost no animals were harmed during the making of this example (except a chicken that was fried to feed the developer).
 */
public class MainActivity extends Activity {

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WebView wv = findViewById(R.id.webview);
        wv.getSettings().setJavaScriptEnabled(true);
        wv.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
            }
        });
        wv.setWebViewClient(
                new WebViewClient() {
                    public boolean shouldOverrideUrlLoading(WebView view, String url) {
                        view.loadUrl(url);
                        return false;
                    }
                });
        wv.loadUrl("file:///android_asset/www/index.html");
        wv.addJavascriptInterface(new WebSocketFactory(wv), "WebSocketFactory");

    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }

}
