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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import adapter.DL_panduan;
import adapter.JieXi;

public class DengluActivity extends AppCompatActivity {
    public static final int SHOW_RESPONSE = 0;
    private Button btn_log,btn_reg,btn_yanzheng;
    private EditText ed_email,ed_password;
    private static final String TAG = "DengluActivity";
    private DL_panduan panduan;
    private JieXi JieXi;
    private JSONObject jsonObject;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_denglu);

        //绑定id-s
        btn_log = findViewById(R.id.btn_denglu_login);  //登录按钮
        btn_reg = findViewById(R.id.btn_denglu_register);  //注册按钮
        btn_yanzheng = findViewById(R.id.DL_BT_yanzheng);
        ed_email=(EditText)findViewById(R.id.username);

        String yanzhen=ed_email.getText().toString();

        panduan = new DL_panduan();
        

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
            public void onClick(View v) {
                Intent intent = new Intent(DengluActivity.this, Study1Activity.class);
                startActivity(intent);
            }
        });

        //点击验证码的事件
        btn_yanzheng.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                sendRequestWithHttpURLConnection();
                parseEasyJson(panduan.toString());
                Log.d(TAG,"asd");

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
                String yanzhen = ed_email.getText().toString();



                try {
                    URL url1 = new URL("http://112.74.184.181:8000/register/?token=SMvwlN1kjrtKzIfxCLHlejDedpVSTRvW&" + "email= " + yanzhen);
                    connection = (HttpURLConnection) url1.openConnection();
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
                    Log.d(TAG, response.toString());
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


    private void parseEasyJson(String json){
        ArrayList<JieXi> message = new ArrayList <JieXi>();
        try{
            JSONArray jsonArray = new JSONArray(json);
            for(int i = 0;i < jsonArray.length();i++){
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                JieXi person = new JieXi();
                person.setcode(i+"");
                person.setstatus(jsonObject.getString("status"));
                person.setID(jsonObject.getString("ID"));
                message.add(person);

                Log.d(TAG, String.valueOf(message));
                
            }
        }catch (Exception e){e.printStackTrace();}
    }


    public static class OrgJSONTest {
        public static String json = "{\"code\":\"status\",\"status\":\"18\",\"id\":true}";
        public static void main(String[] args) throws JSONException {
            JSONObject obj = new JSONObject(json);//最外层的JSONObject对象
            JSONObject user = obj.getJSONObject("user");//通过user字段获取其所包含的JSONObject对象
            String name = user.getString("name");//通过name字段获取其所包含的字符串

            System.out.println(name);


        }
    }
    
    
}
