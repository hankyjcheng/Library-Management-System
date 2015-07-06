package com.gcit.lms.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.dao.AuthorDAO;
import com.gcit.lms.dao.BookCopyDAO;
import com.gcit.lms.dao.BookDAO;
import com.gcit.lms.dao.BookLoanDAO;
import com.gcit.lms.dao.BorrowerDAO;
import com.gcit.lms.dao.BranchDAO;
import com.gcit.lms.dao.GenreDAO;
import com.gcit.lms.dao.PublisherDAO;
import com.gcit.lms.domain.Author;
import com.gcit.lms.domain.Book;
import com.gcit.lms.domain.BookCopy;
import com.gcit.lms.domain.BookLoan;
import com.gcit.lms.domain.Borrower;
import com.gcit.lms.domain.Branch;
import com.gcit.lms.domain.Genre;
import com.gcit.lms.domain.Publisher;

public class AdministrativeService {

	public void createAuthor(Author author) throws Exception {
		Connection connection = ConnectionUtil.createConnection();
		try {
			if (author == null || author.getAuthorName() == null
					|| author.getAuthorName().length() == 0
					|| author.getAuthorName().length() > 45) {
				throw new Exception("Author name cannot be empty or more than 45 characters.");
			} 
			else {
				AuthorDAO authorDAO = new AuthorDAO(connection);
				authorDAO.create(author);
				connection.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
			connection.rollback();
		} finally {
			connection.close();
		}
	}
	
	public void updateAuthor(Author author) throws Exception {
		Connection connection = ConnectionUtil.createConnection();
		try {
			AuthorDAO authorDAO = new AuthorDAO(connection);
			if (author == null || author.getAuthorName() == null
					|| author.getAuthorName().length() == 0
					|| author.getAuthorName().length() > 45) {
				throw new Exception("Author name cannot be empty or more than 45 characters.");
			} 
			else if (authorDAO.readOne(author.getAuthorId()) == null) {
				throw new Exception("The author with this ID does not exist.");
			}
			else {
				authorDAO.update(author);
				connection.commit();
			}	
		} catch (Exception e) {
			e.printStackTrace();
			connection.rollback();
		} finally {
			connection.close();
		}
	}
	
	public void deleteAuthor(Author author) throws Exception {
		Connection connection = ConnectionUtil.createConnection();
		try {
			AuthorDAO authorDAO = new AuthorDAO(connection);
			if (author == null || authorDAO.readOne(author.getAuthorId()) == null) {
				throw new Exception("The author with this ID does not exist.");
			} 
			else {
				authorDAO.delete(author);
				connection.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
			connection.rollback();
		} finally {
			connection.close();
		}
	}
	
	public List<Author> readAllAuthors() throws Exception {
		Connection connection = ConnectionUtil.createConnection();
		try {
			AuthorDAO authorDAO = new AuthorDAO(connection);
			List<Author> authors = new ArrayList<Author>();
			authors = authorDAO.readAll();
			connection.commit();
			return authors;
		} catch (Exception e) {
			e.printStackTrace();
			connection.rollback();
			return null;
		} finally {
			connection.close();
		}
	}
	
	public Author readOneAuthor(int authorId) throws Exception {
		Connection connection = ConnectionUtil.createConnection();
		try {
			AuthorDAO authorDAO = new AuthorDAO(connection);
			Author author = new Author();
			author = authorDAO.readOne(authorId);
			connection.commit();
			return author;
		} catch (Exception e) {
			e.printStackTrace();
			connection.rollback();
			return null;
		} finally {
			connection.close();
		}
	}
	
	public void createGenre(Genre genre) throws Exception {
		Connection connection = ConnectionUtil.createConnection();
		try {
			if (genre == null || genre.getGenreName() == null
					|| genre.getGenreName().length() == 0
					|| genre.getGenreName().length() > 45) {
				throw new Exception("Genre name cannot be empty or more than 45 characters.");
			} 
			else {
				GenreDAO genreDAO = new GenreDAO(connection);
				genreDAO.create(genre);
				connection.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
			connection.rollback();
		} finally {
			connection.close();
		}
	}
	
	public void updateGenre(Genre genre) throws Exception {
		Connection connection = ConnectionUtil.createConnection();
		try {
			GenreDAO genreDAO = new GenreDAO(connection);
			if (genre == null || genre.getGenreName() == null
					|| genre.getGenreName().length() == 0
					|| genre.getGenreName().length() > 45) {
				throw new Exception("Genre name cannot be empty or more than 45 characters.");
			} 
			else if (genreDAO.readOne(genre.getGenreId()) == null) {
				throw new Exception("The genre with this ID does not exist.");
			}
			else {
				genreDAO.update(genre);
				connection.commit();
			}	
		} catch (Exception e) {
			e.printStackTrace();
			connection.rollback();
		} finally {
			connection.close();
		}
	}
	
	public void deleteGenre(Genre genre) throws Exception {
		Connection connection = ConnectionUtil.createConnection();
		try {
			GenreDAO genreDAO = new GenreDAO(connection);
			if (genre == null || genreDAO.readOne(genre.getGenreId()) == null) {
				throw new Exception("The genre with this ID does not exist.");
			} 
			else {
				genreDAO.delete(genre);
				connection.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
			connection.rollback();
		} finally {
			connection.close();
		}
	}
	
	public List<Genre> readAllGenres() throws Exception {
		Connection connection = ConnectionUtil.createConnection();
		try {
			GenreDAO genreDAO = new GenreDAO(connection);
			List<Genre> genres = new ArrayList<Genre>();
			genres = genreDAO.readAll();
			connection.commit();
			return genres;
		} catch (Exception e) {
			e.printStackTrace();
			connection.rollback();
			return null;
		} finally {
			connection.close();
		}
	}
	
	public Genre readOneGenre(int genreId) throws Exception {
		Connection connection = ConnectionUtil.createConnection();
		try {
			GenreDAO genreDAO = new GenreDAO(connection);
			Genre genre = new Genre();
			genre = genreDAO.readOne(genreId);
			connection.commit();
			return genre;
		} catch (Exception e) {
			e.printStackTrace();
			connection.rollback();
			return null;
		} finally {
			connection.close();
		}
	}
	
	public void createPublisher(Publisher publisher) throws Exception {
		Connection connection = ConnectionUtil.createConnection();
		try {
			if (publisher == null || publisher.getPublisherName() == null
					|| publisher.getPublisherName().length() == 0
					|| publisher.getPublisherName().length() > 45) {
				throw new Exception("Publisher name cannot be empty or more than 45 characters.");
			}
			else if (publisher.getPublisherAddress() == null
					|| publisher.getPublisherAddress().length() == 0
					|| publisher.getPublisherAddress().length() > 45) {
				throw new Exception("Publisher address cannot be empty or more than 45 characters.");
			} 
			else if (publisher.getPublisherPhone() == null
					|| publisher.getPublisherPhone().length() == 0
					|| publisher.getPublisherPhone().length() > 45) {
				throw new Exception("Publisher phone cannot be empty or more than 45 characters.");
			} 
			else {
				PublisherDAO publisherDAO = new PublisherDAO(connection);
				publisherDAO.create(publisher);
				connection.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
			connection.rollback();
		} finally {
			connection.close();
		}
	}
	
	public void updatePublisher(Publisher publisher) throws Exception {
		Connection connection = ConnectionUtil.createConnection();
		try {
			PublisherDAO publisherDAO = new PublisherDAO(connection);
			if (publisher == null || publisher.getPublisherName() == null
					|| publisher.getPublisherName().length() == 0
					|| publisher.getPublisherName().length() > 45) {
				throw new Exception("Publisher name cannot be empty or more than 45 characters.");
			}
			else if (publisher.getPublisherAddress() == null
					|| publisher.getPublisherAddress().length() == 0
					|| publisher.getPublisherAddress().length() > 45) {
				throw new Exception("Publisher address cannot be empty or more than 45 characters.");
			} 
			else if (publisher.getPublisherPhone() == null
					|| publisher.getPublisherPhone().length() == 0
					|| publisher.getPublisherPhone().length() > 45) {
				throw new Exception("Publisher phone cannot be empty or more than 45 characters.");
			} 
			else if (publisherDAO.readOne(publisher.getPublisherId()) == null){
				throw new Exception("The publisher with this ID does not exist.");
			}
			else {	
				publisherDAO.update(publisher);
				connection.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
			connection.rollback();
		} finally {
			connection.close();
		}
	}
	
	public void deletePublisher(Publisher publisher) throws Exception {
		Connection connection = ConnectionUtil.createConnection();
		try {
			PublisherDAO publisherDAO = new PublisherDAO(connection);
			if (publisher == null) {
				throw new Exception("Publisher must exist.");
			}
			else if (publisherDAO.readOne(publisher.getPublisherId()) == null) {
				throw new Exception("The publisher with this ID does not exist.");
			}
			else {
				publisherDAO.delete(publisher);
				connection.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
			connection.rollback();
		} finally {
			connection.close();
		}
	}
	
	public List<Publisher> readAllPublishers() throws Exception {
		Connection connection = ConnectionUtil.createConnection();
		try {
			PublisherDAO publisherDAO = new PublisherDAO(connection);
			List<Publisher> publishers = new ArrayList<Publisher>();
			publishers = publisherDAO.readAll();
			connection.commit();
			return publishers;
		} catch (Exception e) {
			e.printStackTrace();
			connection.rollback();
			return null;
		} finally {
			connection.close();
		}
	}
	
	public Publisher readOnePublisher(int pubId) throws Exception {
		Connection connection = ConnectionUtil.createConnection();
		try {
			PublisherDAO publisherDAO = new PublisherDAO(connection);
			Publisher publisher = new Publisher();
			publisher = publisherDAO.readOne(pubId);
			connection.commit();
			return publisher;
		} catch (Exception e) {
			e.printStackTrace();
			connection.rollback();
			return null;
		} finally {
			connection.close();
		}
	}
	
	public void createBook(Book book, BookCopy bookCopy) throws Exception {
		Connection connection = ConnectionUtil.createConnection();
		try {
			if (book == null || book.getTitle() == null
					|| book.getTitle().length() == 0
					|| book.getTitle().length() > 45) {
				throw new Exception("Book title cannot be empty or more than 45 characters.");
			}
			else {
				BookDAO bookDAO = new BookDAO(connection);
				BookCopyDAO bookCopyDAO = new BookCopyDAO(connection);
				bookDAO.create(book);
				bookCopy.setBook(book);
				bookCopyDAO.create(bookCopy);
				connection.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
			connection.rollback();
		} finally {
			connection.close();
		}
	}
	
	public void updateBook(Book book) throws Exception {
		Connection connection = ConnectionUtil.createConnection();
		try {
			BookDAO bookDAO = new BookDAO(connection);
			if (book == null || book.getTitle() == null
					|| book.getTitle().length() == 0
					|| book.getTitle().length() > 45) {
				throw new Exception("Book title cannot be empty or more than 45 characters.");
			} 
			else if (bookDAO.readOne(book.getBookId()) == null) {
				throw new Exception("Book with this id does not exist.");
			}
			else {
				bookDAO.update(book);
				connection.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
			connection.rollback();
		} finally {
			connection.close();
		}
	}
	
	public void deleteBook(Book book) throws Exception {
		Connection connection = ConnectionUtil.createConnection();
		try {
			BookDAO bookDAO = new BookDAO(connection);
			if (book == null || bookDAO.readOne(book.getBookId()) == null) {
				throw new Exception("Book with this id does not exist.");
			}
			else {
				bookDAO.delete(book);
				connection.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
			connection.rollback();
		} finally {
			connection.close();
		}
	}
	
	public List<Book> readAllBooks() throws Exception {
		Connection connection = ConnectionUtil.createConnection();
		try {
			BookDAO bookDAO = new BookDAO(connection);
			List<Book> books = new ArrayList<Book>();
			books = bookDAO.readAll();
			connection.commit();
			return books;
		} catch (Exception e) {
			e.printStackTrace();
			connection.rollback();
			return null;
		} finally {
			connection.close();
		}
	}
	
	public Book readOneBook(int bookId) throws Exception {
		Connection connection = ConnectionUtil.createConnection();
		try {
			BookDAO bookDAO = new BookDAO(connection);
			Book book = new Book();
			book = bookDAO.readOne(bookId);
			connection.commit();
			return book;
		} catch (Exception e) {
			e.printStackTrace();
			connection.rollback();
			return null;
		} finally {
			connection.close();
		}
	}
	
	public void createBorrower(Borrower borrower) throws Exception {
		Connection connection = ConnectionUtil.createConnection();
		try {
			if (borrower == null || borrower.getName() == null
					|| borrower.getName().length() == 0
					|| borrower.getName().length() > 45) {
				throw new Exception("Borrower name cannot be empty or more than 45 characters.");
			}
			else if (borrower.getAddress() == null
					|| borrower.getAddress().length() == 0
					|| borrower.getAddress().length() > 45) {
				throw new Exception("Borrower address cannot be empty or more than 45 characters.");
			}
			else if (borrower.getPhone() == null
					|| borrower.getPhone().length() == 0
					|| borrower.getPhone().length() > 45) {
				throw new Exception("Borrower phone cannot be empty or more than 45 characters.");
			}
			else {
				BorrowerDAO borrowerDAO = new BorrowerDAO(connection);
				borrowerDAO.create(borrower);
				connection.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
			connection.rollback();
		} finally {
			connection.close();
		}
	}
	
	public void updateBorrower(Borrower borrower) throws Exception {
		Connection connection = ConnectionUtil.createConnection();
		try {
			BorrowerDAO borrowerDAO = new BorrowerDAO(connection);
			if (borrower == null || borrower.getName() == null
					|| borrower.getName().length() == 0
					|| borrower.getName().length() > 45) {
				throw new Exception("Borrower name cannot be empty or more than 45 characters.");
			}
			else if (borrower == null || borrower.getAddress() == null
					|| borrower.getAddress().length() == 0
					|| borrower.getAddress().length() > 45) {
				throw new Exception("Borrower address cannot be empty or more than 45 characters.");
			}
			else if (borrower == null || borrower.getPhone() == null
					|| borrower.getPhone().length() == 0
					|| borrower.getPhone().length() > 45) {
				throw new Exception("Borrower phone cannot be empty or more than 45 characters.");
			}
			else if (borrowerDAO.readOne(borrower.getCardNo()) == null) {
				throw new Exception("Borrower with this card number does not exist.");
			}
			else {
				borrowerDAO.update(borrower);
				connection.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
			connection.rollback();
		} finally {
			connection.close();
		}
	}
	
	public void deleteBorrower(Borrower borrower) throws Exception {
		Connection connection = ConnectionUtil.createConnection();
		try {
			BorrowerDAO borrowerDAO = new BorrowerDAO(connection);
			if (borrower == null || borrowerDAO.readOne(borrower.getCardNo()) == null) {
				throw new Exception("Borrower with this card number does not exist.");
			}
			else {	
				borrowerDAO.delete(borrower);
				connection.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
			connection.rollback();
		} finally {
			connection.close();
		}
	}
	
	public List<Borrower> readAllBorrowers() throws Exception {
		Connection connection = ConnectionUtil.createConnection();
		try {
			BorrowerDAO borrowerDAO = new BorrowerDAO(connection);
			List<Borrower> borrowers = new ArrayList<Borrower>();
			borrowers = borrowerDAO.readAll();
			connection.commit();
			return borrowers;
		} catch (Exception e) {
			e.printStackTrace();
			connection.rollback();
			return null;
		} finally {
			connection.close();
		}
	}
	
	public Borrower readOneBorrower(int cardNo) throws Exception {
		Connection connection = ConnectionUtil.createConnection();
		try {
			BorrowerDAO borrowerDAO = new BorrowerDAO(connection);
			Borrower borrower = new Borrower();
			borrower = borrowerDAO.readOne(cardNo);
			connection.commit();
			return borrower;
		} catch (Exception e) {
			e.printStackTrace();
			connection.rollback();
			return null;
		} finally {
			connection.close();
		}
	}
	
	public void createBranch(Branch branch) throws Exception {
		Connection connection = ConnectionUtil.createConnection();
		try {
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
			else {
				BranchDAO branchDAO = new BranchDAO(connection);
				branchDAO.create(branch);
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
	
	public void deleteBranch(Branch branch) throws Exception {
		Connection connection = ConnectionUtil.createConnection();
		try {
			BranchDAO branchDAO = new BranchDAO(connection);
			if (branch == null || branchDAO.readOne(branch.getBranchId()) == null) {
				throw new Exception("Branch with this id does not exist.");
			}
			else {
				branchDAO.delete(branch);
				connection.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
			connection.rollback();
		} finally {
			connection.close();
		}
	}
	
	public List<Branch> readAllBranches() throws Exception {
		Connection connection = ConnectionUtil.createConnection();
		try {
			BranchDAO branchDAO = new BranchDAO(connection);
			List<Branch> branches = new ArrayList<Branch>();
			branches = branchDAO.readAll();
			connection.commit();
			return branches;
		} catch (Exception e) {
			e.printStackTrace();
			connection.rollback();
			return null;
		} finally {
			connection.close();
		}
	}
	
	public Branch readOneBranch(int branchId) throws Exception {
		Connection connection = ConnectionUtil.createConnection();
		try {
			BranchDAO branchDAO = new BranchDAO(connection);
			Branch branch = new Branch();
			branch = branchDAO.readOne(branchId);
			connection.commit();
			return branch;
		} catch (Exception e) {
			e.printStackTrace();
			connection.rollback();
			return null;
		} finally {
			connection.close();
		}
	}
	
	public void updateBookLoan(BookLoan bookLoan) throws Exception {
		Connection connection = ConnectionUtil.createConnection();
		try {
			BookLoanDAO bookLoanDAO = new BookLoanDAO(connection);
			if (bookLoan == null || bookLoan.getDueDate() == null
					|| bookLoan.getDueDate().length() == 0
					|| bookLoan.getDueDate().length() > 45) {
				throw new Exception("Due date cannot be empty or more than 45 characters.");
			}
			else if (bookLoanDAO.readOne(bookLoan.getBook().getBookId(), bookLoan.getBranch().getBranchId(), bookLoan.getBorrower().getCardNo()) == null) {
				throw new Exception("Book loan record does not exist.");
			}
			else if (bookLoan.getDateOut() == null
					|| bookLoan.getDateOut() != bookLoanDAO.readOne(bookLoan.getBook().getBookId(), bookLoan.getBranch().getBranchId(), bookLoan.getBorrower().getCardNo()).getDateOut()) {
				throw new Exception("Date out cannot be changed by administrator.");
			}
			else if (bookLoan.getDateIn() != bookLoanDAO.readOne(bookLoan.getBook().getBookId(), bookLoan.getBranch().getBranchId(), bookLoan.getBorrower().getCardNo()).getDateIn()) {
				throw new Exception("Date in cannot be changed by administrator.");
			}
			else {
				bookLoanDAO.update(bookLoan);
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
