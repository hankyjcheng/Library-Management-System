package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.domain.Author;
import com.gcit.lms.domain.Book;

public class AuthorDAO extends BaseDAO<Author> {
	
	public AuthorDAO(Connection connection) throws Exception {
		super(connection);
	}
	
	public void create(Author author) throws Exception {
		save("INSERT INTO tbl_author (authorName) VALUES(?)",
				new Object[] { author.getAuthorName() });
	}

	public void update(Author author) throws Exception {
		save("UPDATE tbl_author SET authorName=? WHERE authorId=?",
				new Object[] { author.getAuthorName(), author.getAuthorId() });

	}

	public void delete(Author author) throws Exception {
		save("DELETE FROM tbl_author WHERE authorId=?",
				new Object[] { author.getAuthorId() });
	}

	@SuppressWarnings("unchecked")
	public List<Author> readAll() throws Exception{
		return (List<Author>) read("SELECT * FROM tbl_author", null);
		
	}

	public Author readOne(int authorId) throws Exception {
		@SuppressWarnings("unchecked")
		List<Author> authors = (List<Author>) read("SELECT * FROM tbl_author", new Object[] {authorId});
		if (authors != null && authors.size() > 0){
			return authors.get(0);
		}
		return null;
	}

	@Override
	public List<Author> extractData(ResultSet resultSet) throws Exception {
		List<Author> authors =  new ArrayList<Author>();
		BookDAO bookDAO = new BookDAO(getConnection());
		
		while(resultSet.next()){
			Author author = new Author();
			author.setAuthorId(resultSet.getInt("authorId"));
			author.setAuthorName(resultSet.getString("authorName"));
			@SuppressWarnings("unchecked")
			List<Book> books = (List<Book>) bookDAO.readFirstLevel("select * from tbl_books where bookId In"
					+ "(select bookId from tbl_book_authors where authorId=?)", new Object[] {resultSet.getInt("authorId")});
			author.setBooks(books);
			authors.add(author);
		}
		return authors;
	}

	@Override
	public List<Author> extractDataFirstLevel(ResultSet resultSet) throws Exception {
		List<Author> authors =  new ArrayList<Author>();	
		while(resultSet.next()){
			Author author = new Author();
			author.setAuthorId(resultSet.getInt("authorId"));
			author.setAuthorName(resultSet.getString("authorName"));
			authors.add(author);
		}
		return authors;
	}

}
