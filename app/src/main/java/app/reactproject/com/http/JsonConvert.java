/*
 * Copyright 2016 jeasonlzy(廖子尧)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package app.reactproject.com.http;

import android.text.TextUtils;

import com.example.administrator.lmw.entity.BaseResult;
import com.example.administrator.lmw.entity.SimpleResult;
import com.example.administrator.lmw.utils.ALLog;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * json转化器
 *
 * @author snubee
 * @email snubee96@gmail.com
 * @time 2017/6/21 11:51
 **/
public class JsonConvert<T> {

    private Type type;
    private Class<T> clazz;

    public JsonConvert() {
    }

    public JsonConvert(Type type) {
        this.type = type;
    }

    public JsonConvert(Class<T> clazz) {
        this.clazz = clazz;
    }


    /**
     * 主要作用是解析网络返回的 response 对象，生成onSuccess回调中需要的数据对象
     */
    public T convertResponse(JsonObject response) throws Throwable {

        if (type == null) {
            if (clazz == null) {
                // 如果没有通过构造函数传进来，就自动解析父类泛型的真实类型（有局限性，继承后就无法解析到）
                Type genType = getClass().getGenericSuperclass();
                type = ((ParameterizedType) genType).getActualTypeArguments()[0];
            } else {
                return parseClass(response, clazz);
            }
        }

        if (type instanceof ParameterizedType) {
            return parseParameterizedType(response, (ParameterizedType) type);
        } else if (type instanceof Class) {
            return parseClass(response, (Class<?>) type);
        } else {
            return parseType(response, type);
        }
    }

    private T parseClass(JsonObject response, Class<?> rawType) throws Exception {
        if (rawType == null) return null;

        if (response == null) return null;

        if (rawType == String.class) {
            //noinspection unchecked
            return (T) GsonConvert.toJson(response);
        } else if (rawType == JSONObject.class) {
            //noinspection unchecked
            return (T) new JSONObject(response.toString());
        } else if (rawType == JSONArray.class) {
            //noinspection unchecked
            return (T) new JSONArray(response.toString());
        } else {
            T t = GsonConvert.fromJson(response, rawType);
            return t;
        }
    }

    private T parseType(JsonObject response, Type type) throws Exception {
        if (type == null) return null;

        if (response == null) return null;

        // 泛型格式如下： new JsonCallback<任意JavaBean>(this)
        T t = GsonConvert.fromJson(response, type);

        return t;

    }

    private T parseParameterizedType(JsonObject response, ParameterizedType type) throws Exception {
        if (type == null) return null;

        if (response == null) return null;

        Type rawType = type.getRawType();                     // 泛型的实际类型
        Type typeArgument = type.getActualTypeArguments()[0]; // 泛型的参数
        if (rawType != BaseResult.class) {
            // 泛型格式如下： new JsonCallback<外层BaseBean<内层JavaBean>>(this)
            T t = GsonConvert.fromJson(response, type);
            return t;
        } else {
            if (typeArgument == Void.class) {
                // 泛型格式如下： new JsonCallback<BaseResult<Void>>(this)
                SimpleResult simpleResult = GsonConvert.fromJson(response, SimpleResult.class);
                //noinspection unchecked
                ALLog.i("snubee" + "---Void泛型--" + simpleResult.msg);
                return (T) simpleResult.toBaseResult();
            } else if (!response.has("data") || response.get("data") != null && ((TextUtils.isEmpty(response.get("data").toString())) || "[]".equals(response.get("data").toString()) || "null".equals(response.get("data").toString()))) {//无data 数据
                SimpleResult simpleResponse = GsonConvert.fromJson(response, SimpleResult.class);
                ALLog.i("snubee" + "---无data数据--" + simpleResponse.msg);

                return ((T) simpleResponse.toBaseResult());
            } else {
                // 泛型格式如下： new JsonCallback<BaseResult<内层JavaBean>>(this)
                BaseResult result = GsonConvert.fromJson(response, type);

                //noinspection unchecked
                return (T) result;
            }
        }
    }
}
