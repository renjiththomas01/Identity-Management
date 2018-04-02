package com.springboot.identitymanagement.scheduledtask;

import java.text.SimpleDateFormat;
import java.util.Date;
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

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	@Autowired
	ScheduledTasks(UserService service) {
		this.service = service;
	}

	@Scheduled(fixedRate = 60000)
	public void reportCurrentTime() {
		List<UserDTO> allUsers = service.findAll();

		StringBuffer users = new StringBuffer();
		for (UserDTO user : allUsers) {
			users.append(user.getUserName());
			users.append(";");
		}
		log.info("The time is now {}", dateFormat.format(new Date()));
		if (null != users && users.length() != 0) {
			log.info("The users are {}", users.toString());
		} else {
			log.info("No users are created");
		}
	}
}
