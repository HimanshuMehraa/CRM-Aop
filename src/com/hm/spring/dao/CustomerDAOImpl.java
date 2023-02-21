package com.hm.spring.dao;

import java.util.List;

import org.apache.taglibs.standard.lang.jstl.test.beans.PublicBean1;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hm.spring.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {
    @Autowired
	private SessionFactory sessionFactory;
    
    @Override
	public List<Customer> getCustomers() {
          
    	Session currentSession= sessionFactory.getCurrentSession();
    	
    	//create a query
    	Query<Customer> theQuery = currentSession.createQuery("from Customer order by lastName",Customer.class);
    	
    	//execute query and get the results
    	List<Customer> customers = theQuery.getResultList();

		return customers;
	}

	@Override
	public void saveCustomer(Customer theCustomer) {
		//get current hibernate session
		Session currentSession= sessionFactory.getCurrentSession();
		//save the cutomer
		currentSession.saveOrUpdate(theCustomer);
	}

	@Override
	public Customer getCustomer(int theId) {
		//get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		//now retrieve/read data from DB using primary key
		Customer theCustomer= currentSession.get(Customer.class,theId);
		
		return theCustomer;
	}

	@Override
	public void deleteCustomer(int theId) {
		//get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		//delete object with the primary key
		Query theQuery = currentSession.createQuery("delete from Customer where id=:customerId");
		theQuery.setParameter("customerId",theId);
		
		theQuery.executeUpdate();
		
	}
}
