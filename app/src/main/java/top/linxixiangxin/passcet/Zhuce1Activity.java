package top.linxixiangxin.passcet;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Zhuce1Activity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "Zhuce1Activity";
    private Button btn_zc1_sure;
    private EditText edit_zc1_mail, edit_zc1_yzm;
    private CountDownTimerButton btn_zc1_send;
    private String id;
    private Handler getYZM, queren;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhuce1);

        //绑定id
        btn_zc1_send = findViewById(R.id.btn_zc1_send);  //发送验证码
        edit_zc1_mail = findViewById(R.id.edit_zc1_mail); //输入邮箱地址
        edit_zc1_yzm = findViewById(R.id.edit_zc1_yzm);  //输入的验证码
        btn_zc1_sure=findViewById(R.id.btn_zc1_sure);

        //确认注册后的点击事件
//        TODO 跳转页面
        btn_zc1_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread() {
                    @Override
                    public void run() {
                        queren = new Handler() {
                            @Override
                            public void handleMessage(Message msg) {
                                switch (msg.what) {
                                    case 0:
                                        Log.d(TAG, "正确");
                                        Intent intent = new Intent(Zhuce1Activity.this, Zhuce2Activity.class);
                                        intent.putExtra("email", edit_zc1_mail.getText().toString());
                                        startActivity(intent);
                                        break;
                                    case 1:
                                        Log.d(TAG, "错误");
                                        break;
                                }
                            }
                        };
                        Message message = new Message();
                        Log.d(TAG, "edit_zc1_yzm.getText().toString(): " + edit_zc1_yzm.getText().toString());
                        OkHttpClient okHttpClient= new OkHttpClient();
                        Request request = new Request.Builder()
                                .url("http://112.74.184.181:8000/checkcode/?token=SMvwlN1kjrtKzIfxCLHlejDedpVSTRvW&id="+ id +"&code=" + edit_zc1_yzm.getText().toString())
                                .build();
                        try {
                            Response response = okHttpClient.newCall(request).execute();
                            String responseData = response.body().string();
                            Log.d(TAG, "responseData: " + responseData);
                            if (response.isSuccessful()) {
                                message.what = 0;

                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                            message.what = 1;
                        }
                        queren.sendMessage(message);
                    }
                }.start();
            }
        });


        btn_zc1_send.setOnClickListener(new View.OnClickListener() {
            public static final String TAG ="Zhuce1Activity" ;

            @Override
            public void onClick(View view) {
                getYZM = new Handler() {
                    @Override
                    public void handleMessage(Message msg) {
                        switch (msg.what) {
                            case 0:
                                Log.d(TAG, "正确");
                                break;
                            case 1:
                                Log.d(TAG, "错误");
                                break;
                }
            }
                };
                sendRequestWithHttpURLConnection();
                Log.d(TAG,"asd");
            }
        });
    }

    private void sendRequestWithHttpURLConnection() {
        //开启线程来发起网络请求
        new Thread(new Runnable() {

            @Override
            public void run() {
                OkHttpClient okHttpClient = new OkHttpClient();
                Request request = new Request.Builder()
                        .url("http://112.74.184.181:8000/register?token=SMvwlN1kjrtKzIfxCLHlejDedpVSTRvW&email=" + edit_zc1_mail.getText().toString())
                        .build();
                Message message = new Message();
                try {
                    Response response = okHttpClient.newCall(request).execute();
                    String responseData = response.body().string();
                    Log.d(TAG, "responseData: " + responseData);
                    if (response.isSuccessful()) {
                        JSONObject jsonObject = new JSONObject(responseData);
                        id = jsonObject.getString("id");
                        Log.d(TAG, "id: " + id);
                        message.what = 0;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    message.what = 1;
                } catch (JSONException e) {
                    e.printStackTrace();
                    message.what = 1;
                }
                getYZM.sendMessage(message);
            }
        }).start();
    }


    @Override
    public void onClick(View view) {

    }
}
