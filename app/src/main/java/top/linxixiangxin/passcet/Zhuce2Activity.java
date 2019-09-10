package top.linxixiangxin.passcet;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Zhuce2Activity extends AppCompatActivity {
    private static final String TAG = "Zhuce2Activity";
    private Button btn_zc2_x, btn_zc2_back, btn_zc_finish;
    private EditText name;


    private static final int TAKE_PHOTO = 11;// 拍照
    private static final int CROP_PHOTO = 12;// 裁剪图片
    private static final int LOCAL_CROP = 13;// 本地图库


    private ImageView IM_zc2_tx;
    private Uri imageUri;// 拍照时的图片uri

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhuce2);
        setViews();// 初始化控件
        setListeners();// 设置监听
        final String TAG = "Zhuce2Activity";
        final Intent zhuCe1 = getIntent();


        //绑定id
        btn_zc2_back = findViewById(R.id.btn_zc2_back);  //返回上一层
        btn_zc_finish = findViewById(R.id.btn_zc_finish);//进入主页面
        btn_zc2_x = findViewById(R.id.btn_zc2_x);//返回登录页
        name = findViewById(R.id.edit_zc2_nc);

        btn_zc2_x.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(Zhuce2Activity.this, DengluActivity.class);
                startActivity(intent2);
            }
        });

        btn_zc2_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Zhuce2Activity.this, Zhuce1Activity.class);
                startActivity(intent);
            }
        });

        btn_zc_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(Zhuce2Activity.this,Study1Activity.class);
//                startActivity(intent);
                Handler handler = new Handler() {

                };
                new Thread() {
                    @Override
                    public void run() {

                        File file=new File(Environment.getExternalStorageDirectory(), "take_photo_image.jpg");
                        MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");
                        RequestBody filebody = MultipartBody.create(MEDIA_TYPE_PNG, file);
                        MultipartBody.Builder multiBuilder=new MultipartBody.Builder();
                        //这里是 封装上传图片参数
                        multiBuilder.addFormDataPart("image", file.getName(), filebody);
                        //参数以添加header方式将参数封装，否则上传参数为空
                        // 设置请求体
                        multiBuilder.setType(MultipartBody.FORM);
                        //这里是 封装上传图片参数
                        multiBuilder.addFormDataPart("image", file.getName(), filebody);
                        multiBuilder.addFormDataPart("name",name.getText().toString());
                        multiBuilder.addFormDataPart("token","SMvwlN1kjrtKzIfxCLHlejDedpVSTRvW");
                        multiBuilder.addFormDataPart("email",zhuCe1.getStringExtra("email"));
                        multiBuilder.addFormDataPart("level","4");

                        HashMap<String, String> params = new HashMap<>();
                        params.put("name",name.getText().toString());
                        params.put("token","SMvwlN1kjrtKzIfxCLHlejDedpVSTRvW");
                        params.put("email",zhuCe1.getStringExtra("email"));
                        params.put("level","4");
                        params.put("image", file.getName());
                        Log.d(TAG, "run: " + params);
                        //参数以添加header方式将参数封装，否则上传参数为空
                        if (params != null && !params.isEmpty()) {
                            for (String key : params.keySet()) {
                                multiBuilder.addPart(
                                        Headers.of("Content-Disposition", "form-" +
                                                "" +
                                                "                                                                                                                                                      data; name=\"" + key + "\""),
                                        RequestBody.create(null, params.get(key)));
                            }
                        }
                        RequestBody multiBody=multiBuilder.build();
                        OkHttpClient okHttpClient = new OkHttpClient();
                        Request request=new   Request.Builder().url("http://112.74.184.181:8000/addaccount/").post(multiBody).build();
                        try {
                            Response response = okHttpClient.newCall(request).execute();
                            String responseData = response.body().string();
                            Log.d(TAG, "responseData132: " + responseData);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                }.start();
            }
        });


    }

    private void setListeners() {
        // 展示图片按钮点击事件
       IM_zc2_tx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePhotoOrSelectPicture();// 拍照或者调用图库
            }
       });

    }

    /**
     *
     */
    private void takePhotoOrSelectPicture() {
        CharSequence[] items = {"拍照","图库"};
        // 裁剪items选项
        // 弹出对话框提示用户拍照或者是通过本地图库选择图片
        new AlertDialog.Builder(Zhuce2Activity.this)
                .setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        switch (which){
                            // 选择了拍照
                            case 0:
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                    StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
                                    StrictMode.setVmPolicy( builder.build() );
                                }
                                // 创建文件保存拍照的图片
                                File takePhotoImage = new File(Environment.getExternalStorageDirectory(), "take_photo_image.jpg");
                                try {
                                    // 文件存在，删除文件
                                    if(takePhotoImage.exists()){
                                        takePhotoImage.delete();
                                    }
                                    // 根据路径名自动的创建一个新的空文件
                                    takePhotoImage.createNewFile();
                                }catch (Exception e){
                                    e.printStackTrace();
                                }
                                // 获取图片文件的uri对象
                                imageUri = Uri.fromFile(takePhotoImage);
                                // 创建Intent，用于启动手机的照相机拍照
                                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                // 指定输出到文件uri中
                                intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
                                intent.putExtra("return-data", true);
                                // 启动intent开始拍照
                                startActivityForResult(intent, TAKE_PHOTO);
                                break;
                            // 调用系统图库
                            case 1:

                                // 创建Intent，用于打开手机本地图库选择图片
                                Intent intent1 = new Intent(Intent.ACTION_PICK,
                                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                // 启动intent打开本地图库
                                startActivityForResult(intent1,LOCAL_CROP);
                                break;
                        }
                    }
                }).show();
    }


    /**
     * 调用startActivityForResult方法启动一个intent后，
     * 可以在该方法中拿到返回的数据
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (requestCode){
            case TAKE_PHOTO:// 拍照
                if(resultCode == RESULT_OK){
                    // 创建intent用于裁剪图片
                    Intent intent = new Intent("com.android.camera.action.CROP");
                    // 设置数据为文件uri，类型为图片格式
                    intent.setDataAndType(imageUri,"image/*");
                    Log.d(TAG,"url:" + imageUri);
                    // 允许裁剪
                    intent.putExtra("scale",true);
                    // 指定输出到文件uri中
                    intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
                    // 启动intent，开始裁剪
                    startActivityForResult(intent, CROP_PHOTO);
                }
                break;
            case LOCAL_CROP:// 系统图库
                if(resultCode == RESULT_OK){
                    // 创建intent用于裁剪图片
                    Intent intent1 = new Intent("com.android.camera.action.CROP");
                    // 获取图库所选图片的uri
                    Uri uri = data.getData();
                    intent1.setDataAndType(uri,"image/*");
                    //  设置裁剪图片的宽高
                    intent1.putExtra("outputX", 300);
                    intent1.putExtra("outputY", 300);
                    // 裁剪后返回数据
                    intent1.putExtra("return-data", true);
                    // 启动intent，开始裁剪
                    startActivityForResult(intent1, CROP_PHOTO);
                }

                break;
            case CROP_PHOTO:// 裁剪后展示图片
                if(resultCode == RESULT_OK){
                    try{
                        // 展示拍照后裁剪的图片
                        if(imageUri != null){
                            // 创建BitmapFactory.Options对象
                            BitmapFactory.Options option = new BitmapFactory.Options();
                            // 属性设置，用于压缩bitmap对象
                            option.inSampleSize = 2;
                            option.inPreferredConfig = Bitmap.Config.RGB_565;
                            // 根据文件流解析生成Bitmap对象
                            Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri), null, option);
                            // 展示图片
                            IM_zc2_tx.setImageBitmap(bitmap);
                        }

                        // 展示图库中选择裁剪后的图片
                        if(data != null){
                            // 根据返回的data，获取Bitmap对象
                            Bitmap bitmap = data.getExtras().getParcelable("data");
                            // 展示图片
                            IM_zc2_tx.setImageBitmap(bitmap);
                        }

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
                break;
        }
    }

    /**
     * 控件初始化
     */
    private void setViews() {
        IM_zc2_tx = (ImageView) findViewById(R.id.IM_zc2_tx);
    }
}


