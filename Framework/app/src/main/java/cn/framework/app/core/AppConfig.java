package cn.framework.app.core;

/**
 * Created by lenovo on 2017/9/22.
 */

public class AppConfig {

    public static boolean isDebug() {
        return false;
    }

    public static class Http {

        //开发 192.168.18.200
        //测试 192.168.18.202

        public static final String HOST_USER;

        public static final String envStr;
        public static final int envInt;


        //private static final String ENV_DEVELOPMENT_IP = "http://192.168.199.216";//开发环境IP
        private static final String ENV_DEVELOPMENT_IP = "http://192.168.18.166";//开发环境IP
        private static final String ENV_TEST_IP = "http://192.168.18.202";//测试环境IP

        public static final int ENV_DEVELOPMENT = 0;//开发环境
        public static final int ENV_TEST = 1;//测试环境
        public static final int ENV_SHOW = 2;//演示环境
        public static final int ENV_PRODUCT = 3;//线上环境
        public static final int HAITUN = 10;//海豚本地环境

        static {
            Integer environment = ENV_DEVELOPMENT;
            if (environment == null) {
                environment = ENV_PRODUCT;
            }
            if (environment < ENV_DEVELOPMENT) {
                environment = ENV_TEST;//默认内网测试环境
            }
            environment = ENV_DEVELOPMENT;
            envInt = environment;

            if (environment == ENV_DEVELOPMENT) {//开发
                envStr = "开发";
                HOST_USER = ENV_DEVELOPMENT_IP + ":3000/";


            } else if (environment == ENV_TEST) {//测试（内网）
                envStr = "测试";
                HOST_USER = ENV_TEST_IP + ":10100/";

            } else if (environment == ENV_SHOW) {//51bm
                envStr = "演示";
                HOST_USER = "http://user.51bm.net.cn/";

            } else if (environment == ENV_PRODUCT) {//生产环境
                //envStr="Beta 版";
                envStr = "";
                HOST_USER = "https://user.artstudent.cn/";

            } else if (environment == HAITUN) {//海豚开发
                envStr = "海豚开发";
                HOST_USER = ENV_DEVELOPMENT_IP + ":10100/";

            } else {
                //未定
                //envStr="Beta 版";
                envStr = "";
                HOST_USER = "https://user.artstudent.cn/";

            }
        }

    }
}
