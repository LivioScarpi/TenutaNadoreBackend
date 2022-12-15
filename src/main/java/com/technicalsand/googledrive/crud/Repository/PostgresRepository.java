package com.technicalsand.googledrive.crud.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.technicalsand.googledrive.crud.model.Review;

public interface PostgresRepository extends JpaRepository<Review, Long>{
	List<Review> findByUsername(String username);
	List<Review> findAll();
}