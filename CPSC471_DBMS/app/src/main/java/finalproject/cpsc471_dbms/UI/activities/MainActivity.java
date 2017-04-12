package finalproject.cpsc471_dbms.UI.activities;

import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import finalproject.cpsc471_dbms.R;
import finalproject.cpsc471_dbms.UI.custom.SlidingTabLayout;
import finalproject.cpsc471_dbms.UI.adapters.ViewPagerAdapter;

/* Contains the bulk of the program.
 * This class will do everything that manages the general program.
 * The number and title of tabs will change. There may only be one or two tabs in the final product. */
public class MainActivity extends AppCompatActivity {

    // Constants
    public static final int NORMAL = 0;
    public static final int LIBRARIAN = 1;
    public static final int SPONSOR = 2;

    // Global end user flag.
    public static int user = 0;

    // Declaring Your View and Variables
    private Toolbar toolbar;
    private ViewPager pager;
    private ViewPagerAdapter adapter;
    private SlidingTabLayout tabs;
    private CharSequence Titles[] = {"Dashboard", "Account", "Categories"};
    private int Numboftabs = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Sets the toolbar.
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //------------- The following code is for the tabs setup in the application. -------------

        //Get username to toast
        Toast.makeText(this, "Logged in as " + user, Toast.LENGTH_SHORT).show();

        // Creating The ViewPagerAdapter and Passing Fragment Manager, Titles fot the Tabs and Number Of Tabs.
        adapter = new ViewPagerAdapter(getSupportFragmentManager(), Titles, Numboftabs);

        // Assigning ViewPager View and setting the adapter
        pager = (ViewPager) findViewById(R.id.viewPager);
        pager.setAdapter(adapter);

        // Assigning the Sliding Tab Layout View
        tabs = (SlidingTabLayout) findViewById(R.id.tabs);
        tabs.setDistributeEvenly(true); // To make the Tabs Fixed set this true, This makes the tabs Space Evenly in Available width

        // Setting the ViewPager For the SlidingTabsLayout
        tabs.setViewPager(pager);

        //----------------------------------------------------------------------------------------
    }
}