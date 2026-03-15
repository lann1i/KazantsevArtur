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

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        String currentLanguage = getResources().getConfiguration().getLocales().get(0).getDisplayLanguage();
        Log.i("LangTest", "Current language: " + currentLanguage);
        Log.i("Унбо-05-24", "Информация");
        Button myButton = findViewById(R.id.my_button);
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Действие при нажатии на кнопку, например:
                Toast.makeText(getApplicationContext(), "Кнопка нажата",
                        Toast.LENGTH_SHORT).show();
            }
        }
        );
    }

    public void onNextActivity(View view) {
        EditText nameText = findViewById(R.id.editTextText2);
        EditText groupText = findViewById(R.id.editTextText);
        EditText ageText = findViewById(R.id.editTextNumber);
        EditText scoreText = findViewById(R.id.editTextNumber2);
        String name = nameText.getText().toString();
        String group = groupText.getText().toString();
        int age = Integer.parseInt(ageText.getText().toString());
        int score = Integer.parseInt(scoreText.getText().toString());
        MyObject  myObject= new MyObject(name, group, age, score);
        Intent intent = new Intent(this, MainActivity2.class);
        intent.putExtra("myObject", myObject);
        startActivity(intent);
    }
}


