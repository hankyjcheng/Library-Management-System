package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.domain.BookLoan;
import com.gcit.lms.domain.Borrower;

public class BorrowerDAO extends BaseDAO<Borrower> {
	
	public BorrowerDAO(Connection connection) throws Exception {
		super(connection);
	}
	
	public void create(Borrower borrower) throws Exception {
		save("INSERT INTO tbL_borrower (name) VALUES(?)", new Object[] { borrower.getName() });
	}

	public void update(Borrower borrower) throws Exception {
		save("UPDATE tbl_borrower SET name=? WHERE bookId=?", new Object[] { borrower.getCardNo() });

	}

	public void delete(Borrower borrower) throws Exception {
		save("DELETE FROM tbl_borrower WHERE cardNo=?",
				new Object[] { borrower.getCardNo() });
	}

	@SuppressWarnings("unchecked")
	public List<Borrower> readAll() throws Exception{
		return (List<Borrower>) read("SELECT * FROM tbl_borrower", null);
	}

	public Borrower readOne(int bookId) throws Exception {
		@SuppressWarnings("unchecked")
		List<Borrower> borrowers = (List<Borrower>) read("SELECT * FROM tbl_borrower", new Object[] {bookId});
		if (borrowers != null && borrowers.size() > 0){
			return borrowers.get(0);
		}
		return null;
	}

	@Override
	public List<Borrower> extractData(ResultSet resultSet) throws Exception {
		List<Borrower> borrowers =  new ArrayList<Borrower>();
		BookLoanDAO bookLoanDAO = new BookLoanDAO(getConnection());
		
		while (resultSet.next()){
			Borrower borrower = new Borrower();
			borrower.setCardNo(resultSet.getInt("cardNo"));
			borrower.setName(resultSet.getString("name"));
			@SuppressWarnings("unchecked")
			List<BookLoan> bookLoans = (List<BookLoan>) bookLoanDAO.readFirstLevel("select * from tbl_book_loans where cardNo=?", new Object[] {resultSet.getInt("cardNo")});
			borrower.setBookLoans(bookLoans);
			borrowers.add(borrower);
		}
		return borrowers;
	}

	@Override
	public List<Borrower> extractDataFirstLevel(ResultSet resultSet) throws Exception {
		List<Borrower> borrowers =  new ArrayList<Borrower>();	
		while(resultSet.next()){
			Borrower borrower = new Borrower();
			borrower.setCardNo(resultSet.getInt("cardNo"));
			borrower.setName(resultSet.getString("name"));
			borrowers.add(borrower);
		}
		return borrowers;
	}
}
