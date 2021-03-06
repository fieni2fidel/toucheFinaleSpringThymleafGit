package com.group.touchefinale.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.annotation.sql.DataSourceDefinition;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PostLoad;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;

import com.group.touchefinale.dao.ArtisteRepository;

import ch.qos.logback.core.joran.spi.NoAutoStart;
import net.bytebuddy.implementation.bind.annotation.Default;

@Entity
public class Artiste implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idartiste;
	
	private String nomcompletartiste;
	
	 @DateTimeFormat(pattern="yyyy-MM-dd")
	 private Date datenaissanceartiste;
	
	private String origineartiste;
	
	@OneToMany(fetch = FetchType.LAZY)
	private Collection<Video>videos;
	
    @OneToMany(fetch = FetchType.LAZY)
	private Collection<Photo>photos;
	
	@OneToMany(fetch = FetchType.LAZY)
	private Collection<Biographie>biographies;
	
	@OneToMany(fetch = FetchType.EAGER)
	private Collection<Evenement>evenements;

//	@PostLoad
//	public void findBiographiesByArtiste() {
//		biographies = artRepo.findBiographieByIdArtist(idartiste);
////		biographies = new ArrayList<Biographie>();
////		Biographie b = new Biographie("nombiographie", "descriptionbiographie");
////		b.setIdbiographie(idartiste);
////		biographies.add(b);
//	}
	public Artiste() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Artiste(Long idartiste, String nomcompletartiste, Date datenaissanceartiste, String origineartiste,
			Collection<Video> videos, Collection<Photo> photos, Collection<Biographie> biographies) {
		super();
		this.idartiste = idartiste;
		this.nomcompletartiste = nomcompletartiste;
		this.datenaissanceartiste = datenaissanceartiste;
		this.origineartiste = origineartiste;
		this.videos = videos;
		this.photos = photos;
		this.biographies=biographies;
	}

	public Artiste(Long idartiste) {
		super();
		this.idartiste = idartiste;
	}

	public Artiste(String nomcompletartiste, Date datenaissanceartiste, String origineartiste, Collection<Video> videos,
			Collection<Photo> photos) {
		super();
		this.nomcompletartiste = nomcompletartiste;
		this.datenaissanceartiste = datenaissanceartiste;
		this.origineartiste = origineartiste;
		this.videos = videos;
		this.photos = photos;
	}

	public Artiste(String nomcompletartiste, Date datenaissanceartiste, String origineartiste) {
		super();
		this.nomcompletartiste = nomcompletartiste;
		this.datenaissanceartiste = datenaissanceartiste;
		this.origineartiste = origineartiste;
	}

	public Long getIdartiste() {
		return idartiste;
	}

	public void setIdartiste(Long idartiste) {
		this.idartiste = idartiste;
	}

	public String getNomcompletartiste() {
		return nomcompletartiste;
	}

	public void setNomcompletartiste(String nomcompletartiste) {
		this.nomcompletartiste = nomcompletartiste;
	}

	public Date getDatenaissanceartiste() {
		return datenaissanceartiste;
	}

	public void setDatenaissanceartiste(Date datenaissanceartiste) {
		this.datenaissanceartiste = datenaissanceartiste;
	}

	public String getOrigineartiste() {
		return origineartiste;
	}

	public void setOrigineartiste(String origineartiste) {
		this.origineartiste = origineartiste;
	}

	public Collection<Video> getVideos() {
		return videos;
	}

	public void setVideos(Collection<Video> videos) {
		this.videos = videos;
	}

	public Collection<Photo> getPhotos() {
		return photos;
	}

	public void setPhotos(Collection<Photo> photos) {
		this.photos = photos;
	}

	public Collection<Biographie> getBiographies() {
		return biographies;
	}

	public void setBiographies(Collection<Biographie> biographies) {
		this.biographies = biographies;
	}
	
	

	public Collection<Evenement> getEvenements() {
		return evenements;
	}

	public void setEvenements(Collection<Evenement> evenements) {
		this.evenements = evenements;
	}

	@Override
	public String toString() {
		return "Artiste [idartiste=" + idartiste + ", nomcompletartiste=" + nomcompletartiste
				+ ", datenaissanceartiste=" + datenaissanceartiste + ", origineartiste=" + origineartiste + ", videos="
				+ videos + ", photos=" + photos + ", biographies=" + biographies + "]";
	}


	
	
}
