package com.android.alarmy_test2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TimePicker;
import android.widget.Toast;


public class AddAlarm extends AppCompatActivity {
    TimePicker timePicker;
    ImageView backBtn, playRingtoneBtn;
    private boolean isPlay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_alarm);

        backBtn = findViewById(R.id.back_pressed);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddAlarm.super.onBackPressed();
            }
        });

        isPlay = false;
        playRingtoneBtn = findViewById(R.id.play_ring_tone);
        playRingtoneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isPlay) {
                    playRingtoneBtn.setImageResource(R.drawable.ic_play);
                    isPlay = false;
                } else {
                    playRingtoneBtn.setImageResource(R.drawable.ic_stop);
                    isPlay = true;
                }
            }
        });
        timePicker = findViewById(R.id.timePicker);
        timePicker.setIs24HourView(true);
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int i, int i1) {

            }
        });

    }



}