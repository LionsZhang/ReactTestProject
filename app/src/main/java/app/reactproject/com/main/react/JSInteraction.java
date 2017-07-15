
package app.reactproject.com.main.react;

import android.app.Activity;
import android.content.Intent;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import java.util.HashMap;
import java.util.Map;


/**
 * 提供Js调用的方法，Js与原生通信
 */
public class JSInteraction extends ReactContextBaseJavaModule {
    private static final String JUMP_NOT_KEY = "JUMP_NOT_KEY";
    private static final String JUMP_KEY = "JUMP_KEY";
   public static final String JS_INFORMATION_KEY = "JS_INFORMATION_KEY";

    public JSInteraction(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    //定义一个module名字
    @Override
    public String getName() {
        return "NativeAndroid";
    }

    //一个可选的方法getContants返回了需要导出给JavaScript使用的常量。它并不一定需要实现，但在定义一些可以被JavaScript同步访问到的预定义的值时非常有用
    @Override
    public Map<String, Object> getConstants() {
        final Map<String, Object> constants = new HashMap<>();
        constants.put(JUMP_NOT_KEY, 0);
        constants.put(JUMP_KEY, 1);
        return constants;
    }

    //定义一个react native 调用的方法
    @ReactMethod
    public void jumpActivity(int type, String message) {
        Activity activity = getCurrentActivity();
        if (type == 1) {
            Intent intent = new Intent(activity, JsJumpActivity.class);
            intent.putExtra(JS_INFORMATION_KEY, message);
            activity.startActivity(intent);
        }

    }


}
