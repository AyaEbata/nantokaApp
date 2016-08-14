package com.example.aya.nantokaapp;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by aya on 2016/05/05.
 */
public class TimerActivity extends AppCompatActivity {


    private static final int DEFAULT_START_TIME_MINUTE = 30;
    private static final int DEFAULT_START_TIME_SECOND = 0;
    private static final int ONE_SECOND = 60000;
    private static final int ONE_MINUTE_SECOND = 1000;
    private static final int DEFAULT_START_TIME_MS = DEFAULT_START_TIME_MINUTE * ONE_SECOND;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        initToolbar();

        setCountDownTimer(DEFAULT_START_TIME_MINUTE, DEFAULT_START_TIME_SECOND);
        setTimePickerDialog();
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setMenuReturnButton();
    }

    private void setMenuReturnButton() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setCountDownTimer(int startTimeMinute, int startTimeSecond) {
        TextView timerTextView = (TextView) findViewById(R.id.timer_text);
        initCountDownTimerText(timerTextView, startTimeMinute, startTimeSecond);

        CountDownTimer countDownTimer = new CountDownTimer(startTimeMinute * ONE_SECOND, ONE_MINUTE_SECOND){

            @Override
            public void onFinish(){
                Toast.makeText(getApplicationContext(), "洗濯終わったよ！", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTick(long millisUntilFinished){
                timerTextView.setText(getString(R.string.timer,
                        millisUntilFinished/ONE_SECOND, millisUntilFinished/ONE_MINUTE_SECOND%60));
            }
        };

        Button startButton = (Button) findViewById(R.id.start_button);
        startButton.setOnClickListener(view -> countDownTimer.start());

        Button cancelButton = (Button) findViewById(R.id.cancel_button);
        cancelButton.setOnClickListener(view -> {
            countDownTimer.cancel();
            initCountDownTimerText(timerTextView, startTimeMinute, startTimeSecond);
        });
    }

    private void initCountDownTimerText(TextView timerTextView, int startTimeMinute, int startTimeSecond) {
        timerTextView.setText(
                getString(R.string.timer, startTimeMinute, startTimeSecond));
    }

    private void setTimePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        int nowHour = calendar.get(Calendar.HOUR_OF_DAY);
        int nowMinute = calendar.get(Calendar.MINUTE);
        TimePickerDialog dialog = new TimePickerDialog(
                this,
                (view, selectHour, selectMinute) -> setCountDownTimer(selectMinute, DEFAULT_START_TIME_SECOND),
                nowHour,
                nowMinute,
                true);

        TextView timerText = (TextView)findViewById(R.id.timer_text);
        timerText.setClickable(true);
        timerText.setOnClickListener(view -> dialog.show());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;

            case R.id.action_top:
                startActivity(new Intent(TimerActivity.this, MainActivity.class));
                break;

            case R.id.action_reset:
                break;

            case R.id.action_total:
                startActivity(new Intent(TimerActivity.this, TotalActivity.class));
                break;

            case R.id.action_timer:
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}