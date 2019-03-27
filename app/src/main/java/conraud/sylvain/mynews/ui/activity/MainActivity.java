package conraud.sylvain.mynews.ui.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.telecom.Call;
import android.view.Menu;


import conraud.sylvain.mynews.R;
import conraud.sylvain.mynews.ui.adapters.ViewPagerAdapter;
import conraud.sylvain.mynews.utils.CallBack;
import conraud.sylvain.mynews.utils.CallService;

public class MainActivity extends AppCompatActivity {

    CallBack callBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        configureAll();
    }

    /*Configure all*/
    private void configureAll(){
        configureToolbar();
        callBack = CallBack.getInstance();
        configureViewPagerAndTabs();
        call();
    }

    /*Toolbar*/
    //Configure Toolbar
    private void configureToolbar(){
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }
    //Implement toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    /*Configure ViewPager and Tabs*/
    private void configureViewPagerAndTabs(){
        ViewPager viewPager = findViewById(R.id.activity_main_view_pager);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        callBack.viewPagerAdapter = viewPagerAdapter;
        viewPager.setAdapter(viewPagerAdapter);
        TabLayout tabLayout = findViewById(R.id.activity_main_tab_layout);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setupWithViewPager(viewPager);
    }

    /*Call*/
    private void call(){
        CallService.callTopStories(callBack, "home", CallBack.KEY_TOPSTORIES, this);
        CallService.callMostPopular(callBack,"viewed", CallBack.KEY_MOSTPOPULAR, this);
        CallService.callTopStories(callBack, "science", CallBack.KEY_SCIENCE,this);
    }
}
