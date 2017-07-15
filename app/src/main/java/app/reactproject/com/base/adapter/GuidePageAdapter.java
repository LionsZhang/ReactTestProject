package app.reactproject.com.base.adapter;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * 引导页面的
 * @Description:TODO
 * @author lion
 * @Date 2015-11-2
 */
public class GuidePageAdapter extends PagerAdapter
{
    private List<View> views = new ArrayList<View>();

    public GuidePageAdapter(List<View> views)
    {
        this.views = views;
    }
    
    @Override
    public boolean isViewFromObject(View arg0, Object arg1)
    {
        return arg0 == arg1;
    }
    
    @Override
    public int getCount()
    {
        return views.size();
    }
    
    @Override
    public void destroyItem(View container, int position, Object object)
    {
        ((ViewPager)container).removeView(views.get(position));
    }
    
    @Override
    public Object instantiateItem(View container, int position)
    {
        ((ViewPager)container).addView(views.get(position));
        return views.get(position);
    }
}
