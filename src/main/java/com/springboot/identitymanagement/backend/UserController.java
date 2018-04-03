package com.springboot.identitymanagement.backend;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
final class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private final UserService service;

    @Autowired
    UserController(UserService service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    UserDTO create(@RequestBody @Valid UserDTO userEntry) {
        LOGGER.info("Creating a new user entry with information: {}", userEntry);

        UserDTO created = service.create(userEntry);
        LOGGER.info("Created a new user entry with information: {}", created);

        return created;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    UserDTO delete(@PathVariable("id") String id) {
        LOGGER.info("Deleting user entry with id: {}", id);
        
        /*Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        auth.getPrincipal();
        LOGGER.info("auth.getPrincipal(): {}", auth.getPrincipal());
        LOGGER.info("auth.getName(): ", auth.getName());
        LOGGER.info("auth.isAuthenticated(): {}", auth.isAuthenticated());*/

        UserDTO deleted = service.delete(id);
        LOGGER.info("Deleted user entry with information: {}", deleted);

        return deleted;
    }

   /* @RequestMapping(method = RequestMethod.GET)
    List<UserDTO> findAll() {
        LOGGER.info("Finding all user entries");

        List<UserDTO> userEntries = service.findAll();
        LOGGER.info("Found {} user entries", userEntries.size());

        return userEntries;
    }*/

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    UserDTO read(@PathVariable("id") String id) {
        LOGGER.info("Finding user entry with id: {}", id);

        UserDTO userEntry = service.read(id);
        LOGGER.info("Found user entry with information: {}", userEntry);

        return userEntry;
    }
    
    @RequestMapping(value = "{userName}/{password}", method = RequestMethod.GET)
    ResponseEntity login(@PathVariable("userName") String userName, @PathVariable("password") String password) {
        LOGGER.info("Finding login entry with userName: {}", userName);

        boolean loginMatched = service.login(userName, password);
        LOGGER.info("Found login entry with information: {}", loginMatched);
        
        if (loginMatched) {
        	LOGGER.info("Found user entry");
        return new ResponseEntity(HttpStatus.OK);
        } else {
        	LOGGER.info("No user entry");
        	return new ResponseEntity(HttpStatus.FORBIDDEN);
        }
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    UserDTO update(@RequestBody @Valid UserDTO userEntry) {
        LOGGER.info("Updating user entry with information: {}", userEntry);

        UserDTO updated = service.update(userEntry);
        LOGGER.info("Updated user entry with information: {}", updated);

        return updated;
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleTodoNotFound(UserNotFoundException ex) {
        LOGGER.error("Handling error with message: {}", ex.getMessage());
    }
}
