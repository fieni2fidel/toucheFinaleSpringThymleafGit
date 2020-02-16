package com.group.touchefinale.controllers;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.group.touchefinale.dao.ArtisteRepository;
import com.group.touchefinale.dao.BiographieRepository;
import com.group.touchefinale.dao.EvenementRepository;
import com.group.touchefinale.entities.Artiste;
import com.group.touchefinale.entities.Biographie;
import com.group.touchefinale.entities.Evenement;
import com.group.touchefinale.entities.Video;


@Controller
public class BackEndAccueilController {
	
	@Autowired
	private ArtisteRepository artisteRepository;
	
	@Autowired
	private BiographieRepository biographieRepository;
	
	@Autowired
	private EvenementRepository evenementRepository;

	/*------------------------------------------------------------------------------------*/
	
	  @RequestMapping(value= "/afroo_admin_menu") public
	  String menu_principal_afroo_admin_menu(Model model) {
	  
	  return "admin/be_menuPrincipal";
	  
	  }
	 
	
	  
	  @RequestMapping(value= "/tf_connect") 
	  public String menu_principal_frontend() {
	  
	  return "admin/tf_connect";
	  
	  }
	  
	  // Login form with error
	  
	  @RequestMapping(value= "/login_error")
	  public String loginError(Model model) {
	    model.addAttribute("loginError", true);
	    return "admin/tf_connect";
	  }
	  
	  // Login form with error
	  @RequestMapping(value= "/deconnexion")
	  public String deconnexion(Model model) {
	    model.addAttribute("deconnecte", true);
	    return "admin/tf_connect";
	  }
	  
	  @RequestMapping(value= "/getUser")
	  @ResponseBody
	  public Map<String, Object>getLogedUser(HttpServletRequest httpServletRequest){
		  HttpSession httpSession=httpServletRequest.getSession();
		  SecurityContext securityContext=(SecurityContext) httpSession.getAttribute("SPRING_SECURITY_CONTEXT");
		  String username=securityContext.getAuthentication().getName();
		  List<String>roles=new ArrayList<>();
		  for (GrantedAuthority ga:securityContext.getAuthentication().getAuthorities()) {
			  roles.add(ga.getAuthority());
			
		}
		  
		  Map<String, Object>params=new HashMap<>();
		  params.put("username", username);
		  params.put("roles", roles);
		  return params;
	  }
	
	/*-----------------------------------------------------------*/

	
}


