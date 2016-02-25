package com.thiraviyum.service;

import java.util.List;

import com.thiraviyum.domain.Debit;
import com.thiraviyum.domain.User;

public interface DebitService {
	public void save(Debit debit, User user);
	public void delete(Debit debit);
	public Debit find(Long id);
	public List<Debit> find(String year, User user);
}
