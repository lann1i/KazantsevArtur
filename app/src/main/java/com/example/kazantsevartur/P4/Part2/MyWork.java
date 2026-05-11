package com.example.kazantsevartur.P4.Part2;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class MyWork extends Worker {
    public MyWork(@NonNull Context context, @NonNull WorkerParameters
            workerParams) {
        super(context, workerParams);
        int x = workerParams.getInputData().getInt("key2",0);
        String s = getInputData().getString("key1");
    }

    @NonNull
    @Override
    public Result doWork() {
        Data outputData = new Data.Builder()
                .putString("key3", "Hello from MyWork!")
                .putInt("key0", 456)
                .build();
        return Result.success(outputData);
    }
}
