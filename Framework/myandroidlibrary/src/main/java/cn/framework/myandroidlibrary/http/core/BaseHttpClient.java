package cn.framework.myandroidlibrary.http.core;

import android.os.Looper;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import cn.framework.myandroidlibrary.http.Code;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by lenovo on 2017/9/24.
 */

public abstract class BaseHttpClient {

    private  OkHttpClient httpClient = null;

    private  OkHttpClient getOKHttpClient(){
        if (httpClient == null) {
            httpClient = new OkHttpClient.Builder()
                    //.addInterceptor(new MockInterceptor())(测试增加拦截器)
                    //.addNetworkInterceptor(new MockInterceptor())
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .build();
        }
        return httpClient;
    }


    public  void doPost(final String url, final Map<String, Object> args, final Type mType, final String reqTag, boolean isSync, final HttpResponseCallBack callback){

        doPost(url, args, isSync, new ResponseCallback() {
            @Override
            public void onResponse(Response response, IOException exception) {

                if(response!=null&&exception==null){

                    if(response.isSuccessful()){
                        String msg=null;
                        try {
                            msg   = response.body().string();
                        } catch (IOException e) {
                            e.printStackTrace();
                            callback.onFail(Code.Error.CODE_MSG_IOEXCEPTION, e.getMessage(),reqTag);
                            return;
                        }

                        if(msg == null||msg.length()==0||msg.isEmpty()){
                            callback.onFail(Code.Error.CODE_MSG_NULL, "从服务端返回的消息为空",reqTag);
                            return;
                        }
                        callback.onSuccess(msg,mType,reqTag);
                    }else {
                        callback.onFail(response.code(),  response.message(),reqTag);
                    }

                }else{
                    callback.onFail(Code.Error.CODE_NETREQUEST_ERROR, exception.getMessage(),reqTag);
                }
            }
        });
    }

    //frame
    private  void doPost(String url, Map<String, Object> args,boolean isSync,final ResponseCallback callback){
        if(isSync){
            Response response = null;
            try {
                if( Looper.getMainLooper().getThread() == Thread.currentThread()){
                    callback.onResponse(null,new IOException("网络请求不能在主线程中运行"));
                    return;
                }

                response = doPostSync(getRequest(getRequestBody(args),url,getMapHeaders()));
                if(response!=null){
                    callback.onResponse(response,null);
                }else{
                    callback.onResponse(null,new IOException("response object is null"));
                }
            } catch (IOException e) {
                e.printStackTrace();
                callback.onResponse(null,e);
            } finally {

            }

        }else{
            doPostAsync(getRequest(getRequestBody(args), url, getMapHeaders()), new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    callback.onResponse(null,e);
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    callback.onResponse(response,null);
                }
            });
        }
    }

    //同步请求(frame)
    private  Response doPostSync(Request request) throws IOException{
        Response response=null;
        response = getOKHttpClient().newCall(request).execute();
        return response;
    }

    //异步请求(frame)
    private  void doPostAsync(Request request,Callback callback ){

        Call call=getOKHttpClient().newCall(request);
        call.enqueue(callback);

    }

    //获取Request (frame)
    private  Request getRequest(RequestBody requestBody, String url, Map<String,String> header){

        boolean isLogin=false;
        long reqStartTime=System.currentTimeMillis();
        Request.Builder builder = new Request.Builder();
        for (Map.Entry<String,String> entry:header.entrySet()){
            builder.addHeader(entry.getKey(),entry.getValue());
        }
        builder.url(url);
        Request request = builder.post(requestBody).build();
        return request;
    }

    //获取RequestBody(frame)
     abstract  public  RequestBody getRequestBody( Map<String, Object> map );


    //获取MapHeaders(frame)
    abstract  public  Map<String,String>  getMapHeaders();


    private interface ResponseCallback{
        public void onResponse(Response response, IOException exception);
    }
}
