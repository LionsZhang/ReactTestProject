package app.reactproject.com.http;

import java.util.Map;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by lionzhang on 2016/8/16.
 * <p>
 * 主导网络请求与事件调度工具类
 */
public class RxManager {


    public static <T> void toSubscrib(Observable<T> observable, HttpSubscriber<T> httpSubscriber) {
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(httpSubscriber);
    }


    /**
     * 订阅一个post请求
     * lmw-product/lmw
     *
     * @param params
     * @param httpSubscriber
     * @param <T>
     */
    public static <T> void toPostSubscrib(Map<String, String> params, CovertHttpSubscriber<T> httpSubscriber) {
        //订阅一个网络事件
        LmwHttp.getInstance().createHttpInterface().post(params).subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(httpSubscriber);
    }



    /**
     * 订阅一个get请求
     * lmw-product/lmw
     *
     * @param params
     * @param httpSubscriber
     * @param <T>
     */
    public static <T> void toGetSubscrib(Map<String, String> params, CovertHttpSubscriber<T> httpSubscriber) {
        //订阅一个网络事件
        LmwHttp.getInstance().createHttpInterface().get(params).subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(httpSubscriber);
    }

    /**
     * 订阅一个活动模块get请求
     * lmw-activity/activity
     *
     * @param params
     * @param httpSubscriber
     * @param <T>
     */
    public static <T> void toGetActivitySubscrib(Map<String, String> params, CovertHttpSubscriber<T> httpSubscriber) {
        //订阅一个网络事件
        LmwHttp.getInstance().createHttpInterface().getActivity(params).subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(httpSubscriber);
    }


}
