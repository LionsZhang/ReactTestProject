package app.reactproject.com.base.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 通用基础适配器
 *
 * @author snubee
 * @email snubee96@gmail.com
 * @time 2017/2/13 14:30
 **/
public abstract class CommonBaseAdapter<T, IVH extends CommonViewHolder> extends BaseAdapter {
    protected LayoutInflater mInflater;
    protected Context mContext;
    protected List<T> mDatas;
    protected Resources mResources;
    private Map<Object, Object> mCache = new HashMap<>();

    public CommonBaseAdapter(Context context) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(mContext);
        this.mResources = mContext.getResources();
        mDatas = new ArrayList<>();

    }

    protected String getString(@StringRes int id) {
        return mResources.getString(id);
    }

    protected int getColor(@ColorRes int id) {
        return ContextCompat.getColor(mContext, id);
    }

    protected Drawable getDrawable(@DrawableRes int id) {
        return ContextCompat.getDrawable(mContext, id);
    }

    public CommonBaseAdapter(Context context, List<T> datas) {
        this(context);
        if (datas != null) {
            this.mDatas.addAll(datas);
        }
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public T getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void setData(List<T> mDatas) {
        this.mDatas = mDatas;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final CommonViewHolder viewHolder = onCreateViewHolder(position, convertView, parent);
        convert((IVH) viewHolder, getItem(position), position);
        return viewHolder.getConvertView();

    }

    public abstract IVH onCreateViewHolder(int position, View convertView, ViewGroup parent);

    public abstract void convert(IVH holder, T item, int position);


    /**
     * 添加数据源显示列表
     *
     * @param datas
     */
    public void addData(List<T> datas) {
        if (mDatas != null) {
            mDatas.addAll(datas);
            notifyDataSetChanged();
        }
    }

    /**
     * 更换列表数据源
     *
     * @param datas
     */
    public void resetDatas(List<T> datas) {
        clearCacheSize();
        if (mDatas != null) {
            mDatas.clear();
            mDatas.addAll(datas);
            notifyDataSetChanged();
        }
    }

    /**
     * 添加一项
     *
     * @param data
     */
    public void addData(T data) {
        if (data != null) {
            mDatas.add(data);
            notifyDataSetChanged();
        }
    }

    /**
     * 更新一项
     *
     * @param data
     */
    public void updateItem(T data, int position) {
        if (data != null) {
            mDatas.remove(position);
            mDatas.add(position, data);
            notifyDataSetChanged();
        }
    }

    /**
     * 删除一项
     *
     * @param data
     */
    public void deleteData(T data) {
        if (data != null) {
            mDatas.remove(data);
            notifyDataSetChanged();
        }
    }

    /**
     * 填减一个数据到缓存
     *
     * @param k
     * @param v
     */
    public void putTagCashe(Object k, Object v) {
        mCache.put(k, v);
    }

    /**
     * 根据key获取一个缓存value
     *
     * @param k
     */
    public Object getTagCashe(Object k) {
        return mCache != null ? mCache.get(k) : null;
    }

    /**
     * 根据key清楚一个缓存value
     *
     * @param k
     */
    public void removeCasheByey(Object k) {
        if (mCache != null) {
            mCache.remove(k);
        }
    }

    /**
     * 获取缓存的大小
     *
     * @return
     */
    public int getCacheSize() {
        return mCache != null ? mCache.size() : 0;
    }

    /**
     * 获取缓存集合
     *
     * @return
     */
    public Map<Object, Object> getCacheMap() {
        return mCache;
    }

    /**
     * 清楚缓存
     *
     * @return
     */
    public void clearCacheSize() {
        if (mCache != null) {
            mCache.clear();
        }
    }

}
