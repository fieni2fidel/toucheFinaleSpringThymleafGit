package com.group.touchefinale.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.group.touchefinale.entities.Photo;


public interface PhotoRepository extends JpaRepository<Photo, Long>{
	
	@Query("select e from Photo e where e.nomphoto like :x")
	public Page<Photo>chercherPhotos(@Param("x")String mc, Pageable pageable);

}
