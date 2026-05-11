package com.example.kazantsevartur.P4.Part1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.kazantsevartur.R;

public class P4Part1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_p4_part1);
        TextView tvDate = findViewById(R.id.tvDate);
        TextView tvTime = findViewById(R.id.tvTime);
        TextView tvName = findViewById(R.id.tvName);

        tvDate.setText("Дата: " + getIntent().getStringExtra("selectedDate"));
        tvTime.setText("Время: " + getIntent().getStringExtra("selectedTime"));
        tvName.setText("Имя: " + getIntent().getStringExtra("enteredName"));

        Intent serviceIntent = new Intent(this, TimerService.class);

        findViewById(R.id.btnStart).setOnClickListener(v -> startService(serviceIntent));
        findViewById(R.id.btnStop).setOnClickListener(v -> stopService(serviceIntent));
    }
}