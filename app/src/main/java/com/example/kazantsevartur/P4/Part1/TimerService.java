package com.example.kazantsevartur.P4.Part1;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class TimerService extends Service {
    private Handler handler = new Handler();
    private Runnable runnable;
    private int counter = 0;
    private TextView tvDate, tvTime, tvName;
    private Intent serviceIntent;


    @Override
    public void onCreate() {
        super.onCreate();
        runnable = new Runnable() {
            @Override
            public void run() {
                counter++;
                if (counter % 5 ==0) {
                    Toast.makeText(TimerService.this, "Сервис работает " + counter + " сек", Toast.LENGTH_SHORT).show();
                }
                handler.postDelayed(this,1000);
            }
        };

    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        handler.postDelayed(runnable,1000);
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable);
        Toast.makeText(this, "Сервис остановлен", Toast.LENGTH_SHORT).show();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}