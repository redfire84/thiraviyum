package com.thiraviyum.service.impl;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thiraviyum.dao.CreditDao;
import com.thiraviyum.domain.Credit;
import com.thiraviyum.domain.User;
import com.thiraviyum.service.CreditService;

@Service
public class CreditServiceImpl implements CreditService {
	@Autowired
	private CreditDao creditDao;

	@Override
	@Transactional
	public void save(Credit credit, User user) {
		credit.setCreditUser(user);
		credit.setModUser(user.getUsername());
		credit.setEffectiveYear(new SimpleDateFormat("yyyy").format(credit.getEffectiveDate()));
		creditDao.save(credit);
	}

	@Override
	@Transactional
	public void delete(Credit credit) {
		creditDao.delete(credit);
	}

	@Override
	@Transactional
	public Credit find(Long id) {
		return creditDao.findOne(id);
	}

	@Override
	@Transactional
	public List<Credit> find(String year, User user) {
		return creditDao.findAllByCreditUserAndEffectiveYearOrderByEffectiveDate(user, year);
	}

}
