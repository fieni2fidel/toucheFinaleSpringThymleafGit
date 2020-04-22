package com.group.touchefinale;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import com.group.touchefinale.dao.VisiteurRepository;
import com.group.touchefinale.entities.Visiteur;

public class MethodesUtiles {

	
	public Visiteur voirVisiteur(HttpServletRequest request ) {
	Visiteur visiteur = null;
	
	if (request!=null) {
		
		 visiteur=new Visiteur(request.getHeader("referer"), request.getHeader("cf-ipcountry"),
				request.getHeader("accept-language"), request.getHeader("cookie"),
				request.getHeader("x-forwarded-for"), request.getHeader("x-real-ip"),
				request.getHeader("x-forwarded-server"), request.getHeader("x-forwarded-host"),
				request.getHeader("cf-visitor"), request.getHeader("connection"), 
				request.getHeader("cf-connecting-ip"), request.getHeader("user-agent"),
				new Date());

	}

	 return visiteur;
	}
	
}
