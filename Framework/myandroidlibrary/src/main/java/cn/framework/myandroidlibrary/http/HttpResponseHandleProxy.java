package cn.framework.myandroidlibrary.http;

import android.content.Context;
import android.os.Message;
import android.widget.Toast;

import cn.framework.myandroidlibrary.http.core.HttpResponseBaseProxy;


/**
 * Created by lenovo on 2017/9/22.
 */

public class HttpResponseHandleProxy extends HttpResponseBaseProxy {
    private static  int MSG_TOAST = 10010;

    public HttpResponseHandleProxy(Context context, HttpResponseProxyCallback callback) {
        super(context, callback);
    }

    //这个函数在UI线程中执行
    //处理在interceptorMessage中拦截发出的消息
    @Override
    public void onHandleMessage(Message msg) {
        super.onHandleMessage(msg);
        int what = msg.what;
        if(what == MSG_TOAST){
            Toast.makeText(context,(String)msg.obj,Toast.LENGTH_SHORT).show();
            return;
        }
    }

    //对服务端返回数据进行拦截,服务端成功返回数据第一个回调此方法
    //如果返回true，表示拦截了此条消息，就不会回调onSuccess方法
    //如果返回false,不拦截此条消息，此消息就交给被代理者处理
    @Override
    public boolean InterceptorMessage(RespDataBase message, String reqFlag){
        if(message.isCache()){
            cacheData(message);
        }

        if(message.getCode().equals("520")){
            sendMessage(MSG_TOAST,message.getMessage());
            return true;
        }


        return false;
    }

    private void cacheData(RespDataBase message){

    }
}
