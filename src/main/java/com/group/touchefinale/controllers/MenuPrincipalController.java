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
	@RequestMapping(value= {"/","/menuprincipal"})
	public String menu_principal_frontend(Model model) {

	  List<Evenement> listeEvenementSurleContinentAfricain = evenementRepository.evenementsurlecontinent("afrique");
	  Collections.sort(listeEvenementSurleContinentAfricain);
	  model.addAttribute("listeEvenementSurleContinentAfricain", listeEvenementSurleContinentAfricain);
	  
	  List<Evenement> listeEvenementSurleContinentEuropeen = evenementRepository.evenementsurlecontinent("europe");
	  Collections.sort(listeEvenementSurleContinentEuropeen);
	  //Collections.reverse(listeEvenementSurleContinentEuropeen);
	  model.addAttribute("listeEvenementSurleContinentEuropeen", listeEvenementSurleContinentEuropeen);
	  
	  List<Evenement> listeEvenementSurleContinentAmericain = evenementRepository.evenementsurlecontinent("amerique");
	  Collections.sort(listeEvenementSurleContinentAmericain);
	  //Collections.reverse(listeEvenementSurleContinentAmericain);
	  model.addAttribute("listeEvenementSurleContinentAmericain", listeEvenementSurleContinentAmericain);
	  
	  
	  // ---------------------------------------------------------------------------------------------
	  
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
	
	
	@RequestMapping(value="/{continent}")
	public String menu_principal_afrique(Model model, @PathVariable("continent") String continent) {

	  List<Evenement> listeEvenementAVenir = evenementRepository.prochainevenement(continent, new Date());
	  Collections.sort(listeEvenementAVenir);
	  Collections.reverse(listeEvenementAVenir);
	  model.addAttribute("listeEvenementAVenir", listeEvenementAVenir);
	  
	  List<Evenement> listeEvenementTermine = evenementRepository.evenementpasse(continent, new Date());
	  Collections.sort(listeEvenementTermine);
	  //Collections.reverse(listeEvenementTermine);
	  model.addAttribute("listeEvenementTermine", listeEvenementTermine);
	  
	  
	  return "front_end/fe_menuprincipal/menuPrincipalContinent";
	  
	  }
	
	/*-----------------------------------lister les artistes -------------------------------------------------*/

	@RequestMapping(value = "/recherche")
	public String liste_artistes(Model model, @RequestParam(name = "motcleRP", defaultValue = "") String motcle) {

		List<Artiste> listDesArtistes = artisteRepository.rechercherArtistes("%" + motcle + "%");
		model.addAttribute("listDesArtistes", listDesArtistes);

		List<Evenement>listEvenementParVilleAVenir=evenementRepository.evenementSurLaVilleAVenir(motcle, new Date());
		model.addAttribute("listEvenementParVilleAVenir", listEvenementParVilleAVenir);
		
		List<Evenement>listEvenementParVilleTermine=evenementRepository.evenementSurLaVilleTermine(motcle, new Date());
		model.addAttribute("listEvenementParVilleTermine", listEvenementParVilleTermine);
		
		model.addAttribute("motcle", motcle);
		
		return "front_end/fe_menuprincipal/ResultatRecherche";
	}
	
}


