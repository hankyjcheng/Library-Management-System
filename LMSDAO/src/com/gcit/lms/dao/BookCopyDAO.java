package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.domain.BookCopy;

public class BookCopyDAO extends BaseDAO<BookCopy> {
	public BookCopyDAO(Connection connection) throws Exception {
		super(connection);
	}

	public void create(BookCopy bookCopy) throws Exception {
		save("INSERT INTO tbl_book_copies VALUES (?,?,?)", new Object[] {
				bookCopy.getBook().getBookId(),
				bookCopy.getBranch().getBranchId(), bookCopy.getNoOfCopies() });
	}

	public void update(BookCopy bookCopy) throws Exception {
		save("UPDATE tbl_book_copies SET noOfCopies=? WHERE bookId=? AND branchId=?",
				new Object[] { bookCopy.getNoOfCopies(),
						bookCopy.getBook().getBookId(),
						bookCopy.getBranch().getBranchId() });

	}

	public void delete(BookCopy bookCopy) throws Exception {
		save("DELETE FROM tbl_book_copies WHERE bookId=? AND branchId=?",
				new Object[] { bookCopy.getBook().getBookId(),
						bookCopy.getBranch().getBranchId() });
	}

	@SuppressWarnings("unchecked")
	public List<BookCopy> readAll() throws Exception {
		return (List<BookCopy>) read("SELECT * FROM tbl_book_copies", null);
	}

	public BookCopy readOne(int bookId, int branchId)
			throws Exception {
		@SuppressWarnings("unchecked")
		List<BookCopy> bookCopies = (List<BookCopy>) read(
				"SELECT * FROM tbl_book_copies", new Object[] { bookId,
						branchId });
		if (bookCopies != null && bookCopies.size() > 0) {
			return bookCopies.get(0);
		}
		return null;
	}

	@Override
	public List<BookCopy> extractData(ResultSet resultSet) throws Exception {
		List<BookCopy> bookCopies = new ArrayList<BookCopy>();
		BookDAO bookDAO = new BookDAO(getConnection());
		BranchDAO branchDAO = new BranchDAO(getConnection());

		while (resultSet.next()) {
			BookCopy bookCopy = new BookCopy();
			bookCopy.setNoOfCopies(resultSet.getInt("noOfCopies"));
			bookCopy.setBook(bookDAO.readOne(resultSet.getInt("bookId")));
			bookCopy.setBranch(branchDAO.readOne(resultSet.getInt("branchId")));
			bookCopies.add(bookCopy);
		}
		return bookCopies;
	}

	@Override
	public List<BookCopy> extractDataFirstLevel(ResultSet resultSet)
			throws Exception {
		List<BookCopy> bookCopies = new ArrayList<BookCopy>();
		while (resultSet.next()) {
			BookCopy bookCopy = new BookCopy();
			bookCopy.setNoOfCopies(resultSet.getInt("noOfCopies"));
			bookCopies.add(bookCopy);
		}
		return bookCopies;
	}
}
