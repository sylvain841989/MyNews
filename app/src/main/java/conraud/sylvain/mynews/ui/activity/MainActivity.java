package conraud.sylvain.mynews.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;


import conraud.sylvain.mynews.R;
import conraud.sylvain.mynews.ui.adapters.ViewPagerAdapter;
import conraud.sylvain.mynews.utils.CallBack;
import conraud.sylvain.mynews.utils.CallService;
import conraud.sylvain.mynews.utils.Notification;
import conraud.sylvain.mynews.utils.NotificationReceveir;
import conraud.sylvain.mynews.utils.Save;

public class MainActivity extends AppCompatActivity {

    CallBack callBack;
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    public ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        configureAll();

    }

    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }

    /*Configure all*/
    private void configureAll() {
        progressBar = findViewById(R.id.progress_bar);
        configureToolbar();
        callBack = CallBack.getInstance();
        configureViewPagerAndTabs();
        call();
        configureSave();
        configureNotification();
        configureDrawerLayout();
        configureNavigationView();


    }

    /*Toolbar*/
    //Configure Toolbar
    private void configureToolbar() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    //Implement toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
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

    /*Configure Drawer layout*/
    private void configureDrawerLayout(){
        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout, toolbar,0,0);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }
    /*configure Navigation View*/
    private void configureNavigationView(){
        final NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                progressBar.setVisibility(View.VISIBLE);
                int id = menuItem.getItemId();
                switch (id){
                    case R.id.activity_main_drawer_arts:
                        callMenuDrawer("arts");
                        break;
                    case R.id.activity_main_drawer_business:
                        callMenuDrawer("business");
                        break;
                    case R.id.activity_main_drawer_entrepreneur:
                        callMenuDrawer("movies");
                        break;
                    case R.id.activity_main_drawer_politic:
                        callMenuDrawer("politics");
                        break;
                    case R.id.activity_main_drawer_sports:
                        callMenuDrawer("sports");
                        break;
                    case R.id.activity_main_drawer_travel:
                        callMenuDrawer("travel");
                        break;
                }
                drawerLayout.closeDrawer(GravityCompat.START);

                return true;
            }
        });
    }

    /*Configure Save*/
    private void configureSave(){
        SharedPreferences preferences = getSharedPreferences("save",MODE_PRIVATE);
        Save save = Save.getInstance();
        //save.context = this;
        save.preferences = preferences;
    }

    private void configureNotification(){
        //Notification.getInstance().context = this;
        Notification.getInstance().changeStateNotification(this);
    }

    /*Call*/
    private void call() {
        CallService.callTopStories(callBack, "home", CallBack.KEY_TOPSTORIES, this);
        CallService.callMostPopular(callBack, CallBack.KEY_MOSTPOPULAR, this);
        CallService.callTopStories(callBack, "science", CallBack.KEY_SCIENCE, this);
    }
    private void callMenuDrawer (String filter){
        CallService.callTopStories(callBack,filter,CallBack.KEY_MENU_DRAWER,this);
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
