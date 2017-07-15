package app.reactproject.com.base.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;


/**
 * 通用的Viewpager的fragment页面适配器
 *
 * @author snubee
 * @email snubee96@gmail.com
 * @time 2017/2/14 14:24
 **/
public class BasePagerAdapter extends PagerAdapter {
    private FragmentManager fragmentManager;
    private List<Fragment> fragments;
    private List<String> titles;

    public BasePagerAdapter(FragmentManager fragmentManager, List<Fragment> fragments) {
        this.fragmentManager = fragmentManager;
        this.fragments = fragments;
    }

    public BasePagerAdapter(FragmentManager fragmentManager, List<Fragment> fragments, List<String> titles) {
        this.fragmentManager = fragmentManager;
        this.fragments = fragments;
        this.titles = titles;
    }


    @Override
    public int getCount() {
        return fragments != null && !fragments.isEmpty() ? fragments.size() : 0;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles != null && titles.size() > 0 ? titles.get(position) : "";
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // 移出viewpager两边之外的page布局
        container.removeView(fragments.get(position).getView());
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        if (fragments == null) return null;

        if (fragments.isEmpty()) return null;

        Fragment fragment = fragments.get(position);

        if (fragment == null) {
            return null;
        }

        if (!fragment.isAdded()) {
            // 如果fragment还没有added
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.add(fragment, fragment.getClass().getSimpleName());
            ft.commitAllowingStateLoss();
            fragmentManager.executePendingTransactions();
        }

        if (fragment.getView().getParent() == null) {
            container.addView(fragment.getView());
        }

        return fragment.getView();
    }


}
