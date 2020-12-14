package com.home.model;

import com.google.appengine.api.datastore.Blob;

public interface User {
	 public void setId(long id);
	 public void setName(String name);
	 public void setPassword(String password);
	 public void setProfilePic(Blob image);
	 public long getId();
	 public String getName();
	 public String getPassword();
	 public Blob getProfilePic();
}
