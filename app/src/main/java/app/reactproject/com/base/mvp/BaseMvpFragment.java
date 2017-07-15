package app.reactproject.com.base.mvp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import butterknife.ButterKnife;

/**
 * @author lionszhang.
 * @explain
 * @time 2017/7/15 16:48.
 *
 *
 * 一个MVP架构的基类Fragment
 */


public abstract class BaseMvpFragment<P extends IPresent> extends Fragment implements IView<P> {
    protected Context mContext;
    protected View contentView;
    protected ProgressDialog loadingDialog;
    private Handler mHandler = new Handler(Looper.getMainLooper());
    protected LayoutInflater inflater;
    private String mainScreen = this.getClass().getName();

    public static <T extends BaseMvpFragment> BaseMvpFragment newInstance(Bundle paramBundle, Class<T> paramClass) {
        BaseMvpFragment fragment = null;
        try {
            fragment = (BaseMvpFragment) paramClass.newInstance();
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        fragment.setArguments(paramBundle);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.inflater = inflater;
        // contentView = inflater.inflate(getContentLayout(), null);
        if (null != contentView) {
            ViewGroup parent = (ViewGroup) contentView.getParent();
            if (null != parent) {
                parent.removeView(contentView);
            }
        } else
            contentView = inflater.inflate(getLayoutId(), null);
        ButterKnife.bind(this, contentView);
        initView();
        return contentView;
    }


    public void showToast(String txt) {
        Toast.makeText(mContext, txt, Toast.LENGTH_LONG).show();
    }


    /**
     * 显示对话框加载进度条
     */
    protected void showLoadingDialog() {
        mHandler.post(new Runnable() {

            @Override
            public void run() {
                if (loadingDialog == null) {
                    loadingDialog = new ProgressDialog(getActivity());
                }
                if (!loadingDialog.isShowing()) {
                    loadingDialog.show();
                }

            }
        });
    }

    /**
     * 隐藏对话框加载进度条
     */
    protected void hideLoadingDialog() {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if (loadingDialog != null && loadingDialog.isShowing()) {
                    loadingDialog.dismiss();
                }

            }
        });
    }


    /**
     * 默认启动一个activity
     *
     * @param clazz
     */
    protected void startActivity(Class<?> clazz) {
        Intent startIntent = new Intent(getActivity(), clazz);
        this.startActivity(startIntent);
    }

    public void onResume() {
        super.onResume();
        // MobclickAgent.onPageStart(mainScreen); //统计页面，"MainScreen"为页面名称，
    }

    public void onPause() {
        super.onPause();
        //  MobclickAgent.onPageEnd(mainScreen);
    }
}
