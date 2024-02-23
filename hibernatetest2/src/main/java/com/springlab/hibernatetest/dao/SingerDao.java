package com.springlab.hibernatetest.dao;

import java.util.List;

import com.springlab.hibernatetest.entities.Singer;

public interface SingerDao {
	
	List<Singer> findAll();
	List<Singer> findAllWithAlbum();
	Singer findById(Long id);
	void insert(Singer singer);
	void update(Singer singer);
	void delete(Singer singer);
	
}
