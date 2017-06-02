package com.bwie.fanliang.fanliangliang1503d20170522;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.bwie.fanliang.fanliangliang1503d20170522.Fragment.Fragment02;
import com.bwie.fanliang.fanliangliang1503d20170522.Fragment.Fragment03;
import com.bwie.fanliang.fanliangliang1503d20170522.Fragment.Fragment04;
import com.bwie.fanliang.fanliangliang1503d20170522.Fragment.Fragment05;

public class MainActivity extends FragmentActivity {
    private ViewPager vp;
    private TabLayout tablayout;
    String[] TITLE = {"推荐","热点","北京","视频","社会"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vp = (ViewPager) findViewById(R.id.tablayout_vp);
        tablayout = (TabLayout) findViewById(R.id.tablayout_tablayout);



//绑//定
        tablayout.setupWithViewPager(vp);

//设置选择和选中字体的颜色
        tablayout.setTabTextColors(getResources().getColor(R.color.balck), getResources().getColor(R.color.balck));

//设置显示器颜色
        tablayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.white));

//设置模式有常规和滚动
        tablayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        vp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            Fragment fragment = null;
            @Override
            public Fragment getItem(int position) {

                switch (position){
                    case 0:
                        fragment = new MainFragment();
                        break;

                    case 1:
                        fragment = new Fragment02();
                        break;

                    case 2:
                        fragment = new Fragment03();
                        break;
                    case 3:
                        fragment = new Fragment04();
                        break;
                    case 4:
                        fragment = new Fragment05();
                        break;


                }
                return fragment;
            }

            @Override
            public int getCount() {
                return TITLE.length;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return TITLE[position];
            }
        });

    }
}
