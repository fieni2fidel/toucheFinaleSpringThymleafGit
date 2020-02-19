package com.group.touchefinale.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.group.touchefinale.entities.Artiste;
import com.group.touchefinale.entities.Biographie;
import com.group.touchefinale.entities.Lieu;
import com.group.touchefinale.entities.Photo;
import com.group.touchefinale.entities.Salle;

public interface BiographieRepository extends JpaRepository<Biographie, Long>{
	
	@Query("select e from Biographie e where e.nombiographie like :x")
	public Page<Biographie>chercherBiographies(@Param("x")String mc, Pageable pageable);
	
	List<Biographie> findAllByArtiste(Artiste artiste);
	/* List<Biographie> findAllBySalle(Salle salle); */
	

}
