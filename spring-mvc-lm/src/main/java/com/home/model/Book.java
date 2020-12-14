package com.home.model;

import java.time.LocalDateTime;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;



@PersistenceCapable
public class Book {
	
	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;
	
	
	@Persistent
	private long id;
	
	@Persistent
	private String name;
	
	@Persistent
	private Long barrowerId;
	
	@Persistent
	private String borrowedOn;
	
	@Persistent
	private String dueDate;
	
	public Book(){}
	
	public Book(long i,String name,long j,String borrowedOn,String dueDate ){
		this.id = i;
		this.name = name;
		this.barrowerId = j;
		this.borrowedOn = borrowedOn;
		this.dueDate = dueDate;
	}
	
//	public LocalDateTime getBorrowedOn() {
//		return LocalDateTime.parse(this.borrowedOn);
//	}
//	
//	public LocalDateTime getDueDate() {
//		return LocalDateTime.parse(this.dueDate);
//	}
	
	public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		this.key = key;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getBarrowerId() {
		return barrowerId;
	}

	public void setBarrowerId(Long barrowerId) {
		this.barrowerId = barrowerId;
	}

	public String getBorrowedOn() {
		return borrowedOn;
	}

	public void setBorrowedOn(String borrowedOn) {
		this.borrowedOn = borrowedOn;
	}

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	@Override
	public String toString() {
		return "Book [bookId=" + id + ", name=" + name + "]";
	}
}
