package com.trade.core.common.datasource;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;

public abstract class AbstractCustomRepo {
	@Autowired
	private EntityManager entityManager;

	protected Session getSession() {
		return entityManager.unwrap(Session.class);
	}
}
