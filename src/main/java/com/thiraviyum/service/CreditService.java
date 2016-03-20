package com.thiraviyum.service;

import java.util.List;

import com.thiraviyum.domain.Credit;
import com.thiraviyum.domain.User;

public interface CreditService {
	public void save(Credit credit, User user);
	public void delete(Credit credit);
	public Credit find(Long id, User user);
	public List<Credit> find(Integer year, User user);
}
