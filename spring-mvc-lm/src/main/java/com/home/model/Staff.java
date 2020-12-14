package com.home.model;

import java.util.ArrayList;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Blob;
import com.google.appengine.api.datastore.Key;



@PersistenceCapable
public class Staff implements User{
	
	

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private  Key key;
	
	@Persistent
	private long id;
	
	@Persistent
	private String name;
	
	@Persistent
	private String password;
	
	@Persistent
	private Blob profilePic;
	
	
	public Staff(long id, String name, String password,Blob profilepPic){
		this.id = id;
		this.name = name;
		this.password = password;
		this.profilePic = profilepPic;
	}
	public Staff(){}

	@Override
	public void setId(long id) {
		// TODO Auto-generated method stub
		this.id = id;
	}

	@Override
	public void setName(String name) {
		// TODO Auto-generated method stub
		this.name = name;
	}

	@Override
	public void setPassword(String password) {
		// TODO Auto-generated method stub
		this.password = password;
	}


	@Override
	public long getId() {
		// TODO Auto-generated method stub
		return this.id;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.name;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.password;
	}
	@Override
	public void setProfilePic(Blob image) {
		// TODO Auto-generated method stub
		this.profilePic = image;
		
	}
	@Override
	public Blob getProfilePic() {
		// TODO Auto-generated method stub
		return this.profilePic;
	}

}
