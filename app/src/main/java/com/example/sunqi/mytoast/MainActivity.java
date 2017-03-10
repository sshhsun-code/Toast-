package com.example.sunqi.mytoast;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {
    private SystemToastWindow systemToastWindow;
    private FloatToastWindow floatToastWindow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnShowToast();
        systemToastWindow = new SystemToastWindow(this);
        systemToastWindow.show();
        floatToastWindow = new FloatToastWindow(this);
        floatToastWindow.show();
    }

    public void btnShowToast() {
        // 一直显示... 事实上设定的时间是int的最大值 , MyToast.LENGTH_MAX = Integer.MAX_VALUE = 2的31次方-1
//        MyToast.makeText(this, "一直显示...", MyToast.LENGTH_MAX).show();
//         显示5秒
         MyToast.makeText(this, "一直显示...(10秒钟)", 10*1000).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (systemToastWindow != null && systemToastWindow.isShow()){
            systemToastWindow.remove();
        }
        if (floatToastWindow != null && floatToastWindow.isShow()){
            floatToastWindow.remove();
        }
    }

    private void showToastWindow() {

    }
}
