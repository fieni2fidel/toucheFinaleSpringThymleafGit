package com.group.touchefinale.controllers;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;

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
import com.group.touchefinale.dao.VideoRepository;
import com.group.touchefinale.entities.Artiste;
import com.group.touchefinale.entities.Biographie;
import com.group.touchefinale.entities.Evenement;
import com.group.touchefinale.entities.Video;

@Controller
public class MenuPrincipalController {
	
	@Autowired
	private ArtisteRepository artisteRepository;

	
	@Autowired
	private EvenementRepository evenementRepository;
	
	@Autowired
	private VideoRepository videoRepository;

	/*------------------------------------------------------------------------------------*/
	@RequestMapping(value= {"/","/menuprincipal"})
	public String menu_principal_frontend(Model model) {
		
		
		List<Evenement>listeEvenementSurleContinentAfricain=new ArrayList<>();
	  List<Evenement> listeEvenementSurleContinentAfricainXX = evenementRepository.evenementsurlecontinent("afrique");
	//liste des artiste qui remplissent les 3 conditions (photo, biographie, video)
	 for (Evenement laa : listeEvenementSurleContinentAfricainXX) { if
	 ((!laa.getArtiste().getPhotos().isEmpty())&&(!laa.getArtiste().
	 getVideos().isEmpty())) { listeEvenementSurleContinentAfricain.add(laa); } }
			  
	  Collections.sort(listeEvenementSurleContinentAfricain);
	  model.addAttribute("listeEvenementSurleContinentAfricain", listeEvenementSurleContinentAfricain);
	  
	  
	  List<Evenement>listeEvenementSurleContinentEuropeen=new ArrayList<>();
	  List<Evenement> listeEvenementSurleContinentEuropeenXX = evenementRepository.evenementsurlecontinent("europe");
	//liste des artiste qui remplissent les 3 conditions (photo, biographie, video)
		 for (Evenement laa : listeEvenementSurleContinentEuropeenXX) { if
		 ((!laa.getArtiste().getPhotos().isEmpty())&&(!laa.getArtiste().
		 getVideos().isEmpty())) { listeEvenementSurleContinentEuropeen.add(laa); } }
		 
	  Collections.sort(listeEvenementSurleContinentEuropeen);
	  //Collections.reverse(listeEvenementSurleContinentEuropeen);
	  model.addAttribute("listeEvenementSurleContinentEuropeen", listeEvenementSurleContinentEuropeen);
	  
	  List<Evenement>listeEvenementSurleContinentAmericain=new ArrayList<>();
	  List<Evenement> listeEvenementSurleContinentAmericainXX = evenementRepository.evenementsurlecontinent("amerique");
	//liste des artiste qui remplissent les 3 conditions (photo, biographie, video)
		 for (Evenement laa : listeEvenementSurleContinentAmericainXX) { if
		 ((!laa.getArtiste().getPhotos().isEmpty())&&(!laa.getArtiste().
		 getVideos().isEmpty())) { listeEvenementSurleContinentAmericain.add(laa); } }
	  
	  Collections.sort(listeEvenementSurleContinentAmericain);
	  //Collections.reverse(listeEvenementSurleContinentAmericain);
	  model.addAttribute("listeEvenementSurleContinentAmericain", listeEvenementSurleContinentAmericain);
	  
	  
	  // ---------------------------------------------------------------------------------------------
	  
	  List<Evenement>listeProchainEvenementEnAfriqueAA=new ArrayList<>();
	  List<Evenement>listeProchainEvenementEnAfriqueXX=evenementRepository.prochainevenement("afrique",new Date());
	//liste des artiste qui remplissent les 3 conditions (photo, biographie, video)
		
		  for (Evenement laa : listeProchainEvenementEnAfriqueXX) { if
		  ((!laa.getArtiste().getPhotos().isEmpty())&&(!laa.getArtiste().
		  getVideos().isEmpty())) { listeProchainEvenementEnAfriqueAA.add(laa); } }
		 
	  
	  Collections.sort(listeProchainEvenementEnAfriqueAA);
	  Collections.reverse(listeProchainEvenementEnAfriqueAA);
	  model.addAttribute("listeProchainEvenementEnAfriqueAA", listeProchainEvenementEnAfriqueAA);
	  
	  
	  List<Evenement>listeProchainEvenementEnEuropeEE=new ArrayList<>();
	  List<Evenement>listeProchainEvenementEnEuropeXX=evenementRepository.prochainevenement("europe",new Date());
	  for (Evenement laa : listeProchainEvenementEnEuropeXX) { if
		  ((!laa.getArtiste().getPhotos().isEmpty())&&(!laa.getArtiste().
		  getVideos().isEmpty())) { listeProchainEvenementEnEuropeEE.add(laa); } }
	  
	  Collections.sort(listeProchainEvenementEnEuropeEE);
	  Collections.reverse(listeProchainEvenementEnEuropeEE);
	  model.addAttribute("listeProchainEvenementEnEuropeEE", listeProchainEvenementEnEuropeEE);
	  
	  
	  
	  List<Evenement>listeProchainEvenementEnAmeriqueAM=new ArrayList<>();
	  List<Evenement>listeProchainEvenementEnAmeriqueXX=evenementRepository.prochainevenement("amerique",new Date());
	  for (Evenement laa : listeProchainEvenementEnAmeriqueXX) { if
		  ((!laa.getArtiste().getPhotos().isEmpty())&&(!laa.getArtiste().
		  getVideos().isEmpty())) { listeProchainEvenementEnAmeriqueAM.add(laa); } }
	  
	  Collections.sort(listeProchainEvenementEnAmeriqueAM);
	  Collections.reverse(listeProchainEvenementEnAmeriqueAM);
	  model.addAttribute("listeProchainEvenementEnAmeriqueAM", listeProchainEvenementEnAmeriqueAM);
	  
	  
	  List<Video>listeVideox=videoRepository.findAll();
		 Collections.shuffle(listeVideox, new Random()); 
	  model.addAttribute("listeVideox", listeVideox);
	  
	  return "front_end/fe_menuprincipal/menuPrincipal";
	  
	  }
	
	
	@RequestMapping(value="/{continent}")
	public String menu_principal_afrique(Model model, @PathVariable("continent") String continent) {

	  List<Evenement> listeEvenementAVenir = evenementRepository.prochainevenement(continent, new Date());
	  List<Evenement>tabloEvenementAVenir=new ArrayList<>();
	  for (Evenement EE : listeEvenementAVenir) {
		if ((!(EE.getArtiste().getPhotos().isEmpty()))&&(!(EE.getArtiste().getVideos().isEmpty()))) {
			tabloEvenementAVenir.add(EE);
		}
	}
	  
	  Collections.sort(tabloEvenementAVenir);
	  Collections.reverse(tabloEvenementAVenir);
	  model.addAttribute("tabloEvenementAVenir", tabloEvenementAVenir);
	  
	  
	  List<Evenement>tabloEvenementATerminer=new ArrayList<>();
	  List<Evenement> listeEvenementTermine = evenementRepository.evenementpasse(continent, new Date());
	  for (Evenement EE : listeEvenementTermine) {
		  if ((!(EE.getArtiste().getPhotos().isEmpty()))&&(!(EE.getArtiste().getVideos().isEmpty()))) {
			  tabloEvenementATerminer.add(EE);
			}
	}
	  Collections.sort(tabloEvenementATerminer);
	  //Collections.reverse(listeEvenementTermine);
	  model.addAttribute("tabloEvenementATerminer", tabloEvenementATerminer);
	  
	  
	  return "front_end/fe_menuprincipal/menuPrincipalContinent";
	  
	  }
	
	/*-----------------------------------lister les artistes -------------------------------------------------*/

	@RequestMapping(value = "/recherche")
	public String liste_artistes(Model model, @RequestParam(name = "motcleRP", defaultValue = "") String motcle) {
		
		 List<Artiste>listDesArtistes=new ArrayList<>();
		List<Artiste> listDesArtistesXX = artisteRepository.rechercherArtistes("%" + motcle + "%");
		
		//liste des artiste qui remplissent les 3 conditions (photo, biographie, video)
				
				  for (Artiste laa : listDesArtistesXX) { if
				  ((!laa.getPhotos().isEmpty())&&(!laa.getBiographies().isEmpty())&&(!laa.
				  getVideos().isEmpty())) { listDesArtistes.add(laa); } }
				 
		
		model.addAttribute("listDesArtistes", listDesArtistes);
		
		List<Evenement>listEvenementParVilleAVenir=new ArrayList<>();
		List<Evenement>listEvenementParVilleAVenirXX=evenementRepository.evenementSurLaVilleAVenir(motcle, new Date());
		
		 for (Evenement laa : listEvenementParVilleAVenirXX) { if
			  ((!laa.getArtiste().getPhotos().isEmpty())&&(!laa.getArtiste().
			  getVideos().isEmpty())) { listEvenementParVilleAVenir.add(laa); } }
		 
		model.addAttribute("listEvenementParVilleAVenir", listEvenementParVilleAVenir);
		
		List<Evenement>listEvenementParVilleTermine=new ArrayList<>();
		List<Evenement>listEvenementParVilleTermineXX=evenementRepository.evenementSurLaVilleTermine(motcle, new Date());
		
		for (Evenement laa : listEvenementParVilleTermineXX) { if
			  ((!laa.getArtiste().getPhotos().isEmpty())&&(!laa.getArtiste().
			  getVideos().isEmpty())) { listEvenementParVilleTermine.add(laa); } }
		 
		model.addAttribute("listEvenementParVilleTermine", listEvenementParVilleTermine);
		
		model.addAttribute("motcle", motcle);
		
		return "front_end/fe_menuprincipal/ResultatRecherche";
	}
	
	
	@RequestMapping(value="/videos")
	public String videos(Model model) {
		
		/*
		 * List<Video>listePageVideo=videoRepository.findAll();
		 * Collections.reverse(listePageVideo); model.addAttribute("listePageVideo",
		 * listePageVideo);
		 */
		
		ArrayList<String> tabloArt = new ArrayList<String>(Arrays.asList( "A", "B", "C","D","E","F","G","H","I","J","K","L","M"
				,"N","O","P","Q","R","S","T","U","V","W","X","Y","Z") );
		 List<Artiste>listeArtisteAZ=new ArrayList<>();
		for(int i = 0; i < tabloArt.size(); i++)
		{
		    System.out.println(tabloArt.get(i));
		    List<Artiste> listeAZ = artisteRepository.findByNomcompletartisteStartingWith(tabloArt.get(i));
		    
		    
		    for (Artiste z : listeAZ) {
				if ((!z.getPhotos().isEmpty())&&(!z.getBiographies().isEmpty())&&(!z.getVideos().isEmpty())&&(!z.getEvenements().isEmpty())){
					listeArtisteAZ.add(z);
				}
				
				//model.addAttribute("listeArtisteAZ", listeArtisteAZ);
		    }
		}

		model.addAttribute("listeArtisteAZ", listeArtisteAZ);
		model.addAttribute("tabloArt", tabloArt);
		
		List<Artiste> artistefrontend = artisteRepository.findAll();

			Collections.reverse(artistefrontend);
			model.addAttribute("artistefrontend", artistefrontend);
		
		return "front_end/fe_videos/videos";
	}
	
}


