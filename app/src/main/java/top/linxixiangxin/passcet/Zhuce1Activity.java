package top.linxixiangxin.passcet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Zhuce1Activity extends AppCompatActivity implements View.OnClickListener {

    private Button sure;
    private EditText email, username, yzm, password, passwordagain;
    private CountDownTimerButton send;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhuce1);

        //绑定id-S
        send = findViewById(R.id.zc_send);  //发送验证码
        email = findViewById(R.id.zc_mail); //输入邮箱地址
        yzm = findViewById(R.id.zc_yzm);  //输入的验证码
        sure=findViewById(R.id.zc_sure);

        //确认注册后的点击事件
        sure.setOnClickListener(new View.OnClickListener() {
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
