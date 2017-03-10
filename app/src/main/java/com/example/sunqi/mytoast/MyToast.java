package com.example.sunqi.mytoast;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.PixelFormat;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

/**
 * Created by sunqi on 2017/2/27.
 */

public class MyToast {

    private Context mContext;
    private WindowManager mManager;
    private int mDuration;
    private View mNextView;

    public static final int LENGTH_SHORT = 1500;
    public static final int LENGTH_LONG = 1500;
    public static final int LENGTH_MAX = Integer.MAX_VALUE;
    private boolean isShow = false;
    public MyToast(Context context) {
        mContext = context.getApplicationContext();
    }



    public static MyToast makeText(Context context, CharSequence text,
                                   int time) {
        MyToast result = new MyToast(context);
        result.mNextView = Toast.makeText(context, text, time).getView();
        result.mDuration = time;
        return result;
    }


    public static MyToast makeText(Context context, int resId, int time)
            throws Resources.NotFoundException {
        return makeText(context, context.getResources().getText(resId),
                time);
    }

    public void show() {
        if (mNextView != null) {
            mManager = (WindowManager) mContext
                    .getSystemService(Context.WINDOW_SERVICE);
            WindowManager.LayoutParams params = new WindowManager.LayoutParams();
            params.gravity = Gravity.FILL;
            params.height = WindowManager.LayoutParams.WRAP_CONTENT;
            params.width = WindowManager.LayoutParams.WRAP_CONTENT;
            params.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                    | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
                    | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;
            params.format = PixelFormat.TRANSLUCENT;
            params.y = dip2px(mContext, 64);
            params.type = WindowManager.LayoutParams.TYPE_TOAST;
            mManager.addView(mNextView, params);
            isShow = true;
            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    if (mNextView != null) {
                        mManager.removeView(mNextView);
                        mNextView = null;
                        mManager = null;
                    }
                }
            }, mDuration);
        }
    }

    private void remove(){
        if (mManager != null && isShow){
            mManager.removeView(mNextView);
        }
    }

    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }


}
