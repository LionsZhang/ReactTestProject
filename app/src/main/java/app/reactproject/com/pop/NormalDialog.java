package app.reactproject.com.pop;

import android.app.Dialog;
import android.content.Context;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import app.reactproject.com.R;
import app.reactproject.com.inteface.OnDialogClickListener;

/**
 * 内容提示对话框dialog
 *
 * @author lion
 * @Description:TODO
 * @Date 2015-10-14
 */
public class NormalDialog extends Dialog {
    public static final int RIGHT_ONCLICK = 1;
    public static final int lEFT_ONCLICK = 0;
    public static final int ONE_ONCLICK = 2;

    private String titleTxt, centerTxt, leftBtnTxt, rightBtnTxt, oneButtonText;
    private int titleColor = -1, centerColor = -1, leftBtnColor = -1, rightBtnColor = -1;
    private Context context;
    private OnDialogClickListener listener;

    /**
     * id 0 左边按钮 1右边按钮
     *
     * @param context       上下文对象
     * @param centerTxt     内容
     * @param leftBtnTxt    左按钮
     * @param rightBtnTxt   右按钮
     * @param titleTxt      标题
     * @param titleColor    标题颜色
     * @param centerColor   内容颜色
     * @param leftBtnColor  左按钮颜色
     * @param rightBtnColor 右按钮颜色
     * @param listener
     */
    public NormalDialog(Context context, String titleTxt, String centerTxt, String leftBtnTxt, String rightBtnTxt,
                        int titleColor, int centerColor, int leftBtnColor, int rightBtnColor, final OnDialogClickListener listener) {
        super(context, R.style.Normal_dialog);
        this.context = context;
        this.titleTxt = titleTxt;
        this.centerTxt = centerTxt;
        this.leftBtnTxt = leftBtnTxt;
        this.rightBtnTxt = rightBtnTxt;
        this.titleColor = titleColor;
        this.centerColor = centerColor;
        this.leftBtnColor = leftBtnColor;
        this.rightBtnColor = rightBtnColor;
        this.listener = listener;
        initView();

    }

    /**
     * id 0 左边按钮 1右边按钮
     *
     * @param context      上下文对象
     * @param centerTxt    内容
     * @param oneButonText 一个button 文本
     */
    public NormalDialog(Context context, String titleTxt, String centerTxt, String oneButonText, final OnDialogClickListener listener) {
        super(context, R.style.Normal_dialog);
        this.context = context;
        this.centerTxt = centerTxt;
        this.listener = listener;
        this.titleTxt = titleTxt;
        this.oneButtonText = oneButonText;
        initView();

    }

    /**
     * id 0 左边按钮 1右边按钮
     *
     * @param context     上下文对象
     * @param centerTxt   内容
     * @param leftBtnTxt  左按钮
     * @param rightBtnTxt 右按钮
     */
    public NormalDialog(Context context, String titleTxt, String centerTxt, String leftBtnTxt, String rightBtnTxt, final OnDialogClickListener listener) {
        super(context, R.style.Normal_dialog);
        this.context = context;
        this.centerTxt = centerTxt;
        this.leftBtnTxt = leftBtnTxt;
        this.rightBtnTxt = rightBtnTxt;
        this.titleTxt = titleTxt;
        this.listener = listener;
        initView();

    }

    private void initView() {
        View view = View.inflate(context, R.layout.message_show_dialog, null);
        TextView tv_title = (TextView) view.findViewById(R.id.tv_title);
        TextView tv_center = (TextView) view.findViewById(R.id.tv_center);
        TextView bt_left = (TextView) view.findViewById(R.id.left_text);
        TextView bt_right = (TextView) view.findViewById(R.id.right_text);
        TextView one_bt = (TextView) view.findViewById(R.id.one_bt);
        LinearLayout left_right_rl = (LinearLayout) view.findViewById(R.id.left_right_rl);

        //标题
        if (!TextUtils.isEmpty(titleTxt)) {
            tv_title.setVisibility(View.VISIBLE);
            tv_title.setText(titleTxt);
        } else {
            tv_title.setVisibility(View.GONE);
        }
        if (titleColor != -1) {
            tv_title.setTextColor(titleColor);
        }
        //内容
        if (!TextUtils.isEmpty(centerTxt)) {
            tv_center.setText(Html.fromHtml(centerTxt));
        }
        if (centerColor != -1) {
            tv_center.setTextColor(centerColor);
        }
        //左按钮
        if (!TextUtils.isEmpty(leftBtnTxt)) {
            bt_left.setText(leftBtnTxt);
            one_bt.setVisibility(View.GONE);
            left_right_rl.setVisibility(View.VISIBLE);
        }
        if (leftBtnColor != -1) {
            bt_left.setTextColor(leftBtnColor);
        }
        //右按钮
        if (!TextUtils.isEmpty(rightBtnTxt)) {
            bt_right.setText(rightBtnTxt);
            one_bt.setVisibility(View.GONE);
            left_right_rl.setVisibility(View.VISIBLE);
        }
        if (rightBtnColor != -1) {
            bt_right.setTextColor(rightBtnColor);
        }
        if (!TextUtils.isEmpty(oneButtonText)) {
            one_bt.setText(oneButtonText);
            one_bt.setVisibility(View.VISIBLE);
            left_right_rl.setVisibility(View.GONE);
        }
        bt_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                if (listener != null) {
                    listener.onClick(lEFT_ONCLICK, v);
                }
            }
        });
        bt_right.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dismiss();
                if (listener != null) {
                    listener.onClick(RIGHT_ONCLICK, v);
                }
            }
        });
        one_bt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dismiss();
                if (listener != null) {
                    listener.onClick(ONE_ONCLICK, v);
                }
            }
        });

        this.setContentView(view);
        this.show();
    }

}
