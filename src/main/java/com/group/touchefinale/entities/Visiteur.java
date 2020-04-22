package com.group.touchefinale.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Visiteur implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idvisiteur;
	
	private String referer ;
	 
	private String cfipcountry ;
	 
	private	String acceptlanguage;
	 
	private	String cookie ;
	
	private	String xforwardedfor; //<------ This is client real IP

	private	String xrealip ; //<------ This is cloudflare IP
	 
	private	String xforwardedserver;
	 
	private	String xforwardedhost;
	 
	private	String cfvisitor;

	private	String connection;
	 
	private	String cfconnectingip;

	private	String useragent;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private	Date datedeconnexion;

	public Visiteur() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Visiteur(String referer, String cfipcountry, String acceptlanguage, String cookie, String xforwardedfor,
			String xrealip, String xforwardedserver, String xforwardedhost, String cfvisitor, String connection,
			String cfconnectingip, String useragent, Date datedeconnexion) {
		super();
		this.referer = referer;
		this.cfipcountry = cfipcountry;
		this.acceptlanguage = acceptlanguage;
		this.cookie = cookie;
		this.xforwardedfor = xforwardedfor;
		this.xrealip = xrealip;
		this.xforwardedserver = xforwardedserver;
		this.xforwardedhost = xforwardedhost;
		this.cfvisitor = cfvisitor;
		this.connection = connection;
		this.cfconnectingip = cfconnectingip;
		this.useragent = useragent;
		this.datedeconnexion = datedeconnexion;
	}

	public Long getIdvisiteur() {
		return idvisiteur;
	}

	public void setIdvisiteur(Long idvisiteur) {
		this.idvisiteur = idvisiteur;
	}

	public String getReferer() {
		return referer;
	}

	public void setReferer(String referer) {
		this.referer = referer;
	}

	public String getCfipcountry() {
		return cfipcountry;
	}

	public void setCfipcountry(String cfipcountry) {
		this.cfipcountry = cfipcountry;
	}

	public String getAcceptlanguage() {
		return acceptlanguage;
	}

	public void setAcceptlanguage(String acceptlanguage) {
		this.acceptlanguage = acceptlanguage;
	}

	public String getCookie() {
		return cookie;
	}

	public void setCookie(String cookie) {
		this.cookie = cookie;
	}

	public String getXforwardedfor() {
		return xforwardedfor;
	}

	public void setXforwardedfor(String xforwardedfor) {
		this.xforwardedfor = xforwardedfor;
	}

	public String getXrealip() {
		return xrealip;
	}

	public void setXrealip(String xrealip) {
		this.xrealip = xrealip;
	}

	public String getXforwardedserver() {
		return xforwardedserver;
	}

	public void setXforwardedserver(String xforwardedserver) {
		this.xforwardedserver = xforwardedserver;
	}

	public String getXforwardedhost() {
		return xforwardedhost;
	}

	public void setXforwardedhost(String xforwardedhost) {
		this.xforwardedhost = xforwardedhost;
	}

	public String getCfvisitor() {
		return cfvisitor;
	}

	public void setCfvisitor(String cfvisitor) {
		this.cfvisitor = cfvisitor;
	}

	public String getConnection() {
		return connection;
	}

	public void setConnection(String connection) {
		this.connection = connection;
	}

	public String getCfconnectingip() {
		return cfconnectingip;
	}

	public void setCfconnectingip(String cfconnectingip) {
		this.cfconnectingip = cfconnectingip;
	}

	public String getUseragent() {
		return useragent;
	}

	public void setUseragent(String useragent) {
		this.useragent = useragent;
	}

	public Date getDatedeconnexion() {
		return datedeconnexion;
	}

	public void setDatedeconnexion(Date datedeconnexion) {
		this.datedeconnexion = datedeconnexion;
	}
	
	
	

}
