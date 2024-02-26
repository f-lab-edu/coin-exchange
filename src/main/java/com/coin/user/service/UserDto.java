package com.coin.user.service;

public class UserDto {
	private String userNumber;		//회원번호
	private String userId;	    	//회원아이디
	private String password;		//비밀번호
	private String address;			//주소
	private String name;			//이름
	private String handPhone;		//전화번호	
	private String createTime;		//회원 가입시간
	private String updateTime;		//회원정보 수정시간
	
	public UserDto(String userId, String password, String address, String name, String handPhone) {
		this.userId = userId;
		this.password = password;
		this.address = address;
		this.name = name;
		this.handPhone = handPhone;
	}
	
	public String getUserNumber() {
		return userNumber;
	}
	public void setUserNumber(String userNumber) {
		this.userNumber = userNumber;
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
	public String getHandPhone() {
		return handPhone;
	}
	public void setHandphone(String handPhone) {
		this.handPhone = handPhone;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	
	@Override
	public String toString() {
		return "UserDto [userNumber=" + userNumber + ", userId=" + userId + ", password=" + password + ", address="
				+ address + ", name=" + name + ", handPhone=" + handPhone + ", createTime=" + createTime
				+ ", updateTime=" + updateTime + "]";
	}
}
