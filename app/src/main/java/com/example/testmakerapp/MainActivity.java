package com.example.testmakerapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private Button practiceButton;
    private EditText practiceInput;
    private String practiceAPIStandard = "";
    private final int REQUEST_CODE = 123;
    private String waURL = "https://api.commonstandardsproject.com/api/v1/standard_sets/7432D25024594EA9A2092DF45BBA7F6C_D1000385_grade-06?";
    RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        queue = MySingleton.getInstance(this.getApplicationContext())
                .getRequestQueue();

        JsonObjectRequest testStandardsObject = new JsonObjectRequest(Request.Method.GET,
                waURL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            practiceAPIStandard = response.getJSONObject("data")
                                    .getJSONObject("standards")
                                    .getJSONObject("2A816130DFE80131C06868A86D17958E")
                                    .getString("description");
                            Log.d("JSON STANDARDS: ", "onResponse: " + practiceAPIStandard);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("API ERROR: ", "onErrorResponse: " + error.getMessage());
            }
        });
        queue.add(testStandardsObject);


        ///////////////

        practiceButton = findViewById(R.id.practice_button);
        practiceInput = findViewById(R.id.practice_input);

        practiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input = practiceInput.getText().toString().trim();
                Intent intent = new Intent(MainActivity.this, DashboardActivity.class);
                intent.putExtra("practice_input", input);
                intent.putExtra("practice_API", practiceAPIStandard);
                startActivityForResult(intent, REQUEST_CODE);
                // startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            String message = data.getStringExtra("dashboard_callback");
            Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
        }
    }
}
