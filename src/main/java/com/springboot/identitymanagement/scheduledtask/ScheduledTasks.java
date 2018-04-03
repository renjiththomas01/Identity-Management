package com.springboot.identitymanagement.scheduledtask;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.springboot.identitymanagement.backend.UserDTO;
import com.springboot.identitymanagement.backend.UserService;

@Component
public class ScheduledTasks {
	private final UserService service;

	private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

	@Autowired
	ScheduledTasks(UserService service) {
		this.service = service;
	}

	//call the method in every minute
	@Scheduled(fixedRate = 60000)
	public void displayAllUsers() {
		//Retrieve all users from service
		List<UserDTO> allUsers = service.findAll();

		StringBuffer users = new StringBuffer();
		for (UserDTO user : allUsers) {
			users.append(user.getUserName());
			users.append(";");
		}
		
		if (null != users && users.length() != 0) {
			log.info("The current users are: {}", users.toString());
		} else {
			log.info("No users are created");
		}
	}
}
