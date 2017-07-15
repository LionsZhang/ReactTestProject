package app.reactproject.com.http;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import rx.Subscriber;

/**
 * Rx请求管理者接口实现类
 *
 * @author snubee
 * @email snubee96@gmail.com
 * @time 2017/4/26 10:47
 **/
public class RxActionManagerImp implements RxActionManager<Object> {
    private HashMap<Object, Subscriber> mTagMaps;

    private RxActionManagerImp() {
        mTagMaps = new HashMap<>();
    }

    /**
     * 单例获取管理者
     *
     * @return
     */
    public static RxActionManagerImp create() {
        return RxManagerHolder.holder;
    }

    private static class RxManagerHolder {
        private static RxActionManagerImp holder = new RxActionManagerImp();
    }

    /**
     * 添加一个请求标记到集合
     *
     * @param tag
     * @param subscription
     */
    @Override
    public void addSubscriber(Object tag, Subscriber subscription) {
        mTagMaps.put(tag, subscription);
//        ALLog.i("snubee + 添加一个请求" + subscription.toString() + "  + 发出去的请求个数" + mTagMaps.size());
    }

    /**
     * 根据tag取消一个请求
     *
     * @param tag
     */
    @Override
    public void cancelSubscriber(Object tag) {
        if (mTagMaps.isEmpty()) {
            return;
        }

        Iterator it = mTagMaps.keySet().iterator();
        while (it.hasNext()) {
            Object obj = (Object) it.next();
            if (obj.toString().contains(tag.toString())) {
                if (mTagMaps.get(obj) != null && mTagMaps.get(obj).isUnsubscribed()) {
                    mTagMaps.get(obj).unsubscribe();
//                    ALLog.i("snubee1 + cancelSubscriber取消并移除一个请求" + mTagMaps.get(obj).toString());
                }
                it.remove();
//                ALLog.i("snubee + 发出去的请求个数" + mTagMaps.size());
            }


        }
    }


    /**
     * 根据tag移除一个请求
     *
     * @param tag
     */
    public void removeSubscriber(Object tag) {
        if (mTagMaps.isEmpty()) {
            return;
        }
        if (mTagMaps.get(tag) == null) {
            return;
        }
        if (!mTagMaps.get(tag).isUnsubscribed()) {
            mTagMaps.get(tag).unsubscribe();
//            ALLog.i("snubee + removeSubscriber取消并移除一个请求" + mTagMaps.get(tag).toString());
        }
//        ALLog.i("snubee + 移除一个请求" + mTagMaps.get(tag).toString());
        mTagMaps.remove(tag);
//        ALLog.i("snubee + 发出去的请求个数" + mTagMaps.size());
    }


    /**
     * 取消所有的请求
     */
    @Override
    public void cancelAllSubscriber() {
        if (mTagMaps.isEmpty()) {
            return;
        }
        Set<Object> keys = mTagMaps.keySet();
        for (Object apiKey : keys) {
            if (mTagMaps.get(apiKey) != null && mTagMaps.get(apiKey).isUnsubscribed())
                mTagMaps.get(apiKey).unsubscribe();
        }
        mTagMaps.clear();
    }

}
