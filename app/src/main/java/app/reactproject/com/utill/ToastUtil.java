package app.reactproject.com.utill;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/2/15 0015.
 */
public class ToastUtil {

    public static void showToast(Context context, String text) {
        Toast.makeText(context,text, Toast.LENGTH_SHORT).show();
    }

    public static void showToast(Context context, int resId) {
        Toast.makeText(context,resId, Toast.LENGTH_SHORT).show();
    }

}
