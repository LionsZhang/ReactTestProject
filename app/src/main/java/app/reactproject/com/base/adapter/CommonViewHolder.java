package app.reactproject.com.base.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.content.ContextCompat;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class CommonViewHolder {
    private final SparseArray<View> mViews;
    private int mPosition;
    private View mConvertView;
    private Context context;

    public CommonViewHolder(Context context, ViewGroup parent, int layoutId,
                            int position) {
        this.context = context;
        this.mPosition = position;
        this.mViews = new SparseArray<View>();
        mConvertView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        // setTag
        mConvertView.setTag(this);
    }

    /**
     * 拿到一个ViewHolder对象
     *
     * @param context
     * @param convertView
     * @param parent
     * @param layoutId
     * @param position
     * @return
     */
    public static CommonViewHolder get(Context context, View convertView,
                                       ViewGroup parent, int layoutId, int position) {
        if (convertView == null) {
            return new CommonViewHolder(context, parent, layoutId, position);
        }
        return (CommonViewHolder) convertView.getTag();
    }

    public View getConvertView() {
        return mConvertView;
    }

    /**
     * 通过控件的Id获取对于的控件，如果没有则加入views
     *
     * @param viewId
     * @return
     */
    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    /**
     * 为TextView设置字符串
     *
     * @param viewId
     * @param text
     * @return
     */
    public CommonViewHolder setText(int viewId, String text) {
        TextView view = getView(viewId);
        view.setText(text);
        return this;
    }

    /**
     * 为TextView设置字符串
     *
     * @param viewId
     * @param text
     * @return
     */
    public CommonViewHolder setText(int viewId, int text) {
        TextView view = getView(viewId);
        view.setText(text);
        return this;
    }

    /**
     * 为TextView设置字符串
     *
     * @param viewId
     * @param text
     * @return
     */
    public CommonViewHolder setText(int viewId, CharSequence text) {
        TextView view = getView(viewId);
        view.setText(text);
        return this;
    }

    /**
     * 为TextView设置背景
     *
     * @param viewId
     * @param resId
     * @return
     */
    public CommonViewHolder setTextBackground(int viewId, int resId) {
        TextView view = getView(viewId);
        view.setBackgroundResource(resId);
        return this;
    }

    /**
     * 为TextView设置字体颜色
     *
     * @param viewId
     * @param resId
     * @return
     */
    public CommonViewHolder setTextColor(int viewId, int resId) {
        TextView view = getView(viewId);
        view.setTextColor(ContextCompat.getColor(context, resId));
        return this;
    }

    /**
     * 为TextView设置背景
     *
     * @param viewId
     * @param color
     * @return
     */
    public CommonViewHolder setTextBackgroundColor(int viewId, int color) {
        TextView view = getView(viewId);
        view.setBackgroundColor(color);
        return this;
    }

    /**
     * 为ImageView设置图片
     *
     * @param viewId
     * @param drawableId
     * @return
     */
    public CommonViewHolder setImageResource(int viewId, int drawableId) {
        ImageView view = getView(viewId);
        view.setImageResource(drawableId);

        return this;
    }

    /**
     * 为ImageView设置图片
     *
     * @param viewId
     * @param bm
     * @return
     */
    public CommonViewHolder setImageBitmap(int viewId, Bitmap bm) {
        ImageView view = getView(viewId);
        view.setImageBitmap(bm);
        return this;
    }


    public int getPosition() {
        return mPosition;
    }

}
