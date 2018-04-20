package cn.framework.myandroidlibrary.utils;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.CsvFormatStrategy;
import com.orhanobut.logger.DiskLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;

/**
 * Created by lenovo on 2017/9/21.
 */

public class LogUtils {

    public static void initLogger(final boolean isShowLog){

            FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()

                    .showThreadInfo(true)  // (Optional) Whether to show thread info or not. Default true
                    .methodCount(4)         // (Optional) How many method line to show. Default 2
                    .methodOffset(0)        // (Optional) Hides internal method calls up to offset. Default 5
                   // .logStrategy(customLog) // (Optional) Changes the log strategy to print out. Default LogCat
                    .tag("xiongbin")   // (Optional) Global tag for every log. Default PRETTY_LOGGER
                    .build();

            Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy) {
                @Override public boolean isLoggable(int priority, String tag) {
                    return isShowLog;
                }
            });
            FormatStrategy CsvformatStrategy = CsvFormatStrategy.newBuilder()
                    .tag("custom")
                    .build();

            Logger.addLogAdapter(new DiskLogAdapter(CsvformatStrategy){
                @Override
                public boolean isLoggable(int priority, String tag) {
                    return isShowLog;
                }
            });

        }

        public static  void e(String msg){
            Logger.e(msg);
        }



        public static  void e(String msg,int code,String reqTag){
            LogUtils.e(msg+"-----code:"+code+"------reqTag:"+reqTag);
        }

        public static void json(String msg){
            Logger.json(msg);
        }

    public static void d(String msg){
        Logger.d(msg);
    }


}




