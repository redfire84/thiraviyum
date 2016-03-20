package com.thiraviyum.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.thiraviyum.domain.Credit;
import com.thiraviyum.domain.User;

public interface CreditDao extends CrudRepository<Credit, Long> {

	public Credit findOneByIdAndCreditUser(Long id, User user);
	public List<Credit> findAllByCreditUserOrderByEffectiveDate(User user);
	public List<Credit> findAllByCreditUserAndEffectiveYearOrderByEffectiveDate(User user, Integer effectiveYear);
}
