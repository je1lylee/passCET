package top.linxixiangxin.passcet;

        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;

public class DengluActivity extends AppCompatActivity {
    private Button btn_log,btn_reg,btn_fot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_denglu);

        //绑定id-s
        btn_log=findViewById(R.id.login);  //登录按钮
        btn_reg=findViewById(R.id.register);  //注册按钮
        btn_fot=findViewById(R.id.Forgot);  //忘记密码按钮

        //注册按钮的点击事件
        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DengluActivity.this,ZhuceActivity.class);
                startActivity(intent);
            }
        });

    }
}
