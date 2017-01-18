package com.tz.bms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
/**
 * 
 * 本来用来演示 用户实体类
 *@author 杨倩Yoki
 *@2016-12-28 @下午1:50:34
 */
@Entity
@Table(name="ONLINE_USER")
public class User {
	private Long userId;
	@NotNull
	@Size(min=6,max=12,message="{user.username.invalid}")
	private String username;
	@NotNull
	@Size(min=6,max=12,message="{user.password.invalid}")
	private String password;
	@NotNull
	@Size(min=11,max=11,message="{user.tel.invalid}")
	private String tel;
	@NotNull
	@Size(min=6,max=20,message="{user.email.invalid}")
	private String email;
	@NotNull
	@Size(min=1,max=100,message="{user.address.invalid}")
	private String address;
	@NotNull
	@Size(min=1,max=100,message="{user.comparate.invalid}")
	private String comparate;
	
	@Id
	@GeneratedValue
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	@Column(length=50,nullable=false,unique=true)
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Column(length=20,nullable=false)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Column(length=12)
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	@Column(length=200)
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Column(length=200)
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Column(length=200)
	public String getComparate() {
		return comparate;
	}
	public void setComparate(String comparate) {
		this.comparate = comparate;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username
				+ ", password=" + password + ", tel=" + tel + ", email="
				+ email + ", address=" + address + ", comparate=" + comparate
				+ "]";
	}
	
}

