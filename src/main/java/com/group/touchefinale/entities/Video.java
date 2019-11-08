package com.group.touchefinale.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Video implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idvideo;
	
	private String nomvideo;
	
	private String urlvideo;

	@ManyToOne
	@JoinColumn
	private Artiste artiste;

	public Video(String nomvideo, String urlvideo) {
		super();
		this.nomvideo = nomvideo;
		this.urlvideo = urlvideo;
	}

	public Video(Long idvideo, String nomvideo, String urlvideo) {
		super();
		this.idvideo = idvideo;
		this.nomvideo = nomvideo;
		this.urlvideo = urlvideo;
	}
	
	

	public Video(Long idvideo, String nomvideo, String urlvideo, Artiste artiste) {
		super();
		this.idvideo = idvideo;
		this.nomvideo = nomvideo;
		this.urlvideo = urlvideo;
		this.artiste = artiste;
	}

	public Video() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getIdvideo() {
		return idvideo;
	}

	public void setIdvideo(Long idvideo) {
		this.idvideo = idvideo;
	}

	public String getNomvideo() {
		return nomvideo;
	}

	public void setNomvideo(String nomvideo) {
		this.nomvideo = nomvideo;
	}

	public String getUrlvideo() {
		return urlvideo;
	}

	public void setUrlvideo(String urlvideo) {
		this.urlvideo = urlvideo;
	}

	public Artiste getArtiste() {
		return artiste;
	}

	public void setArtiste(Artiste artiste) {
		this.artiste = artiste;
	}
	
	
	
	
}
