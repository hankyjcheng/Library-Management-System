package com.gcit.lms.domain;

import java.util.List;

public class Borrower {
	
	private int cardNo;
	private String name;
	private String address;
	private String phone;
	private List<BookLoan> bookLoans;
	
	public List<BookLoan> getBookLoans() {
		return bookLoans;
	}
	public void setBookLoans(List<BookLoan> bookLoans) {
		this.bookLoans = bookLoans;
	}
	public int getCardNo() {
		return cardNo;
	}
	public void setCardNo(int cardNo) {
		this.cardNo = cardNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
}
