package com.group.touchefinale.dao;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.group.touchefinale.entities.Artiste;
import com.group.touchefinale.entities.Lieu;
import com.group.touchefinale.entities.Salle;


public interface LieuRepository extends JpaRepository<Lieu, Long>{

	List<Lieu> findAllByOrderByIdlieuDesc();
	List<Lieu> findAllByOrderByPayslieu();
	List<Lieu> findAllByOrderByVillelieu();
	
	
	
	@Query("select e from Lieu e where e.villelieu like :x")
	public Page<Lieu>chercherLieux(@Param("x")String mc, Pageable pageable);
	
	
}
