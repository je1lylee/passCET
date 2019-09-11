package top.linxixiangxin.passcet;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
    public  String id, code;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
                Log.d(TAG, "message.what: " + msg.what);
            switch (msg.what) {
                case 107:
                    Intent intent = new Intent(DengluActivity.this, Study1Activity.class);
                    startActivity(intent);
                    Toast.makeText(DengluActivity.this, "登录成功！", Toast.LENGTH_SHORT).show();
                    finish();
                    break;
                case 105:
                    Toast.makeText(DengluActivity.this, "验证码获取成功", Toast.LENGTH_SHORT).show();
                    break;
                case 209:
                    Toast.makeText(DengluActivity.this, "验证码不存在", Toast.LENGTH_SHORT).show();
                    break;
                case 205:
                    Toast.makeText(DengluActivity.this, "用户不存在", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    Toast.makeText(DengluActivity.this, "获取失败，请重试！", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };;



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
                getUsreHead("SMvwlN1kjrtKzIfxCLHlejDedpVSTRvW", id, "http://112.74.184.181:8000/getusericon/");
                sendRequestWithHttpURLConnection("1");


            }
        });


        //点击验证码的事件
        btn_yanzheng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendRequestWithHttpURLConnection("0");
            }

        });

    }
    public static void getUsreHead (final String token, final String id, final String url){
        Log.d(TAG, "getUsreHead: ");
        new Thread() {
            @Override
            public void run() {
                try {
                    OkHttpClient okHttpClient =new OkHttpClient();
                    RequestBody requestBody = new FormBody.Builder()
                            .add("token", token)
                            .add("id", id)
                            .build();
                    Request request = new Request.Builder()
                            .post(requestBody)
                            .url(url)
                            .build();
                    Response response = okHttpClient.newCall(request).execute();
                    String data = response.body().string();
                    Log.d(TAG, "data: " + data);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    private void sendRequestWithHttpURLConnection(final String type) {
        //开启线程来发起网络请求
        new Thread() {
            @Override
            public void run() {
                Message message = new Message();
                email = ed_email.getText().toString();
                OkHttpClient okHttpClient = new OkHttpClient();
                RequestBody requestBody = null;
                RequestBody requestBody1 = null;
                Request request = null;
                Request request1 = null;
                try {
                    if (type.equals("0")) {
                        requestBody = new FormBody.Builder()
                                .add("type", type)
                                .add("token", "SMvwlN1kjrtKzIfxCLHlejDedpVSTRvW")
                                .add("email", email)
                                .build();
                    } else if (type.equals("1")) {
                        requestBody = new FormBody.Builder()
                                .add("type", type)
                                .add("token", "SMvwlN1kjrtKzIfxCLHlejDedpVSTRvW")
                                .add("id", id)
                                .add("code", ed_password.getText().toString())
                                .build();
                        requestBody1 = new FormBody.Builder()
                                .add("token", "SMvwlN1kjrtKzIfxCLHlejDedpVSTRvW")
                                .add("email", ed_email.getText().toString())
                                .build();
                        request1 = new Request.Builder()
                                .url("http://112.74.184.181:8000/getuserinfo/")
                                .post(requestBody1)
                                .build();
                        Response response1 = okHttpClient.newCall(request1).execute();
                        String data = response1.body().string();
                        Log.d(TAG, "data: " + data);
                    }
                    request = new Request.Builder()
                            .url("http://112.74.184.181:8000/login/")
                            .post(requestBody)
                            .build();
                    Response response = okHttpClient.newCall(request).execute();
                    String responseData = response.body().string();
                    Log.d(TAG, "responseData: " + responseData);
                    if (response.isSuccessful()) {
                        if (type.equals("0")) {
                            JSONObject jsonObject = new JSONObject(responseData);
                            id = jsonObject.getString("id");
                            message.what = 105;
                        } else if (type.equals("1")) {
                            message.what = 107;
                        }

                    }
                } catch(NullPointerException n){
                    message.what = 209;
                    Log.d(TAG, "NullPointerException: ");
                    n.printStackTrace();
                } catch(IllegalArgumentException i) {
                    Log.d(TAG, "run: ");
                    message.what = 209;
                    i.printStackTrace();
                } catch (IOException e) {
                    Log.d(TAG, "run: ");
                    message.what = 0;
                    e.printStackTrace();
                } catch (JSONException e) {
                    Log.d(TAG, "JSONException: ");
                    message.what = 205;
                    e.printStackTrace();
                }
                handler.sendMessage(message);
            }
        }.start();

    }
}

class UserInternet {

    private static final String TAG = "UserInternet";
    private UserInformation userInformation;

    public UserInternet (){
        userInformation = new UserInformation();
    }


}
