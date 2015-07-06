package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.domain.Author;
import com.gcit.lms.domain.Book;
import com.gcit.lms.domain.Genre;

public class BookDAO extends BaseDAO<Book> {
	
	public BookDAO(Connection connection) throws Exception {
		super(connection);
	}
	
	public void create(Book book) throws Exception {
		int bookId = saveWithId("INSERT INTO tbl_book (title) VALUES(?)",
				new Object[] { book.getTitle()});
		
		for(Author author: book.getAuthors()){
			save("INSERT INTO tbl_book_authors VALUES (?,?)", 
				new Object[] { bookId, author.getAuthorId() });
		}
		
		for(Genre genre: book.getGenres()){
			save("INSERT INTO tbl_book_genres VALUES (?,?)", 
				new Object[] { genre.getGenreId(), bookId });
		}
	}

	public void update(Book book) throws Exception {
		save("UPDATE tbl_book SET title=? WHERE bookId=?", new Object[] { book.getTitle(), book.getBookId() });

	}

	public void delete(Book book) throws Exception {
		save("DELETE FROM tbl_book WHERE bookId=?", new Object[] { book.getBookId() });
	}

	@SuppressWarnings("unchecked")
	public List<Book> readAll() throws Exception{
		return (List<Book>) read("SELECT * FROM tbl_book", null);
		
	}

	public Book readOne(int bookId) throws Exception {
		@SuppressWarnings("unchecked")
		List<Book> books = (List<Book>) read("SELECT * FROM tbl_book", new Object[] {bookId});
		if (books != null && books.size() > 0){
			return books.get(0);
		}
		return null;
	}

	@Override
	public List<Book> extractData(ResultSet resultSet) throws Exception {
		List<Book> bookArray = new ArrayList<Book>();
		PublisherDAO pubDAO = new PublisherDAO(getConnection());
		AuthorDAO authorDAO = new AuthorDAO(getConnection());
		GenreDAO genreDAO = new GenreDAO(getConnection());
		while(resultSet.next()){
			Book book = new Book();
			book.setBookId(resultSet.getInt("bookId"));
			book.setTitle(resultSet.getString("title"));
			book.setPublisher(pubDAO.readOne(resultSet.getInt("pubId")));
			@SuppressWarnings("unchecked")
			List<Author> authors = (List<Author>) authorDAO.readFirstLevel("SELECtT * FROM tbl_author WHERE authorId IN"
					+ "(SELECT authorId FROM tbl_book_authors WHERE bookId=?)", new Object[] {resultSet.getInt("bookId")});
			book.setAuthors(authors);
			@SuppressWarnings("unchecked")
			List<Genre> genres = (List<Genre>) genreDAO.readFirstLevel("SELECT * FROM tbl_genre JOIN tbl_book_genres ON tbl_genre.genre_id=tbl_book_genres.genre_id WHERE bookId=?", new Object[] {resultSet.getInt("bookId")});
			book.setGenres(genres);
			bookArray.add(book);
		}
		
		return bookArray;
	}
	
	@Override
	public List<Book> extractDataFirstLevel(ResultSet resultSet) throws Exception {
		List<Book> bookArray = new ArrayList<Book>();
		while(resultSet.next()){
			Book book = new Book();
			book.setBookId(resultSet.getInt("bookId"));
			book.setTitle(resultSet.getString("title"));
			bookArray.add(book);
		}
		
		return bookArray;
	}
	
}
