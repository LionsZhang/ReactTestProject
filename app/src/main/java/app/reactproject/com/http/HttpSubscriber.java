package app.reactproject.com.http;

import android.content.Context;


import java.net.ConnectException;
import java.net.SocketTimeoutException;

import app.reactproject.com.utill.ALLog;
import app.reactproject.com.utill.ToastUtil;
import rx.Subscriber;

/**
 * 一个订阅者对象处理消费事件
 * Created by lionzhang on 16/8/16.
 */
public abstract class HttpSubscriber<T> extends Subscriber<T> {
    private Context context;

    public HttpSubscriber(Context context) {
        this.context = context;
    }


    /**
     * 订阅开始时调用
     * 显示ProgressDialog
     */
    @Override
    public void onStart() {

    }

    /**
     * 完成，隐藏ProgressDialog
     */
    @Override
    public void onCompleted() {

    }

    /**
     * 对错误进行统一处理
     *
     * @param e
     */
    @Override
    public void onError(Throwable e) {
        if (e instanceof SocketTimeoutException) {
            ToastUtil.showToast(context, "通往土豪的路上有点拥挤，请耐心等待...");
        } else if (e instanceof ConnectException) {
            ToastUtil.showToast(context, "通往土豪的路上有点拥挤，请耐心等待...");
        } else if (e.getMessage().equals("HTTP 404 Not Found")) {
            ToastUtil.showToast(context, "通往土豪的路上有点拥挤，请耐心等待...");
        } else if (e.getMessage().equals("Unable to resolve host \"m.limin.com\": No address associated with hostname")) {
            ToastUtil.showToast(context, "通往土豪的路上有点拥挤，请耐心等待...");
        } else {
            ALLog.e("error:" + e.getMessage());
        }

    }

    /**
     * 将onNext方法中的返回结果交给Activity或Fragment自己处理
     *
     * @param t 创建Subscriber时的泛型类型
     */
    @Override
    public abstract void onNext(T t);

    /**
     * 取消对observable的订阅，同时也取消了http请求
     */
    public void onCancelHttp() {
        if (!this.isUnsubscribed()) {
            this.unsubscribe();
        }
    }
}