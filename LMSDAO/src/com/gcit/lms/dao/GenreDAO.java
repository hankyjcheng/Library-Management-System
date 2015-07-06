package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.domain.Book;
import com.gcit.lms.domain.Genre;

public class GenreDAO extends BaseDAO<Genre> {
	
	public GenreDAO(Connection connection) throws Exception {
		super(connection);
	}
	
	public void create(Genre genre) throws Exception {
		save("INSERT INTO tbL_genre (genre_name) VALUES(?)", new Object[] { genre.getGenreName() });
	}

	public void update(Genre genre) throws Exception {
		save("UPDATE tbl_genre SET genre_name=? WHERE genre_id=?", new Object[] { genre.getGenreId() });

	}

	public void delete(Genre genre) throws Exception {
		save("DELETE FROM tbl_genre WHERE genre_id=?",
				new Object[] { genre.getGenreId() });
	}

	@SuppressWarnings("unchecked")
	public List<Genre> readAll() throws Exception{
		return (List<Genre>) read("SELECT * FROM tbl_genre", null);
	}

	public Genre readOne(int genreId) throws Exception {
		@SuppressWarnings("unchecked")
		List<Genre> genres = (List<Genre>) read("SELECT * FROM tbl_genre", new Object[] {genreId});
		if (genres != null && genres.size() > 0){
			return genres.get(0);
		}
		return null;
	}

	@Override
	public List<Genre> extractData(ResultSet resultSet) throws Exception {
		List<Genre> genres =  new ArrayList<Genre>();
		BookDAO bookDAO = new BookDAO(getConnection());
		while (resultSet.next()){
			Genre genre = new Genre();
			genre.setGenreId(resultSet.getInt("genre_id"));
			genre.setGenreName(resultSet.getString("genre_name"));
			@SuppressWarnings("unchecked")
			List<Book> books = (List<Book>) bookDAO.readFirstLevel("select * from tbl_book_genres where genre_id=?", new Object[] {resultSet.getInt("genre_id")});
			genre.setBooks(books);
			genres.add(genre);
		}
		return genres;
	}

	@Override
	public List<Genre> extractDataFirstLevel(ResultSet resultSet) throws Exception {
		List<Genre> genres =  new ArrayList<Genre>();
		while (resultSet.next()){
			Genre genre = new Genre();
			genre.setGenreId(resultSet.getInt("genre_id"));
			genre.setGenreName(resultSet.getString("genre_name"));
			genres.add(genre);
		}
		return genres;
	}
}
