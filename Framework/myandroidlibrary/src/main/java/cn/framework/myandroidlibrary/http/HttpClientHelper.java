package cn.framework.myandroidlibrary.http;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import cn.framework.myandroidlibrary.http.core.BaseHttpClient;
import cn.framework.myandroidlibrary.http.core.HttpResponseCallBack;
import cn.framework.myandroidlibrary.utils.AppUtils;
import cn.framework.myandroidlibrary.utils.JsonUtils;
import cn.framework.myandroidlibrary.utils.UserAgentUtils;
import okhttp3.FormBody;
import okhttp3.RequestBody;

/**
 * Created by lenovo on 2017/9/20.
 */

public class HttpClientHelper {

    private static BaseHttpClient httpClient = null;
    private static BaseHttpClient getHttpClient(){
        if (httpClient == null) {
            httpClient = new BaseHttpClient() {
                @Override
                public Map<String, String> getMapHeaders() {
                    HashMap<String,String> map = new HashMap<String,String>();
                    map.put("User-Agent", UserAgentUtils.getClientInfo(null));
                    map.put("Accept", "application/json; q=0.5");
                    map.put("platformType", "1");
                    map.put("yks", "1");
                    map.put("tkn", "yx001");
                    map.put("udid", UserAgentUtils.getDeviceInfo(AppUtils.getContext()));
                    return map;
                }

                @Override
                public RequestBody getRequestBody(Map<String, Object> map) {
                    String json=map2json(map);
                    FormBody.Builder builder = new FormBody.Builder();
                    builder.add("data", json);
                    builder.add("ts",System.currentTimeMillis()+"");
                    //  String ticket=UserInfoKey.getLoginToken();
                    //        if(ticket!=null&&ticket.length()>8){
                    //            builder.add("ticket",ticket);
                    //        }
                    return builder.build();
                }
            };
        }
        return httpClient;
    }

    public static void doPost(final String url, final Map<String, Object> args, final Type mType, final String reqTag, boolean isSync, final HttpResponseCallBack callback){
       getHttpClient().doPost(url,args,mType,reqTag,isSync,callback);
    }

    private final static String map2json(Map<String,Object> map){
        if(map==null||map.size()==0){
            return "{\"p\":{},\"m\":\"\"}";
        }
        String json= JsonUtils.toJson(map);
        String jsonStr="{\"p\":"+json+",\"m\":\"\"}";
        return jsonStr;
    }

}
