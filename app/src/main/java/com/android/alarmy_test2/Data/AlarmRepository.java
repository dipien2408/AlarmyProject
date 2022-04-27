package com.android.alarmy_test2.Data;

import android.annotation.SuppressLint;
import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import java.util.List;

public class AlarmRepository {
    private AlarmDao alarmDao;
    private LiveData<List<Alarm>> alarmsLiveData;
    private List<Alarm> alarmsList;

    public AlarmRepository(Application application) {
        AlarmDatabase db = AlarmDatabase.getDatabase(application);
        alarmDao = db.alarmDao();
        alarmsLiveData = alarmDao.getAlarms();
    }

    @SuppressLint("LongLogTag")
    public void insert(Alarm alarm) {
        AlarmDatabase.databaseWriteExecutor.execute(() -> {
            Log.d("Tới đây r nè", String.valueOf(alarm));
            alarmDao.insert(alarm);
            alarmsList = alarmDao.getList();
            for(int i =0;i<alarmsList.size();i++) {
                Log.d("Show list data getAlarmId", String.valueOf(alarmsList.get(i).getAlarmId()));
                Log.d("Show list data getHour", String.valueOf(alarmsList.get(i).getHour()));
                Log.d("Show list data getMinute", String.valueOf(alarmsList.get(i).getMinute()));
                Log.d("Show list data getTitle", String.valueOf(alarmsList.get(i).getTitle()));
                Log.d("Show list data isDaily", String.valueOf(alarmsList.get(i).isDaily()));
                Log.d("Show list data isRecurring", String.valueOf(alarmsList.get(i).isRecurring()));
                Log.d("Show list data isStarted", String.valueOf(alarmsList.get(i).isStarted()));
                Log.d("Show list data isMonday", String.valueOf(alarmsList.get(i).isMonday()));
                Log.d("Show list data isTuesday", String.valueOf(alarmsList.get(i).isTuesday()));
                Log.d("Show list data isWednesday", String.valueOf(alarmsList.get(i).isWednesday()));
                Log.d("Show list data isThursday", String.valueOf(alarmsList.get(i).isThursday()));
                Log.d("Show list data isFriday", String.valueOf(alarmsList.get(i).isFriday()));
                Log.d("Show list data isSaturday", String.valueOf(alarmsList.get(i).isSaturday()));
                Log.d("Show list data isSunday", String.valueOf(alarmsList.get(i).isSunday()));
            }
        });
    }

    public void update(Alarm alarm) {
        AlarmDatabase.databaseWriteExecutor.execute(() -> {
            alarmDao.update(alarm);
        });
    }

    public LiveData<List<Alarm>> getAlarmsLiveData() {
        return alarmsLiveData;
    }
}
