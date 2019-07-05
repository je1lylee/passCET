package top.linxixiangxin.passcet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Zhuce2Activity extends AppCompatActivity {
    private Button btn_zc2_x,btn_zc2_back,btn_zc_finish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhuce2);

        //绑定id
        btn_zc2_back=findViewById(R.id.btn_zc2_back);  //返回上一层
        btn_zc_finish=findViewById(R.id.btn_zc_finish);//进入主页面
        btn_zc2_x=findViewById(R.id.btn_zc2_x);//返回登录页

        btn_zc2_x.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Zhuce2Activity.this,DengluActivity.class);
                startActivity(intent);
            }
        });

        btn_zc2_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Zhuce2Activity.this,Zhuce1Activity.class);
                startActivity(intent);
            }
        });

        btn_zc_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Zhuce2Activity.this,Study1Activity.class);
                startActivity(intent);
            }
        });

    }
}
