package com.tz.bms.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
/**
 * 
 * 本来用来演示 收货地址实体类
 *@author 杨倩Yoki
 *@2016-12-28 @下午1:47:56
 */
@Entity
@Table(name="ONLINE_ADDRESS")
public class Address {
	private Long addressId;
	private String area;
	private String detailAddres;
	private String emailCode;
	private String reciver;
	private String tel;
	private String isDefault;
	private User user;
	
	@Id
	@GeneratedValue
	public Long getAddressId() {
		return addressId;
	}
	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getDetailAddres() {
		return detailAddres;
	}
	public void setDetailAddres(String detailAddres) {
		this.detailAddres = detailAddres;
	}
	public String getEmailCode() {
		return emailCode;
	}
	public void setEmailCode(String emailCode) {
		this.emailCode = emailCode;
	}
	public String getReciver() {
		return reciver;
	}
	public void setReciver(String reciver) {
		this.reciver = reciver;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getIsDefault() {
		return isDefault;
	}
	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}
	@ManyToOne
	@JoinColumn(name="USER_ID")
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return "Address [addressId=" + addressId + ", area=" + area
				+ ", detailAddres=" + detailAddres + ", emailCode=" + emailCode
				+ ", reciver=" + reciver + ", tel=" + tel + ", isDefault="
				+ isDefault + ", user=" + user + "]";
	}

}

