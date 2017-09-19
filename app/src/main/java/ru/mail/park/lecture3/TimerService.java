package ru.mail.park.lecture3;

import android.app.Service;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;

public class TimerService extends Service {

    public static final String ACTION_START = "start";
    public static final String ACTION_STOP = "stop";

    public static final String ACTION_TICK = "tick";
    public static final String ACTION_FINISH = "finish";

    public static final String EXTRA_SECONDS = "seconds";

    private CountDownTimer timer;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(final Intent intent, final int flags, final int startId) {
        switch (intent.getAction()) {
            case ACTION_START:
                int seconds = intent.getIntExtra(EXTRA_SECONDS, 0);
                startTimer(seconds);
                break;
            case ACTION_STOP:
                stopTimer();
                break;
        }
        return START_NOT_STICKY;
    }

    private void startTimer(final int seconds) {
        if (seconds <= 0) {
            return;
        }

        if (timer != null) {
            timer.cancel();
        }

        final LocalBroadcastManager broadcastManager = LocalBroadcastManager.getInstance(this);

        timer = new CountDownTimer(seconds * 1000L, 500L) {
            @Override
            public void onTick(final long millisUntilFinished) {
                long estimate = millisUntilFinished / 1000;
                Intent tickIntent = new Intent(ACTION_TICK);
                tickIntent.putExtra(EXTRA_SECONDS, estimate);
                broadcastManager.sendBroadcast(tickIntent);
            }

            @Override
            public void onFinish() {
                Intent tickIntent = new Intent(ACTION_FINISH);
                broadcastManager.sendBroadcast(tickIntent);
            }
        };
        timer.start();
    }

    private void stopTimer() {
        if (timer != null) {
            timer.cancel();
        }
        Intent tickIntent = new Intent(ACTION_FINISH);
        LocalBroadcastManager.getInstance(this).sendBroadcast(tickIntent);
    }
}
