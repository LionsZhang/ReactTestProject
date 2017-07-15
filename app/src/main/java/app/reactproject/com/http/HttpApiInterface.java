package app.reactproject.com.http;

import com.google.gson.JsonObject;

import java.util.Map;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by lionzhang on 2016/8/16.
 */
public interface HttpApiInterface {


    /**
     * post请求
     *
     * @param params
     * @return
     */
    @POST("lmw-product/lmw")
    @FormUrlEncoded
    Observable<JsonObject> post(@FieldMap Map<String, String> params);


    /**
     * get请求
     *
     * @param params
     * @return
     */
    @GET("lmw-product/lmw")
    Observable<JsonObject> get(@QueryMap Map<String, String> params);

    /**
     * get请求 活动请求专用
     *
     * @param params
     * @return
     */
    @GET("lmw-activity/activity")
    Observable<JsonObject> getActivity(@QueryMap Map<String, String> params);

}
