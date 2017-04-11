package finalproject.cpsc471_dbms.UI.adapters;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import finalproject.cpsc471_dbms.UI.fragments.AccountFragment;
import finalproject.cpsc471_dbms.UI.fragments.CategoryFragment;
import finalproject.cpsc471_dbms.UI.fragments.DashboardFragment;

/**
 * Created by hp1 on 21-01-2015.
 *
 * This code is not ours, it is from android4devs, with slight modifications from us.
 * No point in reinventing the wheel especially if it's just aesthetic.
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    CharSequence Titles[]; // This will Store the Titles of the Tabs which are Going to be passed when ViewPagerAdapter is created
    int NumbOfTabs; // Store the number of tabs, this will also be passed when the ViewPagerAdapter is created


    // Build a Constructor and assign the passed Values to appropriate values in the class
    public ViewPagerAdapter(FragmentManager fm,CharSequence mTitles[], int mNumbOfTabsumb) {
        super(fm);

        this.Titles = mTitles;
        this.NumbOfTabs = mNumbOfTabsumb;

    }

    //This method return the fragment for the every position in the View Pager
    @Override
    public Fragment getItem(int position) {

        if(position == 0) // if the position is 0 we are returning the First tab
        {
            DashboardFragment tab1 = new DashboardFragment();
            return tab1;
        }
        else if(position == 1)
        {
            AccountFragment tab2 = new AccountFragment();
            return tab2;
        }
        else
        {
            CategoryFragment tab3 = new CategoryFragment();
            return tab3;
        }
    }

    // This method return the titles for the Tabs in the Tab Strip

    @Override
    public CharSequence getPageTitle(int position) {
        return Titles[position];
    }

    // This method return the Number of tabs for the tabs Strip

    @Override
    public int getCount() {
        return NumbOfTabs;
    }
}