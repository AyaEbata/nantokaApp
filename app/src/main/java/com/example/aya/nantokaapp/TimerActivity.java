package com.example.aya.nantokaapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Created by aya on 2016/05/05.
 */
public class TimerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        initToolbar();
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setMenuReturnButton();
    }

    private void setMenuReturnButton() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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