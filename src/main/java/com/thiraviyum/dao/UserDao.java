package com.thiraviyum.dao;

import org.springframework.data.repository.CrudRepository;

import com.thiraviyum.domain.User;

public interface UserDao extends CrudRepository<User, String> {

}
