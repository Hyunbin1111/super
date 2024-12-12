package com.kh.hyper.member.model.vo;

import java.sql.Date;

public class Member {
	private String userId;
	private String userPwd;
	private String userName;
	private String email;
	private String gender;
	private int number;
	private String phone;
	private String address;
	private Date enrollDate;
	private Date modifyDate;
	
	public Member() {
		super();
	}

	public Member(String userId, String userPwd, String userName, String email, String gender, int number, String phone,
			String address, Date enrollDate, Date modifyDate) {
		super();
		this.userId = userId;
		this.userPwd = userPwd;
		this.userName = userName;
		this.email = email;
		this.gender = gender;
		this.number = number;
		this.phone = phone;
		this.address = address;
		this.enrollDate = enrollDate;
		this.modifyDate = modifyDate;
	}
	
	// getter 메소드 객체 속성의 값을 가져오기 위한 메소드
	// setter 메소드 

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getEnrollDate() {
		return enrollDate;
	}
	public void setEnrollDate(Date enrollDate) {
		this.enrollDate = enrollDate;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	@Override
	public String toString() {
		return "Member [userId=" + userId + ", userPwd=" + userPwd + ", userName=" + userName + ", email=" + email
				+ ", gender=" + gender + ", number=" + number + ", phone=" + phone + ", address=" + address
				+ ", enrollDate=" + enrollDate + ", modifyDate=" + modifyDate + "]";
	}
	
	
}
