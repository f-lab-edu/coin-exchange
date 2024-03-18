package com.coin.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = false)
public class UserDTO {
	private int userNumber;		//회원번호
	private String userId;	    	//회원아이디
	private String password;		//비밀번호
	private String address;			//주소
	private String name;			//이름
	private String handPhone;		//전화번호	
	private Timestamp createTime;		//회원 가입시간
	private Timestamp updateTime;		//회원정보 수정시간
	
	@Builder
	public UserDTO(String userId, String password, String address, String name, String handPhone) {
		this.userId = userId;
		this.password = password;
		this.address = address;
		this.name = name;
		this.handPhone = handPhone;
	}
}
