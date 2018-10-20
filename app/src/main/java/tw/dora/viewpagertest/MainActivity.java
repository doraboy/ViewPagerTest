package tw.dora.viewpagertest;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.concurrent.atomic.AtomicLongArray;

public class MainActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private Fragment[] fs = new Fragment[5];
    private ActionBar actionBar;
    private MyTabListener myTabListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fs[0] = new Page0();
        fs[1] = new Page1();
        fs[2] = new Page2();
        fs[3] = new Page3();
        fs[4] = new Page4();


        viewPager = findViewById(R.id.container);
        viewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));

        // viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
        viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                Log.v("brad","now:"+position);
                if(position==0) viewPager.setCurrentItem(1);
                else if(position==4) viewPager.setCurrentItem(3);
                else{
                    actionBar.setSelectedNavigationItem(position-1);
                }
            }
        });

        myTabListener = new MyTabListener();
        initActionBar();
        viewPager.setCurrentItem(1);

    }

    private void initActionBar(){
        actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        for(int i=1;i<4;i++){
            actionBar.addTab(actionBar.newTab()
            .setText("Page "+i)
            .setTabListener(myTabListener));
        }
    }

    private class MyTabListener implements ActionBar.TabListener{

        @Override
        public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

            viewPager.setCurrentItem(tab.getPosition()+1);
        }

        @Override
        public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

        }

        @Override
        public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

        }
    }

    public void gotoPage1(View view) {
        viewPager.setCurrentItem(1);
    }

    public void gotoPage2(View view) {
        viewPager.setCurrentItem(2);
    }

    public void gotoPage3(View view) {
        viewPager.setCurrentItem(3);
    }

    private class MyPagerAdapter extends FragmentStatePagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            return fs[i];
        }

        @Override
        public int getCount() {
            return fs.length;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            String title = "Page"+position;
            if(position==0||position==4) title="";
            return title;
            //return super.getPageTitle(position);
        }
    }
}
