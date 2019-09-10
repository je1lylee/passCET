package top.linxixiangxin.passcet;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class DengluActivity extends AppCompatActivity {
    public static final int SHOW_RESPONSE = 0;
    private Button btn_log, btn_reg, btn_yanzheng;
    private EditText ed_email;
    private String email;
    private EditText ed_password;
    private static final String TAG = "DengluActivity";
    public  String id;



    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_denglu);


        //绑定id-s
        btn_log = findViewById(R.id.btn_denglu_login);  //登录按钮
        btn_reg = findViewById(R.id.btn_denglu_register);  //注册按钮
        btn_yanzheng = findViewById(R.id.DL_BT_yanzheng);
        ed_email = findViewById(R.id.username);
        ed_password = findViewById(R.id.DL_password);
        email= ed_email.getText().toString();
        Log.d(TAG, email);

        //注册按钮的点击事件
        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DengluActivity.this, Zhuce1Activity.class);
                startActivity(intent);
            }
        });

        //登陆的点击事件
        btn_log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = null;
                sendRequestPost(url,email);
                Intent intent = new Intent(DengluActivity.this, Study1Activity.class);
                startActivity(intent);
                Toast.makeText(DengluActivity.this, "欢迎使用PassCET!", Toast.LENGTH_LONG).show();
            }


            public void sendRequestPost(final String url,final String email){

            }
        });


        //点击验证码的事件
        btn_yanzheng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendRequestWithHttpURLConnection();
                Log.d(TAG, "asd");
            }

            private Handler handler = new Handler() {
            };

            private void sendRequestWithHttpURLConnection() {
                //开启线程来发起网络请求
                new Thread() {
                    @Override
                    public void run() {
                        email = ed_email.getText().toString();
                        Log.d(TAG, "email:" + email);
                        OkHttpClient okHttpClient = new OkHttpClient();
                        RequestBody requestBody = new FormBody.Builder()
                                .add("type", "0")
                                .add("token", "SMvwlN1kjrtKzIfxCLHlejDedpVSTRvW")
                                .add("email", email)
                                .build();
                        Request request = new Request.Builder()
                                .url("http://112.74.184.181:8000/login/")
                                .post(requestBody)
                                .build();

                        try {
                            Response response = okHttpClient.newCall(request).execute();
                            String responseData = response.body().string();
                            Log.d(TAG, "responseData: " + responseData);
                            if (response.isSuccessful()) {
                                JSONObject jsonObject = new JSONObject(responseData);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
            }

        });
    }
}


