package cn.framework.myandroidlibrary.http;


/**
 * Created by lenovo on 2017/9/21.
 */

 public interface  HttpResponseProxyCallback {
    public void onSuccess(RespDataBase data, boolean cache, String reqTag);
    public void onError(int code, String message, String reqTag);

}
