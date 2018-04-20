package cn.framework.myandroidlibrary.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import cn.framework.myandroidlibrary.http.RespDataBase;


public class JsonUtils {

	private static final Gson gson=new GsonBuilder().create();
	/**
	 *
	 * @param json
	 * @param type
	 * @return
	 */
	public static <T> T parseJson(String json,Type type) {
		try{
			if(type==null){
				return null;
			}
			return gson.fromJson(json, type);
		}catch(Exception e){
			System.out.println("error json:"+json);
			e.printStackTrace();
			return null;
		}
	}

	public static RespDataBase parseRespJson(String json, Type type) throws JsonSyntaxException {
			if(type==null){
				type=new TypeToken<RespDataBase>() {}.getType();
			}
			RespDataBase respData = gson.fromJson(json, type);
			return respData;
	}




	public static String toJson(Object obj) {

		return gson.toJson(obj);
	}

}