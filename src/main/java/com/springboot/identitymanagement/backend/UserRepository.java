package com.springboot.identitymanagement.backend;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.Repository;

interface UserRepository extends Repository<User, String> {

    void delete(User deleted);

    List<User> findAll();

    Optional<User> findOne(String id);

    User save(User saved);
}
