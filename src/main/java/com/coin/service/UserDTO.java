package com.coin.service;

import java.sql.Timestamp;
import java.util.Random;

import com.coin.AES256Cipher;

public class UserDTO {
	private int userNumber;		//회원번호
	private String userId;	    	//회원아이디
	private String password;		//비밀번호
	private String address;			//주소
	private String name;			//이름
	private String handPhone;		//전화번호	
	private Timestamp createTime;		//회원 가입시간
	private Timestamp updateTime;		//회원정보 수정시간
	
	
	public UserDTO(String userId, String password, String address, String name, String handPhone) {
		this.userId = userId;
		this.password = password;
		this.address = address;
		this.name = name;
		this.handPhone = handPhone;
//		String userId = "ksj" + random.nextInt();
//		String password = "ksj";
//		String encodingPassword = aes256.aesEncode(password);
//		String address = "서울시";
//		String name = "김승주";
//		String handPhone = "01012341234";
	}
	public int getUserNumber() {
		return userNumber;
	}
	public void setUserNumber(int userNumber) {
		this.userNumber = userNumber;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public Timestamp getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}
	public String getHandPhone() {
		return handPhone;
	}
	public void setHandPhone(String handPhone) {
		this.handPhone = handPhone;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "UserDto [userNumber=" + userNumber + ", userId=" + userId + ", password=" + password + ", address="
				+ address + ", name=" + name + ", handPhone=" + handPhone + ", createTime=" + createTime
				+ ", updateTime=" + updateTime + "]";
	}
}
