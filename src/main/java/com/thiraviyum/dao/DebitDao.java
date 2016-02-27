package com.thiraviyum.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.thiraviyum.domain.Debit;
import com.thiraviyum.domain.User;

public interface DebitDao extends CrudRepository<Debit, Long> {

	public List<Debit> findAllByDebitUserOrderByEffectiveDate(User user);
	public List<Debit> findAllByDebitUserAndEffectiveYearOrderByEffectiveDate(User user, String effectiveYear);
}
