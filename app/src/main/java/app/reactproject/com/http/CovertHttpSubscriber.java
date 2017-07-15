package app.reactproject.com.http;

import android.content.Context;
import android.text.TextUtils;

import com.example.administrator.lmw.config.DespositAccountManager;
import com.example.administrator.lmw.utils.ALLog;
import com.example.administrator.lmw.utils.ActivityTools;
import com.example.administrator.lmw.utils.PopWindowManager;
import com.example.administrator.lmw.utils.Singlton;
import com.example.administrator.lmw.utils.ToastUtil;
import com.google.gson.JsonObject;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.ConnectException;
import java.net.SocketTimeoutException;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * 统一的订阅者事件处理
 *
 * @author snubee
 * @email snubee96@gmail.com
 * @time 2017/4/25 15:45
 **/
public abstract class CovertHttpSubscriber<T> extends Subscriber<JsonObject> {
    private Context context;
    private Object mTag;//订阅标记
    private long mCurrentTimestamp;//当前时间戳
    private Type type;
    private Class<T> clazz;

    /**
     * @param context 上下文对象
     * @param args    可变参数 agrs[0] 表示网络请求标记tag,如果不传既默认标记为cotext
     */
    public CovertHttpSubscriber(Context context, Object... args) {
        this.context = context;
        mTag = args != null && args.length > 0 ? args[0] : context;
    }

    /**
     * @param context 上下文对象
     * @param type    要解析的实体类的Type
     * @param args    可变参数 agrs[0] 表示网络请求标记tag,如果不传既默认标记为cotext
     */
    public CovertHttpSubscriber(Context context, Type type, Object... args) {
        this.context = context;
        this.type = type;
        mTag = args != null && args.length > 0 ? args[0] : context;
    }

    /**
     * @param context 上下文对象
     * @param clazz   要解析返回的实体的class
     * @param args    可变参数 agrs[0] 表示网络请求标记tag,如果不传既默认标记为cotext
     */
    public CovertHttpSubscriber(Context context, Class<T> clazz, Object... args) {
        this.context = context;
        this.clazz = clazz;
        mTag = args != null && args.length > 0 ? args[0] : context;
    }

    /**
     * 设置网络请求标记
     *
     * @param tag
     */
    public void setTag(Object tag) {
        mTag = tag;
    }


    /**
     * 获取网络请求tag标记,加上事件请求时间戳防止同样的tag被覆盖
     */
    public Object getTag() {
        return new StringBuffer().append(mTag).append(mCurrentTimestamp).toString();
    }


    /**
     * 订阅开始时调用
     * 显示ProgressDialog
     */
    @Override
    public void onStart() {
        //TODO 请求开始添加一个tag请求管理
        mCurrentTimestamp = System.currentTimeMillis();
//        ALLog.i("snubee1 + 添加一个请求tag" + getTag().toString() + " 当前订阅者 " + this.toString() + mCurrentTimestamp);
        RxActionManagerImp.create().addSubscriber(getTag(), this);
    }

    /**
     * 完成，隐藏ProgressDialog
     */
    @Override
    public void onCompleted() {
        //TODO 请求完成需要移除标记
//        ALLog.i("snubee1 + 移除一个请求标记" + getTag().toString() + " 当前订阅者 " + this.toString() + mCurrentTimestamp);
        RxActionManagerImp.create().removeSubscriber(getTag());
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
            //  ToastUtil.showToast(context, "服务器繁忙，请稍后重试。");
            ALLog.e("error:" + e.getMessage());
        }
        ALLog.e("snubee" + "错误了----" + e.getMessage());
    }

    /**
     * 将onSuccess方法中的返回结果交给Activity或Fragment自己处理
     */
    public abstract void onSuccess(T result);

    private JsonConvert<T> convert = null;

    @Override
    public void onNext(final JsonObject result) {
        if (result != null) {
            String code = result.get("code").getAsString();

            ALLog.i("snubee" + mCurrentTimestamp + "  " + result.toString());

            if (type == null) {
                if (clazz == null) {
                    Type genType = CovertHttpSubscriber.super.getClass().getGenericSuperclass();
                    type = ((ParameterizedType) genType).getActualTypeArguments()[0];
                    convert = new JsonConvert<>(type);
                } else {
                    convert = new JsonConvert<>(clazz);
                }
            } else {
                convert = new JsonConvert<>(type);
            }

            try {
                onSuccess(convert.convertResponse(result));
            } catch (Throwable throwable) {
                throwable.printStackTrace();
                onSuccess(null);
            }

            // TODO 统一处理返回码的一些特殊情况
            if (TextUtils.equals(ResponseStatue.STATE_STOP_SERVICE, code)) {//停服
                Singlton.getInstance(PopWindowManager.class).showStopDialogShow(context, code, result.get("msg").getAsString());
            } else if (TextUtils.equals(ResponseStatue.STATE_LOGOUT, code)) {//重新登录
                //清除本地存管相关的缓存，防止登录的是另外一个账号引起判断状态没修改
                Singlton.getInstance(DespositAccountManager.class).onDestroy();
                ActivityTools.startToLogin(context);
            } else if (!TextUtils.equals(ResponseStatue.STATE_SUCCESS, code)) {
                ToastUtil.showToast(context, result.get("msg").getAsString());
            }


        }
    }

    /**
     * 使用rxjava多线程切换 ，把gson解析bean切换到io线程多线程解析bean，然后再切换主线程回调，这样减缓主线程的工作压力，加快显示
     *
     * @param result
     */
    private void rxCall(JsonObject result) {

        Observable.just(result)
                // flatMap方法是用于数据格式转换的方法，参数一表示原数据， 参数二表示转换的数据，
                .flatMap(new Func1<JsonObject, Observable<T>>() {
                    @Override
                    public Observable<T> call(JsonObject jsonObject) {
//                        ALLog.i("rx  Func1  call  " + Thread.currentThread().getName());

                        if (type == null) {
                            if (clazz == null) {
                                Type genType = CovertHttpSubscriber.super.getClass().getGenericSuperclass();
                                type = ((ParameterizedType) genType).getActualTypeArguments()[0];
                                convert = new JsonConvert<>(type);
                            } else {
                                convert = new JsonConvert<>(clazz);
                            }
                        } else {
                            convert = new JsonConvert<>(type);
                        }

                        try {
                            return Observable.just(convert.convertResponse(jsonObject));
                        } catch (Throwable throwable) {
                            throwable.printStackTrace();
                            return Observable.just(null);
                        }
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<T>() {
                    @Override
                    public void call(T t) {
//                        ALLog.i("rx Action1  " + Thread.currentThread().getName());
                        onSuccess(t);
                    }

                });

    }

}