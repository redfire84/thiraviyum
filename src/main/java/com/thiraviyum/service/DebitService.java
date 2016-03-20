package com.thiraviyum.service;

import java.util.List;

import com.thiraviyum.domain.Debit;
import com.thiraviyum.domain.User;

public interface DebitService {
	public void save(Debit debit, User user);
	public void delete(Debit debit);
	public Debit find(Long id, User user);
	public List<Debit> find(Integer year, User user);
}
