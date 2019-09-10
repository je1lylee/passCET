package top.linxixiangxin.passcet;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import adapter.shengci;

public class ShengciActivity extends Activity {

    private ListView friendsList;
    private shengci friendAdapter;
    private List<Map<String, Object>> datas;
    private Button btn_sc_xx,btn_sc_zxj;

    private String[] word = {"accident", "adventure",
            "advertisement", "ambulance", "anxious", "apologize","cottage","cube","advance","allinall","bind"};

    private String[] detail = {"n.事故，意外的事","n.冒险，奇遇","n.广告",
            "n.救护车","a.焦虑的，焦急的","vi.道歉，谢罪","n.村舍，小屋","n.水晶，结晶体","vi.前进;提高","a.总的说来;头等重要的","vt.捆绑;包扎"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shengci);


        friendsList = (ListView) findViewById(R.id.lv_show);
        datas=getFriendItems();
        friendAdapter = new shengci(this,datas);
        friendsList.setAdapter(friendAdapter);

        btn_sc_xx = findViewById(R.id.btn_sc_xx);
        btn_sc_zxj = findViewById(R.id.btn_sc_zxj);


        btn_sc_xx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShengciActivity.this,Study1Activity.class);
                startActivity(intent);
            }
        });

        btn_sc_zxj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShengciActivity.this,ZXJActivity.class);
                startActivity(intent);
            }
        });




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


    private List<Map<String,Object>>getFriendItems() {
        List <Map <String, Object>> datas = new ArrayList <Map <String, Object>>();
        for (int i = 0; i < word.length; i++) {
            Map <String, Object> map = new HashMap<String, Object>();
            map.put("word", word[i]);
            map.put("detail", detail[i]);
            datas.add(map);

        }

        return datas;
    }



}
