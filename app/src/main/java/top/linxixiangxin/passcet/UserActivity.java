package top.linxixiangxin.passcet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class UserActivity extends AppCompatActivity {
    private Button btn_user_tc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        btn_user_tc = findViewById(R.id.btn_user_tc);
        btn_user_tc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserActivity.this,DengluActivity.class);
                startActivity(intent);
            }
        });


        String[] ctype = new String[]{"CET_4", "CET_6"};
        ArrayAdapter<String> adapter = new ArrayAdapter <String>(this, android.R.layout.simple_spinner_item, ctype);  //创建一个数组适配器
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);     //设置下拉列表框的下拉选项样式
        Spinner spinner = super.findViewById(R.id.spn_user_spn);
        spinner.setAdapter(adapter);
    }
}
