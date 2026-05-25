package com.example.kazantsevartur.P6.Part1;

import android.Manifest;
import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import com.example.kazantsevartur.R;

public class P6Part1 extends AppCompatActivity {

    private static final String CHANNEL_ID = "example_channel";
    public static final String DELAYED_CHANNEL_ID = "delayed_channel";

    private MediaPlayer mediaPlayer;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p6_part1);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.POST_NOTIFICATIONS}, 100);
            }
        }

        // === 1. WebView ===
        webView = findViewById(R.id.webview);
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("https://online-edu.mirea.ru");

        // === 2. MediaPlayer (удалённый файл) ===
        Button playButton = findViewById(R.id.playButton);
        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource("https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3");
            mediaPlayer.prepare();
        } catch (Exception e) { e.printStackTrace(); }

        playButton.setOnClickListener(v -> {
            if (!mediaPlayer.isPlaying()) mediaPlayer.start();
            else mediaPlayer.pause();
        });

        // === 3. Анимации ===
        ImageView rotateImageView = findViewById(R.id.rotateImageView);
        Button moveButton = findViewById(R.id.moveButton);
        TextView scaleTextView = findViewById(R.id.scaleTextView);

        // Вращение
        android.animation.ObjectAnimator rotateAnim =
                android.animation.ObjectAnimator.ofFloat(rotateImageView, "rotation", 0f, 360f);
        rotateAnim.setDuration(2000);
        rotateAnim.setRepeatCount(android.animation.ObjectAnimator.INFINITE);
        rotateAnim.start();

        // Перемещение
        moveButton.setOnClickListener(v -> {
            android.animation.ObjectAnimator moveAnim =
                    android.animation.ObjectAnimator.ofFloat(moveButton, "translationX", 0f, 300f);
            moveAnim.setDuration(1000);
            moveAnim.start();
        });

        // Масштабирование
        scaleTextView.setOnClickListener(v -> {
            android.animation.ObjectAnimator scaleX =
                    android.animation.ObjectAnimator.ofFloat(scaleTextView, "scaleX", 1f, 2f);
            android.animation.ObjectAnimator scaleY =
                    android.animation.ObjectAnimator.ofFloat(scaleTextView, "scaleY", 1f, 2f);
            scaleX.setDuration(1000);
            scaleY.setDuration(1000);
            scaleX.start();
            scaleY.start();
        });

        // === 4. Уведомления ===
        createNotificationChannel();
        createDelayedNotificationChannel();

        Button notifyButton = findViewById(R.id.notifyButton);
        Button delayedNotifyButton = findViewById(R.id.delayedNotifyButton);

        notifyButton.setOnClickListener(v -> {
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                    .setSmallIcon(android.R.drawable.ic_dialog_info)
                    .setContentTitle("Example Notification")
                    .setContentText("This is a test notification")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);

            NotificationManager nm = getSystemService(NotificationManager.class);
            nm.notify(1, builder.build());
            Toast.makeText(this, "Уведомление отправлено", Toast.LENGTH_SHORT).show();
        });

        delayedNotifyButton.setOnClickListener(v -> scheduleNotification(10000)); // 10
    }

    // Канал для обычных уведомлений
    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Example Channel";
            String desc = "Channel for example notifications";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(desc);
            NotificationManager nm = getSystemService(NotificationManager.class);
            nm.createNotificationChannel(channel);
        }
    }

    // Канал для отложенных уведомлений
    private void createDelayedNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Delayed Notifications";
            String desc = "Channel for delayed example notifications";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(DELAYED_CHANNEL_ID, name, importance);
            channel.setDescription(desc);
            NotificationManager nm = getSystemService(NotificationManager.class);
            nm.createNotificationChannel(channel);
        }
    }

    // Планирование отложенного уведомления
    private void scheduleNotification(long delay) {
        Intent intent = new Intent(this, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        long futureInMillis = System.currentTimeMillis() + delay;
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, futureInMillis, pendingIntent);
        Toast.makeText(this, "Уведомление запланировано", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}