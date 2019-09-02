package top.linxixiangxin.passcet;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;

//导航栏
//下拉框

public class YibeiActivity extends Activity {

    //导航栏
    private BottomNavigationBar bottomNavigationBar;
    int lastSelectedPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yibei);


        //下拉框1
        String[] ctype2 = new String[]{"CET_4","CET_6"};
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ctype2);  //创建一个数组适配器
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);     //设置下拉列表框的下拉选项样式
        Spinner spinner2 = super.findViewById(R.id.yibei_choose);
        spinner2.setAdapter(adapter2);

        //下拉框2
        String[] ctype3 = new String[]{"新旧排序","旧新排序","A-Z排序","Z-A排序"};
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ctype3);  //创建一个数组适配器
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);     //设置下拉列表框的下拉选项样式
        Spinner spinner3 = super.findViewById(R.id.yibei_paixu);
        spinner3.setAdapter(adapter3);

    }
}
