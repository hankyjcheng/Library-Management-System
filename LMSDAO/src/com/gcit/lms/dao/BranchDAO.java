package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.domain.BookCopy;
import com.gcit.lms.domain.BookLoan;
import com.gcit.lms.domain.Branch;

public class BranchDAO extends BaseDAO<Branch> {
	
	public BranchDAO(Connection connection) throws Exception {
		super(connection);
	}
	
	public void create(Branch branch) throws Exception {
		save("INSERT INTO tbL_library_branch (branchName) VALUES(?)", new Object[] { branch.getBranchName() });
	}

	public void update(Branch branch) throws Exception {
		save("UPDATE tbl_library_branch SET branchName=? WHERE branchId=?", new Object[] { branch.getBranchId() });

	}

	public void delete(Branch branch) throws Exception {
		save("DELETE FROM tbl_library_branch WHERE branchId=?",
				new Object[] { branch.getBranchId() });
	}

	@SuppressWarnings("unchecked")
	public List<Branch> readAll() throws Exception{
		return (List<Branch>) read("SELECT * FROM tbl_library_branch", null);
	}

	public Branch readOne(int branchId) throws Exception {
		@SuppressWarnings("unchecked")
		List<Branch> branches = (List<Branch>) read("SELECT * FROM tbl_library_branch", new Object[] {branchId});
		if (branches != null && branches.size() > 0){
			return branches.get(0);
		}
		return null;
	}

	@Override
	public List<Branch> extractData(ResultSet resultSet) throws Exception {
		List<Branch> branches =  new ArrayList<Branch>();
		BookLoanDAO bookLoanDAO = new BookLoanDAO(getConnection());
		BookCopyDAO bookCopyDAO = new BookCopyDAO(getConnection());
		while (resultSet.next()){
			Branch branch = new Branch();
			branch.setBranchId(resultSet.getInt("branchId"));
			branch.setBranchName(resultSet.getString("branchName"));
			branch.setBranchAddress(resultSet.getString("branchAddress"));
			@SuppressWarnings("unchecked")
			List<BookLoan> bookLoans = (List<BookLoan>) bookLoanDAO.readFirstLevel("select * from tbl_book_loans where branchId=?", new Object[] {resultSet.getInt("branchId")});
			branch.setBookLoans(bookLoans);
			@SuppressWarnings("unchecked")
			List<BookCopy> bookCopies = (List<BookCopy>) bookCopyDAO.readFirstLevel("select * from tbl_book_copies where branchId=?", new Object[] {resultSet.getInt("branchId")});
			branch.setBookCopies(bookCopies);
			branches.add(branch);
		}
		return branches;
	}

	@Override
	public List<Branch> extractDataFirstLevel(ResultSet resultSet) throws Exception {
		List<Branch> branches =  new ArrayList<Branch>();
		while (resultSet.next()){
			Branch branch = new Branch();
			branch.setBranchId(resultSet.getInt("branchId"));
			branch.setBranchName(resultSet.getString("branchName"));
			branch.setBranchAddress(resultSet.getString("branchAddress"));
			branches.add(branch);
		}
		return branches;
	}
}
