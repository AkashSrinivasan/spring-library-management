package com.home.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;

import com.google.appengine.api.datastore.Blob;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;


public class Service {
	 static DatastoreService dataStoreService = DatastoreServiceFactory.getDatastoreService();
	 static PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("LibraryManagement");
	 static PersistenceManager pm = pmf.getPersistenceManager();
	 
	 public static Student getStudent(int id){
		 Filter propertyFilter = new FilterPredicate("id", FilterOperator.EQUAL, id);
		 Student student = new Student();
		 try {
				Query query = new Query("Student").setFilter(propertyFilter);
				PreparedQuery pq = dataStoreService.prepare(query);
				for(Entity ent:pq.asIterable()) {
					student.setId((long) ent.getProperty("id"));
					 student.setName((String) ent.getProperty("name"));
					 student.setPassword((String) ent.getProperty("password"));
					 student.setProfilePic((Blob) ent.getProperty("profilePic"));
				}
			}catch(NullPointerException nullPointer){
				return null;
			}
		 return student;
	}
	 
	 public static Staff getStaff( int id){
		 Filter propertyFilter = new FilterPredicate("id", FilterOperator.EQUAL, id);
		 Staff staff = new Staff();
		 try {
				Query query = new Query("Student").setFilter(propertyFilter);
				PreparedQuery pq = dataStoreService.prepare(query);
				for(Entity ent:pq.asIterable()) {
					staff.setId((long) ent.getProperty("id"));
					staff.setName((String) ent.getProperty("name"));
					staff.setPassword((String) ent.getProperty("password"));
					staff.setProfilePic((Blob) ent.getProperty("profilePic"));
				}
			}catch(NullPointerException nullPointer){
				return null;
			}
		 return staff;
	}
	 
	public static ArrayList<Book> getBooks(long id){
		Filter propertyFilter = new FilterPredicate("barrowerId", FilterOperator.EQUAL, id);
	    Query query = new Query("Book").setFilter(propertyFilter);
		PreparedQuery pq = dataStoreService.prepare(query);
		ArrayList<Book> books = new ArrayList<>();
		for(Entity ent:pq.asIterable()) {
			Book book = new Book();
			book.setId((long) ent.getProperty("id"));
			book.setName((String) ent.getProperty("name"));
			book.setBarrowerId((Long) ent.getProperty("barrowerId"));
			book.setBorrowedOn((String) ent.getProperty("borrowedOn"));
			book.setDueDate((String) ent.getProperty("dueDate"));
			books.add(book);
		}
		return books;
	}
	
	public static boolean addUserIdInBook(long bookId, long userId) {
	    DatastoreService dataStoreService = DatastoreServiceFactory.getDatastoreService();
	    Filter propertyFilter = new FilterPredicate("id", FilterOperator.EQUAL, bookId);
	    Query query = new Query("Book").setFilter(propertyFilter);
		PreparedQuery pq = dataStoreService.prepare(query);
		Book book = new Book();
		Key key = null;
		for(Entity ent:pq.asIterable()) {
			book.setId((long) ent.getProperty("id"));
			book.setName( (String) ent.getProperty("name"));
			try {
				book.setBarrowerId(userId);
			}catch(NullPointerException nullPointerExc) {
				System.out.println(nullPointerExc);
			}
			LocalDate currentDate = LocalDate.now();
			book.setBorrowedOn(currentDate + "");
			LocalDate currentPlusFifteen = currentDate.plusDays(15);
			book.setDueDate(currentPlusFifteen+"");
			key = ent.getKey();
		}
		try {
			dataStoreService.delete(key);
			pm.makePersistent(book);
			System.out.println("book barrowed sucessfully.!");
			return true;
		}catch(NullPointerException nullPointerExc){
			return false;
		}
	}
	
	public static boolean updateUserIdInBook(long bookId) {
		Filter propertyFilter =null;
	    try {
	    	 propertyFilter = new FilterPredicate("id", FilterOperator.EQUAL, bookId);
		}catch(NullPointerException nullPointerExc){
			return false;
		}
	    
	    Query query = new Query("Book").setFilter(propertyFilter);
		PreparedQuery pq = dataStoreService.prepare(query);
		Book book = new Book();
		
		Key key = null;
		for(Entity ent:pq.asIterable()) {
			System.out.println("getting ion");
			book.setId( (long) ent.getProperty("id"));
			book.setName((String) ent.getProperty("name"));
			book.setBarrowerId((long) 0);
			book.setBorrowedOn(null);
			book.setDueDate(null);
			key = ent.getKey();
		}
		
		try {
			dataStoreService.delete(key);
			pm.makePersistent(book);
			System.out.println("repleced sucessfully");
			return true;
		}catch(NullPointerException nullPointerExc){
			return false;
		}
	}
	
	public static boolean addNewBook(Book book) {
		pm.makePersistent(book);
		return true;
	}

	public static List<Book> getAllBooks() {
		Query query = new Query("Book");
		List<Book> allBooks = new ArrayList<>();
		for(Entity ent:dataStoreService.prepare(query).asIterable()) {
			Book book = new Book();
			book.setId((long) ent.getProperty("id"));
			book.setName((String) ent.getProperty("name"));
			book.setBarrowerId((Long) ent.getProperty("barrowerId"));
			book.setBorrowedOn((String) ent.getProperty("borrowedOn"));
			book.setDueDate((String) ent.getProperty("dueDate"));
			allBooks.add(book);
		}
		return allBooks;
	}
	
	public static List<Student> getAllStudent(){
		Query query = new Query("Student");
		List<Student> allStudent = new ArrayList<>();
		for (Entity entity : dataStoreService.prepare(query).asIterable()) {
			Student student = new Student();
			student.setId((long) entity.getProperty("id"));
			student.setName( (String) entity.getProperty("name"));
			student.setProfilePic((Blob) entity.getProperty("profilePic"));
			allStudent.add(student);
		}
		return allStudent;
	}

	public static List<Staff> getAllStaff() {
		// TODO Auto-generated method stub
		Query StaffQuery = new Query("Staff");
		List<Staff> allStaff = new ArrayList<>();
		for (Entity entity : dataStoreService.prepare(StaffQuery).asIterable()) {
			Staff staff = new Staff();
			staff.setId((long) entity.getProperty("id"));
			staff.setName( (String) entity.getProperty("name"));
			staff.setProfilePic((Blob) entity.getProperty("profilePic"));
			allStaff.add(staff);
		}
		return allStaff;
	}
	
	public static boolean addStudent(int id,String name,String password,Blob imageBlob) {
		Student student = getStudent(id);
		if(student.getId()!=0) {
			return false;
		}else {
			student = new Student((long)id, name, password,imageBlob);
			pm.makePersistent(student);
			return true;
		}
	}
	
	public static boolean addStaff(int id,String name,String password,Blob imageBlob) {
		Staff staff = getStaff(id);
		if(staff ==null || staff.getId()==0) {
			return false;
		}else {
			staff = new Staff((long)id, name, password,imageBlob);
			pm.makePersistent(staff);
			return true;
		}
	}

	public static boolean uploadImage(long id, Blob imageBlob,String userType) {
		Filter propertyFilter =null;
	    try {
	    	 propertyFilter = new FilterPredicate("id", FilterOperator.EQUAL, id);
		}catch(NullPointerException nullPointerExc){
			return false;
		}
	    
	    Query query = new Query(userType).setFilter(propertyFilter);
		PreparedQuery pq = dataStoreService.prepare(query);
		User user = new Student();
		Key key = null;
		for(Entity ent:pq.asIterable()) {
			System.out.println("getting ion");
			user.setId((long) ent.getProperty("id"));
			user.setName((String) ent.getProperty("name"));
			user.setPassword((String) ent.getProperty("password"));
			user.setProfilePic(imageBlob);
			key = ent.getKey();
		}
		
		try {
			dataStoreService.delete(key);
			pm.makePersistent(user);
			System.out.println("repleced sucessfully");
			return true;
		}catch(NullPointerException nullPointerExc){
			return false;
		}
	}
}
