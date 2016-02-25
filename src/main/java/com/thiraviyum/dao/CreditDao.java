package com.thiraviyum.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.thiraviyum.domain.Credit;
import com.thiraviyum.domain.User;

public interface CreditDao extends CrudRepository<Credit, Long> {

	public List<Credit> findAllByCreditUserOrderByEffectiveDateDesc(User user);
	public List<Credit> findAllByCreditUserAndEffectiveYearOrderByEffectiveDate(User user, String effectiveYear);
}
