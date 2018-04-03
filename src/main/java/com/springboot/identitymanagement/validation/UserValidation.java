package com.springboot.identitymanagement.validation;

import java.util.ArrayList;
import java.util.List;

public final class UserValidation {

	
	public static void isTrue(boolean expression, String errorMessage) {
        if (!expression) {
            throw new IllegalArgumentException(errorMessage);
        }
    }
 
    public static void notEmpty(String string, String errorMessage) {
        if (string.isEmpty()) {
            throw new IllegalArgumentException(errorMessage);
        }
    }
  
    public static void notNull(Object reference, String errorMessage) {
        if (reference == null) {
            throw new NullPointerException(errorMessage);
        }
    }
    
    public static List<String> validateUserRole() {
    	List<String> userRoleList = new ArrayList<String>();
        userRoleList.add("ADMIN");
        userRoleList.add("USER");
        return userRoleList;
    }
}
