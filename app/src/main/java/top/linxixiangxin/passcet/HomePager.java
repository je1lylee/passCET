package top.linxixiangxin.passcet;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by dell on 2019/8/29.
 */

public class HomePager extends FragmentPagerAdapter {

    private FristFragment firsts;
    private SecondFragment second;
    private ThridFragment thrid;

    public HomePager(FragmentManager fragmentManager) {
        super(fragmentManager);
        firsts = new FristFragment();
        second = new SecondFragment();
        thrid = new ThridFragment();
    }


    @Override
    public Fragment getItem(int i) {
        Fragment fragment = null;
        switch (i) {
            case Study1Activity.HOME_VIEW:
                fragment = firsts;
                break;
            case Study1Activity.ORDER_VIEW:
                fragment = second;
                break;
            case Study1Activity.MINE_VIEW:
                fragment = thrid;
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }
}