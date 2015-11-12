package com.myshopapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class HtmlActivity extends AppCompatActivity {
    private WebView myWeb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_html);
        myWeb=(WebView)findViewById(R.id.myWeb);

        myWeb.getSettings().setJavaScriptEnabled(true);
        myWeb.setWebChromeClient(new WebChromeClient());
        myWeb.setWebViewClient(new WebViewClient());

        myWeb.addJavascriptInterface(new JsHelper(),"helper");
        myWeb.loadUrl("file:///android_assets/hybird/index.html");
    }
    private class JsHelper{
        @JavascriptInterface
        public void goActivity(){
            //startActivity(new Intent(getApplicationContext(),SecondActivity.class));
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_second, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            myWeb.loadUrl("javascript:changeBackground()");

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
