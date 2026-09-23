package com.example.demo.PartnerData;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Partner {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer partnerId;
	private String partnerName;
	private long mobileNumber;
	private String password;
	public Partner(Integer partnerId, String partnerName, long mobileNumber, String password) {
		super();
		this.partnerId = partnerId;
		this.partnerName = partnerName;
		this.mobileNumber = mobileNumber;
		this.password = password;
	}
	public Partner() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getPartnerId() {
		return partnerId;
	}
	public void setPartnerId(Integer partnerId) {
		this.partnerId = partnerId;
	}
	public String getPartnerName() {
		return partnerName;
	}
	public void setPartnerName(String partnerName) {
		this.partnerName = partnerName;
	}
	public long getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
