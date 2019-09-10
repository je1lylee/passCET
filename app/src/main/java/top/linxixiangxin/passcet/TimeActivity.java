package top.linxixiangxin.passcet;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.sql.Time;

public class TimeActivity extends AppCompatActivity {
    private Button addTimeBtn;
    private Button decreaseTimeBtn;
    private Button startBtn;
    private Button closeMusicBtn;
    private TextView timeText;

    private int brewTime = 3;
    private CountDownTimer countDownTimer;
    private boolean isBrewing = false;
    private MediaPlayer alarmMusic;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time);

        addTimeBtn = (Button) findViewById(R.id.main_btn_up);
        decreaseTimeBtn = (Button) findViewById(R.id.main_btn_down);
        startBtn = (Button) findViewById(R.id.main_start);
        closeMusicBtn = (Button) findViewById(R.id.main_btn_close_music);
        timeText = (TextView) findViewById(R.id.main_tv);

        setBrewTime(3);

        addTimeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBrewTime(brewTime + 1);

            }
        });

        decreaseTimeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBrewTime(brewTime - 1);
            }
        });

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isBrewing) {
                    stopBrew();
                } else {
                    startBrew();
                }
            }
        });

        closeMusicBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alarmMusic.stop();
                closeMusicBtn.setVisibility(8);
            }
        });


    }

    /**
     * 设置闹钟倒计时初始值
     *
     * @param minutes
     */
    public void setBrewTime(int minutes) {
        if (isBrewing)
            return;
        brewTime = minutes;

        if (brewTime < 1) {
            brewTime = 1;
        }
        timeText.setText(String.valueOf(brewTime) + "m");
    }

    /**
     * 开启闹钟
     */
    public void startBrew() {
        // 创建一个CountDownTimer对象记录闹钟时间
        countDownTimer = new CountDownTimer(brewTime * 60 * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeText.setText(String.valueOf(millisUntilFinished / 1000)
                        + "s");
            }

            @SuppressLint("WrongConstant")
            @Override
            public void onFinish() {
                isBrewing = false;
                timeText.setText(brewTime + "m");
                startBtn.setText("Start");
                // 加载指定音乐，并为之创建MediaPlayer对象
                alarmMusic = MediaPlayer.create(TimeActivity.this, R.raw.rise);
                // 设置为循环播放
                alarmMusic.setLooping(true);
                // 播放音乐
                alarmMusic.start();
                closeMusicBtn.setVisibility(0);
            }
        };
        countDownTimer.start();
        startBtn.setText("Stop");
        isBrewing = true;
    }

    /**
     * 停止计时
     */
    public void stopBrew() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        isBrewing = false;
        startBtn.setText("Start");
    }

}