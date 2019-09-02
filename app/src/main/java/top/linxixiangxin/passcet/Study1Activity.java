
package top.linxixiangxin.passcet;

<<<<<<< HEAD
import android.content.Intent;
=======
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Build;
>>>>>>> parent of dbcdb43... 卡片的更新
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
<<<<<<< HEAD
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
=======
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

import Fragment.SC;
import Fragment.XUEXI;
import Fragment.ZXJ;
import card.CardGroupView;

//导航栏
//下拉框
//卡片

public class Study1Activity extends Activity implements BottomNavigationBar.OnTabSelectedListener {

    //导航栏
    private BottomNavigationBar bottomNavigationBar;
    int lastSelectedPosition = 0;
    private String TAG = Study1Activity.class.getSimpleName();
    private XUEXI xuexiFragment;
    private ZXJ zxjFragment;
    private SC scFragment;


    private CardGroupView mCardGroupView;


    private Button study1_bt_tianjia;
    private Boolean b_sub_square = false;
    private TextView study1_tx_yb;
>>>>>>> parent of dbcdb43... 卡片的更新

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
<<<<<<< HEAD
=======
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
>>>>>>> parent of dbcdb43... 卡片的更新
        setContentView(R.layout.activity_study1);
        initView();
        initEvent();
        addCard();

<<<<<<< HEAD
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
=======

        //绑定id

        study1_tx_yb=findViewById(R.id.study1_tx_yb);
        study1_bt_tianjia=findViewById(R.id.study1_bt_tianjia);



        study1_bt_tianjia.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               if (!b_sub_square) {
                   b_sub_square = true;
                   //设置是否被激活状态，true表示被激    
                   study1_bt_tianjia.setActivated(b_sub_square);
                   study1_bt_tianjia.setText("已加入生词");
               } else {
                   b_sub_square = false;
                   //设置是否被激活状态，false表示未激活
                   study1_bt_tianjia.setActivated(b_sub_square);

                   study1_bt_tianjia.setText("添加到生词库");
               }

           }

        });

        study1_tx_yb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Study1Activity.this,YibeiActivity.class);
                startActivity(intent);
            }
        });


        //下拉框
        String[] ctype = new String[]{"CET_4","CET_6"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ctype);  //创建一个数组适配器
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);     //设置下拉列表框的下拉选项样式
        Spinner spinner = super.findViewById(R.id.spinner);
        spinner.setAdapter(adapter);

        //导航栏
        bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);

        /** 导航基础设置 包括按钮选中效果 导航栏背景色等 */
        bottomNavigationBar
                .setTabSelectedListener(this)
                .setMode(BottomNavigationBar.MODE_FIXED)
                /**
                 *  setMode() 内的参数有三种模式类型：
                 *  MODE_DEFAULT 自动模式：导航栏Item的个数<=3 用 MODE_FIXED 模式，否则用 MODE_SHIFTING 模式
                 *  MODE_FIXED 固定模式：未选中的Item显示文字，无切换动画效果。
                 *  MODE_SHIFTING 切换模式：未选中的Item不显示文字，选中的显示文字，有切换动画效果。
                 */

                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC)
                /**
                 *  setBackgroundStyle() 内的参数有三种样式
                 *  BACKGROUND_STYLE_DEFAULT: 默认样式 如果设置的Mode为MODE_FIXED，将使用BACKGROUND_STYLE_STATIC
                 *                                    如果Mode为MODE_SHIFTING将使用BACKGROUND_STYLE_RIPPLE。
                 *  BACKGROUND_STYLE_STATIC: 静态样式 点击无波纹效果
                 *  BACKGROUND_STYLE_RIPPLE: 波纹样式 点击有波纹效果
                 */

                .setActiveColor("#FF107FFD") //选中颜色
                .setInActiveColor("#e9e6e6") //未选中颜色
                .setBarBackgroundColor("#efecec");//导航栏背景色

        /** 添加导航按钮 */
        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.xx_1, "学习"))
                .addItem(new BottomNavigationItem(R.drawable.zxj, "照相机"))
                .addItem(new BottomNavigationItem(R.drawable.sc_1, "生词"))
                .setFirstSelectedPosition(lastSelectedPosition )
                .initialise(); //initialise 一定要放在 所有设置的最后一项

        setDefaultFragment();//设置默认导航栏

    }

    /**
     * 设置默认导航栏
     */
    private void setDefaultFragment() {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        xuexiFragment = xuexiFragment.newInstance("首页");
        transaction.replace(R.id.tb, xuexiFragment);
        transaction.commit();
    }

    /**
     * 设置导航选中的事件
     */

    public void onTabSelected(int position) {
        Log.d(TAG, "onTabSelected() called with: " + "position = [" + position + "]");
        FragmentManager fm = this.getFragmentManager();
        //开启事务
        FragmentTransaction transaction = fm.beginTransaction();
        switch (position) {
            case 0:
                if (xuexiFragment == null) {
                    xuexiFragment = xuexiFragment.newInstance("学习");
                }
                transaction.replace(R.id.tb, xuexiFragment);
                break;
            case 1:
                if (zxjFragment == null) {
                    zxjFragment = zxjFragment.newInstance("照相机");
                }
                transaction.replace(R.id.tb, zxjFragment);
                break;
            case 2:
                if (scFragment == null) {
                    scFragment = scFragment.newInstance("生词");
                }
                transaction.replace(R.id.tb, scFragment);
>>>>>>> parent of dbcdb43... 卡片的更新
                break;

        }
<<<<<<< HEAD
=======

        transaction.commit();// 事务提交
    }

    /**
     * 设置未选中Fragment 事务
     */

    public void onTabUnselected(int position) {

    }

    /**
     * 设置释放Fragment 事务
     */

    public void onTabReselected(int position) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }


    private void initView() {
        mCardGroupView = (CardGroupView) findViewById(R.id.card);
        mCardGroupView.setLoadSize(3);
        mCardGroupView.setMargin(0.15);
    }

    private void initEvent() {
        mCardGroupView.setLoadMoreListener(new CardGroupView.LoadMore() {
            @Override
            public void load() {
                mCardGroupView.addView(getCard());
                mCardGroupView.addView(getCard());
                mCardGroupView.addView(getCard());
                mCardGroupView.addView(getCard());
                mCardGroupView.addView(getCard());
                mCardGroupView.addView(getCard());
                mCardGroupView.addView(getCard());
                mCardGroupView.addView(getCard());
            }
        });
        mCardGroupView.setLeftOrRightListener(new CardGroupView.LeftOrRight() {
            @Override
            public void leftOrRight(boolean left) {
                if (left) {
                    Toast.makeText(Study1Activity.this, "向左滑喜欢！", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Study1Activity.this, "向右滑不喜欢！", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void addCard() {
        mCardGroupView.addView(getCard());
        mCardGroupView.addView(getCard());
        mCardGroupView.addView(getCard());
        mCardGroupView.addView(getCard());
        mCardGroupView.addView(getCard());
        mCardGroupView.addView(getCard());
        mCardGroupView.addView(getCard());
        mCardGroupView.addView(getCard());
    }

    private View getCard() {
        View card = LayoutInflater.from(this).inflate(R.layout.activity_cardstyle, null);
        View view = card.findViewById(R.id.remove);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCardGroupView.removeTopCard(true);
            }
        });
        return card;
>>>>>>> parent of dbcdb43... 卡片的更新
    }
}
