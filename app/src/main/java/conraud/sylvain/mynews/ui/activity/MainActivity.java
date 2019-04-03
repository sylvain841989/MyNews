package conraud.sylvain.mynews.ui.activity;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;


import java.util.Calendar;

import conraud.sylvain.mynews.R;
import conraud.sylvain.mynews.ui.adapters.ViewPagerAdapter;
import conraud.sylvain.mynews.utils.CallBack;
import conraud.sylvain.mynews.utils.CallService;
import conraud.sylvain.mynews.utils.Notification;
import conraud.sylvain.mynews.utils.NotificationReceveir;
import conraud.sylvain.mynews.utils.Save;

public class MainActivity extends AppCompatActivity {

    CallBack callBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        configureAll();
    }

    /*Configure all*/
    private void configureAll() {
        configureToolbar();
        callBack = CallBack.getInstance();
        configureViewPagerAndTabs();
        call();
        configureSave();
        configureNotification();
    }

    /*Toolbar*/
    //Configure Toolbar
    private void configureToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    //Implement toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    //Configure click
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_button_search:
                startSearchActivity();
                return true;
            case R.id.menu_button_notifications:
                startNotificationActivity();
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    /*Configure ViewPager and Tabs*/
    private void configureViewPagerAndTabs() {
        ViewPager viewPager = findViewById(R.id.activity_main_view_pager);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        callBack.viewPagerAdapter = viewPagerAdapter;
        viewPager.setAdapter(viewPagerAdapter);
        TabLayout tabLayout = findViewById(R.id.activity_main_tab_layout);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setupWithViewPager(viewPager);
    }
    /*Configure Save*/
    private void configureSave(){
        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        Save save = Save.getInstance();
        save.context = this;
        save.preferences = preferences;
    }

    private void configureNotification(){
        Notification.getInstance().context = this;
        Notification.getInstance().configureNotification();
    }

    /*Call*/
    private void call() {
        CallService.callTopStories(callBack, "home", CallBack.KEY_TOPSTORIES, this);
        CallService.callMostPopular(callBack, CallBack.KEY_MOSTPOPULAR, this);
        CallService.callTopStories(callBack, "science", CallBack.KEY_SCIENCE, this);
    }

    /*Open Activity*/
    //Search activity
    private void startSearchActivity() {
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
    }

    //Notification activity
    private void startNotificationActivity(){
        Intent intent = new Intent(this,NotificationActivity.class);
        startActivity(intent);
    }


    }
