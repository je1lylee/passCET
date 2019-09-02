package top.linxixiangxin.passcet;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

//导航栏
//下拉框

public class ShengciActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yibei);


        //下拉框1
        String[] ctype4 = new String[]{"CET_4", "CET_6"};
        ArrayAdapter <String> adapter4 = new ArrayAdapter <String>(this, android.R.layout.simple_spinner_item, ctype4);  //创建一个数组适配器
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);     //设置下拉列表框的下拉选项样式
        Spinner spinner4 = super.findViewById(R.id.shengci_choose);
        spinner4.setAdapter(adapter4);

        //下拉框2
        String[] ctype3 = new String[]{"新旧排序", "旧新排序", "A-Z排序", "Z-A排序"};
        ArrayAdapter <String> adapter3 = new ArrayAdapter <String>(this, android.R.layout.simple_spinner_item, ctype3);  //创建一个数组适配器
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);     //设置下拉列表框的下拉选项样式
        Spinner spinner3 = super.findViewById(R.id.shengci_paixu);
        spinner3.setAdapter(adapter3);


    }
}
