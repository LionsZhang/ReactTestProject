package app.reactproject.com.http;

import com.example.administrator.lmw.utils.ALLog;
import com.example.administrator.lmw.utils.APPUtil;
import com.example.administrator.lmw.utils.MD5;
import com.example.administrator.lmw.utils.SdkVersionUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by lion on 2016/8/15.
 */
public class LmwHttp {

    //public static String baseUrl = "http://www.limin.net/";//开发环境
    //   public static String baseUrl = "http://m.liminwang.com.cn/";//存管测试环境
    // public static String baseUrl = "http://m.liminwang.net/";//测试环境
    public static String baseUrl = "https://batem.limin.com/";//预发布环境
//      public static String baseUrl = "https://m.limin.com/";//发布环境
    //  public static String baseUrl = "http://192.168.2.223:9080/";//前端联调环境

    private static final int DEFAULT_TIMEOUT = 60;
    private static LmwHttp instance;
    private static Retrofit retrofit;

    private LmwHttp() {
    }

    /**
     * 获取lmwhttp单例
     *
     * @auture lionzhang
     */

    public static LmwHttp getInstance() {
        if (instance == null) {
            synchronized (LmwHttp.class) {
                if (instance == null) {
                    instance = new LmwHttp();
                }
            }
        }
        return instance;
    }

    /**
     * 持有一个 HttpApiInterface单例操作网络请求
     *
     * @auture lionzhang
     */

    public HttpApiInterface createHttpInterface() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        builder.readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        builder.addInterceptor(new LogInterceptor());
        retrofit = new Retrofit.Builder()
                .client(builder.build())
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit.create(HttpApiInterface.class);
    }


    /**
     * 初始化一个http请求服务接口代理类并返回
     *
     * @auture lionzhang
     */
    public static <T> T inintHttp(Class<T> httpService) {
        return retrofit.create(httpService);

    }

    /**
     * 共有协议的参数
     * <p/>
     * method	是	接口名。例如: user.login
     * app_key	是	服务端分配。
     * app_version	是	app版本号
     * version	是	API版本号，目前版本号是1.0.0
     * timestamp	是	时间戳格式: 1123555
     * systemSource	是	系统来源，android,ios,wechat,pc
     * sign	是	只有anroid ,ios 需要签名
     */


    public Map getBasePara(String method) {
        Map<String, String> params = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        String timeMills = System.currentTimeMillis() + "";
        params.put("method", method);
        params.put("app_key", "lmw_product_android");
        params.put("app_version", APPUtil.getVersion(false));
        params.put("version", SdkVersionUtil.getSystemSdkVersion());
        params.put("timestamp", timeMills);
        params.put("systemSource", "android");//android
        String sign = sb.append("3d84539692ee42c7aedb3f54bafd7630")
                .append("method")
                .append(method)
                .append("app_key")
                .append("lmw_product_android")//lmw_product_android
                .append("app_version")
                .append(APPUtil.getVersion(false))
                .append("version")
                .append(SdkVersionUtil.getSystemSdkVersion())
                .append("systemSource")
                .append("android")
                .append("timestamp")
                .append(timeMills)
                .append("3d84539692ee42c7aedb3f54bafd7630")
                .toString();
        params.put("sign", MD5.getMD5(sign).toUpperCase());
        return params;
    }


    public String getPictureVerifyCodeUrl(String method, String noncestr) {
        StringBuilder singStringBuilder = new StringBuilder();
        StringBuilder sb = new StringBuilder();
        String timeMills = System.currentTimeMillis() + "";
        String sign = singStringBuilder.append("3d84539692ee42c7aedb3f54bafd7630")
                .append("method")
                .append(method)
                .append("app_key")
                .append("lmw_product_android")//lmw_product_android
                .append("app_version")
                .append(APPUtil.getVersion(false))
                .append("version")
                .append(SdkVersionUtil.getSystemSdkVersion())
                .append("systemSource")
                .append("android")
                .append("timestamp")
                .append(timeMills)
                .append("3d84539692ee42c7aedb3f54bafd7630")
                .toString();
        sign = MD5.getMD5(sign).toUpperCase();

        return sb.append(baseUrl)
                .append("lmw-product/lmw/")
                .append("?")
                .append("app_key=")
                .append("lmw_product_android")
                .append("&")
                .append("method=")
                .append(method)
                .append("&")
                .append("app_version=")
                .append(APPUtil.getVersion(false))
                .append("&")
                .append("version=")
                .append(SdkVersionUtil.getSystemSdkVersion())
                .append("&")
                .append("systemSource=")
                .append("android")
                .append("&")
                .append("timestamp=")
                .append(timeMills)
                .append("&")
                .append("sign=")
                .append(sign)
                .append("&")
                .append("noncestr=")
                .append(noncestr)
                .toString();

    }


    /**
     * 日志log拦截器
     *
     * @author snubee
     * @email snubee96@gmail.com
     * @time 2017/5/9 9:34
     **/

    private class LogInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();

            Request.Builder builder = request.newBuilder();
            builder.addHeader("x-version", APPUtil.getVersion(false));
            builder.addHeader("x-symbol", "1");
            builder.addHeader("x-request-limin", "android");
            request = builder.build();
            ALLog.i("okhttp" + " request:" + request.toString());
            long t1 = System.nanoTime();
            Response response = chain.proceed(request);
            long t2 = System.nanoTime();
            ResponseBody responseBody = response.peekBody(1024 * 1024);
//            ALLog.v("okhttp" + String.format(Locale.getDefault(), "Received response for %s in %.1fms%n%s",
//                    response.request().url(), (t2 - t1) / 1e6d, response.headers()));
            ALLog.i("okhttp" + "response body: " + responseBody.string());
            return response;
        }
    }


}
