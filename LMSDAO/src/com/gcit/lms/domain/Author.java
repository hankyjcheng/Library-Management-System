package com.gcit.lms.domain;

import java.util.List;

public class Author {
	private int authorId;
	private String authorName;
	private List<Book> books;
	
	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	public int getAuthorId() {
		return authorId;
	}
	
	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}
	
	public String getAuthorName() {
		return authorName;
	}
	
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
}
