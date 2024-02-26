package com.coin.user.service;

public class UserDto {
	private String USER_NUMBER;		//회원번호
	private String USER_ID;	    	//회원아이디
	private String PASSWORD;		//비밀번호
	private String ADDRESS;			//주소
	private String NAME;			//이름
	private String HANDPHONE;		//전화번호	
	private String CREATE_TIME;		//회원 가입시간
	private String UPDATE_TIME;		//회원정보 수정시간
	public String getUSER_NUMBER() {
		return USER_NUMBER;
	}
	public void setUSER_NUMBER(String uSER_NUMBER) {
		USER_NUMBER = uSER_NUMBER;
	}
	public String getUSER_ID() {
		return USER_ID;
	}
	public void setUSER_ID(String uSER_ID) {
		USER_ID = uSER_ID;
	}
	public String getPASSWORD() {
		return PASSWORD;
	}
	public void setPASSWORD(String pASSWORD) {
		PASSWORD = pASSWORD;
	}
	public String getADDRESS() {
		return ADDRESS;
	}
	public void setADDRESS(String aDDRESS) {
		ADDRESS = aDDRESS;
	}
	public String getNAME() {
		return NAME;
	}
	public void setNAME(String nAME) {
		NAME = nAME;
	}
	public String getHANDPHONE() {
		return HANDPHONE;
	}
	public void setHANDPHONE(String hANDPHONE) {
		HANDPHONE = hANDPHONE;
	}
	public String getCREATE_TIME() {
		return CREATE_TIME;
	}
	public void setCREATE_TIME(String cREATE_TIME) {
		CREATE_TIME = cREATE_TIME;
	}
	public String getUPDATE_TIME() {
		return UPDATE_TIME;
	}
	public void setUPDATE_TIME(String uPDATE_TIME) {
		UPDATE_TIME = uPDATE_TIME;
	}
	@Override
	public String toString() {
		return "UserDto [USER_NUMBER=" + USER_NUMBER + ", USER_ID=" + USER_ID + ", PASSWORD=" + PASSWORD + ", ADDRESS="
				+ ADDRESS + ", NAME=" + NAME + ", HANDPHONE=" + HANDPHONE + ", CREATE_TIME=" + CREATE_TIME
				+ ", UPDATE_TIME=" + UPDATE_TIME + "]";
	}

}
