package com.group.touchefinale.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.group.touchefinale.entities.Artiste;
import com.group.touchefinale.entities.Lieu;
import com.group.touchefinale.entities.Photo;
import com.group.touchefinale.entities.Salle;

public interface SalleRepository extends JpaRepository<Salle, Long>{
	
	List<Salle> findAllByOrderByNomsalle();
	
	@Query("select e from Salle e where e.nomsalle like :x")
	public Page<Salle>chercherNomSalle(@Param("x")String mc, Pageable pageable);
	
	List<Salle> findAllBySalle(Lieu lieu);
	
}
