package cn.framework.myandroidlibrary.http;

/**
 * Created by lenovo on 2017/9/21.
 */

public class Code {
    public static class Error{
        public static int CODE_MSG_NULL = 10000;//从网络读到空消息
        public static int CODE_MSG_IOEXCEPTION = 10002;//读取网络字节流抛出异常
        public static int CODE_NETREQUEST_ERROR = 10003;//读取网络字节流抛出异常

        public static int CODE_MSG_NOT_JSON = 10004;//不是json数据
        public static int CODE_JSON_SYNTAX_EXCEPTION = 10005;//不是json数据

        public static int CODE_REFLECTCLASS_EXCEPTION = 10006;// 反射类方法错误


    }


    public static class MsgCode{
        public static int CODE_MSG_ = 10000;//从网络读到空消息
    }



}
