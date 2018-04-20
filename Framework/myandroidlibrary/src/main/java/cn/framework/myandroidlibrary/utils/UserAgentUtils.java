package cn.framework.myandroidlibrary.utils;


import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings.Secure;
import android.view.Display;
import android.view.WindowManager;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class UserAgentUtils {
	
	private static String userAgent=null;
	private static String device=null;
	private static Map<String,Object> appArgsMap;

	private static final Object lock=new Object();

	static{
		appArgsMap=new HashMap<String,Object>();
	}
	/**
	 * 获取Android客户端信息
	 * @param context
	 * @return
	 */
	public static String getClientInfo(Context context){
		if(userAgent!=null){
			return userAgent;
		}
		if(context==null){
			context=AppUtils.getContext();
		}
		if(context==null){
			return "";
		}
		WindowManager win=(WindowManager)context.getSystemService("window");
		if(win==null){
			return "";
		}
		int width=0;
		int height=0;
		Display display = win.getDefaultDisplay();
		if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB_MR2) {
    	    width=display.getWidth();
    	    height=display.getHeight();
        } else {
    	   Point size = new Point(); 
    	   display.getSize(size);
    	   width=size.x;
    	   height=size.y;
        }
		//width=720;
		//height=1280;
		int versionCode = 0;
		String versionName = null;
		try{
			PackageManager packagemanager = context.getPackageManager();
			String packageName = context.getPackageName();
			PackageInfo packageInfo = packagemanager.getPackageInfo(packageName, 8192);
			versionCode = packageInfo.versionCode;
			versionName = packageInfo.versionName;
		}catch(Exception e){
			versionCode=1;
			versionName = "1.0.0";
		}
		if(versionName!=null){
			int idx=versionName.indexOf(" ");
			if(idx!=-1){
				versionName=versionName.substring(0,idx);
			}
			idx=versionName.indexOf("(");
			if(idx!=-1){
				versionName=versionName.substring(0,idx);
			}
		}
		String release= Build.VERSION.RELEASE;
		int idx=release.indexOf("-");
		if(idx!=-1){
			release=release.substring(0,idx);
		}
		idx=release.indexOf(" ");
		if(idx!=-1){
			release=release.substring(0,idx);
		}
		String phoneName= Build.MODEL;
		if(phoneName==null){
			phoneName="";
		}else{
			phoneName=phoneName.replace("_", "-");
			if(phoneName.length()>10){
				phoneName=phoneName.substring(0,10);
			}
		}
		String netstr="unknown";
		NetworkInfo networkinfo = ((ConnectivityManager)context.getSystemService("connectivity")).getActiveNetworkInfo();
		if (networkinfo == null || !networkinfo.isAvailable()){
		}else{
			netstr=networkinfo.getTypeName();
			//netstr=networkinfo.getExtraInfo();
		}
		if(netstr!=null){
			netstr=netstr.replace("_", "-");
		}
		
		String aid=getDeviceInfo(context);

		String bssid="nan";
		String wifiName="nan";
		try{
			WifiManager wifi_service = (WifiManager)AppUtils.getActivity().getApplicationContext().getSystemService(Context.WIFI_SERVICE);
			if(wifi_service!=null){
				WifiInfo wifiInfo = wifi_service.getConnectionInfo();
				if(wifiInfo!=null){
					bssid=wifiInfo.getBSSID();
					wifiName=wifiInfo.getSSID();
					if(bssid==null||bssid.length()==0){
						bssid="nan";
					}
					if(wifiName==null||wifiName.length()==0){
						wifiName="nan";
					}
				}
			}
		}catch(Exception e){
	    }catch(Error e){}
		bssid=bssid.replace("_", "-");
		wifiName=wifiName.replace("_", "-");

		synchronized (lock){
			//友盟后台出现如下异常
			//java.lang.ArrayIndexOutOfBoundsException: length=0; index=-959619819
			//at java.util.HashMap.put(HashMap.java:407)
			//目前怀疑这个地方多线程会有问题
			if (userAgent!=null){
				return userAgent;
			}
			//String clientID= PushManager.getInstance().getClientid(context);
			appArgsMap.put("platform", "android");
			appArgsMap.put("osVersion", release);
			appArgsMap.put("appVersion", versionCode+"");
			appArgsMap.put("hvga", width+"*"+height);
			appArgsMap.put("vendor", phoneName);
			appArgsMap.put("udid", aid);
			appArgsMap.put("bssid", bssid);
			appArgsMap.put("wifiName", wifiName);
			appArgsMap.put("networkWay", netstr);
			//appArgsMap.put("clientID", clientID);

			//下面两行防止一些用户通过第3方软件改手机厂商名称,导致后台拦截掉
			phoneName=phoneName.replace("iPhone","AdriPh");
			phoneName=phoneName.replace("iPod","AdriPh");
			phoneName=phoneName.replace("webOS","wos");
			phoneName=phoneName.replace("BlackBerry","bberry");

			wifiName=wifiName.replace("Android","adr");
			wifiName=wifiName.replace("iPhone","iph");
			wifiName=wifiName.replace("iPod","ipd");
			wifiName=wifiName.replace("webOS","wos");
			wifiName=wifiName.replace("BlackBerry","bberry");

			try{
				phoneName= URLEncoder.encode(phoneName,"utf-8");
			}catch(Exception e){}
			if (wifiName==null||wifiName.length()==0){
				wifiName="unknown";
			}else{
				try{
					wifiName=wifiName.replace("_","-");
					wifiName= URLEncoder.encode(wifiName,"utf-8");
				}catch(Exception e){}
			}

			//平台_系统版本_应用版本_设备分辨率_设备唯一标识_连网方式_WIFI路由ID_WIFI名称_手机厂商
			userAgent="yks_andr"+release+
					"_v"+versionCode+
					"_w"+width+"*"+height+
					"_"+phoneName+
					"_"+aid+
					"_"+netstr+
					"_"+bssid+
					"_"+wifiName;

			return userAgent;
		}
	}


	/*
	//平台_系统版本_应用版本_设备分辨率_设备唯一标识_连网方式_WIFI路由ID_WIFI名称_手机厂商
		userAgent="android_"+release+
                "_"+versionCode+
                "_"+width+"*"+height+
                "_"+aid+
                "_"+netstr+
                "_"+bssid+
                "_"+wifiName+
                "_"+phoneName;
	 */
	public static Map<String,Object> getClientInfoMap(){

		return appArgsMap;
	}
	
	public static String getDeviceInfo(Context context){
		if(device!=null){
			return device;
		}
		if(context==null){
			context=AppUtils.getContext();
		}
		String aid=Secure.getString(context.getContentResolver(), Secure.ANDROID_ID);
		String info="baoming"+Build.BOARD+"=="+Build.BRAND+
        "=="+Build.CPU_ABI+"=="+Build.DEVICE+
        "=="+Build.DISPLAY+"=="+Build.HOST+
        "=="+Build.ID+"=="+Build.MANUFACTURER+
        "=="+Build.MODEL+"=="+Build.PRODUCT+
        "=="+Build.TAGS+"=="+Build.TYPE+"=="+Build.USER;
		aid=aid+"=="+info;
		device=SecurityUtils.encrypt(aid, null);
		return device;
	}
}
