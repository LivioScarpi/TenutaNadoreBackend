package com.technicalsand.googledrive.crud.Repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.technicalsand.googledrive.crud.model.Review;

public interface PostgresRepository extends CrudRepository<Review, Long>{
	List<Review> findByUsername(String username);
	List<Review> findAll();
}