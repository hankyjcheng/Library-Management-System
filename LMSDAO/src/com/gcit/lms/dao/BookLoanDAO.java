package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.domain.BookLoan;

public class BookLoanDAO extends BaseDAO<BookLoan> {

	public BookLoanDAO(Connection connection) throws Exception {
		super(connection);
	}

	public void create(BookLoan bookLoan) throws Exception {
		save("INSERT INTO tbl_book_loans (bookId, branchId, cardNo, dateOut, dueDate) VALUES (?,?,?,?,?)",
				new Object[] { bookLoan.getBook().getBookId(),
						bookLoan.getBranch().getBranchId(),
						bookLoan.getBorrower().getCardNo(),
						bookLoan.getDateOut(), bookLoan.getDueDate() });
	}

	public void update(BookLoan bookLoan) throws Exception {
		save("UPDATE tbl_book_loans SET dateOut=?, dueDate=?, dateIn=? WHERE bookId=? AND branchId=? AND cardNo=?",
				new Object[] { bookLoan.getDateOut(), bookLoan.getDueDate(),
						bookLoan.getDateIn(), bookLoan.getBook().getBookId(),
						bookLoan.getBranch().getBranchId(),
						bookLoan.getBorrower().getCardNo() });

	}

	public void delete(BookLoan bookLoan) throws Exception {
		save("DELETE FROM tbl_book_loans WHERE bookId=? AND branchId=? AND cardNo=?",
				new Object[] { bookLoan.getBook().getBookId(),
						bookLoan.getBranch().getBranchId(),
						bookLoan.getBorrower().getCardNo() });
	}

	@SuppressWarnings("unchecked")
	public List<BookLoan> readAll() throws Exception {
		return (List<BookLoan>) read("SELECT * FROM tbl_book_loans", null);
	}

	public BookLoan readOne(int bookId, int branchId, int cardNo)
			throws Exception {
		@SuppressWarnings("unchecked")
		List<BookLoan> bookLoans = (List<BookLoan>) read(
				"SELECT * FROM tbl_book_loans", new Object[] { bookId,
						branchId, cardNo });
		if (bookLoans != null && bookLoans.size() > 0) {
			return bookLoans.get(0);
		}
		return null;
	}

	@Override
	public List<BookLoan> extractData(ResultSet resultSet) throws Exception {
		List<BookLoan> bookLoans = new ArrayList<BookLoan>();
		BookDAO bookDAO = new BookDAO(getConnection());
		BranchDAO branchDAO = new BranchDAO(getConnection());
		BorrowerDAO borrowerDAO = new BorrowerDAO(getConnection());

		while (resultSet.next()) {
			BookLoan bookLoan = new BookLoan();
			bookLoan.setDateOut(resultSet.getString("dateOut"));
			bookLoan.setDueDate(resultSet.getString("dueDate"));
			bookLoan.setDateIn(resultSet.getString("dateIn"));
			bookLoan.setBook(bookDAO.readOne(resultSet.getInt("bookId")));
			bookLoan.setBranch(branchDAO.readOne(resultSet.getInt("branchId")));
			bookLoan.setBorrower(borrowerDAO.readOne(resultSet.getInt("cardNo")));
			bookLoans.add(bookLoan);
		}
		return bookLoans;
	}

	@Override
	public List<BookLoan> extractDataFirstLevel(ResultSet resultSet)
			throws Exception {
		List<BookLoan> bookLoans = new ArrayList<BookLoan>();
		while (resultSet.next()) {
			BookLoan bookLoan = new BookLoan();
			bookLoan.setDateOut(resultSet.getString("dateOut"));
			bookLoan.setDueDate(resultSet.getString("dueDate"));
			bookLoan.setDateIn(resultSet.getString("dateIn"));
			bookLoans.add(bookLoan);
		}
		return bookLoans;
	}
}
