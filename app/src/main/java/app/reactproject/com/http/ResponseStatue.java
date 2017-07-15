package app.reactproject.com.http;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 网络响应状态码
 *
 * @author snubee
 * @email snubee96@gmail.com
 * @time 2017/5/31 15:34
 **/

@StringDef
@Retention(RetentionPolicy.SOURCE)
public @interface ResponseStatue {
    String STATE_SUCCESS = "0";//成功
    String STATE_LOGOUT = "150006";//重新登了
    String STATE_STOP_SERVICE = "1000";//停服
}
