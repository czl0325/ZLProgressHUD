package com.github.zlprogresshud_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import com.github.zlprogresshud.ZLProgressHUD;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ZLProgressHUD hud;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hud = new ZLProgressHUD(this);

        Button btn1 = findViewById(R.id.btn1);
        Button btn2 = findViewById(R.id.btn2);
        Button btn3 = findViewById(R.id.btn3);
        Button btn4 = findViewById(R.id.btn4);
        Button btn5 = findViewById(R.id.btn5);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                hud.showWithStatus("数据加载中...");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        hud.dismiss();
                    }
                },2000);
                break;
            case R.id.btn2:
                hud.showWithStatus("加载第一段数据...");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        hud.showWithStatus("加载第二段数据...");
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                hud.showWithStatus("加载第三段数据...");
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        hud.showInfoWithStatus("数据加载完成");
                                    }
                                },2000);
                            }
                        },2000);
                    }
                },2000);
                break;
            case R.id.btn3:
                hud.showWithStatus("网络请求中...");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        hud.showSuccessWithStatus("网络请求成功!");
                    }
                },2000);
                break;
            case R.id.btn4:
                hud.showWithStatus("网络请求中...");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        hud.showErrorWithStatus("网络请求失败!");
                    }
                },2000);
                break;
            case R.id.btn5:
                final int[] progress = {0};
                final Timer timer = new Timer();
                TimerTask timerTask = new TimerTask() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                progress[0]++;
                                hud.showWithProgress("视频压缩处理中...", progress[0]);
                                if (progress[0] >= 100) {
                                    timer.cancel();
                                    hud.showWithStatus("视频上传中...");
                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            hud.showSuccessWithStatus("视频上传成功!");
                                        }
                                    },2000);
                                }
                            }
                        });
                    }
                };
                timer.schedule(timerTask, 100, 100);
                break;
        }
    }
}
