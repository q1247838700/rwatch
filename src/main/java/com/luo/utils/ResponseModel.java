package com.luo.utils;

import java.io.Serializable;

public class ResponseModel implements Serializable{
	
	private static final long serialVersionUID = 5285476448351872035L;
	
	/**
	 * 状态码
	 * 200 成功
	 * 404 不存在
	 * 500 出现异常
	 */
	private int status;
	
	/**
	 * 提示信息
	 */
	private String msg;
	
	/**
	 * 数据实体
	 */
	private Object data;

	public ResponseModel(){
		
	}
	
	public ResponseModel(int status, String msg, Object data) {
		this.status = status;
		this.msg = msg;
		this.data = data;
	}
	public ResponseModel(int status, String msg) {
		this.status = status;
		this.msg = msg;
	}
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
