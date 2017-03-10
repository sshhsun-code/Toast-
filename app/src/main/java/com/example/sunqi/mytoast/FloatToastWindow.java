package com.example.sunqi.mytoast;

import android.content.Context;
import android.graphics.PixelFormat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by sunqi on 2017/3/10.
 */

public class FloatToastWindow implements View.OnClickListener{

    protected FloatToastManager mfloatToastManager;
    protected Context mContext;
    protected View mView;

    protected boolean mIsShow = false;

    private void initView() {
        View rootView = LayoutInflater.from(mContext).inflate(R.layout.system_float_window_layout,null);
        mView = rootView;
        mView.findViewById(R.id.emui_window_close).setOnClickListener(this);
    }

    public FloatToastWindow(Context context) {
        mContext = context;
    }

    private void init(Context context) {
        mfloatToastManager = new FloatToastManager(mContext);
        initView();
    }

    public void remove() {
        if (mfloatToastManager != null) {
            try {
                if (null != mView) {
                    mfloatToastManager.hideView();
                    mView = null;
                }
                mIsShow = false;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void show() {
        init(mContext);

        if (null != mfloatToastManager && null != mView) {
            try {
                mfloatToastManager.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL , 0 , 0 );
                mfloatToastManager.setSize(WindowManager.LayoutParams.WRAP_CONTENT,WindowManager.LayoutParams.WRAP_CONTENT);
                mfloatToastManager.showViewOther(mView);
                mIsShow = true;
            } catch (Exception e) {

            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.emui_window_close:
                remove();
                break;
        }
    }

    public boolean isShow() {
        return mIsShow;
    }
}
