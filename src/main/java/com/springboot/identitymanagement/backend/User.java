package com.springboot.identitymanagement.backend;

import org.springframework.data.annotation.Id;

final class User {

 
    @Id
    private String id;
    
    private String name;
    
    private String userName;
    
    private String password;

    private String email;    

    public User() {}

    private User(Builder builder) {
        this.name = builder.name;
        this.userName = builder.userName;
        this.password = builder.password;
        this.email = builder.email;
    }

    static Builder getBuilder() {
        return new Builder();
    }

    public String getId() {
        return id;
    }

    public void update(String name, String userName, String password, String email) {

        this.name = name;
        this.userName = userName;
        this.password = password;
        this.email = email;
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

	@Override
    public String toString() {
        return String.format(
                "User[id=%s, name=%s, userName=%s, password=%s, email=%s]",
                this.id,
                this.name,
                this.userName,
                this.password,
                this.email
        );
    }

    /**
     * We don't have to use the builder pattern here because the constructed class has only two String fields.
     * However, I use the builder pattern in this example because it makes the code a bit easier to read.
     */
    static class Builder {

        private String name;

        private String userName;
        
        private String password;
        
        private String email;

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

        User build() {
            User build = new User(this);

            return build;
        }
    }
}
