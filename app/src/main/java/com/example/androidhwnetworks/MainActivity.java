package com.example.androidhwnetworks;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.*;
//import com.android.volley.toolbox.Volley;
import com.android.volley.RequestQueue;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.*;

import org.json.*;

public class MainActivity extends AppCompatActivity {

    private String url = "http://date.jsontest.com/";
    private String dataString, timeString;

    private RequestQueue requestQueue;
    private Button requestButton;
    private TextView textView;

    private void SentRequest(String url) {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,
                url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    dataString = response.getString("date");
                    timeString = response.getString("time");
                    String requestResult = "Data: " + dataString + "\nTime: " + timeString;
                    textView.setText(requestResult);

                } catch (JSONException error) {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Exception: " + error.getMessage(), Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Exception: " + error.getMessage(), Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        requestQueue.add(request);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestQueue = Volley.newRequestQueue(this);
        textView = findViewById(R.id.textView);
        requestButton = findViewById(R.id.button);
        requestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SentRequest(url);
            }
        });

    }
}