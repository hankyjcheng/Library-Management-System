package com.gcit.lms.service;

import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.gcit.lms.dao.BookCopyDAO;
import com.gcit.lms.dao.BookDAO;
import com.gcit.lms.dao.BookLoanDAO;
import com.gcit.lms.dao.BorrowerDAO;
import com.gcit.lms.dao.BranchDAO;
import com.gcit.lms.domain.BookCopy;
import com.gcit.lms.domain.BookLoan;

public class BorrowerService {
	
	public void bookCheckOut(BookLoan bookLoan) throws Exception {
		Connection connection = ConnectionUtil.createConnection();
		try {
			BookDAO bookDAO = new BookDAO(connection);
			BranchDAO branchDAO = new BranchDAO(connection);
			BorrowerDAO borrowerDAO = new BorrowerDAO(connection);
			if (bookLoan == null || bookDAO.readOne(bookLoan.getBook().getBookId()) == null) {
				throw new Exception("Book does not exist.");
			} 
			else if (branchDAO.readOne(bookLoan.getBranch().getBranchId()) == null) {
				throw new Exception("Branch does not exist.");
			}
			else if (borrowerDAO.readOne(bookLoan.getBorrower().getCardNo()) == null) {
				throw new Exception("Borrower does not exist.");
			}
			else {
				BookLoanDAO bookLoanDAO = new BookLoanDAO(connection);
				bookLoanDAO.create(bookLoan);
				BookCopyDAO bookCopyDAO = new BookCopyDAO(connection);
				BookCopy bookCopy = bookCopyDAO.readOne(bookLoan.getBook().getBookId(), bookLoan.getBranch().getBranchId());
				bookCopy.setNoOfCopies(bookCopy.getNoOfCopies() - 1);
				bookCopyDAO.update(bookCopy);
				connection.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
			connection.rollback();
		} finally {
			connection.close();
		}
	}
	
	public void bookReturn(BookLoan bookLoan) throws Exception {
		Connection connection = ConnectionUtil.createConnection();
		try {
			BookDAO bookDAO = new BookDAO(connection);
			BranchDAO branchDAO = new BranchDAO(connection);
			BorrowerDAO borrowerDAO = new BorrowerDAO(connection);
			if (bookLoan == null || bookDAO.readOne(bookLoan.getBook().getBookId()) == null) {
				throw new Exception("Book does not exist.");
			} 
			else if (branchDAO.readOne(bookLoan.getBranch().getBranchId()) == null) {
				throw new Exception("Branch does not exist.");
			}
			else if (borrowerDAO.readOne(bookLoan.getBorrower().getCardNo()) == null) {
				throw new Exception("Borrower does not exist.");
			}
			else {
				BookLoanDAO bookLoanDAO = new BookLoanDAO(connection);
				DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
			    Date date = new Date();
				bookLoan.setDateIn(dateformat.format(date));
				bookLoanDAO.update(bookLoan);
				BookCopyDAO bookCopyDAO = new BookCopyDAO(connection);
				BookCopy bookCopy = bookCopyDAO.readOne(bookLoan.getBook().getBookId(), bookLoan.getBranch().getBranchId());
				bookCopy.setNoOfCopies(bookCopy.getNoOfCopies() + 1);
				bookCopyDAO.update(bookCopy);
				connection.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
			connection.rollback();
		} finally {
			connection.close();
		}
	}
}
