package top.linxixiangxin.passcet;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Zhuce2Activity extends AppCompatActivity {
    private Button btn_zc2_x,btn_zc2_back,btn_zc_finish;
    private EditText name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhuce2);
        final String TAG = "Zhuce2Activity";

        final Intent zhuCe1 = getIntent();

        //绑定id
        btn_zc2_back=findViewById(R.id.btn_zc2_back);  //返回上一层
        btn_zc_finish=findViewById(R.id.btn_zc_finish);//进入主页面
        btn_zc2_x=findViewById(R.id.btn_zc2_x);//返回登录页
        name = findViewById(R.id.edit_zc2_nc);

        btn_zc2_x.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(Zhuce2Activity.this,DengluActivity.class);
                startActivity(intent2);
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
//                Intent intent = new Intent(Zhuce2Activity.this,Study1Activity.class);
//
//                startActivity(intent);
                Handler handler = new Handler() {

                };
                new Thread(){
                    @Override
                    public void run() {
                        OkHttpClient okHttpClient = new OkHttpClient();
                        RequestBody requestBody = new FormBody.Builder()
                                .add("name", name.getText().toString())
                                .add("token", "SMvwlN1kjrtKzIfxCLHlejDedpVSTRvW")
                                .add("email", zhuCe1.getStringExtra("email"))
                                .add("image", String.valueOf(R.drawable.denglu))
//                                暂时是4，具体是用户选择
                                .add("level", "band4")
                                .build();
                        Request request = new Request.Builder()
                                .url("http://112.74.184.181:8000/addaccount/")
                                .post(requestBody)
                                .build();
                        try {
                            Response response = okHttpClient.newCall(request).execute();
                            String s = response.body().string();
                            Log.d(TAG, "s: " + s);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
            }
        });


    }

}
