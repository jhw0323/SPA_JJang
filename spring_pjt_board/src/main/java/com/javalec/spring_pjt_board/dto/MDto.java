package com.javalec.spring_pjt_board.dto;

import java.sql.Timestamp;

public class MDto {
	private String id;
	private String name;
	private String password;
	private String birth;
	private String phone;
	private String homePhone;
	private String email;
	private String grade;
	private String type;
	private String address;
	private String zipcode;
	private String state;
	private Timestamp insertDate;
	private Timestamp updateDate;
	
	public MDto(){
		
	}

	public MDto(String id, String password, String birth, String phone, String homePhone, String email, String grade,
			String type, String address, String zipcode, String state, Timestamp insertDate, Timestamp updateDate) {
		this.id = id;
		this.password = password;
		this.birth = birth;
		this.phone = phone;
		this.homePhone = homePhone;
		this.email = email;
		this.grade = grade;
		this.type = type;
		this.address = address;
		this.zipcode = zipcode;
		this.state = state;
		this.insertDate = insertDate;
		this.updateDate = updateDate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getHomePhone() {
		return homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Timestamp getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(Timestamp insertDate) {
		this.insertDate = insertDate;
	}

	public Timestamp getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
	}
	
	
}
