package com.example.jordan.androidwebapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void fetchBaidu(View view) {
        new FetchBaiduTask().execute();
    }

    private class FetchBaiduTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            try {
                URI uri = new URI("http://www.baidu.com");
                URL url = uri.toURL();
                InputStream inputStream = url.openStream();
                byte[] bytes = new byte[250];
                int readSize = inputStream.read(bytes);

                Log.i(MainActivity.class.getName(), "Read size: " + readSize);
                // decode as utf-8, or the Chinese character will not be displayed correctly
                // but it always display incorrectly while testing on virtual machine
                Log.i(MainActivity.class.getName(), "bytes: " + new String(bytes, "utf-8"));

                inputStream.close();
            } catch (URISyntaxException | IOException ex) {
                ex.printStackTrace();
            }
            return null;
        }
    }
}
