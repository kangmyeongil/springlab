package com.springlab.hibernatetest.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.springlab.hibernatetest.entities.Singer;

@Repository("singerDao")
@Transactional
public class SingerDaoImpl implements SingerDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Transactional(readOnly = true)
	@Override
	public List<Singer> findAll() {
		// TODO Auto-generated method stub
		
		List<Singer> singers = sessionFactory.getCurrentSession().createNamedQuery(Singer.FIND_ALL, Singer.class).list();
		
		return singers;
	}

	
	@Override
	public List<Singer> findAllWithAlbum() {
		// TODO Auto-generated method stub
        List<Singer> singers = sessionFactory.getCurrentSession().createNamedQuery(Singer.FIND_ALL_WITH_ALBUM, Singer.class).list();
		
		return singers;
	}


	@Override
	public Singer findById(Long id) {
		// TODO Auto-generated method stub
		return sessionFactory.getCurrentSession().createNamedQuery(Singer.FIND_BY_ID, Singer.class).setParameter("id", id).uniqueResult();
	}

	@Override
	public void insert(Singer singer) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().persist(singer);
	}

	@Override
	public void update(Singer singer) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().merge(singer);
	}

	@Override
	public void delete(Singer singer) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().remove(singer);
	}

}
