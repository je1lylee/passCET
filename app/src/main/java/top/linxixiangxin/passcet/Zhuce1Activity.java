package top.linxixiangxin.passcet;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static top.linxixiangxin.passcet.DengluActivity.SHOW_RESPONSE;

public class Zhuce1Activity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_zc1_sure;
    private EditText edit_zc1_mail, edit_zc1_yzm;
    private CountDownTimerButton btn_zc1_send;
    private int data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhuce1);

        //绑定id-S
        btn_zc1_send = findViewById(R.id.btn_zc1_send);  //发送验证码
        edit_zc1_mail = findViewById(R.id.edit_zc1_mail); //输入邮箱地址
        edit_zc1_yzm = findViewById(R.id.edit_zc1_yzm);  //输入的验证码
        btn_zc1_sure=findViewById(R.id.btn_zc1_sure);

        String zc1_username = edit_zc1_mail.getText().toString();
        String zc2_password = edit_zc1_yzm.getText().toString();

        //确认注册后的点击事件
        btn_zc1_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Zhuce1Activity.this,Zhuce2Activity.class);
                startActivity(intent);
            }
        });

        btn_zc1_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendRequestWithHttpURLConnection();
            }
        });
    }


    private Handler handler = new Handler() {
        //handleMessage方法运行在主线程，处理子线程发送回来的数据。
        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            super.handleMessage(msg);
            switch (msg.what) {
                case SHOW_RESPONSE:
                    String response = (String) msg.obj;
                    //在这里进行UI操作，将结果显示到界面上
                    break;
                default:
                    break;
            }
        }
    };
    private void sendRequestWithHttpURLConnection() {
        //开启线程来发起网络请求
        new Thread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                HttpURLConnection connection = null;
                String yanzhen=edit_zc1_mail.getText().toString();
                try {
                    URL url = new URL("http://112.74.184.181:8000/register/?token=SMvwlN1kjrtKzIfxCLHlejDedpVSTRvW&" + "email= " + yanzhen);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(5000);
                    connection.setReadTimeout(5000);
                    InputStream in = connection.getInputStream();
                    //下面对获取到的输入流进行读取
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    //实例化Message对象
                    Message message = new Message();
                    message.what = SHOW_RESPONSE;
                    //将服务器返回的结果存放到Message中
                    message.obj = response.toString();
                    //sendMessage方法运行在子线程
                    handler.sendMessage(message);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (connection != null) {
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }


    @Override
    public void onClick(View view) {

    }
}
