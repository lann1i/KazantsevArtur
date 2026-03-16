package com.example.kazantsevartur;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.Serializable;

public class Pr2_Activity3 extends AppCompatActivity {

    private EditText day, time, comment;
    private Button okButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Base_Theme_KazantsevArtur);
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.pr2_activity3);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        day = findViewById(R.id.editTextText4);
        time = findViewById(R.id.editTextText5);
        comment = findViewById(R.id.editTextText6);
        okButton = findViewById(R.id.button4);
    }
    public void onNextActivity6(View view) {

        String info = day.getText().toString() + " " +
                time.getText().toString() + " " +
                comment.getText().toString();

        Intent resultIntent = new Intent();
        resultIntent.putExtra("info", info);
        setResult(RESULT_OK,resultIntent);
        finish();
    }
}


