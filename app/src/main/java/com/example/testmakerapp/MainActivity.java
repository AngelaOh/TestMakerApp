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

public class MainActivity extends AppCompatActivity {

    private Button practiceButton;
    private EditText practiceInput;
    private final int REQUEST_CODE = 123;
    private String practiceURL = "https://jsonplaceholder.typicode.com/todos/1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        practiceButton = findViewById(R.id.practice_button);
        practiceInput = findViewById(R.id.practice_input);

        Log.d("MainActivity Input", "onCreate: practiceInput valid?" + practiceInput.getText());

        practiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input = practiceInput.getText().toString().trim();
                Intent intent = new Intent(MainActivity.this, DashboardActivity.class);
                intent.putExtra("practice_input", input);
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
