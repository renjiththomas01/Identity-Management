package com.springboot.identitymanagement.backend;

import static com.springboot.identitymanagement.validation.UserValidation.isTrue;
import static com.springboot.identitymanagement.validation.UserValidation.notEmpty;
import static com.springboot.identitymanagement.validation.UserValidation.notNull;
import static com.springboot.identitymanagement.validation.UserValidation.validateUserRole;

import org.springframework.data.annotation.Id;

final class User {	
 
    @Id
    private String id;
    
    private String name;
    
    private String userName;
    
    private String password;

    private String email;  
    
    private String userRole;

    public User() {}

    private User(Builder builder) {
        this.name = builder.name;
        this.userName = builder.userName;
        this.password = builder.password;
        this.email = builder.email;
        this.userRole = builder.userRole;
    }

    static Builder getBuilder() {
        return new Builder();
    }

    public String getId() {
        return id;
    }

    public void update(String name, String userName, String password, String email, String userRole) {

        this.name = name;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.userRole = userRole;
    }

    public String getName() {
		return name;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}
	
	public String getUserRole() {
		return userRole;
	}

	@Override
    public String toString() {
        return String.format(
                "User[id=%s, name=%s, userName=%s, password=%s, email=%s, userRole=%s]",
                this.id,
                this.name,
                this.userName,
                this.password,
                this.email,
                this.userRole
        );
    }

 
    static class Builder {

        private String name;

        private String userName;
        
        private String password;
        
        private String email;
        
        private String userRole;

        private Builder() {}

        Builder name(String name) {
            this.name = name; 
            return this;
        }

        Builder userName(String userName) {
            this.userName = userName;
            return this;
        }
        
        Builder password(String password) {
            this.password = password; 
            return this;
        }

        Builder email(String email) {
            this.email = email;
            return this;
        }
        
        Builder userRole(String userRole) {
            this.userRole = userRole;
            return this;
        }

        User build() {
            User build = new User(this);
            
            build.inputValidations(build.getName(),build.getUserName(), build.getPassword(), build.getUserRole());

            return build;
        }
    }
    
    private void inputValidations(String name, String userName, String password, String userRole) {
        notNull(name, "Name cannot be null");
        notNull(userName, "userName cannot be null");
        notNull(password, "password cannot be null");
        notNull(userRole, "userRole should be either 'admin' or 'user' ");
        notEmpty(name, "Name cannot be empty");
        notEmpty(userName, "userName cannot be empty");
        notEmpty(password, "password cannot be empty");
        notEmpty(userRole, "userRole should be either 'admin' or 'user' ");
        isTrue(validateUserRole().contains(userRole.toUpperCase()),
                "User roles should be either 'admin' or 'user'"           
        );
        
    }
}
