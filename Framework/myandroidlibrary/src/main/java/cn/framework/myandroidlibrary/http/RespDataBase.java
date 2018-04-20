package cn.framework.myandroidlibrary.http;

import java.io.Serializable;

public class RespDataBase<T> implements Serializable{

	private boolean success;
	private String message;
	private String code;
	private boolean cache=true;//默认要缓存

	private String ticket;//登录接口用
	
	private T datas;
	
	//备用方案
	private String toast;//统一Toast提示
	private String alert;//统一弹框提示
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public T getDatas() {
		return datas;
	}
	public void setDatas(T datas) {
		this.datas = datas;
	}
	public String getTicket() {
		return ticket;
	}
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
	public String getToast() {
		return toast;
	}
	public void setToast(String toast) {
		this.toast = toast;
	}
	public String getAlert() {
		return alert;
	}
	public void setAlert(String alert) {
		this.alert = alert;
	}

	public boolean isCache() {
		return cache;
	}

	public void setCache(boolean cache) {
		this.cache = cache;
	}
}
