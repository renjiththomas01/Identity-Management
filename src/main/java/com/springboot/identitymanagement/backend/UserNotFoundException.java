package com.springboot.identitymanagement.backend;

public class UserNotFoundException extends RuntimeException {
	
	public UserNotFoundException(String id) {
        super(String.format("No user entry found with id: <%s>", id));
    }
}
