package com.group.touchefinale.controllers;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
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
	  
	 
	/*
	 * @RequestMapping(value= "/getheader") public String
	 * getRequestHeadersInMap(HttpServletRequest request, Model model) {
	 * 
	 * Map<String, String> result = new HashMap<>();
	 * 
	 * Enumeration headerNames = request.getHeaderNames(); while
	 * (headerNames.hasMoreElements()) { String key = (String)
	 * headerNames.nextElement(); String value = request.getHeader(key);
	 * result.put(key, value); }
	 * 
	 * model.addAttribute("result", result);
	 * 
	 * String remoteAddr = ""; if (request != null) { remoteAddr =
	 * request.getHeader("referer"); if (remoteAddr == null ||
	 * "".equals(remoteAddr)) { remoteAddr = request.getRemoteAddr(); } }
	 * model.addAttribute("remoteAddr", remoteAddr);
	 * 
	 * String remoteAddr1 = ""; if (request != null) { remoteAddr1 =
	 * request.getHeader("cf-visitor"); if (remoteAddr1 == null ||
	 * "".equals(remoteAddr1)) { remoteAddr1 = request.getRemoteAddr(); } }
	 * model.addAttribute("remoteAddr1", remoteAddr1);
	 * 
	 * String remoteAddr2 = ""; if (request != null) { remoteAddr2 =
	 * request.getHeader("cookie"); if (remoteAddr2 == null ||
	 * "".equals(remoteAddr2)) { remoteAddr2 = request.getRemoteAddr(); } }
	 * model.addAttribute("remoteAddr2", remoteAddr2);
	 * 
	 * 
	 * String remoteAddr3 = ""; if (request != null) { remoteAddr3 =
	 * request.getHeader("referer"); if (remoteAddr3 == null ||
	 * "".equals(remoteAddr3)) { remoteAddr3 = request.getRemoteAddr(); } }
	 * model.addAttribute("remoteAddr3", remoteAddr3);
	 * 
	 * return "getheader"; }
	 */
	  	
	  
	  
	/*
	 * "referer" :"https://www.google.com/", "cf-ipcountry" :"US", "cf-ray"
	 * :"348c7acba8a02210-EWR", "x-forwarded-proto" :"https", "accept-language"
	 * :"en-US,en;q=0.8", "cookie"
	 * :"____cfduid=d3c6e5d73aa55b6b42fad9600c94849851490726068; __ga=GA1.2.450731937.1490726069"
	 * ,
	 * 
	 * "x-forwarded-for" :"100.8.204.40", //<------ This is client real IP
	 * 
	 * "accept"
	 * :"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,** /** ;q=0.8"
	 * ,
	 * 
	 * "x-real-ip" :"108.162.219.236", //<------ This is cloudflare IP
	 * 
	 * "x-forwarded-server" :"hostingcompass.com", "x-forwarded-host"
	 * :"hostingcompass.com", "cf-visitor" :"{\"scheme\":\"https\"}", "host"
	 * :"127.0.0.1:8080", "upgrade-insecure-requests" :"1", "connection" :"close",
	 * "cf-connecting-ip" :"100.8.204.40", "accept-encoding" :"gzip", "user-agent" :
	 * "Mozilla/5.0 (Macintosh; Intel Mac OS X 10__11__6) AppleWebKit/537.36 (KHTML,
	 * like Gecko) Chrome/56.0.2
	 */
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
	  
	/*
	 * @RequestMapping(value= "/getUser")
	 * 
	 * @ResponseBody public Map<String, Object>getLogedUser(HttpServletRequest
	 * httpServletRequest){ HttpSession httpSession=httpServletRequest.getSession();
	 * SecurityContext securityContext=(SecurityContext)
	 * httpSession.getAttribute("SPRING_SECURITY_CONTEXT"); String
	 * username=securityContext.getAuthentication().getName(); List<String>roles=new
	 * ArrayList<>(); for (GrantedAuthority
	 * ga:securityContext.getAuthentication().getAuthorities()) {
	 * roles.add(ga.getAuthority());
	 * 
	 * }
	 * 
	 * Map<String, Object>params=new HashMap<>(); params.put("username", username);
	 * params.put("roles", roles); return params; }
	 */
	
	/*-----------------------------------------------------------*/

	
}


