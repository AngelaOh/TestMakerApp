package com.example.testmakerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DashboardActivity extends AppCompatActivity {
    private TextView outputText;
    private TextView headerTwo;
    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

//        outputText = findViewById(R.id.practice_output);
        headerTwo = findViewById(R.id.app_header2);
        backButton = findViewById(R.id.back_button);

        String practiceValue = String.valueOf(getIntent().getStringExtra("practice_input"));

        if (practiceValue != null ) {
//            outputText.setText(practiceValue);
            headerTwo.setText("Welcome, " + practiceValue);
        }

        Log.d("Intent", "onCreate: what is being passed in" + practiceValue);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();
                intent.putExtra("dashboard_callback", "Message from dashboard");
                setResult(RESULT_OK, intent);
                finish();
            }
        });

    }
}
