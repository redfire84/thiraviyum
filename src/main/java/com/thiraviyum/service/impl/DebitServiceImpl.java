package com.thiraviyum.service.impl;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thiraviyum.dao.DebitDao;
import com.thiraviyum.domain.Debit;
import com.thiraviyum.domain.User;
import com.thiraviyum.service.DebitService;

@Service
public class DebitServiceImpl implements DebitService {
	@Autowired
	private DebitDao debitDao;

	@Override
	@Transactional
	public void save(Debit debit, User user) {
		debit.setEffectiveYear(new SimpleDateFormat("yyyy").format(debit.getEffectiveDate()));
		debit.setDebitUser(user);
		debit.setModUser(user.getUsername());
		debitDao.save(debit);
	}

	@Override
	@Transactional
	public void delete(Debit debit) {
		debitDao.delete(debit);
	}

	@Override
	@Transactional
	public Debit find(Long id) {
		return debitDao.findOne(id);
	}

	@Override
	@Transactional
	public List<Debit> find(String year, User user) {
		return debitDao.findAllByDebitUserAndEffectiveYearOrderByEffectiveDate(user, year);
	}

}
