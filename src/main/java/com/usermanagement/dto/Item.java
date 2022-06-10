package com.usermanagement.dto;

import java.util.Set;

import com.usermanagement.data.User;

public class Item {


	private long itemId;
	private  String title;
	private String Description;
	
	private Set<Comments> comments;

	private User user;
	public User getUser() {
		return user;
	}
	
	public long getItemId() {
		return itemId;
	}

	public void setItemId(long itemId) {
		this.itemId = itemId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public Set<Comments> getComments() {
		return comments;
	}

	public void setComments(Set<Comments> comments) {
		this.comments = comments;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	

	public Item() {
		super();
	}

	public Item(long itemId, String title, String description, Set<Comments> comments, User user) {
		super();
		this.itemId = itemId;
		this.title = title;
		Description = description;
		this.comments = comments;
		this.user = user;
	}
	
	
	
	

	
}
