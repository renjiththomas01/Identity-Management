package com.springboot.identitymanagement.backend;

public final class UserDTO {

    private String id;
    
    private String name;

    private String userName;
    
    private String password;
    
    private String email;
    
    private String userRole;

    public UserDTO() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}	
	
	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	@Override
    public String toString() {
        return String.format(
                "UserDTO[id=%s, name=%s, userName=%s, password=%s, email=%s, userRole=%s]",
                this.id,
                this.name,
                this.userName,
                this.password,
                this.email,
                this.userRole
        );
    }
}
