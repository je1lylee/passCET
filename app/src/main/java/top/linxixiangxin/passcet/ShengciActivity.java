package top.linxixiangxin.passcet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

//导航栏
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.util.Log;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import Fragment.SC;
import Fragment.XUEXI;
import Fragment.ZXJ;


//下拉框
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class ShengciActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener {

    //导航栏
    private BottomNavigationBar bottomNavigationBar;
    int lastSelectedPosition = 0;
    private String TAG = Study1Activity.class.getSimpleName();
    private XUEXI xuexiFragment;
    private ZXJ zxjFragment;
    private SC scFragment;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yibei);


        //下拉框1
        String[] ctype4 = new String[]{"CET_4","CET_6"};
        ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ctype4);  //创建一个数组适配器
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);     //设置下拉列表框的下拉选项样式
        Spinner spinner4 = super.findViewById(R.id.shengci_choose);
        spinner4.setAdapter(adapter4);

        //下拉框2
        String[] ctype3 = new String[]{"新旧排序","旧新排序","A-Z排序","Z-A排序"};
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ctype3);  //创建一个数组适配器
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);     //设置下拉列表框的下拉选项样式
        Spinner spinner3 = super.findViewById(R.id.shengci_paixu);
        spinner3.setAdapter(adapter3);



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
                break;

            default:
                break;
        }

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
}
