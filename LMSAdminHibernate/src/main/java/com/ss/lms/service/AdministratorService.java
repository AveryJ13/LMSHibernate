package com.ss.lms.service;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ss.lms.entity.Author;
import com.ss.lms.entity.Book;
import com.ss.lms.entity.Borrower;
import com.ss.lms.entity.Branch;
import com.ss.lms.entity.Genre;
import com.ss.lms.entity.Loans;
import com.ss.lms.entity.LoansId;
import com.ss.lms.entity.Publisher;
import com.ss.lms.repo.AuthorRepo;
import com.ss.lms.repo.BookRepo;
import com.ss.lms.repo.BorrowerRepo;
import com.ss.lms.repo.BranchRepo;
import com.ss.lms.repo.GenreRepo;
import com.ss.lms.repo.LoansRepo;
import com.ss.lms.repo.PublisherRepo;

@RestController
public class AdministratorService {
	
	@Autowired
	BookRepo brepo;
	
	@Autowired
	BorrowerRepo brrepo;
	
	@Autowired
	GenreRepo grepo;
	
	@Autowired
	AuthorRepo arepo;
	
	@Autowired
	BranchRepo bchrepo;
	
	@Autowired
	PublisherRepo prepo;
	
	@Autowired
	LoansRepo lrepo;
	
	//Reading Functionality
	
	@RequestMapping(value = "/getBooksByQuery", method = RequestMethod.GET, produces = "application/json")
	public List<Book> getBooksByQuery(@RequestParam String searchString) {
		List<Book> books = new ArrayList<>();
		if(searchString != null && searchString.length() > 0) {
			books = brepo.readBooksByTitle(searchString);
		} else {
			books = brepo.findAll();
		}
		return books;
	}
	
	@RequestMapping(value = "/getBooksById", method = RequestMethod.GET, produces = "application/json")
	public List<Book> getBooksById(@RequestParam Integer bookId) {
		List<Book> books = new ArrayList<>();
		if(bookId != null) {
			books = brepo.readBooksById(bookId);
		} else {
			books = brepo.findAll();
		}
		return books;
	}
	
	@RequestMapping(value = "/getBorrowersByCardNo", method = RequestMethod.GET, produces = "application/json")
	public List<Borrower> getBorrowersByQuery(@RequestParam Integer cardNo) {
		List<Borrower> borrowers = new ArrayList<>();
		if(cardNo != null) {
			borrowers = brrepo.readBorrowerById(cardNo);
		} else {
			borrowers = brrepo.findAll();
		}
		return borrowers;
	}
	
	@RequestMapping(value = "/getGenresById", method = RequestMethod.GET, produces = "application/json")
	public List<Genre> getGenresById(@RequestParam Integer genre_id) {
		List<Genre> genres = new ArrayList<>();
		if(genre_id != null) {
			genres = grepo.readGenreById(genre_id);
		} else {
			genres = grepo.findAll();
		}
		return genres;
	}
	
	@RequestMapping(value = "/getGenresByName", method = RequestMethod.GET, produces = "application/json")
	public List<Genre> getGenresByName(@RequestParam String genre_name) {
		List<Genre> genres = new ArrayList<>();
		if(genre_name != null && genre_name.length() > 0) {
			genres = grepo.readGenreByName(genre_name);
		} else {
			genres = grepo.findAll();
		}
		return genres;
	}
	
	@RequestMapping(value = "/getAuthorsById", method = RequestMethod.GET, produces = "application/json")
	public List<Author> getAuthorsById(@RequestParam Integer authorId) {
		List<Author> authors = new ArrayList<>();
		if(authorId != null) {
			authors = arepo.readAuthorById(authorId);
		} else {
			authors = arepo.findAll();
		}
		return authors;
	}
	
	@RequestMapping(value = "/getAuthorsByName", method = RequestMethod.GET, produces = "application/json")
	public List<Author> getAuthorsByName(@RequestParam String authorName) {
		List<Author> authors = new ArrayList<>();
		if(authorName != null && authorName.length() > 0) {
			authors = arepo.readAuthorByName(authorName);
		} else {
			authors = arepo.findAll();
		}
		return authors;
	}
	
	@RequestMapping(value = "/getBranchesById", method = RequestMethod.GET, produces = "application/json")
	public List<Branch> getBranchesById(@RequestParam Integer branchId) {
		List<Branch> branches = new ArrayList<>();
		if(branchId!= null) {
			branches = bchrepo.readBranchesById(branchId);
		} else {
			branches = bchrepo.findAll();
		}
		return branches;
	}
	
	@RequestMapping(value = "/getBranchesByName", method = RequestMethod.GET, produces = "application/json")
	public List<Branch> getBranchesByName(@RequestParam String branchName) {
		List<Branch> branches = new ArrayList<>();
		if(branchName!= null && branchName.length() > 0) {
			branches = bchrepo.readBranchByName(branchName);
		} else {
			branches = bchrepo.findAll();
		}
		return branches;
	}
	
	@RequestMapping(value = "/getBranchesByAddress", method = RequestMethod.GET, produces = "application/json")
	public List<Branch> getBranchesByAddress(@RequestParam String branchAddress) {
		List<Branch> branches = new ArrayList<>();
		if(branchAddress!= null && branchAddress.length() > 0) {
			branches = bchrepo.readBranchByAddress(branchAddress);
		} else {
			branches = bchrepo.findAll();
		}
		return branches;
	}
	
	@RequestMapping(value = "/getPublishersByName", method = RequestMethod.GET, produces = "application/json")
	public List<Publisher> getPublishersByName(@RequestParam String publisherName) {
		List<Publisher> publishers = new ArrayList<>();
		if(publisherName!= null && publisherName.length() > 0) {
			publishers = prepo.readPublisherByName(publisherName);
		} else {
			publishers = prepo.findAll();
		}
		return publishers;
	}
	
	@RequestMapping(value = "/getPublishersById", method = RequestMethod.GET, produces = "application/json")
	public List<Publisher> getPublishersById(@RequestParam Integer publisherId) {
	
			List<Publisher> publishers = new ArrayList<>();
		if(publisherId!= null) {
			publishers = prepo.readPublisherById(publisherId);
		} else {
			publishers = prepo.findAll();
		}
		return publishers;
		
		
	}
	
	//Publisher
	@Transactional
	@RequestMapping(value = "/addPublisher", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public List<Publisher> AddPublisher(@RequestBody Publisher publisher){
			prepo.save(publisher);
			return prepo.findAll();
	}
	
	@Transactional
	@RequestMapping(value = "/deletePublisher", method = RequestMethod.DELETE, produces = "application/json", consumes = "application/json")
	public List<Publisher> deletePublisher(@RequestBody Publisher publisher) throws SQLException {
		
				prepo.delete(publisher);
				return prepo.findAll();
		
	}
	
	@Transactional
	@RequestMapping(value = "/updatePublisher", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
	public List<Publisher> AddPublisher(@RequestParam Integer publisherId, @RequestBody Publisher publisher){
		List<Publisher> oldPublisher = prepo.readPublisherById(publisherId);
		
		publisher.setPublisherId(publisherId);
		
		if(publisher.getPublisherName() == null) {
			publisher.setPublisherName(oldPublisher.get(0).getPublisherName());
		}
		if (publisher.getPublisherAddress() == null) {
			publisher.setPublisherAddress(oldPublisher.get(0).getPublisherName());
		}
		if(publisher.getPublisherPhone() == null) {
			publisher.setPublisherPhone(oldPublisher.get(0).getPublisherPhone());
		}
		
			prepo.save(publisher);
			return prepo.findAll();
	}
	
	//Genre
	@Transactional
	@RequestMapping(value = "/addGenre", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public List<Genre> addGenre(@RequestBody Genre genre) throws SQLException {
		
				grepo.save(genre);
				return grepo.findAll();
		
	}
	
	@Transactional
	@RequestMapping(value = "/deleteGenre", method = RequestMethod.DELETE, produces = "application/json", consumes = "application/json")
	public List<Genre> deleteGenre(@RequestBody Genre genre) throws SQLException {
		
				grepo.delete(genre);
				return grepo.findAll();
		
	}
	
	@Transactional
	@RequestMapping(value = "/updateGenre", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
	public List<Genre> updateGenre(@RequestParam Integer genre_id, @RequestBody Genre genre) throws SQLException {
		List<Genre> oldgenre = grepo.readGenreById(genre_id);
		genre.setGenreId(genre_id);
		
		if(genre.getGenreName() == null) {
			genre.setGenreName(oldgenre.get(0).getGenreName());
		}
		
			grepo.save(genre);
			return grepo.findAll();
		
	}
	
	//Authors
	@Transactional
	@RequestMapping(value = "/addAuthor", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public List<Author> addAuthor(@RequestBody Author author) throws SQLException {
		
				arepo.save(author);
				return arepo.findAll();
		
	}
	
	@Transactional
	@RequestMapping(value = "/deleteAuthor", method = RequestMethod.DELETE, produces = "application/json", consumes = "application/json")
	public List<Author> deleteAuthor(@RequestBody Author author) throws SQLException {
		
				arepo.delete(author);
				return arepo.findAll();
		
	}
	
	@Transactional
	@RequestMapping(value = "/updateAuthor", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
	public List<Author> updateAuthor(@RequestParam Integer authorId, @RequestBody Author author) throws SQLException {
		List<Author> oldAuthor = arepo.readAuthorById(authorId);
		author.setAuthorId(authorId);
		
		if(author.getAuthorName() == null) {
			author.setAuthorName(oldAuthor.get(0).getAuthorName());
		}
		if(author.getBooks() == null) {
			author.setBooks(oldAuthor.get(0).getBooks());
		}
			arepo.save(author);
			return arepo.findAll();
		
	}
	
	//Borrowers
	@Transactional
	@RequestMapping(value = "/addBorrower", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public List<Borrower> addBorrower(@RequestBody Borrower borrower) throws SQLException {
		
				brrepo.save(borrower);
				return brrepo.findAll();
		
	}
	
	@Transactional
	@RequestMapping(value = "/deleteBorrower", method = RequestMethod.DELETE, produces = "application/json", consumes = "application/json")
	public List<Borrower> deleteBorrower(@RequestBody Borrower borrower) throws SQLException {
		
				brrepo.delete(borrower);
				return brrepo.findAll();
		
	}
	
	@Transactional
	@RequestMapping(value = "/updateBorrower", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
	public List<Borrower> updateBorrower(@RequestParam Integer borrowerId, @RequestBody Borrower borrower) throws SQLException {
		
		
		List<Borrower> oldBorrower = brrepo.readBorrowerById(borrowerId);
		borrower.setCardNo(borrowerId);
		
		if(borrower.getName() == null) {
			borrower.setName(oldBorrower.get(0).getName());
		}
		if(borrower.getAddress() == null) {
			borrower.setAddress(oldBorrower.get(0).getAddress());
		}
		if(borrower.getPhone() == null) {
			borrower.setPhone(oldBorrower.get(0).getPhone());
		}
			brrepo.save(borrower);
			return brrepo.findAll();
		
	}
	
	//Branches
	@Transactional
	@RequestMapping(value = "/addBranch", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public List<Branch> addBranch(@RequestBody Branch branch) throws SQLException {
		
				bchrepo.save(branch);
				return bchrepo.findAll();
		
	}
	
	@Transactional
	@RequestMapping(value = "/deleteBranch", method = RequestMethod.DELETE, produces = "application/json", consumes = "application/json")
	public List<Branch> deleteBranch(@RequestBody Branch branch) throws SQLException {
		
				bchrepo.delete(branch);
				return bchrepo.findAll();
				
	}
	
	@Transactional
	@RequestMapping(value = "/updateBranch", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
	public List<Branch> updateBranch(@RequestParam Integer branchId, @RequestBody Branch branch) throws SQLException {
				List<Branch> oldBranch = bchrepo.readBranchesById(branchId);
				branch.setBranchId(branchId);
				
				if(branch.getBranchName() == null) {
					branch.setBranchName(oldBranch.get(0).getBranchName());
				}
				if(branch.getBranchAddress() == null) {
					branch.setBranchAddress(oldBranch.get(0).getBranchAddress());
				}
				bchrepo.save(branch);
				return bchrepo.findAll();
	}
	
	//Books
		@Transactional
		@RequestMapping(value = "/addBook", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
		public List<Book> addBook(@RequestBody Book book) throws SQLException {
			
					brepo.save(book);
					return brepo.findAll();
			
		}
		
		@Transactional
		@RequestMapping(value = "/deleteBook", method = RequestMethod.DELETE, produces = "application/json", consumes = "application/json")
		public List<Book> deleteBook(@RequestBody Book book) throws SQLException {
			
					brepo.delete(book);
					return brepo.findAll();
					
		}
		
		@Transactional
		@RequestMapping(value = "/updateBook", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
		public List<Book> updateBook(@RequestParam Integer bookId, @RequestBody Book book) throws SQLException {
					List<Book> oldBook = brepo.readBooksById(bookId);
					book.setBookId(bookId);
					
					if(book.getTitle() == null) {
						book.setTitle(oldBook.get(0).getTitle());
					}
					if(book.getAuthors() == null) {
						book.setAuthors(oldBook.get(0).getAuthors());
					}
					if(book.getPublisher()==null) {
						book.setPublisher(oldBook.get(0).getPublisher());
					}
					
					brepo.save(book);
					return brepo.findAll();
		}
		
		//readLoans
		@RequestMapping(value = "/getLoansById", method = RequestMethod.GET, produces = "application/json")
		public List<Loans> getLoansById(@RequestParam Integer bookId, @RequestParam Integer branchId, @RequestParam Integer cardNo) {
			List<Loans> loans = new ArrayList<>();
			if(bookId != null) {
				loans = lrepo.readLoansById(bookId, branchId, cardNo);
			} else {
				loans = lrepo.findAll();
			}
			return loans;
		}
		
		
		@Transactional
		@RequestMapping(value = "/overwrite", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
		public List<Loans> overwrite(@RequestParam Integer bookId, @RequestParam Integer branchId, @RequestParam Integer cardNo, @RequestParam String newDueDate, @RequestBody Loans loan) throws SQLException {
					List<Loans> oldLoan = lrepo.readLoansById(bookId, branchId, cardNo);
					LoansId id = new LoansId(bookId, branchId, cardNo);
					loan.setId(id);
					loan.setBook(oldLoan.get(0).getBook());
					loan.setDateIn(oldLoan.get(0).getDateIn());
					loan.setDateOut(oldLoan.get(0).getDateOut());
					loan.setBranch(oldLoan.get(0).getBranch());
					loan.setBorrower(oldLoan.get(0).getBorrower());
					if(newDueDate == null) {
						loan.setDueDate(oldLoan.get(0).getDueDate());
					}else {
						loan.setDueDate(newDueDate);
					}
					
					lrepo.save(loan);
					return lrepo.findAll();
		}

	
//  @Transactional
//	@RequestMapping(value = "/getBooks/{searchString}", method = RequestMethod.GET, produces = "application/json")
//    public List<Book> readBooks(@PathVariable String searchString) throws SQLException {
//        
//        try {
//        	if (searchString != null) {
//        		return bdao.readAllBooksByName(searchString);
//        	}else {
//        		return null;
//        	}
//           
//
//        } catch (ClassNotFoundException | SQLException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//    
//    @RequestMapping(value="/getBooks/", method = RequestMethod.GET, produces = "application/json")
//    public List<Book> readAllBooks()throws SQLException {
//    	try {
//    		return bdao.readAllBooks();
//    	} catch(ClassNotFoundException | SQLException e) {
//    		e.printStackTrace();
//    		return null;
//    	}
//    }
//    
//    @RequestMapping(value="/getGenres/", method = RequestMethod.GET, produces = "application/json")
//    public List<Genre> readGenres() throws SQLException {
//       try {
//    	   return  gdao.readAllGenres();
//       } catch (ClassNotFoundException | SQLException e){
//    	   e.printStackTrace();
//    	   return null;
//       }
//    }
//    
//    @RequestMapping(value="/getBorrowers/{searchInt}", method = RequestMethod.GET, produces = "application/json")
//    public List<Borrower> readBorrowersByCard(@PathVariable Integer searchInt) throws SQLException {
//       try {
//    	   return brdao.readSpecificBorrower(searchInt);
//       } catch (ClassNotFoundException | SQLException e) {
//    	   e.printStackTrace();
//    	   return null;
//       }
//    }
//    
//    @RequestMapping(value="/getBorrowers/", method = RequestMethod.GET, produces = "application/json")
//    public List<Borrower> readBorrowers() throws SQLException {
//       try {
//    	   return brdao.readAllBorrowers();
//       } catch (ClassNotFoundException | SQLException e) {
//    	   e.printStackTrace();
//    	   return null;
//       }
//    }
//    
//    @RequestMapping(value = "/getAuthors/{searchString}", method = RequestMethod.GET, produces = "application/json")
//    public List<Author> readAuthorsByName(@PathVariable String searchString) throws SQLException {
//        
//        try {
//        	if (searchString != null) {
//        		return adao.readAllAuthorsByName(searchString);
//        	}else {
//        		return null;
//        	}
//        } catch (ClassNotFoundException | SQLException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//    
//    @RequestMapping(value="/getAuthors/", method = RequestMethod.GET, produces = "application/json")
//    public List<Author> readAllAuthors()throws SQLException {
//    	try {
//    		return adao.readAllAuthors();
//    	} catch(ClassNotFoundException | SQLException e) {
//    		e.printStackTrace();
//    		return null;
//    	}
//    }
//    
//    @RequestMapping(value="/getLoans/{cardNo}", method = RequestMethod.GET, produces = "application/json")
//    public List<Loans> readUserLoans(@PathVariable Integer cardNo) throws SQLException {
//        try {
//            return ldao.readCheckedOut(cardNo);
//        } catch (ClassNotFoundException | SQLException e) {
//           e.printStackTrace();
//           return null;
//        }
//    }
//
//    @RequestMapping(value="/getLoans/", method = RequestMethod.GET, produces = "application/json")
//    public List<Loans> readAllLoans() throws SQLException {
//        
//        try {
//            return ldao.readAllLoans();
//        } catch (ClassNotFoundException | SQLException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//    
//    @RequestMapping(value="/getBranches/", method = RequestMethod.GET, produces = "application/json")
//    public List<Branch> readAllBranches() throws SQLException {
//        
//    	try {
//        	return bchdao.readAllBranches();
//        } catch (ClassNotFoundException | SQLException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//    
//    @RequestMapping(value="/getPublishers/", method = RequestMethod.GET, produces = "application/json")
//    public List<Publisher> readAllPublishers() throws SQLException {
//        
//    	try {
//        	return pdao.readAllPublishers();
//        } catch (ClassNotFoundException | SQLException e) {
//            e.printStackTrace();
//            return null;
//        }
//       
//    }
//
//
//    public String readPublishers() throws SQLException{
//        Connection conn = null;
//        try {
//            conn = conUtil.getConnection();
//            PublisherDAO pdao = new PublisherDAO(conn);
//            List<Publisher> a = pdao.readAllPublishers();
//            conn.commit();
//            for (int i = 0; i < a.size(); i++) {
//                System.out.println("ID: "+ a.get(i).getPublisherId() + " Publisher Name: " + a.get(i).getPublisherName() + " Publisher Address: " + a.get(i).getPublisherAddress() +
//                        " Publisher Phone No: " + a.get(i).getPublisherPhone());
//            }
//
//            return "Publishers read successfully";
//        } catch (ClassNotFoundException | SQLException e) {
//            e.printStackTrace();
//            if (conn != null) {
//                conn.rollback();
//            }
//            System.err.println("Unable to edit book - contact admin.");
//            return "Unable to edit book - contact admin.";
//        } finally {
//            if (conn != null) {
//                conn.close();
//            }
//        }
//    }
//
//    public String readBranches() throws SQLException{
//        Connection conn = null;
//        try {
//            conn = conUtil.getConnection();
//            BranchDAO brdao = new BranchDAO(conn);
//            List<Branch> a = brdao.readAllBranches();
//            conn.commit();
//            for (int i = 0; i < a.size(); i++) {
//                System.out.println("ID: "+ a.get(i).getBranchId() + " Branch Name: " + a.get(i).getBranchName() + " Branch Address: " + a.get(i).getBranchAddress());
//            }
//
//            return "Branches read successfully";
//        } catch (ClassNotFoundException | SQLException e) {
//            e.printStackTrace();
//            if (conn != null) {
//                conn.rollback();
//            }
//            System.err.println("Unable to read branches - contact admin.");
//            return "Unable to read branches - contact admin.";
//        } finally {
//            if (conn != null) {
//                conn.close();
//            }
//        }
//    }
//
//    public String addGenre(Genre genre) throws SQLException{
//        Connection conn = null;
//
//        try {
//            conn = conUtil.getConnection();
//            GenreDAO gdao = new GenreDAO(conn);
//
//            gdao.addGenre(genre);
//
//            conn.commit();
//            System.out.println("Genre added Successfully");
//            return "Genre added successfully";
//
//        }catch (ClassNotFoundException | SQLException e) {
//            System.out.println("Please check that your server is running and that you entered in the correct SQL data values");
//            if (conn != null) {
//                conn.rollback();
//            }
//            Main.main(null);
//            return "Unable to update book - contact admin.";
//        } finally {
//            if (conn != null) {
//                conn.close();
//            }
//        }
//    }
//
//    public String updateBook(Book book) throws SQLException {
//        Connection conn = null;
//
//        try{
//            conn = conUtil.getConnection();
//            BookDAO bdao = new BookDAO(conn);
//
//            if(book.getPublisherId() == null){
//                bdao.updateBookWithoutPublisher(book);
//            }else{
//                bdao.updateBookWPublisher(book);
//            }
//
//            System.err.println("Would you like to update the author of the book as well?");
//            System.err.println("1) Yes");
//            System.err.println("2) No");
//            try{
//                Scanner scan = new Scanner(System.in);
//                Integer authorUpdate = scan.nextInt();
//                scan.nextLine();
//                switch(authorUpdate){
//                    case 1:
//                        readAuthors();
//                        System.err.println("Please enter the id of the author your book now belongs too");
//                        Integer authorId = scan.nextInt();
//                        AuthorDAO adao = new AuthorDAO(conn);
//                        adao.updateBookAuthors(book.getBookId(), authorId);
//                        break;
//                    case 2:
//                        break;
//                    default:
//                        System.out.println("Not a valid option");
//                        AdminMenu.adminUpdateBookAuthor();
//                }
//            }catch(InputMismatchException e){
//                System.out.println("Please enter an integer value");
//                AdminMenu.adminUpdateBookAuthor();
//            }
//
//
//
//            conn.commit();
//            System.out.println("Book Updated Successfully");
//            AdminMenu.main(null);
//            return "Book added successfully";
//
//        }catch (ClassNotFoundException | SQLException e) {
//            System.out.println("Please check that your server is running and that you entered in the correct SQL data values");
//            if (conn != null) {
//                conn.rollback();
//            }
//            Main.main(null);
//            return "Unable to update book - contact admin.";
//        } finally {
//            if (conn != null) {
//                conn.close();
//            }
//        }
//    }
//
//    public String deleteGenre() throws SQLException{
//        Connection conn = null;
//        try{
//            conn = conUtil.getConnection();
//
//            readGenres();
//            try{
//                Scanner scan = new Scanner(System.in);
//                System.err.println("Please select the genre Id you wish to delete");
//                Integer genreId = scan.nextInt();
//                scan.nextLine();
//                GenreDAO gdao = new GenreDAO(conn);
//                gdao.deleteGenre(genreId);
//            }catch(InputMismatchException e){
//                System.out.println("Please input an integer value");
//            }
//
//            conn.commit();
//
//            AdminMenu.main(null);
//            return "Genre Updated Successfully";
//
//        }catch (ClassNotFoundException | SQLException e) {
//            System.out.println("Please check that your server is running and that you entered in the correct SQL data values");
//            if (conn != null) {
//                conn.rollback();
//            }
//            Main.main(null);
//            return "Unable to Delete Genre - contact admin.";
//        } finally {
//            if (conn != null) {
//                conn.close();
//            }
//        }
//    }
//
//    public String deleteBook() throws SQLException{
//        Connection conn = null;
//
//        try{
//            conn = conUtil.getConnection();
//
//            readBooks();
//            try{
//                Scanner scan = new Scanner(System.in);
//                System.err.println("Please select the book Id you wish to delete, if the bookId is not valid, no operation will occur");
//                Integer deleteId = scan.nextInt();
//                scan.nextLine();
//                BookDAO bdao = new BookDAO(conn);
//                bdao.deleteBook(deleteId);
//            }catch(InputMismatchException e){
//                System.out.println("Please input an integer value");
//            }
//
//
//
//
//
//            conn.commit();
//
//            AdminMenu.main(null);
//            return "Author Updated Successfully";
//
//        }catch (ClassNotFoundException | SQLException e) {
//            System.out.println("Please check that your server is running and that you entered in the correct SQL data values");
//            if (conn != null) {
//                conn.rollback();
//            }
//            Main.main(null);
//            return "Unable to Delete Book - contact admin.";
//        } finally {
//            if (conn != null) {
//                conn.close();
//            }
//        }
//    }
//
//    public String deleteAuthor() throws SQLException{
//        Connection conn = null;
//
//        try{
//            conn = conUtil.getConnection();
//
//            readAuthors();
//            try{
//                Scanner scan = new Scanner(System.in);
//                System.err.println("Please select the Author Id you wish to delete");
//                Integer deleteId = scan.nextInt();
//                scan.nextLine();
//                AuthorDAO adao = new AuthorDAO(conn);
//                adao.deleteAuthor(deleteId);
//            }catch(InputMismatchException e){
//                System.out.println("Please input an integer value");
//            }
//
//
//
//
//
//            conn.commit();
//            System.out.println("Author Updated Successfully");
//            AdminMenu.main(null);
//            return "Author Updated Successfully";
//
//        }catch (ClassNotFoundException | SQLException e) {
//            System.out.println("Please check that your server is running and that you entered in the correct SQL data values");
//            if (conn != null) {
//                conn.rollback();
//            }
//            Main.main(null);
//            return "Unable to update Author - contact admin.";
//        } finally {
//            if (conn != null) {
//                conn.close();
//            }
//        }
//    }
//
//    public String updateGenre(Genre genre) throws SQLException {
//        Connection conn = null;
//
//        try{
//            conn = conUtil.getConnection();
//            GenreDAO gdao = new GenreDAO(conn);
//            gdao.updateGenre(genre);
//
//
//
//            conn.commit();
//            System.out.println("Genre Updated Successfully");
//            AdminMenu.main(null);
//            return "Genre Updated Successfully";
//
//        }catch (ClassNotFoundException | SQLException e) {
//            System.out.println("Please check that your server is running and that you entered in the correct SQL data values");
//            if (conn != null) {
//                conn.rollback();
//            }
//            Main.main(null);
//            return "Unable to update Genre - contact admin.";
//        } finally {
//            if (conn != null) {
//                conn.close();
//            }
//        }
//    }
//
//
//    public String updateAuthor(Author author) throws SQLException {
//        Connection conn = null;
//
//        try{
//            conn = conUtil.getConnection();
//            AuthorDAO adao = new AuthorDAO(conn);
//            adao.updateAuthor(author);
//
//
//
//            conn.commit();
//            AdminMenu.main(null);
//            return "Author Updated Successfully";
//
//        }catch (ClassNotFoundException | SQLException e) {
//            System.out.println("Please check that your server is running and that you entered in the correct SQL data values");
//            if (conn != null) {
//                conn.rollback();
//            }
//            Main.main(null);
//            return "Unable to update Author - contact admin.";
//        } finally {
//            if (conn != null) {
//                conn.close();
//            }
//        }
//    }
//
//    public String addBook(Book book) throws SQLException {
//        Connection conn = null;
//
//        try {
//            conn = conUtil.getConnection();
//            BookDAO bdao = new BookDAO(conn);
//
//            PublisherDAO pdao = new PublisherDAO(conn);
//
//            book.setBookId(bdao.addBookWithPk(book));
//
//
//            readAuthors();
//
//
//
//            Boolean isRunning = true;
//            Scanner scan = new Scanner(System.in);
//            AuthorDAO adao = new AuthorDAO(conn);
//            while(isRunning){
//                System.err.println("Enter the id of at least one author.(0 to exit)");
//                Integer authorId = scan.nextInt();
//                scan.nextLine();
//                if (authorId == 0){
//                    isRunning = false;
//                    break;
//                }
//                adao.addBookAuthors(book.getBookId(), authorId);
//
//            }
//
//
//            System.err.println("Enter the id of the genre your new book belongs too");
//            readGenres();
//
//            GenreDAO gdao = new GenreDAO(conn);
//            Integer genreId = scan.nextInt();
//            scan.nextLine();
//            book.setGenre(genreId);
//
//           gdao.setBookGenre(book);
//
//           readBranches();
//
//           Boolean isRunning2 = true;
//           BranchDAO brdao = new BranchDAO(conn);
//            while(isRunning2){
//                System.err.println("Enter the id of at least one Branch.(0 to exit)");
//                Integer branchId = scan.nextInt();
//                scan.nextLine();
//
//                if (branchId == 0){
//                    isRunning2 = false;
//                    break;
//                }
//                System.err.println("Number of copies to add to this branch");
//                Integer noOfCopies = scan.nextInt();
//                scan.nextLine();
//
//                brdao.setBookCopies(book.getBookId(), branchId, noOfCopies);
//
//            }
//            scan.nextLine();
//            conn.commit();
//            System.out.println("Book and Author added successfully");
//            return "Book added successfully";
//        } catch (ClassNotFoundException | SQLException e) {
//
//            System.out.println("Please check that your server is running and that you entered in the correct SQL data values");
//            if (conn != null) {
//                conn.rollback();
//            }
//            Main.main(null);
//            return "Unable to add book - contact admin.";
//        }
//        finally {
//            if (conn != null) {
//                conn.close();
//            }
//        }
//    }
//
//    public String addPublisher(Publisher publisher) throws SQLException{
//        Connection conn = null;
//
//        try {
//            conn = conUtil.getConnection();
//            PublisherDAO pdao = new PublisherDAO(conn);
//
//            pdao.addPublisher(publisher);
//
//            conn.commit();
//            System.out.println("Publisher added Successfully");
//            return "Publisher added successfully";
//
//        }catch (ClassNotFoundException | SQLException e) {
//            System.out.println("Please check that your server is running and that you entered in the correct SQL data values");
//            if (conn != null) {
//                conn.rollback();
//            }
//            Main.main(null);
//            return "Unable to add Publisher - contact admin.";
//        } finally {
//            if (conn != null) {
//                conn.close();
//            }
//        }
//
//    }
//
//    public String editPublisher(Publisher publisher) throws SQLException{
//        Connection conn = null;
//
//        try{
//            conn = conUtil.getConnection();
//            PublisherDAO gdao = new PublisherDAO(conn);
//            gdao.updatePublisher(publisher);
//
//            conn.commit();
//            System.out.println("Publisher Updated Successfully");
//            return "Publisher Updated Successfully";
//
//        }catch (ClassNotFoundException | SQLException e) {
//            System.out.println("Please check that your server is running and that you entered in the correct SQL data values");
//            if (conn != null) {
//                conn.rollback();
//            }
//            Main.main(null);
//            return "Unable to update Publisher - contact admin.";
//        } finally {
//            if (conn != null) {
//                conn.close();
//            }
//        }
//    }
//
//    public String deletePublisher() throws SQLException{
//        Connection conn = null;
//
//        try{
//            conn = conUtil.getConnection();
//
//            readPublishers();
//            try{
//                Scanner scan = new Scanner(System.in);
//                System.err.println("Please select the Publisher Id you wish to delete, if Id value is not found no operation will occur");
//                Integer deleteId = scan.nextInt();
//                scan.nextLine();
//                PublisherDAO pdao = new PublisherDAO(conn);
//                pdao.deletePublisher(deleteId);
//            }catch(InputMismatchException e){
//                System.out.println("Please input an integer value");
//            }
//
//            conn.commit();
//            return "Publisher Updated Successfully";
//
//        }catch (ClassNotFoundException | SQLException e) {
//            System.out.println("Please check that your server is running and that you entered in the correct SQL data values");
//            if (conn != null) {
//                conn.rollback();
//            }
//            Main.main(null);
//            return "Unable to delete Publisher - contact admin.";
//        } finally {
//            if (conn != null) {
//                conn.close();
//            }
//        }
//    }
//
//    public String addBranch(Branch branch) throws SQLException{
//        Connection conn = null;
//
//        try {
//            conn = conUtil.getConnection();
//            BranchDAO brdao = new BranchDAO(conn);
//
//            brdao.addBranch(branch);
//
//            conn.commit();
//            System.out.println("Branch added Successfully");
//            return "Branch added successfully";
//
//        }catch (ClassNotFoundException | SQLException e) {
//            System.out.println("Please check that your server is running and that you entered in the correct SQL data values");
//            if (conn != null) {
//                conn.rollback();
//            }
//            Main.main(null);
//            return "Unable to add Branch - contact admin.";
//        } finally {
//            if (conn != null) {
//                conn.close();
//            }
//        }
//
//    }
//
//    public String updateBranch(Branch branch) throws SQLException{
//        Connection conn = null;
//        readBranches();
//        try {
//            conn = conUtil.getConnection();
//            BranchDAO brdao = new BranchDAO(conn);
//
//            brdao.updateBranch(branch);
//
//            conn.commit();
//            System.out.println("Branch updated Successfully");
//            return "Branch updated successfully";
//
//        }catch (ClassNotFoundException | SQLException e) {
//            System.out.println("Please check that your server is running and that you entered in the correct SQL data values");
//            if (conn != null) {
//                conn.rollback();
//            }
//            Main.main(null);
//            return "Unable to update Branch - contact admin.";
//        } finally {
//            if (conn != null) {
//                conn.close();
//            }
//        }
//
//    }
//
//    public String deleteBranch() throws SQLException{
//        Connection conn = null;
//
//        try{
//            conn = conUtil.getConnection();
//
//            readBranches();
//            try{
//                Scanner scan = new Scanner(System.in);
//                System.err.println("Please select the Branch Id you wish to delete, if no branch is found no operation will be performed");
//                Integer deleteId = scan.nextInt();
//                scan.nextLine();
//                BranchDAO brdao = new BranchDAO(conn);
//                brdao.deleteBranch(deleteId);
//            }catch(InputMismatchException e){
//                System.out.println("Please input an integer value");
//            }
//
//
//
//
//
//            conn.commit();
//            return "Branch Updated Successfully";
//
//        }catch (ClassNotFoundException | SQLException e) {
//            System.out.println("Please check that your server is running and that you entered in the correct SQL data values");
//            if (conn != null) {
//                conn.rollback();
//            }
//            Main.main(null);
//            return "Unable to delete Publisher - contact admin.";
//        } finally {
//            if (conn != null) {
//                conn.close();
//            }
//        }
//    }
//
//    public String addBorrower(Borrower borrower) throws SQLException{
//        Connection conn = null;
//
//        try {
//            conn = conUtil.getConnection();
//            BorrowerDAO bwdao = new BorrowerDAO(conn);
//
//            bwdao.addBorrower(borrower);
//
//            conn.commit();
//            System.out.println("Borrower added successfully");
//            return "Borrower added successfully";
//
//        }catch (ClassNotFoundException | SQLException e) {
//            System.out.println("Please check that your server is running and that you entered in the correct SQL data values");
//            if (conn != null) {
//                conn.rollback();
//            }
//            Main.main(null);
//            return "Unable to add Branch - contact admin.";
//        } finally {
//            if (conn != null) {
//                conn.close();
//            }
//        }
//
//    }
//
//    public String updateBorrower(Borrower borrower) throws SQLException{
//        Connection conn = null;
//        readBorrowers();
//        try {
//            conn = conUtil.getConnection();
//            BorrowerDAO bwdao = new BorrowerDAO(conn);
//
//            bwdao.updateBorrower(borrower);
//
//            conn.commit();
//            System.out.println("Borrower updated Successfully");
//            return "Borrower updated successfully";
//
//        }catch (ClassNotFoundException | SQLException e) {
//            System.out.println("Please check that your server is running and that you entered in the correct SQL data values");
//            if (conn != null) {
//                conn.rollback();
//            }
//            Main.main(null);
//            return "Unable to update Branch - contact admin.";
//        } finally {
//            if (conn != null) {
//                conn.close();
//            }
//        }
//
//    }
//
//    public String deleteBorrower() throws SQLException{
//        Connection conn = null;
//        try{
//            conn = conUtil.getConnection();
//
//            readBorrowers();
//            try{
//                Scanner scan = new Scanner(System.in);
//                System.err.println("Please select the Borrower cardNo you wish to delete");
//                Integer deleteId = scan.nextInt();
//                scan.nextLine();
//                String loanCheck = readUserLoans(deleteId);
//                if (loanCheck.length() > 5){
//                    System.out.println("This user currently has books checked out");
//                }else{
//                    System.out.println("Borrower successfully deleted");
//                    BorrowerDAO bwdao = new BorrowerDAO(conn);
//                bwdao.deleteBorrower(deleteId);
//                }
//
////
//            }catch(InputMismatchException e){
//                System.out.println("Please input an integer value");
//            } catch (ClassNotFoundException e) {
//                e.printStackTrace();
//            }
//
//
//            conn.commit();
//
//            return "Branch Updated Successfully";
//
//        }catch ( SQLException e) {
//            System.out.println("Please check that your server is running and that you entered in the correct SQL data values");
//            if (conn != null) {
//                conn.rollback();
//            }
//            Main.main(null);
//            return "Unable to delete Publisher - contact admin.";
//        } finally {
//            if (conn != null) {
//                conn.close();
//            }
//        }
//    }
//
//    public String overrideDueDate(Integer cardNo, Integer bookId) throws SQLException {
//        Connection conn = null;
//
//        try{
//            conn = conUtil.getConnection();
//            LoansDAO ldao = new LoansDAO(conn);
//            ldao.overideDueDate(cardNo, bookId);
//
//
//
//            conn.commit();
//            return "Due Date Updated Successfully";
//
//        }catch (ClassNotFoundException | SQLException e) {
//            System.out.println("Please check that your server is running and that you entered in the correct SQL data values");
//            if (conn != null) {
//                conn.rollback();
//            }
//            Main.main(null);
//            return "Unable to update Author - contact admin.";
//        } finally {
//            if (conn != null) {
//                conn.close();
//            }
//        }
//    }

}
