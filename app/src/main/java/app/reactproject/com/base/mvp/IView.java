package app.reactproject.com.base.mvp;

/**
 * @author lionszhang.
 * @explain
 * @time 2017/7/15 15:13.
 * Created by lionszhang on 2017/7/15.
 * 一个View 接口，提供给Fragment ,Activity 的开放函数，
 * 两个作用，一定义需要显示的界面UI在View(Fragment /Activity)展示，以及封装在View中调用的公共方法
 * 二，定义需要在主导者P中回调的函数，控制UI效果
 */


public interface IView<P> {
    //  void bindUI(View rootView);//Activity 或者Fragment 绑定的UI界面

    //  void bindEvent();

    void initData();//初始化数据界面切换时接收参数

    void initView();//初始化UI界面
    // int getOptionsMenuId();//Activity 或者Fragment 绑定的菜单界面资源id

    int getLayoutId();//Activity 或者Fragment 绑定的UI界面资源id

    void setView();

    P newP();
}
