package com.example.kazantsevartur;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        MyObject myObjectInput= (MyObject) getIntent().getSerializableExtra("myObject");
        TextView text=findViewById(R.id.textView8);
        text.setText(getResources().getString(R.string.trainer));
        if (myObjectInput!=null) {
            TextView nameText = findViewById(R.id.textView2);
            TextView ageText = findViewById(R.id.textView7);
            TextView groupText = findViewById(R.id.editTextPhone);
            TextView scoreText = findViewById(R.id.editTextPhone2);
            nameText.setText("Name " + myObjectInput.getName());
            groupText.setText("Group " + myObjectInput.getGroup());
            ageText.setText("Age " + myObjectInput.getAge());
            scoreText.setText("Score " + myObjectInput.getScore());
        }
    }
    public void onNextActivity1(View view) {
        finish();
    }
}