package com.android.alarmy_test2.AddAlarm;

import android.annotation.SuppressLint;
import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.android.alarmy_test2.Data.Alarm;
import com.android.alarmy_test2.Data.AlarmRepository;

public class CreateAlarmViewModel extends AndroidViewModel {
    private AlarmRepository alarmRepository;

    public CreateAlarmViewModel(@NonNull Application application) {
        super(application);
        alarmRepository = new AlarmRepository(application);
    }

    @SuppressLint("LongLogTag")
    public void insert(Alarm alarm) {
        Log.d("LOG ---In CreateAlarmViewModel---", String.valueOf(alarm));
        alarmRepository.insert(alarm);
    }
}
