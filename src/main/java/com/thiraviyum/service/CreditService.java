package com.thiraviyum.service;

import java.util.List;

import com.thiraviyum.domain.Credit;
import com.thiraviyum.domain.User;

public interface CreditService {
	public void save(Credit credit, User user);
	public void delete(Credit credit);
	public Credit find(Long id);
	public List<Credit> find(String year, User user);
}
