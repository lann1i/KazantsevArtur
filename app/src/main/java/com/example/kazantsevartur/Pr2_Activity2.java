package com.example.kazantsevartur;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.Serializable;

public class Pr2_Activity2 extends AppCompatActivity {

    private ActivityResultLauncher<Intent> thirdActivityLauncher;
    private EditText subjectEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Base_Theme_KazantsevArtur);
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.pr2_activity2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        String currentLanguage = getResources().getConfiguration().getLocales().get(0).getDisplayLanguage();

        String fullName = getIntent().getStringExtra("fullName");
        if (fullName!=null) {
            TextView nameText = findViewById(R.id.textView9);
            nameText.setText("Name " + fullName);
        }

        subjectEditText = findViewById(R.id.editTextText7);

        thirdActivityLauncher = registerForActivityResult
                (new ActivityResultContracts.StartActivityForResult(),
                        new ActivityResultCallback<ActivityResult>() {
                            @Override
                            public void onActivityResult(ActivityResult result) {
                                if (result.getResultCode() == Activity.RESULT_OK){
                                    Intent data = result.getData();
                                    if (data !=null) {
                                        String info = data.getStringExtra("info");
                                        Toast.makeText(Pr2_Activity2.this, "Время занятия успешно передано: " + info, Toast.LENGTH_SHORT).show();
                                    }
                                }
                                else {
                                    Toast.makeText(Pr2_Activity2.this, "Операция отменена", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                        );

    }
    public void onNextActivity5(View view) {
        Intent intent = new Intent(this, Pr2_Activity3.class);
        intent.putExtra("subject", subjectEditText.getText().toString());
        thirdActivityLauncher.launch(intent);
    }
}


