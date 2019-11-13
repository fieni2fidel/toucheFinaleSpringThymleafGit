package com.group.touchefinale.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.group.touchefinale.entities.Artiste;
import com.group.touchefinale.entities.Photo;
import com.group.touchefinale.entities.Salle;
import com.group.touchefinale.entities.Video;

public interface VideoRepository extends JpaRepository<Video, Long>{
	
	@Query("select e from Video e where e.nomvideo like :x")
	public Page<Video>chercherVideo(@Param("x")String mc, Pageable pageable);
	
	List<Video> findAllByArtiste(Artiste artiste);

}
