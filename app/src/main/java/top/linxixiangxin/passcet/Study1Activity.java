
package top.linxixiangxin.passcet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;

public class Study1Activity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener{


    private ViewPager myPager;
    private ArrayList<View> pager;
    private RadioGroup radioGroup;
    private RadioButton home, order, mine;
    public static final int HOME_VIEW = 0;
    public static final int ORDER_VIEW = 1;
    public static final int MINE_VIEW = 2;
    private static final String TAG = "PrimaryActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study1);

        radioGroup = findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(this);
        home = findViewById(R.id.homes);
        order = findViewById(R.id.order);
        mine = findViewById(R.id.mine);

        myPager = findViewById(R.id.contentView);
        HomePager homePager = new HomePager(getSupportFragmentManager());
        myPager.setAdapter(homePager);
        myPager.setCurrentItem(HOME_VIEW);
        home.setChecked(true);
        myPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
            }

            @Override
            public void onPageSelected(int i) {
                switch (i) {
                    case HOME_VIEW:
                        home.setChecked(true);
                        Log.d("1","1");
                        break;
                    case ORDER_VIEW:
                        order.setChecked(true);
                        Log.d("2","2");
                        break;
                    case MINE_VIEW:
                        mine.setChecked(true);
                        Log.d("3","3");
                        break;
                }
            }
            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });
//点击暂不支付跳转到未支付页面
        Intent intent = getIntent();
        if (intent.getIntExtra("order", 0) == 1){
            myPager.setCurrentItem(ORDER_VIEW);
            order.setChecked(true);
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.homes:
                myPager.setCurrentItem(HOME_VIEW);
                break;
            case R.id.order:
                myPager.setCurrentItem(ORDER_VIEW);
                break;
            case R.id.mine:
                myPager.setCurrentItem(MINE_VIEW);
                break;

        }
    }
}
