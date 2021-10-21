package com.douzone.mysite.exception;

public class UserRepositoryException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public UserRepositoryException(String message) {
		super(message);
	}
	public UserRepositoryException() {
		super("User Repository 예외 발생");
	}
}
