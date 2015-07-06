package com.gcit.lms.service;

import java.sql.Connection;

import com.gcit.lms.dao.BookCopyDAO;
import com.gcit.lms.dao.BookDAO;
import com.gcit.lms.dao.BranchDAO;
import com.gcit.lms.domain.BookCopy;
import com.gcit.lms.domain.Branch;

public class LibrarianService {
	public void updateBookCopy(BookCopy bookCopy, int noOfCopies) throws Exception {
		Connection connection = ConnectionUtil.createConnection();
		try {
			BookDAO bookDAO = new BookDAO(connection);
			BranchDAO branchDAO = new BranchDAO(connection);
			if (bookCopy == null || bookDAO.readOne(bookCopy.getBook().getBookId()) == null) {
				throw new Exception("Book does not exist.");
			} 
			else if (branchDAO.readOne(bookCopy.getBranch().getBranchId()) == null) {
				throw new Exception("Branch does not exist.");
			}
			else if (noOfCopies < 0) {
				throw new Exception("Number of copies is out of range.");
			}
			else {
				BookCopyDAO bookCopyDAO = new BookCopyDAO(connection);
				bookCopy.setNoOfCopies(noOfCopies);
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
	
	public void updateBranch(Branch branch) throws Exception {
		Connection connection = ConnectionUtil.createConnection();
		try {
			BranchDAO branchDAO = new BranchDAO(connection);
			if (branch == null || branch.getBranchName() == null
					|| branch.getBranchName().length() == 0
					|| branch.getBranchName().length() > 45) {
				throw new Exception("Branch name cannot be empty or more than 45 characters.");
			}
			else if (branch.getBranchAddress() == null
					|| branch.getBranchAddress().length() == 0
					|| branch.getBranchAddress().length() > 45) {
				throw new Exception("Branch address cannot be empty or more than 45 characters.");
			}
			else if (branchDAO.readOne(branch.getBranchId()) == null) {
				throw new Exception("Branch with this id does not exist.");
			}
			else {
				branchDAO.update(branch);
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
