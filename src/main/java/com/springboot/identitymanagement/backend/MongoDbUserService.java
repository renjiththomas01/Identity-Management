package com.springboot.identitymanagement.backend;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
final class MongoDbUserService implements UserService{

	 private static final Logger LOGGER = LoggerFactory.getLogger(MongoDbUserService.class);

	    private final UserRepository repository;

	    @Autowired
	    MongoDbUserService(UserRepository repository) {
	        this.repository = repository;
	    }

	    @Override
	    public UserDTO create(UserDTO user) {
	        LOGGER.info("Creating a new User entry with information: {}", user);

	        User persisted = User.getBuilder()
	                .name(user.getName())
	                .userName(user.getUserName())
	                .password(user.getPassword())
	                .email(user.getEmail())	                
	                .userRole(user.getUserRole())
	                .build();

	        persisted = repository.save(persisted);
	        LOGGER.info("Created a new User entry with information: {}", persisted);

	        return convertToDTO(persisted);
	    }

	@Override
	public String delete(String loginUserId, String deleteUserId) {
		LOGGER.info("Deleting a user entry with id: {}", deleteUserId);
		try {
			User loginUser = findUserById(loginUserId);
			User deletedUser = findUserById(deleteUserId);
			if (!loginUser.getUserRole().equalsIgnoreCase("admin")) {
				return "Delete user error: Only admin users can delete profiles";
			}
			repository.delete(deletedUser);
			LOGGER.info("Deleted user entry with informtation: {}", deletedUser);

		} catch (UserNotFoundException e) {
			LOGGER.info("UserNotFoundException thrown");
			return "Invalid user id";
		}
		return "User successfully deleted";
	}

	    @Override
	    public List<UserDTO> findAll() {
	        LOGGER.info("Finding all User entries.");

	        List<User> allUsers = repository.findAll();

	        LOGGER.info("Found {} User entries", allUsers.size());

	        return convertToDTOs(allUsers);
	    }

	    private List<UserDTO> convertToDTOs(List<User> models) {
	        return models.stream()
	                .map(this::convertToDTO)
	                .collect(toList());
	    }

	    @Override
	    public UserDTO read(String id) {
	        LOGGER.info("Finding user entry with id: {}", id);

	        User found = findUserById(id);

	        LOGGER.info("Found user entry: {}", found);

	        return convertToDTO(found);
	    }
	    
	    @Override
	    public boolean login(String userName, String password) {
	        LOGGER.info("Verifying user credentials for: {}", userName);

	        List<User> allUsers = repository.findAll();
	        
	        boolean loginMatched = false;
	        
	        for(User user : allUsers) {
	        	String tmpUserName = user.getUserName();
	        	if (tmpUserName.equals(userName)) {
	        		if(user.getPassword().equals(password)) {
	        			loginMatched = true;
	        			break;
	        		}
	        	}
	        }

	        LOGGER.info("Is user valid: {}", loginMatched);

	       return loginMatched;
	    }

	    @Override
	    public UserDTO update(UserDTO user) {
	        LOGGER.info("Updating user entry with information: {}", user);

	        User updated = findUserById(user.getId());
	      
	        updated.update(user.getName(), user.getUserName(),user.getPassword(), user.getEmail(), user.getUserRole());
	        updated = repository.save(updated);

	        LOGGER.info("Updated user entry with information: {}", updated);

	        return convertToDTO(updated);
	    }

	    private User findUserById(String id) {
	        Optional<User> result = repository.findOne(id);
	        return result.orElseThrow(() -> new UserNotFoundException(id));
	    }
	    
	   

	    private UserDTO convertToDTO(User model) {
	        UserDTO dto = new UserDTO();

	        dto.setId(model.getId());
	        dto.setName(model.getName());
	        dto.setUserName(model.getUserName());
	        dto.setPassword(model.getPassword());
	        dto.setEmail(model.getEmail());
	        dto.setUserRole(model.getUserRole());

	        return dto;
	    }
}
