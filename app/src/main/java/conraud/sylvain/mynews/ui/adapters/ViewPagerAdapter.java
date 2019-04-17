package conraud.sylvain.mynews.ui.adapters;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


import conraud.sylvain.mynews.ui.fragments.MainFragment;


public class ViewPagerAdapter extends FragmentPagerAdapter {

    public final MainFragment[] arrayFragment = new MainFragment[3];

    public ViewPagerAdapter(FragmentManager fm ) {
        super(fm);

        //create fragment array
        for (int i = 0 ; i<arrayFragment.length ; i++)
        {
            arrayFragment[i] = MainFragment.newInstance(i);
        }
    }

    @Override
    public Fragment getItem(int i) {
        return arrayFragment[i];
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0 : return "Top Stories";
            case 1 : return "Most Popular";
            case 2 : return "Science";
            default: return null;
        }
    }
}
