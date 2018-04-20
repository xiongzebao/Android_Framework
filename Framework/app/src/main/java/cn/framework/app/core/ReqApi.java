package cn.framework.app.core;



/**
 * http://192.168.18.201:8082/
 * @author hsm
 *
 */
public class ReqApi {

	public static class User{

		public static final String INFO=AppConfig.Http.HOST_USER+"users";
		//1.用户 / 01.登录接口.md
		public static final String LOGIN=AppConfig.Http.HOST_USER+"login";
		//1.用户 / 01.退出登录接口.md
		public static final String LOGOUT=AppConfig.Http.HOST_USER+"logout";
		

		//1.用户 / 02.注册接口.md
		public static final String API_USER_REG=AppConfig.Http.HOST_USER+"api/m/user/register.ws";
		//1.用户 / 02.注册接口.md
		public static final String API_USER_REG_BY_PHONE=AppConfig.Http.HOST_USER+"api/m/user/regByPhone.ws";

		//密码找回接口-获取问题
		public static final String API_GET_QUESTION=AppConfig.Http.HOST_USER+"api/m/security/get_question.ws";
		//密码找回接口-验证问题答案
		public static final String API_VERFIFY_QUESTION=AppConfig.Http.HOST_USER+"api/m/security/verify_question.ws";
		//密码找回接口-设置新密码
		public static final String API_SET_NEW_PWD=AppConfig.Http.HOST_USER+"api/m/security/set_new_pwd.ws";
		//密码找回接口-验证短信验证码
		public static final String API_VERFIFY_SMSCODE=AppConfig.Http.HOST_USER+"api/m/security/verify_sms_code.ws";

		//安全问题查询
		public static final String API_MY_QUEST_QUERY=AppConfig.Http.HOST_USER+"api/m/auth/security/query_question.ws";

		//安全问题设置
		public static final String API_MY_QUEST_SET=AppConfig.Http.HOST_USER+"api/m/auth/security/question_answer.ws";

		//密码修改接口
		public static final String API_MOD_PWD=AppConfig.Http.HOST_USER+"api/m/auth/security/mod_pwd.ws";
		//绑定手机号查询
		public static final String API_BIND_PHONE_QUERY=AppConfig.Http.HOST_USER+"api/m/auth/user/get_userinfo.ws";
		//绑定手机号
		public static final String API_BIND_PHONE=AppConfig.Http.HOST_USER+"api/m/auth/security/bind_phone.ws";
		//解除绑定手机号
		public static final String API_UNBIND_PHONE=AppConfig.Http.HOST_USER+"api/m/auth/security/unbind_phone.ws";

		//绑定证件号码
		public static final String API_BIND_IDCARDNO=AppConfig.Http.HOST_USER+"api/m/auth/security/bind_idcardNo.ws";

		//短信验证码
		public static final String API_SMS_CODE=AppConfig.Http.HOST_USER+"api/m/security/sms_code.ws";
		//图片验证码
		public static final String API_IMG_CODE=AppConfig.Http.HOST_USER+"api/m/security/img_code.ws";

		//扫码登录请求
		public static final String API_SCAN_REQUEST=AppConfig.Http.HOST_USER+"api/m/auth/login/scan_request.ws";
		//扫码登录确认
		public static final String API_SCAN_SUBMIT=AppConfig.Http.HOST_USER+"api/m/auth/login/scan_confirm.ws";

		//04.考生信息查询接口.md
		public static final String API_GET_STDINFO=AppConfig.Http.HOST_USER+"api/m/auth/user/get_stuinfo.ws";
		//05.考生信息保存接口.md
		public static final String API_SAVE_STDINFO=AppConfig.Http.HOST_USER+"api/m/auth/user/save_stuinfo.ws";

		//考生信息精简接口.md
		public static final String API_GET_SIMPLE_STDINFO=AppConfig.Http.HOST_USER+"api/m/auth/user/get_simple_stuinfo.ws";


		//21.服务项目列表
		public static final String API_SERVICE_QUERY=AppConfig.Http.HOST_USER+"api/m/auth/service/query.ws";
		//22.认证资料查询.md
		public static final String API_QUERY_AUTH_RES=AppConfig.Http.HOST_USER+"api/m/auth/service/query_auth_res_new.ws";
		//23.认证资料确认提交.md
		public static final String API_COMMIT_AUTH_RES=AppConfig.Http.HOST_USER+"api/m/auth/service/commit_auth_res.ws";
		//24.认证资料上传.md
		public static final String API_UPLOAD_AUTH_RES=AppConfig.Http.HOST_USER+"api/m/auth/service/upload_auth_res.ws";
		//25.服务交费.md
		public static final String API_ADD_SERVICE_ORDER_OLD=AppConfig.Http.HOST_USER+"api/m/auth/service/add_service_order.ws";
        //25.服务交费.md(支持微信和支付宝)
        public static final String API_ADD_SERVICE_ORDER=AppConfig.Http.HOST_USER+"api/m/auth/service/add_service_order_new.ws";

		//25.我的服务列表.md
		public static final String API_MY_SERVICE=AppConfig.Http.HOST_USER+"api/m/auth/service/query.ws";
		//25.我的审核状态及排队人数.md
		public static final String API_AUDIT_DATA=AppConfig.Http.HOST_USER+"api/m/auth/manager/audit/audit_data.ws";

	}


}
