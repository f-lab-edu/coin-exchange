package com.coin;

import org.springframework.stereotype.Component;

@Component
public class ResultVO {
	private String msg;	    		//메세지 내용
	private int result;				//결과값
	
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	
	
	
	
}
