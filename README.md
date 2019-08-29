# ZLProgressHUD
基于svprogresshud做了优化的android loading框，显示加载成功失败的hud


### 导入

```
implementation 'com.github.czl0325:ZLProgressHUD:0.0.2'
```

```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
        
### 屏幕截图

![](https://github.com/czl0325/ZLProgressHUD/blob/master/screenshot/demo1.gif?raw=true)![](https://github.com/czl0325/ZLProgressHUD/blob/master/screenshot/demo2.gif?raw=true)![](https://github.com/czl0325/ZLProgressHUD/blob/master/screenshot/demo3.gif?raw=true)![](https://github.com/czl0325/ZLProgressHUD/blob/master/screenshot/demo4.gif?raw=true)![](https://github.com/czl0325/ZLProgressHUD/blob/master/screenshot/demo5.gif?raw=true)


### 用法

```JAVA

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
                                hud.showWithProgress("文件下载中...", progress[0]);
                                if (progress[0] >= 100) {
                                    timer.cancel();
                                    hud.showSuccessWithStatus("文件下载完成");
                                }
                            }
                        });
                    }
                };
                timer.schedule(timerTask, 100, 100);
                break;
        }
    }
    
```
