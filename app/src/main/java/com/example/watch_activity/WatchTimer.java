package com.example.watch_activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class WatchTimer extends AppCompatActivity {

    private TextView textView300;
    private int seconds = 0;
    private Handler handler;
    private boolean isPaused = false; // 일시 정지 상태를 나타내는 변수

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.watch_timer);

        textView300 = findViewById(R.id.textView300);
        handler = new Handler();

        // 초를 측정하기 위해 핸들러를 사용합니다.
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!isPaused) { // 일시 정지 상태가 아닐 때만 초를 증가시킵니다.
                    seconds++;
                    // 'textView300'에 초를 표시합니다. (예: 00:00:01)
                    String time = String.format("%02d:%02d:%02d", seconds / 3600, (seconds % 3600) / 60, seconds % 60);
                    textView300.setText(time);
                }

                // 1초마다 실행합니다.
                handler.postDelayed(this, 1000);
            }
        }, 1000); // 1초마다 실행

        Button pauseButton = findViewById(R.id.pause);
        Button stopButton = findViewById(R.id.stop);

        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 'pause' 버튼을 클릭하면 일시 정지/재개 상태를 토글합니다.
                isPaused = !isPaused;
            }
        });

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 'stop' 버튼을 클릭하면 타이머를 중지하고 토스트 메시지를 표시한 후 'WatchTodoAlarm' 액티비티로 이동합니다.
                handler.removeCallbacksAndMessages(null); // 핸들러 작업 중지

                // 토스트 메시지를 생성하고 사용자 정의 레이아웃을 설정합니다.
                Toast toast = new Toast(getApplicationContext());
                LayoutInflater inflater = getLayoutInflater();
                View layout = inflater.inflate(R.layout.custom_toast, null);
                TextView toastTextView = layout.findViewById(R.id.textView);
                toastTextView.setText("기록을 종료합니다.");
                toast.setView(layout);

                // 토스트 메시지를 표시합니다.
                toast.setDuration(Toast.LENGTH_SHORT);
                toast.show();

                // 일정 시간(예: 3초) 뒤에 'WatchTodoAlarm' 액티비티로 이동합니다.
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(WatchTimer.this, WatchTodoAlarm.class);
                        startActivity(intent);
                    }
                }, 3000); // 3초 딜레이
            }
        });
    }
}
