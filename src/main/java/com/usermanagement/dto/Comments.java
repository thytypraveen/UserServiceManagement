package com.usermanagement.dto;

public class Comments {
	

	
    private long commentId;
	private String comments; 
	
	private Item item;
	private Long userId;
	public long getCommentId() {
		return commentId;
	}
	public void setCommentId(long commentId) {
		this.commentId = commentId;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	
	public Comments() {
		super();
	}
	public Comments(long commentId, String comments, Item item, Long userId) {
		super();
		this.commentId = commentId;
		this.comments = comments;
		this.item = item;
		this.userId = userId;
	}
	
	
	


}
