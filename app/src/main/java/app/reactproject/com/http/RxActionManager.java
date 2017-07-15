package app.reactproject.com.http;

import rx.Subscriber;

/**
 * Rx请求管理者接口
 *
 * @author snubee
 * @email snubee96@gmail.com
 * @time 2017/4/25 15:35
 **/
public interface RxActionManager<T> {
    void addSubscriber(T tag, Subscriber subscription);

    void cancelSubscriber(T tag);

    void cancelAllSubscriber();
}
