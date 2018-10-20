package tw.dora.viewpagertest;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private Fragment[] fs = new Fragment[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fs[0] = new Page1();
        fs[1] = new Page2();
        fs[2] = new Page3();

        viewPager = findViewById(R.id.container);
        viewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));

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
    }
}
