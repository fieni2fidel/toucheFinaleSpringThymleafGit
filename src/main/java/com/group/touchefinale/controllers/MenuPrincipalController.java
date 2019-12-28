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
public class MenuPrincipalController {
	
	@Autowired
	private ArtisteRepository artisteRepository;
	
	@Autowired
	private BiographieRepository biographieRepository;
	
	@Autowired
	private EvenementRepository evenementRepository;

	/*------------------------------------------------------------------------------------*/
	@RequestMapping(value="/")
	public String menu_principal_frontend(Model model) {

	  List<Evenement> listeEvenementSurleContinentAfricain = evenementRepository.evenementsurlecontinentafricain();
	  Collections.sort(listeEvenementSurleContinentAfricain);
	  model.addAttribute("listeEvenementSurleContinentAfricain", listeEvenementSurleContinentAfricain);
	  
	  List<Evenement> listeEvenementSurleContinentEuropeen = evenementRepository.evenementsurlecontinenteuropeen();
	  Collections.sort(listeEvenementSurleContinentEuropeen);
	  //Collections.reverse(listeEvenementSurleContinentEuropeen);
	  model.addAttribute("listeEvenementSurleContinentEuropeen", listeEvenementSurleContinentEuropeen);
	  
	  List<Evenement> listeEvenementSurleContinentAmericain = evenementRepository.evenementsurlecontinentamericain();
	  Collections.sort(listeEvenementSurleContinentAmericain);
	  //Collections.reverse(listeEvenementSurleContinentAmericain);
	  model.addAttribute("listeEvenementSurleContinentAmericain", listeEvenementSurleContinentAmericain);
	  
	  List<Evenement>listeProchainEvenementEnAfriqueAA=evenementRepository.prochainevenement("afrique",new Date());
	  Collections.sort(listeProchainEvenementEnAfriqueAA);
	  Collections.reverse(listeProchainEvenementEnAfriqueAA);
	  model.addAttribute("listeProchainEvenementEnAfriqueAA", listeProchainEvenementEnAfriqueAA);
	  
	  List<Evenement>listeProchainEvenementEnEuropeEE=evenementRepository.prochainevenement("europe",new Date());
	  Collections.sort(listeProchainEvenementEnEuropeEE);
	  Collections.reverse(listeProchainEvenementEnEuropeEE);
	  model.addAttribute("listeProchainEvenementEnEuropeEE", listeProchainEvenementEnEuropeEE);
	  
	  List<Evenement>listeProchainEvenementEnAmeriqueAM=evenementRepository.prochainevenement("amerique",new Date());
	  Collections.sort(listeProchainEvenementEnAmeriqueAM);
	  Collections.reverse(listeProchainEvenementEnAmeriqueAM);
	  model.addAttribute("listeProchainEvenementEnAmeriqueAM", listeProchainEvenementEnAmeriqueAM);
	  
	  return "front_end/fe_menuprincipal/menuPrincipal";
	  
	  }
}


