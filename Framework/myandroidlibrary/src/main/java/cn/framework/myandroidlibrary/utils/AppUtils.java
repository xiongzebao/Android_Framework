package cn.framework.myandroidlibrary.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Looper;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;



public class AppUtils {

	//private static final int default_apk_ver = 1072;
    private static Context ApplicationContext;
	private static Context context;
	private static Activity act;
	
	
	public static void setContext(Context cxt) {
		if(cxt instanceof Activity){
			act=(Activity)cxt;
		}
		context = cxt;
	}

	public static Context getContext() {
		if(act!=null){
			return act;
		}
		return context;
	}

	public static Activity getActivity() {
		return act;
	}

	public static void startActivity(Intent intent) {

		//startActivity(intent,false);
	}

/*
	public static void startWebActivity(String url){
		if(act==null){
			return ;
		}
		Intent intent = new Intent(act, WebActivity.class);
		intent.putExtra("url",url);
		startActivity(intent);
	}
*/




/*
    public static void startActivity(Intent intent,boolean finish) {
        if(act==null){
            return ;
        }
        if (!(act instanceof FragmentActivity)){
        	try{
				act.startActivity(intent);
			}catch (Exception e){
        		if (intent!=null){
					intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				}
				act.startActivity(intent);
			}
			return ;
		}
        FragmentManager manager = ((FragmentActivity)act).getSupportFragmentManager();
        List<Fragment> list = manager.getFragments();
        if (list!=null&&list.size()>0){
            for (Fragment fragment:list){
                if (!(fragment instanceof BaseFragment)){
                    continue;
                }
                if (!fragment.isHidden()&&fragment.getUserVisibleHint()){
                    FragmentManager childManager = fragment.getChildFragmentManager();
                    if (childManager!=null&&childManager.getFragments()!=null&&childManager.getFragments().size()>0){
                        List<Fragment> childList = childManager.getFragments();
                        for (Fragment childFg : childList){
                            if (!(childFg instanceof BaseFragment)){
                                continue;
                            }
                            if (!childFg.isHidden()&&childFg.getUserVisibleHint()){
                                ((BaseFragment)childFg).startActivity(intent,finish);
                                return;
                            }
                        }
                        ((BaseFragment)fragment).startActivity(intent,finish);
                        return;
                    }
                    ((BaseFragment)fragment).startActivity(intent,finish);
                    return;
                }
            }
        }
        ((BaseActivity)act).startActivity(intent,finish);
    }
*/

	public static void startActivity(Class<? extends Activity> classz) {
		if(act==null){
			return ;
		}
       // startActivity(new Intent(act,classz),false);
	}

	public static void startActivityAndFinish(Class<? extends Activity> classz) {
		if(act==null){
			return ;
		}
		act.startActivity(new Intent(act,classz));
		act.finish();
	}
/*	public static BaoMingApp getApp() {
		if(act==null){
			return null;
		}
		BaoMingApp app=(BaoMingApp)act.getApplication();
		return app;
	}*/
	
	public static void runOnUiThread(Runnable run){
		if(act==null){
			return ;
		}
		act.runOnUiThread(run);
	}

	public static boolean hasPermission(String permission) {
		return AppUtils.getContext().checkCallingOrSelfPermission(permission) == 0;
	}

	public static int[] getScreenWH() {
		if (context == null) {
			return new int[] { 480, 854 };
		}
		Display display = ((WindowManager) context.getSystemService("window"))
				.getDefaultDisplay();
		return getScreenWH(display);
	}

	public static int[] getScreenWH(Display display) {
		if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB_MR2) {
			int width = display.getWidth();
			int height = display.getHeight();
			return new int[] { width, height };
		} else {
			Point size = new Point();
			display.getSize(size);
			return new int[] { size.x, size.y };
		}
	}

	public static int getColor(int resId) {
		if (context==null){
			return 0;
		}
		Resources res=context.getResources();
		if (res==null){
			return 0;
		}
		int sdkInt=Build.VERSION.SDK_INT;
		if (sdkInt < Build.VERSION_CODES.M) {
			return res.getColor(resId);
		} else {
			try{
				return res.getColor(resId,null);
			}catch(Exception e){
				return res.getColor(resId, AppUtils.getActivity().getTheme());
			}catch(Error e){
				return res.getColor(resId, AppUtils.getActivity().getTheme());
			}
		}
	}

	//获取values-strings字符串
	public static String getString(int string){
        if (context==null){
            return "";
        }
        Resources res=context.getResources();
        return res.getString(string);
    }

	public static Drawable getDrawable(int resId) {
		int sdkInt=Build.VERSION.SDK_INT;
		if (sdkInt < Build.VERSION_CODES.LOLLIPOP) {
			return context.getResources().getDrawable(resId);
		} else {
			try{
				return context.getResources().getDrawable(resId);
			}catch(Exception e){
				return ContextCompat.getDrawable(AppUtils.getContext(),resId);
			}catch(Error e){
				return ContextCompat.getDrawable(AppUtils.getContext(),resId);
			}
		}
	}

	/**
	 * view设置background drawable
	 * 
	 * @param view
	 * @param drawable
	 */
	public static void setBackgroundDrawable(View view, Drawable drawable) {
		if (view==null){
			return ;
		}
		try{
			//中兴手机上会出错,加try catch
			if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB_MR2) {
				view.setBackgroundDrawable(drawable);
			} else {
				view.setBackground(drawable);
			}
		}catch(Exception e){
			try{
				//中兴手机上会出错,加try catch
				view.setBackgroundDrawable(drawable);
			}catch(Exception e2){}
			catch(Error e2){}
		}catch(Error e){
			try{
				//中兴手机上会出错,加try catch
				view.setBackgroundDrawable(drawable);
			}catch(Exception e2){}
			catch(Error e2){}
		}
		
	}

	public static int getAppVersionCode() {
		//return default_apk_ver;
		if(context==null){ return -1; } 
		try{ 
			PackageManager packagemanager =context.getPackageManager(); 
			String packageName =context.getPackageName(); 
			PackageInfo packageInfo =packagemanager.getPackageInfo(packageName, 8192); 
			return packageInfo.versionCode; 
		}catch(Exception e){} 
		return -1;
	}

	public static String getAppVersion() {
		 if(context==null){ return null; } 
		 try{ 
			 PackageManager packagemanager =context.getPackageManager(); 
			 String packageName =context.getPackageName(); 
		     PackageInfo packageInfo =
		     packagemanager.getPackageInfo(packageName, 8192); 
		     return packageInfo.versionName; 
		 }catch(Exception e){ } 
		 return null;
	}

    public static int getScreenHeight(Display display) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB_MR2) {
            return display.getHeight();
        } else {
            Point size = new Point();
            display.getSize(size);
            return size.y;
        }
    }

   public static int getScreenWidth(Display display) {
	   if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB_MR2) {
    	   return display.getWidth();
       } else {
    	   Point size = new Point(); 
    	   display.getSize(size);
    	   return size.x;
       }
   }

    public static int getScreenHeight(){
        if(context==null){
            return 1280;
        }
        Display display = ((WindowManager)context.getSystemService("window")).getDefaultDisplay();
        return getScreenHeight(display);
    }
   
	public static int getScreenWidth(){
		if(context==null){
			return 480;
		}
		Display display = ((WindowManager)context.getSystemService("window")).getDefaultDisplay();
		return getScreenWidth(display);
	}

    private static String getNavBarOverride() {
        String sNavBarOverride = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            try {
                Class c = Class.forName("android.os.SystemProperties");
                Method m = c.getDeclaredMethod("get", String.class);
                m.setAccessible(true);
                sNavBarOverride = (String) m.invoke(null, "qemu.hw.mainkeys");
            } catch (Throwable e) {
            }
        }
        return sNavBarOverride;
    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    public static boolean hasNavBar(Context context) {
        Resources res = context.getResources();
        int resourceId = res.getIdentifier("config_showNavigationBar", "bool", "android");
        if (resourceId != 0) {
            boolean hasNav = res.getBoolean(resourceId);
            // check override flag
            String sNavBarOverride = getNavBarOverride();
            if ("1".equals(sNavBarOverride)) {
                hasNav = false;
            } else if ("0".equals(sNavBarOverride)) {
                hasNav = true;
            }
            return hasNav;
        } else { // fallback
            return !ViewConfiguration.get(context).hasPermanentMenuKey();
        }
    }

	public static int getNavigationBarHeight(){
	    if (act==null){
	        return 0;
        }
        int result = 0;
        if (hasNavBar(act)) {
            Resources res = act.getResources();
            int resourceId = res.getIdentifier("navigation_bar_height", "dimen", "android");
            if (resourceId > 0) {
                result = res.getDimensionPixelSize(resourceId);
            }
        }
        return result;
    }
	
/*	public static void closeApp(){
		if(act!=null){
			BaoMingApp app=(BaoMingApp)act.getApplication();
			app.closeApp();
		}
	}
	*/
	public static void hideKeyboard(View tokenView) {
		if(act==null){
			return ;
		}
		try{
			if(tokenView==null){
				tokenView=act.getCurrentFocus();
				if(tokenView==null){
					return ;
				}
			}
			InputMethodManager inputMethodManager = (InputMethodManager) act.getSystemService(Activity.INPUT_METHOD_SERVICE);
			inputMethodManager.hideSoftInputFromWindow(tokenView.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
		}catch(Exception e){}
		catch(Error e){}
	}

    //显示软键盘
    public static void showKeyboard(final EditText inputView){
        inputView.setFocusable(true);

        //打开软键盘
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                InputMethodManager m = (InputMethodManager) inputView.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                m.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }, 300);
    }
	
	public static void hideKeyboardWithDone(EditText inputView) {
		if(inputView==null){
			return ;
		}
		if(act==null){
			return ;
		}
		inputView.setImeOptions(EditorInfo.IME_ACTION_DONE);
		inputView.setOnEditorActionListener(new OnEditorActionListener() {  
            @Override  
            public boolean onEditorAction(TextView v, int actionId,  
                    KeyEvent event) {  
                if (actionId == EditorInfo.IME_ACTION_DONE) {
            		InputMethodManager inputMethodManager = (InputMethodManager) act.getSystemService(Activity.INPUT_METHOD_SERVICE);
            		inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                }
                return false;  
            }  
        });
	}

	/**
	 * 跳转到系统的应用权限设置页面
	 */
	public static void gotoAppAuthSetPage(){
		Activity act=getActivity();
		if (act==null){
			return ;
		}
		try{
			Intent intent=new Intent();
			intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
			intent.setData(Uri.fromParts("package", act.getPackageName(), null));
			act.startActivity(intent);
		}catch(Exception e){

		}catch (Error e){}
	}

	//判断当前线程是否是主线程，是返回true，其他返回false
    public static boolean isOnMainThread(){
        if (Looper.myLooper()==Looper.getMainLooper()){
            return true;
        }
        return false;
    }

    //设置当前页面的StatusBar显示与隐藏
    public static void setStatusBarVisibility(boolean visible){
        int flag = visible ? 0 : WindowManager.LayoutParams.FLAG_FULLSCREEN;
        AppUtils.getActivity().getWindow().setFlags(flag, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }




}
