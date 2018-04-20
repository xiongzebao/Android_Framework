package cn.framework.myandroidlibrary.act;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Map;

import cn.framework.myandroidlibrary.http.Code;
import cn.framework.myandroidlibrary.http.HttpClientHelper;
import cn.framework.myandroidlibrary.http.HttpResponseHandleProxy;
import cn.framework.myandroidlibrary.http.HttpResponseProxyCallback;
import cn.framework.myandroidlibrary.http.core.HttpResponseBaseProxy;
import cn.framework.myandroidlibrary.http.RespDataBase;
import cn.framework.myandroidlibrary.utils.AppUtils;
import cn.framework.myandroidlibrary.utils.LogUtils;


public class BaseActivity extends AppCompatActivity implements HttpResponseProxyCallback {

    HttpResponseBaseProxy respHendleProxy=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppUtils.setContext(this);

    }

    @Override
      public void onSuccess(RespDataBase data, boolean cache, String reqTag) {

        RespDataBase d = data;
       Object o= d.getDatas();

        Toast.makeText(this,"成功了东方闪电沙发上",Toast.LENGTH_SHORT).show();
       // invoke(d,reqTag);

    }

  private   void invoke(RespDataBase o,String reqTag){

        try {
            Class clazz = this.getClass();
            Method m = null;
            m= clazz.getDeclaredMethod(reqTag,new Class[]{ RespDataBase.class});
            if(m==null){
                String errorMsg = clazz.getSimpleName()+"获取方法:"+reqTag+ "失败";
                onError(Code.Error.CODE_REFLECTCLASS_EXCEPTION,errorMsg,reqTag);
            }
            m.invoke(this,new Object[]{o});
        } catch (IllegalAccessException e) {
            onError(Code.Error.CODE_REFLECTCLASS_EXCEPTION,e.getMessage(),reqTag);
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            onError(Code.Error.CODE_REFLECTCLASS_EXCEPTION,e.getMessage(),reqTag);
            e.printStackTrace();
        }catch (IllegalArgumentException e){
            onError(Code.Error.CODE_REFLECTCLASS_EXCEPTION,e.getMessage(),reqTag);
        } catch (NoSuchMethodException e) {
            onError(Code.Error.CODE_REFLECTCLASS_EXCEPTION,e.getMessage(),reqTag);
            e.printStackTrace();
        }
}


    @Override
    public void onError(int code, String message, String reqTag) {
        LogUtils.e(message,code,reqTag);
       // invoke("getInfo");
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    public boolean onClick(View v) {
        return false;
    }

    public final void doPost(final boolean showProgress, final String url, final Map<String, Object> args, final Type mType, final String reqTag) {
        initResponseProxy();
        new Thread(){
            @Override
            public void run() {
                super.run();
                HttpClientHelper.doPost(url, args, mType, reqTag,true, respHendleProxy);
            }
        }.start();

    }

    private void initResponseProxy(){
        if (respHendleProxy == null) {
            respHendleProxy = new HttpResponseHandleProxy(this,this);
        } else {
            respHendleProxy.setContext(this);
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }



}
