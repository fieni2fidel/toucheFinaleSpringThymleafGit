package com.group.touchefinale.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Evenement implements Serializable, Comparable<Evenement> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idevenement;

	private String nomevenement;

	private String infoevenement;

	private String tarifevenement;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date datedebutevenement;

	private String heureevenement;

	@ManyToOne
	@JoinColumn
	private Artiste artiste;

	@ManyToOne
	@JoinColumn
	private Salle salle;

	public Evenement() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Evenement(Long idevenement, String nomevenement, Date datedebutevenement) {
		super();
		this.idevenement = idevenement;
		this.nomevenement = nomevenement;
		this.datedebutevenement = datedebutevenement;

	}

	public Evenement(String nomevenement, Date datedebutevenement) {
		super();
		this.nomevenement = nomevenement;
		this.datedebutevenement = datedebutevenement;

	}

	public Evenement(Long idevenement, String nomevenement, String infoevenement, String tarifevenement,
			Date datedebutevenement, String heureevenement, Artiste artiste, Salle salle) {
		super();
		this.idevenement = idevenement;
		this.nomevenement = nomevenement;
		this.infoevenement = infoevenement;
		this.tarifevenement = tarifevenement;
		this.datedebutevenement = datedebutevenement;
		this.heureevenement = heureevenement;
		this.artiste = artiste;
		this.salle = salle;
	}

	public Long getIdevenement() {
		return idevenement;
	}

	public void setIdevenement(Long idevenement) {
		this.idevenement = idevenement;
	}

	public String getNomevenement() {
		return nomevenement;
	}

	public void setNomevenement(String nomevenement) {
		this.nomevenement = nomevenement;
	}

	public Date getDatedebutevenement() {
		return datedebutevenement;
	}

	public void setDatedebutevenement(Date datedebutevenement) {
		this.datedebutevenement = datedebutevenement;
	}

	public Artiste getArtiste() {
		return artiste;
	}

	public void setArtiste(Artiste artiste) {
		this.artiste = artiste;
	}

	public Salle getSalle() {
		return salle;
	}

	public void setSalle(Salle salle) {
		this.salle = salle;
	}

	public String getInfoevenement() {
		return infoevenement;
	}

	public void setInfoevenement(String infoevenement) {
		this.infoevenement = infoevenement;
	}

	public String getTarifevenement() {
		return tarifevenement;
	}

	public void setTarifevenement(String tarifevenement) {
		this.tarifevenement = tarifevenement;
	}

	public String getHeureevenement() {
		return heureevenement;
	}

	public void setHeureevenement(String heureevenement) {
		this.heureevenement = heureevenement;
	}

	@Override
	public int compareTo(Evenement o) {
		return o.getDatedebutevenement().compareTo(getDatedebutevenement());
	}

}
