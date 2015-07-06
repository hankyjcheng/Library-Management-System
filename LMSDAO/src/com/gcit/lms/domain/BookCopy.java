package com.gcit.lms.domain;

public class BookCopy {
	private int noOfCopies;
	private Book book;
	private Branch branch;

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public int getNoOfCopies() {
		return noOfCopies;
	}

	public void setNoOfCopies(int noOfCopies) {
		this.noOfCopies = noOfCopies;
	}
}
