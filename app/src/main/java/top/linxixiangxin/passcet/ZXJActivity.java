package top.linxixiangxin.passcet;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;

public class ZXJActivity extends AppCompatActivity {
    private static final int TAKE_PHOTO = 11;// 拍照
    private static final int CROP_PHOTO = 12;// 裁剪图片
    private static final int LOCAL_CROP = 13;// 本地图库


    private ImageView im_zxj;
    private Button btn_stu1_zxj;
    private Uri imageUri;// 拍照时的图片uri

    private Button btn_zxj_xx,btn_zxj_sc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zxj);
        setViews();// 初始化控件
        setListeners();// 设置监听

        btn_zxj_xx = findViewById(R.id.btn_zxj_xx);
        btn_zxj_xx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ZXJActivity.this,Study1Activity.class);
                startActivity(intent);
            }
        });

        btn_zxj_sc = findViewById(R.id.btn_zxj_sc);
        btn_zxj_sc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ZXJActivity.this,ShengciActivity.class);
                startActivity(intent);
            }
        });



    }



    private void setListeners() {
        // 展示图片按钮点击事件
        btn_stu1_zxj.setOnClickListener(new View.OnClickListener() {
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
        new AlertDialog.Builder(ZXJActivity.this)
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
                            im_zxj.setImageBitmap(bitmap);
                        }

                        // 展示图库中选择裁剪后的图片
                        if(data != null){
                            // 根据返回的data，获取Bitmap对象
                            Bitmap bitmap = data.getExtras().getParcelable("data");
                            // 展示图片
                            im_zxj.setImageBitmap(bitmap);
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
        im_zxj = (ImageView) findViewById(R.id.im_zxj);
        btn_stu1_zxj = findViewById(R.id.btn_stu1_zxj);
    }
}



