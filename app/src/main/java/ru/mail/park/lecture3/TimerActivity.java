package ru.mail.park.lecture3;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class TimerActivity extends AppCompatActivity {

    private TextView time;

    private final BroadcastReceiver timerReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(final Context context, final Intent intent) {
            switch (intent.getAction()) {
                case TimerService.ACTION_TICK:
                    long seconds = intent.getLongExtra(TimerService.EXTRA_SECONDS, 0L);
                    time.setText(getString(R.string.timer_placeholder, seconds));
                    break;
                case TimerService.ACTION_FINISH:
                    time.setText(R.string.timer_finish_title);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        time = (TextView) findViewById(R.id.time);

        findViewById(R.id.start_timer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                startTimer();
            }
        });

        findViewById(R.id.stop_timer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                stopTimer();
            }
        });

        IntentFilter filter = new IntentFilter();
        filter.addAction(TimerService.ACTION_TICK);
        filter.addAction(TimerService.ACTION_FINISH);
        LocalBroadcastManager.getInstance(this).registerReceiver(timerReceiver, filter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(timerReceiver);
    }

    private void startTimer() {
        final EditText time = (EditText) findViewById(R.id.input);
        final int seconds = Integer.parseInt(time.getText().toString());

        final Intent intent = new Intent(this, TimerService.class);
        intent.setAction(TimerService.ACTION_START);
        intent.putExtra(TimerService.EXTRA_SECONDS, seconds);
        startService(intent);
    }

    private void stopTimer() {
        final Intent intent = new Intent(this, TimerService.class);
        intent.setAction(TimerService.ACTION_STOP);
        startService(intent);
    }
}
