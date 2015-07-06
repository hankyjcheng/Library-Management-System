package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.domain.Book;
import com.gcit.lms.domain.Publisher;

public class PublisherDAO extends BaseDAO<Publisher> {
	
	public PublisherDAO(Connection connection) throws Exception {
		super(connection);
	}
	
	public void create(Publisher publisher) throws Exception {
		save("INSERT INTO tbL_publisher (publisherName) VALUES(?)", new Object[] { publisher.getPublisherName() });
	}

	public void update(Publisher publisher) throws Exception {
		save("UPDATE tbl_publisher SET publisherName=? WHERE publisherId=?", new Object[] { publisher.getPublisherId() });

	}

	public void delete(Publisher publisher) throws Exception {
		save("DELETE FROM tbl_publisher WHERE publisherId=?",
				new Object[] { publisher.getPublisherId() });
	}

	@SuppressWarnings("unchecked")
	public List<Publisher> readAll() throws Exception{
		return (List<Publisher>) read("SELECT * FROM tbl_publisher", null);
	}

	public Publisher readOne(int publisherId) throws Exception {
		@SuppressWarnings("unchecked")
		List<Publisher> publishers = (List<Publisher>) read("SELECT * FROM tbl_publisher", new Object[] {publisherId});
		if (publishers != null && publishers.size() > 0){
			return publishers.get(0);
		}
		return null;
	}

	@Override
	public List<Publisher> extractData(ResultSet resultSet) throws Exception {
		List<Publisher> publishers =  new ArrayList<Publisher>();
		BookDAO bookDAO = new BookDAO(getConnection());
		while (resultSet.next()){
			Publisher publisher = new Publisher();
			publisher.setPublisherId(resultSet.getInt("publisherId"));
			publisher.setPublisherName(resultSet.getString("publisherName"));
			@SuppressWarnings("unchecked")
			List<Book> books = (List<Book>) bookDAO.readFirstLevel("select * from tbl_book_genres where genre_id=?", new Object[] {resultSet.getInt("genre_id")});
			publisher.setBooks(books);
			publishers.add(publisher);
		}
		return publishers;
	}

	@Override
	public List<Publisher> extractDataFirstLevel(ResultSet resultSet) throws Exception {
		List<Publisher> publishers =  new ArrayList<Publisher>();
		while (resultSet.next()){
			Publisher publisher = new Publisher();
			publisher.setPublisherId(resultSet.getInt("publisherId"));
			publisher.setPublisherName(resultSet.getString("publisherName"));
			publishers.add(publisher);
		}
		return publishers;
	}
}
