package com.example.kazantsevartur.P4.Part2;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import com.example.kazantsevartur.R;

import java.util.Arrays;

public class Test extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        Button btnSequential = findViewById(R.id.btnSequential);
        btnSequential.setOnClickListener(v -> runSequentialTasks());

        Button btnParallel = findViewById(R.id.btnParallel);
        btnParallel.setOnClickListener(v -> runParallelTasks());
    }

    private void runSequentialTasks() {
        OneTimeWorkRequest task1 = new OneTimeWorkRequest.Builder(MyWorker.class)
                .setInputData(new Data.Builder().putString("task_number", "1").build())
                .build();
        OneTimeWorkRequest task2 = new OneTimeWorkRequest.Builder(MyWorker.class)
                .setInputData(new Data.Builder().putString("task_number", "2").build())
                .build();
        OneTimeWorkRequest task3 = new OneTimeWorkRequest.Builder(MyWorker.class)
                .setInputData(new Data.Builder().putString("task_number", "3").build())
                .build();

        WorkManager.getInstance(this)
                .beginWith(task1)
                .then(task2)
                .then(task3)
                .enqueue();
    }

    private void runParallelTasks() {
        OneTimeWorkRequest taskA = new OneTimeWorkRequest.Builder(MyWorker.class)
                .setInputData(new Data.Builder().putString("task_number", "A").build())
                .build();
        OneTimeWorkRequest taskB = new OneTimeWorkRequest.Builder(MyWorker.class)
                .setInputData(new Data.Builder().putString("task_number", "B").build())
                .build();

        WorkManager.getInstance(this)
                .beginWith(Arrays.asList(taskA, taskB))
                .enqueue();
    }
}