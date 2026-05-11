package com.example.kazantsevartur.P4.Part2;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class MyWorker extends Worker {
    private static final String TAG = "MyWorker";

    public MyWorker(@NonNull Context context, @NonNull WorkerParameters params) {
        super(context, params);
    }

    @NonNull
    @Override
    public Result doWork() {
        String taskNumber = getInputData().getString("task_number");
        Log.d(TAG, "Задача " + taskNumber + " начата в потоке: " + Thread.currentThread().getName());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return Result.failure();
        }
        Log.d(TAG, "Задача " + taskNumber + " завершена");
        return Result.success();
    }

}
