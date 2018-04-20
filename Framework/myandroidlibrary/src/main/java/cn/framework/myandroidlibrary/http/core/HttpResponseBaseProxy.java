package cn.framework.myandroidlibrary.http.core;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.google.gson.JsonSyntaxException;

import java.lang.reflect.Type;

import cn.framework.myandroidlibrary.http.Code;
import cn.framework.myandroidlibrary.http.HttpResponseProxyCallback;
import cn.framework.myandroidlibrary.http.RespDataBase;
import cn.framework.myandroidlibrary.utils.JsonUtils;


/**
 * Created by lenovo on 2017/9/21.
 */

public abstract class HttpResponseBaseProxy implements HttpResponseCallBack {

    public   static  final int MSG_ERROR=1000;
    private static final int MSG_REQ_DATA_ERROR = 1010;//不是json数据
    private static final int MSG_REQ_DATA_SUCCESS = 1011;//成功

    protected Context context=null;
    protected HttpResponseProxyCallback callback=null;
    private Handler handler = null;

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public HttpResponseProxyCallback getCallback() {
        return callback;
    }

    public void setCallback(HttpResponseProxyCallback callback) {
        this.callback = callback;
    }


    public HttpResponseBaseProxy(HttpResponseProxyCallback callback){
        initHandler();
    }
    public HttpResponseBaseProxy(Context context, HttpResponseProxyCallback callback){
         this.context = context;
        this.callback = callback;
         initHandler();
     }

     final  private Handler initHandler(){
         if (handler != null) {
             return handler;
         }
         handler = new Handler() {
             @Override
             public void handleMessage(Message msg) {
                 super.handleMessage(msg);
                 onHandleMessage(msg);

             }
         };
        return handler;
     }



    final protected void sendMessage(int what,Object obj, int code,String reqFlag){
        Message msg = Message.obtain();
        Bundle bundle = new Bundle();
        bundle.putInt("code",code);
        bundle.putString("reqFlag",reqFlag);
        msg.obj = obj;
        msg.what = what;
        msg.setData(bundle);
        handler.sendMessage(msg);
    }

    final protected void sendMessage(int what,Object obj){
        Message msg = Message.obtain();
        Bundle bundle = new Bundle();
        msg.obj = obj;
        msg.what = what;
        msg.setData(bundle);
        handler.sendMessage(msg);
    }



     protected   void onHandleMessage(Message msg){
         Bundle bundle = msg.getData();
         int int_code = bundle.getInt("code");
         String reqFlag = bundle.getString("reqFlag");

        switch (msg.what){

            case MSG_ERROR:{
                callback.onError(int_code,(String)msg.obj, reqFlag);
                break;
            }
            case MSG_REQ_DATA_SUCCESS:{
                callback.onSuccess((RespDataBase) msg.obj,false,reqFlag);
                break;
            }
            default:break;
        }
    }

    //子类实现方法，对消息进行拦截
    public abstract boolean InterceptorMessage(RespDataBase message,String reqFlag);


    private void onParseMesssage(String message, Type mType, String reqTag){

           /*    if(!message.startsWith("{")&&!message.startsWith("[")){
            sendMessage(MSG_ERROR,"message is not json data",Code.Error.CODE_MSG_NOT_JSON,reqTag);
            return;
        }*/

        RespDataBase respDataBase=null;
        try {
            respDataBase = JsonUtils.parseRespJson(message,mType);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            sendMessage(MSG_ERROR,e.getMessage(), Code.Error.CODE_JSON_SYNTAX_EXCEPTION,reqTag);
            return;
        }

        if(respDataBase==null){
            sendMessage(MSG_ERROR, "respDataBase==null",Code.Error.CODE_JSON_SYNTAX_EXCEPTION,reqTag);
            return;
        }
        if(InterceptorMessage(respDataBase, reqTag)){
            return;
        }
        sendMessage(MSG_REQ_DATA_SUCCESS,respDataBase,200,reqTag);
    }


    @Override
    final public void onSuccess(String message, Type mType, String reqTag) {
       // LogUtils.json(message);
        onParseMesssage(message, mType, reqTag);
    }

    @Override
   final public void onFail(int code, String message, String reqTag) {
      //  LogUtils.e(message);
        sendMessage(MSG_ERROR,message,code,reqTag);
    }
}
