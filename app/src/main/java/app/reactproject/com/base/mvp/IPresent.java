package app.reactproject.com.base.mvp;

/**
 * @author lionszhang.
 * @explain
 * @time 2017/7/15 15:12.
 * * * 一个Present 接口，提供给子present 的开放函数，
 * 两个作用，一定义Present的公共函数
 * 二，定义部分在view中回调方法
 */


public interface IPresent<V> {
    // void attachV(V view);//设置View

    // void detachV();//分离View

    V getV();//获取View

    void loadData();
}
