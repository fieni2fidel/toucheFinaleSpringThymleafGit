package com.group.touchefinale.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;


@Entity
public class Biographie implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idbiographie;
	
	private String nombiographie;
	
	@Column(length=1000)
	private String descriptionbiographie;
	
	@ManyToOne
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn
	private Artiste artiste;
	

	public Artiste getArtiste() {
		return artiste;
	}

	public void setArtiste(Artiste artiste) {
		this.artiste = artiste;
	}
	

	public Biographie() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public Biographie(String nombiographie, String descriptionbiographie, Artiste artiste) {
		super();
		this.nombiographie = nombiographie;
		this.descriptionbiographie = descriptionbiographie;
		this.artiste = artiste;
	}
	

	public Long getIdbiographie() {
		return idbiographie;
	}

	public void setIdbiographie(Long idbiographie) {
		this.idbiographie = idbiographie;
	}

	public String getNombiographie() {
		return nombiographie;
	}

	public void setNombiographie(String nombiographie) {
		this.nombiographie = nombiographie;
	}

	public String getDescriptionbiographie() {
		return descriptionbiographie;
	}

	public void setDescriptionbiographie(String descriptionbiographie) {
		this.descriptionbiographie = descriptionbiographie;
	}
	
	

}
