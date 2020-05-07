package com.group.touchefinale.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;

import com.group.touchefinale.entities.Artiste;
import com.group.touchefinale.entities.Evenement;
import com.group.touchefinale.entities.Lieu;
import com.group.touchefinale.entities.Photo;
import com.group.touchefinale.entities.Salle;


public interface EvenementRepository extends JpaRepository<Evenement, Long>{
	
	List<Evenement>findAllByOrderByIdevenementDesc();
	List<Evenement>findAllByOrderByDatedebutevenementDesc();
	List<Evenement> findByArtisteOrderByDatedebutevenementDesc(Artiste artiste);
	@Query("select e from Evenement e where e.datedebutevenement >= :x and e.datedebutevenement <= :y")
	Page<Evenement>chercherDateEvenement(@Param("x")
										 @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
										 Date d1, 
										 
										 @Param("y")
										 @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
										 Date d2, Pageable pageable);
	
	  List<Evenement> findAllByArtiste(Artiste artiste);
	
	  List<Evenement> findAllBySalle(Salle salle);
	  
	  @Query("select e from Evenement e where e.datedebutevenement >= :x")
	  List<Evenement> findAll(Date x);
	
	
	  @Query(value = "Select e FROM Evenement e WHERE e.salle.lieu.continentlieu= :a") 
	  public List<Evenement>evenementsurlecontinent(@Param("a")String a);
	  
	  
	  
	  @Query(value = "Select e FROM Evenement e WHERE e.salle.lieu.continentlieu= :a AND e.datedebutevenement >= :x") 
	  public List<Evenement>prochainevenement(@Param("a")String a, @Param("x")Date x);
	  
	  @Query(value = "Select e FROM Evenement e WHERE e.salle.lieu.continentlieu= :a AND e.datedebutevenement < :x") 
	  public List<Evenement>evenementpasse(@Param("a")String a, @Param("x")Date x);	  
	  
	  @Query(value = "Select e FROM Evenement e WHERE e.artiste.idartiste= :a AND e.datedebutevenement >= :x") 
	  public List<Evenement>dateArtistesAVenir(@Param("a")Long a, @Param("x")Date x);	  
	  
	  @Query(value = "Select e FROM Evenement e WHERE e.artiste.idartiste= :a AND e.datedebutevenement < :x") 
	  public List<Evenement>dateArtistesTermine(@Param("a")Long a, @Param("x")Date x);	  
	  
	  @Query(value = "Select e FROM Evenement e WHERE e.salle.lieu.villelieu= :a AND e.datedebutevenement > :x") 
	  public List<Evenement>evenementSurLaVilleAVenir(@Param("a")String a, @Param("x")Date x);
	  
	  @Query(value = "Select e FROM Evenement e WHERE e.salle.lieu.villelieu= :a AND e.datedebutevenement < :x") 
	  public List<Evenement>evenementSurLaVilleTermine(@Param("a")String a, @Param("x")Date x);	  
	  
	  @Query(value = "Select e FROM Evenement e WHERE e.datedebutevenement >= :x") 
	  public List<Evenement>evenementAvecVideo(@Param("x")Date x);
	
	
	/*
	 * SELECT CLI_NOM, TEL_NUMERO FROM T_CLIENT C, T_TELEPHONE T WHERE C.CLI_ID =
	 * T.CLI_ID AND TYP_CODE = 'FAX'
	 */

}
