package com.example.watch_activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class WatchTodoAlarm extends AppCompatActivity {

    private Button button400;
    private TextView textView300;
    private int seconds = 0;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.watch_todoalarm);

        button400 = findViewById(R.id.button400);
        textView300 = findViewById(R.id.textView300);
        handler = new Handler();

        button400.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 'watch_timer' 액티비티로 이동합니다.
                Intent intent = new Intent(WatchTodoAlarm.this, WatchTimer.class);
                startActivity(intent);

                // 초를 측정하기 위해 핸들러를 사용합니다.
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        seconds++;
                        // 'textView300'에 초를 표시합니다. (예: 00:00:01)
                        String time = String.format("%02d:%02d:%02d", seconds / 3600, (seconds % 3600) / 60, seconds % 60);
                        textView300.setText(time);

                        // 1초마다 실행합니다.
                        handler.postDelayed(this, 1000);
                    }
                }, 1000); // 1초마다 실행
            }
        });
    }
}
