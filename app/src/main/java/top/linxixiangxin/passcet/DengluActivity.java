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

        btn_log=findViewById(R.id.login);
        btn_reg=findViewById(R.id.register);
        btn_fot=findViewById(R.id.Forgot);

        btn_log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DengluActivity.this,ZhuceActivity.class);
                startActivity(intent);
            }
        });

    }
}
