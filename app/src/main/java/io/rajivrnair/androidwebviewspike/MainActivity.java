package io.rajivrnair.androidwebviewspike;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.strumsoft.websocket.phonegap.WebSocketFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Based on http://foretribe.blogspot.com/2013/08/how-to-make-android-webview-support.html which in
 * turn is based on an example by the PhoneGap folks.
 *
 * Almost no animals were harmed during the making of this example (except a chicken that was fried to feed the developer).
 */
public class MainActivity extends Activity {

    private static final String TAG = "WebView";

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WebView wv = findViewById(R.id.webview);

        WebSettings ws = wv.getSettings();
        ws.setJavaScriptEnabled(true);
        ws.setAllowFileAccess(true);
        ws.setAllowContentAccess(true);
        ws.setAllowFileAccessFromFileURLs(true);
        ws.setAllowUniversalAccessFromFileURLs(true);

        try {
            Log.d(TAG, "Enabling HTML5-Features");
            Method m1 = WebSettings.class.getMethod("setDomStorageEnabled", new Class[]{Boolean.TYPE});
            m1.invoke(ws, Boolean.TRUE);

            Method m2 = WebSettings.class.getMethod("setDatabaseEnabled", new Class[]{Boolean.TYPE});
            m2.invoke(ws, Boolean.TRUE);

            Method m3 = WebSettings.class.getMethod("setDatabasePath", new Class[]{String.class});
            m3.invoke(ws, "/data/data/" + getPackageName() + "/databases/");

            Method m4 = WebSettings.class.getMethod("setAppCacheMaxSize", new Class[]{Long.TYPE});
            m4.invoke(ws, 1024*1024*8);

            Method m5 = WebSettings.class.getMethod("setAppCachePath", new Class[]{String.class});
            m5.invoke(ws, "/data/data/" + getPackageName() + "/cache/");

            Method m6 = WebSettings.class.getMethod("setAppCacheEnabled", new Class[]{Boolean.TYPE});
            m6.invoke(ws, Boolean.TRUE);

            Log.d(TAG, "Enabled HTML5-Features");
        }
        catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            Log.e(TAG, "Reflection fail", e);
        }

        wv.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
            }
        });
        wv.setWebViewClient(
                new WebViewClient() {
                    public boolean shouldOverrideUrlLoading(WebView view, String url) {
                        view.loadUrl(url);
                        return true;
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
