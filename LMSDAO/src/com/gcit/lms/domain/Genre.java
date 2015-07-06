package com.gcit.lms.domain;

import java.util.List;

public class Genre {
	private int genreId;
	private String genreName;
	private List<Book> books;
	
	public List<Book> getBooks() {
		return books;
	}
	public void setBooks(List<Book> books) {
		this.books = books;
	}
	public int getGenreId() {
		return genreId;
	}
	public void setGenreId(int genreId) {
		this.genreId = genreId;
	}
	public String getGenreName() {
		return genreName;
	}
	public void setGenreName(String genreName) {
		this.genreName = genreName;
	}
	
}
