package com.coin.service;

public class UserBuilder {
	private String userId;	    	//회원아이디
	private String password;		//비밀번호
	private String address;			//주소
	private String name;			//이름
	private String handPhone;		//전화번호	
	
	public UserBuilder userId(String userId) {
		this.userId = userId;
		return this;
	}

	public UserBuilder password(String password) {
		this.password = password;
		return this;
	}

	public UserBuilder address(String address) {
		this.address = address;
		return this;
	}
	
	public UserBuilder name(String name) {
		this.name = name;
		return this;
	}

    public UserBuilder handPhone(String handPhone) {
    	this.handPhone = handPhone;
		return this;
    }

    public UserDTO build() {
        return new UserDTO(userId, password, address, name, handPhone);
    }
}
