package cn.framework.myandroidlibrary.http.core;

import java.lang.reflect.Type;

/**
 * Created by lenovo on 2017/9/20.
 */

public interface   HttpResponseCallBack {

    public void onSuccess(String message, Type mType, String reqTag);

    public void onFail(int code, String message, String reqTag);

}