package top.linxixiangxin.passcet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Zhuce2Activity extends AppCompatActivity {
    private Button x,back,finish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhuce2);

        //绑定id

        back=findViewById(R.id.zc2_back);
        finish=findViewById(R.id.zc_finish);
        x=findViewById(R.id.zc2_x);

        x.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Zhuce2Activity.this,DengluActivity.class);
                startActivity(intent);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Zhuce2Activity.this,Zhuce1Activity.class);
                startActivity(intent);
            }
        });

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Zhuce2Activity.this,Study1Activity.class);
                startActivity(intent);
            }
        });

    }
}
