package app.reactproject.com.utill;

import android.content.Context;

import app.reactproject.com.inteface.OnDialogClickListener;
import app.reactproject.com.pop.NormalDialog;


/**
 * @Description:TODO 弹窗管理类
 * @create by lion
 * @created at 2016/8/26
 */
public class PopWindowManager {


    private NormalDialog normalDialog;
    private Context mContext;



    /**
     * 两个按钮的弹窗
     *
     * @param context   上下文对象
     * @param content   弹窗内容
     * @param leftText  左按钮文本
     * @param rightText 右按钮文本
     * @param listener  点击监听
     */
    public void showTwoButtonDialog(Context context, String titleText, String content, String leftText, String rightText, OnDialogClickListener listener) {
        if (normalDialog != null && !normalDialog.isShowing()) {
            normalDialog.show();
        } else {
            new NormalDialog(context, titleText, content, leftText, rightText, listener);
        }
    }

    /**
     * 两个按钮的弹窗
     *
     * @param context       上下文对象
     * @param content       弹窗内容
     * @param oneButtonText 左按钮文本
     * @param listener      点击监听
     */
    public void showOneButtonDialog(Context context, String titleText, String content, String oneButtonText, OnDialogClickListener listener) {
        if (normalDialog != null && !normalDialog.isShowing()) {
            normalDialog.show();
        } else {
            new NormalDialog(context, titleText, content, oneButtonText, listener);
        }
    }



}
