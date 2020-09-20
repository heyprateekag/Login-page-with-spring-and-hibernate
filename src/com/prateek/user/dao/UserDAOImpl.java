package com.prateek.user.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.prateek.user.entity.User;

@Repository
public class UserDAOImpl implements UserDAO {

	//need to inject the session factory
	@Autowired
	private SessionFactory sessionFactory;
	@Override
	@Transactional
	public List<User> getUsers() {

		//get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		//create a query
		Query<User> theQuery = currentSession.createQuery("from User", User.class);

		//execute query and get result list
		List<User> users = theQuery.getResultList();

		//return the results
		return users;
	}

}
