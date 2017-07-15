package app.reactproject.com.base.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;

import app.reactproject.com.R;
import app.reactproject.com.utill.ActivityManager;


/**
 * 通用的标题基类
 *
 * @author snubee
 * @email snubee96@gmail.com
 * @time 2017/2/17 11:35
 **/
public abstract class BaseTitleActivity extends BaseActivity {

    /**
     * 标题
     */
    protected TextView titleName;


    /**
     * 身驱的布局
     */
    protected View bodyView;

    /**
     * 标题栏的布局
     */
    protected RelativeLayout title_layout;

    /**
     * 左标题，右标题
     */
    protected TextView title_right, title_left;

    /**
     * Framelayout,加载布局上一层的布局,即id = android.R.id.content
     */
    protected FrameLayout frameLayout;
    /**
     * 系统资源
     */
    protected Resources resources;


    /**
     * id和资源的分割符
     */
    private static final String ID_SPERATOR_RES = "$";

    private void onCreate() {
        Log.i("BaseTitleActivity", getClass().getName());
        // 背景不设置任何元素
        getWindow().setBackgroundDrawable(null);
        // 没有标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 读取加载布局是的上一层布局控件,既是一个FrameLayout
        frameLayout = (FrameLayout) findViewById(android.R.id.content);
        resources = getResources();
    }

    @Override
    protected void initializeData() {

    }

    @Override
    protected int getContentLayout() {
        onCreate();
        setTitleContentView();
        return -1;
    }

    @Override
    protected void initializeView() {

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // TODO Auto-generated method stub
        // empty
    }

    /**
     * 可重写该方法调用 initview（）初始化统一格式标题和内容布局或者调用initAllView（）方法自定义标题和内容布局
     */
    protected abstract void setTitleContentView();

    /**
     * 自定义处理标题头的初始化
     *
     * @param titleViewId 标题栏布局
     * @param bodyViewId  身驱布局
     */
    protected void initAllView(@LayoutRes int titleViewId, @LayoutRes int bodyViewId) {
        initAllView(inflater.inflate(titleViewId, null), inflater.inflate(bodyViewId, null));
    }

    /**
     * 自定义处理标题头的初始化
     *
     * @param titleView
     * @param bodyView
     */
    protected void initAllView(View titleView, View bodyView) {
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT,
                (int) resources.getDimension(R.dimen.dp_48));
        frameLayout.addView(titleView, layoutParams);
        this.bodyView = bodyView;
        FrameLayout.LayoutParams bodyLayoutParams = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        bodyLayoutParams.setMargins(0, (int) resources.getDimension(R.dimen.dp_48), 0, 0);
        frameLayout.addView(bodyView, bodyLayoutParams);
    }

    protected void initView(@StringRes int titleStringId, @LayoutRes int bodyViewId, int... rightResId) {
        initView(getString(titleStringId), inflater.inflate(bodyViewId, null), rightResId);
    }

    protected void initView(@StringRes int titleStringId, View bodyView, int... rightResId) {
        initView(getString(titleStringId), bodyView, rightResId);
    }

    protected void initView(String title, @LayoutRes int bodyViewId, int... rightResId) {
        initView(title, inflater.inflate(bodyViewId, null), rightResId);
    }

    /**
     * @Title: initView
     * @Description: TODO(初始化标题栏, 并添加标题栏下面的布局)
     * @param: title 标题文字
     * @param: bodyView 加入标题栏下面的布局
     * @param: rightResId 标题栏右边的操作集合,可以有多个或者一个,可以传文字也可以传图片
     */
    protected void initView(String title, View bodyView, int... rightResId) {
        inflater.inflate(R.layout.activity_base, frameLayout, true);
        title_layout = (RelativeLayout) findViewById(R.id.layout_base_title);
        this.bodyView = bodyView;
        title_left = (TextView) findViewById(R.id.title_left);
        title_right = (TextView) findViewById(R.id.title_right);
        titleName = (TextView) findViewById(R.id.title_name);
        // 标题
        titleName.setText(TextUtils.isEmpty(title) ? "" : title);
        // 左边按钮的事件
        setLeftClick(null);
        //右边按钮的点击事件
        setRightClick(null, rightResId);
        // 身躯布局
        if (bodyView != null) {
            // 添加身躯
            FrameLayout.LayoutParams bodyLayoutParams = new FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
            bodyLayoutParams.setMargins(0, (int) resources.getDimension(R.dimen.dp_48), 0, 0);
            frameLayout.addView(bodyView, bodyLayoutParams);
        }
    }

    /**
     * @Title: setTitleBackgroundColor
     * @Description: TODO(设置标题栏背景颜色)
     * @param: @param colorResId
     * @return: void
     */
    public void setTitleBackgroundColor(@ColorRes int colorResId) {
        if (title_layout != null) {
            title_layout.setBackgroundColor(resources.getColor(colorResId));
        }
    }

    /**
     * @Title: setTitleBackgroundColor
     * @Description: TODO(设置身躯背景颜色)
     * @param: @param colorResId
     * @return: void
     */
    public void setBodyViewBackgroundColor(@ColorRes int colorResId) {
        if (bodyView != null) {
            bodyView.setBackgroundColor(resources.getColor(colorResId));
        }
    }

    /**
     * 设置默认灰的身躯背景色
     */
    public void setBodyViewDefaultBackground() {
        setBodyViewBackgroundColor(R.color.select_list);
    }


    /**
     * @Title: setTitleVisiable
     * @Description: TODO(设置标题栏是否可见)
     * @param: @param isVisiable
     * @return: void
     */
    public void setTitleVisiable(View title_layout, View bodyView, ViewGroup.MarginLayoutParams bodyLayoutParams, boolean isVisiable) {
        if (title_layout == null) {
            return;
        }
        if (isVisiable) {
            title_layout.setVisibility(View.VISIBLE);
        } else {
            title_layout.setVisibility(View.GONE);
        }
        if (bodyView != null) {
            bodyView.setLayoutParams(bodyLayoutParams);
        }
    }

    /**
     * @Title: setTitleVisiable
     * @Description: TODO(设置标题栏是否可见)
     * @param: @param isVisiable
     * @return: void
     */
    public void setTitleVisiable(View title_layout, boolean isVisiable) {
        if (title_layout == null) {
            return;
        }
        FrameLayout.LayoutParams bodyLayoutParams = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        if (isVisiable) {
            title_layout.setVisibility(View.VISIBLE);
            bodyLayoutParams.setMargins(0, (int) resources.getDimension(R.dimen.dp_48), 0, 0);
        } else {
            title_layout.setVisibility(View.GONE);
            bodyLayoutParams.setMargins(0, 0, 0, 0);
        }
        if (bodyView != null) {
            bodyView.setLayoutParams(bodyLayoutParams);
        }
    }


    /**
     * @Title: setTitleVisiable
     * @Description: TODO(设置标题栏是否可见)
     * @param: @param isVisiable
     * @return: void
     */
    public void setTitleVisiable(boolean isVisiable) {
        setTitleVisiable(title_layout, isVisiable);
    }


    /**
     * 给id和资源添加分割符并返回
     */
    protected String ID$RES(int id, int res) {
        return new StringBuffer().append(id).append(ID_SPERATOR_RES).append(res).toString();
    }

    /**
     * 添间整个布局
     *
     * @param viewId
     */
    protected void initView(int viewId) {
        initView(inflater.inflate(viewId, null));
    }

    /**
     * @Title: initView
     * @Description: TODO(不需要标题栏的布局, 像登录这种界面, 可别乱调用)
     * @param: bodyView
     * @return: void
     */
    protected void initView(View bodyView) {
        // 添加身躯
        FrameLayout.LayoutParams bodyLayoutParams = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        frameLayout.addView(bodyView, bodyLayoutParams);
    }

    /**
     * @Title: initView
     * @Description: TODO(不需要标题栏的布局)
     * @param: bodyView 布局的最外层的标检是merge
     * @return: void
     */
    protected void initMergetView(int layoutId) {
        inflater.inflate(layoutId, frameLayout, true);
    }


    /**
     * 标题栏左边点击事件,默认是关闭当前的Activity
     */
    protected void setLeftClick(TextView title_left, final OnClickListener listener, @NonNull int... resID) {
        if (title_left == null) {
            return;
        }
        int size = resID == null ? 0 : resID.length;

        for (int i = 0; i < size; i++) {
            //判断它是不是图片资源
            if (resID[i] >= 0x7f020000 && resID[i] <= 0x7f02ffff) {
                title_left.setCompoundDrawablesWithIntrinsicBounds(resID[i], 0, 0, 0);
            } else {
                title_left.setText(resID[i]);
            }
            if (title_left.getVisibility() != View.VISIBLE)
                title_left.setVisibility(View.VISIBLE);
        }
        title_left.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onClick(v);
                } else {
                    ActivityManager.create().finishActivity(BaseTitleActivity.this);
                }
            }
        });

    }

    /**
     * 标题栏左边点击事件,默认是关闭当前的Activity
     */
    protected void setLeftClick(final OnClickListener listener, @NonNull int... resID) {
        setLeftClick(title_left, listener, resID);

    }


    /**
     * 隐藏左标题
     */
    protected void setLeftTitleGone() {
        setViewGone(title_left);
    }

    /**
     * 隐藏右标题
     */
    protected void setRightTitleGone() {
        setRightClick(null, null);
    }

    /**
     * 隐藏控件
     */
    protected void setViewGone(View view) {
        view.setVisibility(View.GONE);
    }

    /**
     * 右标题点击事件
     *
     * @param listener
     * @param resID    右标题资源id，最多限定传两个，既最多是一个是图片id和一个字符串id
     */
    protected void setRightClick(TextView title_right, final OnClickListener listener, @NonNull int... resID) {
        if (title_right == null) {
            return;
        }
        int size = resID == null ? 0 : resID.length;
        if (size > 0) {
            title_right.setVisibility(View.VISIBLE);
        } else {
            title_right.setVisibility(View.GONE);
            return;
        }
        for (int i = 0; i < size; i++) {
            //判断它是不是图片资源
            if (resID[i] >= 0x7f020000 && resID[i] <= 0x7f02ffff) {
                title_right.setCompoundDrawablesWithIntrinsicBounds(resID[i], 0, 0, 0);
            } else {
                title_right.setText(resID[i]);
            }
        }
        title_right.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onClick(v);
                }
            }
        });
    }

    /**
     * 右标题点击事件
     *
     * @param listener
     * @param resID    右标题资源id，最多限定传两个，既最多是一个是图片id和一个字符串id
     */
    protected void setRightClick(final OnClickListener listener, @NonNull int... resID) {
        setRightClick(title_right, listener, resID);
    }

    /**
     * 设置标题
     */
    protected void setTitleName(@StringRes int res) {
        setTitleName(titleName, res);
    }

    /**
     * 设置标题
     */
    protected void setTitleName(String title) {
        setTitleName(titleName, title);
    }

    /**
     * 设置标题
     */
    protected void setTitleName(TextView titleName, @StringRes int res) {
        if (titleName != null)
            titleName.setText(res);
    }

    /**
     * 设置标题
     */
    protected void setTitleName(TextView titleName, String title) {
        if (titleName != null)
            titleName.setText(title);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            ActivityManager.create().finishActivity(this);
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * skip to @param(cls)，and call @param(aty's) finish() method
     */
    public void skipActivity(Activity aty, Class<?> cls) {
        showActivity(aty, cls);
        ActivityManager.create().finishActivity(aty);
    }

    /**
     * skip to @param(cls)，and call @param(aty's) finish() method
     */
    public void skipActivity(Activity aty, Intent it) {
        showActivity(aty, it);
        ActivityManager.create().finishActivity(aty);
    }

    /**
     * skip to @param(cls)，and call @param(aty's) finish() method
     */
    public void skipActivity(Activity aty, Class<?> cls, Bundle extras) {
        showActivity(aty, cls, extras);
        ActivityManager.create().finishActivity(aty);
    }

    /**
     * show to @param(cls)，but can't finish activity
     */
    public void showActivity(Activity aty, Class<?> cls) {
        Intent intent = new Intent();
        intent.setClass(aty, cls);
        aty.startActivity(intent);
    }

    /**
     * show to @param(cls)，but can't finish activity
     */
    public void showActivity(Activity aty, Intent it) {
        aty.startActivity(it);
    }

    /**
     * show to @param(cls)，but can't finish activity
     */
    public void showActivity(Activity aty, Class<?> cls, Bundle extras) {
        Intent intent = new Intent();
        intent.putExtras(extras);
        intent.setClass(aty, cls);
        aty.startActivity(intent);
    }

    /**
     * show to @param(cls) (startActivityForResult)，but can't finish activity
     */
    public void showActivityForResult(Activity aty, Class<?> cls, Bundle extras, int requestCode) {
        Intent intent = new Intent();
        intent.putExtras(extras);
        intent.setClass(aty, cls);
        aty.startActivityForResult(intent, requestCode);
    }

    /**
     * show to @param(cls) (startActivityForResult)，but can't finish activity
     */
    public void showActivityForResult(Activity aty, Class<?> cls, int requestCode) {
        Intent intent = new Intent();
        intent.setClass(aty, cls);
        aty.startActivityForResult(intent, requestCode);
    }

    /**
     * 清楚view图片的处理
     *
     * @param view
     */
    final public void clearDrawables(View view) {
        if (view.getBackground() != null)
            view.getBackground().setCallback(null);

        if (view instanceof ImageView) {
            ImageView imageView = (ImageView) view;
            imageView.setImageBitmap(null);
        } else if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i = 0; i < viewGroup.getChildCount(); i++)
                clearDrawables(viewGroup.getChildAt(i));

            if (!(view instanceof AdapterView))
                viewGroup.removeAllViews();
        }
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        clearDrawables(frameLayout);
        ActivityManager.create().finishActivity(this);
    }


}