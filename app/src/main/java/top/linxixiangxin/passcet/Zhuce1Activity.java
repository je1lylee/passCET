package top.linxixiangxin.passcet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Zhuce1Activity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_zc1_sure;
    private EditText edit_zc1_mail, edit_zc1_yzm;
    private CountDownTimerButton btn_zc1_send;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhuce1);

        //绑定id-S
        btn_zc1_send = findViewById(R.id.btn_zc1_send);  //发送验证码
        edit_zc1_mail = findViewById(R.id.edit_zc1_mail); //输入邮箱地址
        edit_zc1_yzm = findViewById(R.id.edit_zc1_yzm);  //输入的验证码
        btn_zc1_sure=findViewById(R.id.btn_zc1_sure);

        //确认注册后的点击事件
        btn_zc1_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Zhuce1Activity.this,Zhuce2Activity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onClick(View v) {

    }
}
