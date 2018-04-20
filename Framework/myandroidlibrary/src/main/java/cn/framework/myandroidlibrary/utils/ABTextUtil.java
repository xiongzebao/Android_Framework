package cn.framework.myandroidlibrary.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.util.DisplayMetrics;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class ABTextUtil {
    /**
     * 获得字体的缩放密度
     *
     * @param context
     * @return
     */
    public static float getScaledDensity(Context context) {
        if (context==null){
            return 0;
        }
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return dm.scaledDensity;
    }


    public static float getDpiDensity() {
        DisplayMetrics metrics = new DisplayMetrics();
        AppUtils.getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int mDensity = metrics.densityDpi;
        return mDensity;
    }

    public static float getDimension(int resId){
        float dimen=0;
        try {
            dimen =  AppUtils.getActivity().getResources().getDimension(resId);
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
            return 0;
        }
        return dimen;
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     * 另外一种就计算方法：TypeValue.applyDimension(TypeValue.COMPLEX_UNIT_DIP,dpval,getResources().getDisplayMetrics());
     */
    public static int dip2px(Context context, float dpValue) {
        if (context==null){
            return 0;
        }
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        if (context==null){
            return 0;
        }
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }


    /**
     * 将px值转换为sp值，保证文字大小不变
     *
     * @param pxValue
     * @return
     */
    public static int px2sp(Context context, float pxValue) {
        if (context==null){
            return 0;
        }
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @param spValue
     * @return
     */
    public static int sp2px(Context context, float spValue) {
        if (context==null){
            return 0;
        }
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }


    /**
     * *************************************************************
     */

    public static boolean isEmpty(Collection collection) {
        return null == collection || collection.isEmpty();
    }

    public static boolean isEmpty(Map map) {
        return null == map || map.isEmpty();
    }

    public static boolean isEmpty(Object[] objs) {
        return null == objs || objs.length <= 0;
    }

    public static boolean isEmpty(int[] objs) {
        return null == objs || objs.length <= 0;
    }

    public static boolean isEmpty(CharSequence charSequence) {
        return null == charSequence || charSequence.length() <= 0;
    }

    public static boolean isBlank(CharSequence charSequence) {
        return null == charSequence || charSequence.toString().trim().length() <= 0;
    }

    public static boolean isLeast(Object[] objs, int count) {
        return null != objs && objs.length >= count;
    }

    public static boolean isLeast(int[] objs, int count) {
        return null != objs && objs.length >= count;
    }

    public static boolean isEquals(String str1, String str2) {
        if (null != str1) {
            return str1.equals(str2);
        }
        if (null != str2) {
            return str2.equals(str1);
        }
        return true;
    }

    public static String trim(CharSequence charSequence) {
        return null == charSequence ? null : charSequence.toString().trim();
    }

    /**
     * 摘取里面第一个不为null的字符串
     * @param options
     * @return
     */
    public static String pickFirstNotNull(CharSequence... options) {
        if (isEmpty(options)) {
            return null;
        }
        String result = null;
        for (CharSequence cs : options) {
            if (null != cs) {
                result = cs.toString();
                break;
            }
        }
        return result;
    }

    /**
     * 替换文本为图片
     *
     * @param charSequence
     * @param regPattern
     * @param drawable
     * @return
     */
    public static SpannableString replaceImageSpan(CharSequence charSequence, String regPattern, Drawable drawable) {
        SpannableString ss = charSequence instanceof SpannableString ? (SpannableString) charSequence : new SpannableString(charSequence);
        try {
            ImageSpan is = new ImageSpan(drawable);
            Pattern pattern = Pattern.compile(regPattern);
            Matcher matcher = pattern.matcher(ss);
            while (matcher.find()) {
                String key = matcher.group();
                ss.setSpan(is, matcher.start(), matcher.start() + key.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
            }
        } catch (Exception ex) {
        }

        return ss;
    }


    /**
     * 压缩字符串到Zip
     *
     * @param str
     * @return 压缩后字符串
     * @throws IOException
     */
    public static String compress(String str) throws IOException {
        if (str == null || str.length() == 0) {
            return str;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        GZIPOutputStream gzip = new GZIPOutputStream(out);
        gzip.write(str.getBytes());
        gzip.close();
        return out.toString("ISO-8859-1");
    }

    /**
     * 解压Zip字符串
     *
     * @param str
     * @return 解压后字符串
     * @throws IOException
     */
    public static String uncompress(String str) throws IOException {
        if (str == null || str.length() == 0) {
            return str;
        }
        ByteArrayInputStream in = new ByteArrayInputStream(str
                .getBytes("UTF-8"));
        return uncompress(in);
    }

    /**
     * 解压Zip字符串
     *
     * @param inputStream
     * @return 解压后字符串
     * @throws IOException
     */
    public static String uncompress(InputStream inputStream) throws IOException {
        if (inputStream == null) {
            return null;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        GZIPInputStream gunzip = new GZIPInputStream(inputStream);
        byte[] buffer = new byte[256];
        int n;
        while ((n = gunzip.read(buffer)) >= 0) {
            out.write(buffer, 0, n);
        }
        return out.toString();
    }

    /**
     * InputStream convert to string
     *
     * @param in
     * @return
     * @throws IOException
     */
    public static String inputStream2String(InputStream in) throws IOException {
        StringBuffer out = new StringBuffer();
        byte[] b = new byte[4096];
        for (int n; (n = in.read(b)) != -1; ) {
            out.append(new String(b, 0, n));
        }
        return out.toString();
    }

    /**
     * 参数：maxLines 要限制的最大行数
     * 参数：content  指TextView中要显示的内容
     */
    public static void setMaxEcplise(final TextView mTextView, final int maxLines, final CharSequence content) {

        ViewTreeObserver observer = mTextView.getViewTreeObserver();
        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mTextView.setText(content);
                if (mTextView.getLineCount() > maxLines) {
                    int lineEndIndex = mTextView.getLayout().getLineEnd(maxLines - 1);
                    //下面这句代码中：我在项目中用数字3发现效果不好，改成1了
                    String text = content.subSequence(0, lineEndIndex - 3) + "...";
                    mTextView.setText(text);
                }
                else {
                    removeGlobalOnLayoutListener(mTextView.getViewTreeObserver(), this);
                }
            }
        });
    }

    @SuppressWarnings("deprecation")
    @SuppressLint("NewApi")
    private static void removeGlobalOnLayoutListener(ViewTreeObserver obs, ViewTreeObserver.OnGlobalLayoutListener listener) {
        if (obs == null)
            return;
        if (Build.VERSION.SDK_INT < 16) {
            obs.removeGlobalOnLayoutListener(listener);
        }
        else {
            obs.removeOnGlobalLayoutListener(listener);
        }
    }


    /**
     * 2个字的标题对齐处理,中间加两个字并设置成白色
     */
    public static void alignTitleHandle(TextView titleView,String title){

        if (title==null||titleView==null){
            return ;
        }
        if (title.length()!=2){
            return ;
        }
        title=title.substring(0,1)+"艺考"+title.substring(1)+":";
        Spannable span = new SpannableString(title);
        span.setSpan(new ForegroundColorSpan(Color.parseColor("#ffffffff")), 1,3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        titleView.setText(span);
    }

    /**
     * 3个字的标题对齐处理,中间加两个字并设置成白色
     */
    /*public static void alignTitleHandleThree(TextView titleView,String title){

        if (title==null||titleView==null){
            return ;
        }
        if (title.length()!=3){
            return ;
        }
        title=title.substring(0,1)+"1"+title.substring(1,2)+"1"+title.substring(2)+":";
        Spannable span = new SpannableString(title);
        span.setSpan(new ForegroundColorSpan(Color.parseColor("#ffffffff")), 1,2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        span.setSpan(new ForegroundColorSpan(Color.parseColor("#ffffffff")), 3,4, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        titleView.setText(span);
    }*/


}
