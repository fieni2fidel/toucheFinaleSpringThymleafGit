package com.group.touchefinale.controllers;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
public class MenuPrincipalEuropeController {
	
	@Autowired
	private ArtisteRepository artisteRepository;
	
	@Autowired
	private BiographieRepository biographieRepository;
	
	@Autowired
	private EvenementRepository evenementRepository;

	/*------------------------------------------------------------------------------------*/
	/*
	 * @RequestMapping(value="/europe") public String menu_principal_europe(Model
	 * model) {
	 * 
	 * List<Evenement> listeEvenementAVenir =
	 * evenementRepository.prochainevenement("europe", new Date());
	 * Collections.sort(listeEvenementAVenir);
	 * Collections.reverse(listeEvenementAVenir);
	 * model.addAttribute("listeEvenementAVenir", listeEvenementAVenir);
	 * 
	 * List<Evenement> listeEvenementTermine =
	 * evenementRepository.evenementpasse("europe", new Date());
	 * Collections.sort(listeEvenementTermine);
	 * //Collections.reverse(listeEvenementTermine);
	 * model.addAttribute("listeEvenementTermine", listeEvenementTermine);
	 * 
	 * 
	 * return "front_end/fe_europe_menu_principal/menuPrincipalEurope";
	 * 
	 * }
	 */
}


