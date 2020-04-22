package com.group.touchefinale.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.group.touchefinale.dao.ArtisteRepository;
import com.group.touchefinale.dao.BiographieRepository;
import com.group.touchefinale.dao.EvenementRepository;
import com.group.touchefinale.dao.PhotoRepository;
import com.group.touchefinale.dao.VideoRepository;
import com.group.touchefinale.dao.VisiteurRepository;
import com.group.touchefinale.entities.Artiste;
import com.group.touchefinale.entities.Biographie;
import com.group.touchefinale.entities.Evenement;
import com.group.touchefinale.entities.Photo;
import com.group.touchefinale.entities.Visiteur;

@Controller
public class ArtisteController {

	@Autowired
	private ArtisteRepository artisteRepository;

	@Autowired
	private PhotoRepository photoRepository;

	@Autowired
	private EvenementRepository evenementRepository;

	@Autowired
	private BiographieRepository biographieRepository;

	@Autowired
	private VideoRepository videoRepository;

	@Value("${x}")
	private String imageDir;

	
	@Autowired
	private VisiteurRepository visiteurRepository;

	/*------------------------------------------------------------------------------------*/
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
	
	/*------------------------------------------------------------------------------------*/
	
	@RequestMapping(value = "/formulaire_artiste", method = RequestMethod.GET)
	public String formulaire_artiste(Model model) {
		model.addAttribute("artiste", new Artiste());

		return "artiste/formulaireArtiste";
	}

	/*------------------------------------------------------------------------------------*/

	/* verifier et valider le formulaire */
	@RequestMapping(value = "/validation_formulaire_artiste", method = RequestMethod.POST)
	public String validation_formulaire_artiste(Artiste artiste) {

		artisteRepository.save(artiste);

		return "artiste/pageMessageConfirmationFormulaire";
	}

	/*-----------------------------------lister les artistes -------------------------------------------------*/
	@RequestMapping(value = "/liste_artistes")
	public String liste_artistes(Model model, @RequestParam(name = "pageRP", defaultValue = "0") int page,
			@RequestParam(name = "motcleRP", defaultValue = "") String motcle) {

		Page<Artiste> listDesArtistes = artisteRepository.chercherArtistes("%" + motcle + "%",
				new PageRequest(page, 10));

		int pagesCount = listDesArtistes.getTotalPages();
		int[] pages = new int[pagesCount];

		for (int i = 0; i < pagesCount; i++)
			pages[i] = i;

		for (Artiste artiste : listDesArtistes) {
			artiste.setPhotos(photoRepository.findAllByArtiste(artiste));
			artiste.setBiographies(biographieRepository.findAllByArtiste(artiste));
			artiste.setVideos(videoRepository.findAllByArtiste(artiste));
			artiste.setEvenements(evenementRepository.findAllByArtiste(artiste));
		}

		model.addAttribute("pages", pages);
		model.addAttribute("pageCourante", page);
		model.addAttribute("motcle", motcle);
		model.addAttribute("listDesArtistes", listDesArtistes);

		return "artiste/listeArtisteChanteurs";
	}

	/*---------------------------supprimer un artiste ---------------------------------------------------------*/
	@RequestMapping(value = "/supprimerartiste")
	public String supprimerartiste(Long id) {
		artisteRepository.deleteById(id);

		return "redirect:liste_artistes";
	}

	/*------------------------------------------------------------------------------------*/
	@RequestMapping(value = "/editerartiste")
	public String editerartiste(Long id, Model model) {

		Artiste artiste = artisteRepository.getOne(id);

		artiste.setEvenements(evenementRepository.findAllByArtiste(artiste));
		artiste.setBiographies(biographieRepository.findAllByArtiste(artiste));
		artiste.setPhotos(photoRepository.findAllByArtiste(artiste));
		artiste.setVideos(videoRepository.findAllByArtiste(artiste));
		// artiste.setBiographies(artisteRepository.findBiographieByIdArtist(id));
		model.addAttribute("artiste", artiste);

		return "artiste/modificationFormulaireArtiste";

	}
	/*------------------------------------------------------------------------------------*/

	/* mise a jour de lartiste */
	@RequestMapping(value = "/mise_a_jour_artiste", method = RequestMethod.POST)
	public String mise_a_jour_artiste(Artiste artiste) {

		artisteRepository.save(artiste);

		return "redirect:liste_artistes";
	}

	/*------------------------------------------------------------------------------------*/
	
	@RequestMapping(value = "/voirartiste")
	public String voirartiste(@RequestParam(value = "id") Long id, Model model) {

		Artiste artiste = artisteRepository.getOne(id);
		model.addAttribute("artiste", artiste);
		return "artiste/voirFormulaireArtiste";

	}

	/*------------------------------------------------------------------------------------*/
	
	@RequestMapping(value = "/artiste/{motcleArtiste}")
	public String artiste(Model model, Long id, @PathVariable("motcleArtiste") String motcleArtiste) {
		boolean ax = false;
		List<Artiste> nomArtiste = artisteRepository.findByNomcompletartisteLike("%" + motcleArtiste + "%");
		Artiste artistefrontend = new Artiste();

		for (Artiste x : nomArtiste) {
			artistefrontend = artisteRepository.getOne(x.getIdartiste());
			Collections.sort((List<Evenement>) artistefrontend.getEvenements());
			Collections.reverse((List<Evenement>) artistefrontend.getEvenements());
			model.addAttribute("artistefrontend", artistefrontend);
		}

		model.addAttribute("nomArtiste", nomArtiste);
		model.addAttribute("motcleArtiste", motcleArtiste);

		return "front_end/fe_artiste/meiway";
	}

	/*------------------------------------------------------------------------------------*/

	@RequestMapping(value = "/artistes")
	public String pageartisteavectouteslesdates(Long id, String nationalite, Model model, HttpServletRequest request) {
		
		// Donnees visiteurs ////////////////////////////////
		Visiteur vvi=voirVisiteur(request);
		visiteurRepository.save(vvi);
		// //////////////////////////////////

		/*----------------------dates de concerts a venir --------------------------------------------------------------*/
		 Artiste artistefrontend = artisteRepository.getOne(id); 
		 
		
		List<Evenement> listeDateArtistesAVenir= evenementRepository.dateArtistesAVenir(id, new Date());
		Collections.sort(listeDateArtistesAVenir);
		Collections.reverse(listeDateArtistesAVenir);
		model.addAttribute("listeDateArtistesAVenir", listeDateArtistesAVenir);
		
		model.addAttribute("artistefrontend", artistefrontend);
		
		/*-------------------------dates de concerts terminés -----------------------------------------------------------*/
		
		List<Evenement> listeDateArtistesTermine= evenementRepository.dateArtistesTermine(id, new Date());
		Collections.sort(listeDateArtistesTermine);
		
		model.addAttribute("listeDateArtistesTermine",listeDateArtistesTermine);
		
		/*-----------Afficher sur la side bar de droite les artistes ayant la meme nationalite que l'artiste-------------------------------------------------------------------------*/
		
		 Artiste artistefrontendComparaison = artisteRepository.getOne(id);
		 nationalite=artistefrontendComparaison.getOrigineartiste();
		 
		 List<Artiste>listeMemeNationalite=new ArrayList<>();
		 List<Artiste>listeMemeNationaliteXX=artisteRepository.chercherArtisteNationalite(nationalite);
		 
		//liste des artiste qui remplissent les 3 conditions (photo, biographie, video)
		  for (Artiste laa : listeMemeNationaliteXX) { 
			  if ((!laa.getPhotos().isEmpty())&&(!laa.getBiographies().isEmpty())&&(!laa.getVideos().isEmpty())) {
				  listeMemeNationalite.add(laa); 
			  } 
		}
	
		 	 
		model.addAttribute("listeMemeNationalite",listeMemeNationalite);
		Collections.shuffle(listeMemeNationalite, new Random());
		
		
		
		// ////////////////////////:::::
		
		//liste des artiste qui remplissent les 3 conditions (photo, biographie, video)
		/*
		 * for (Artiste laa : listeArtistes) { if
		 * ((!laa.getPhotos().isEmpty())&&(!laa.getBiographies().isEmpty())&&(!laa.
		 * getVideos().isEmpty())) { tabloArtisteNonVide.add(laa); } }
		 */

		return "front_end/fe_artiste/meiway";

	}

	/*------------------------------------------------------------------------------------*/
	
	@RequestMapping(value = "/artiste")
	public String listedebutnom(Model model, String debutnom) {

		//String[] monTableau = new String[] {"A", "B", "C"};
		/*
		 * Map<Long, String>test=new HashMap<Long, String>(); test.put((long) 1,
		 * "A");test.put((long) 2, "B");test.put((long) 3, "C");
		 */
		
		ArrayList<String> tabloArt = new ArrayList<String>(Arrays.asList( "A", "B", "C","D","E","F","G","H","I","J","K","L","M"
				,"N","O","P","Q","R","S","T","U","V","W","X","Y","Z") );
		 List<Artiste>listeArtisteAZ=new ArrayList<>();
		for(int i = 0; i < tabloArt.size(); i++)
		{
		    System.out.println(tabloArt.get(i));
		    List<Artiste> listeAZ = artisteRepository.findByNomcompletartisteStartingWith(tabloArt.get(i));
		    
		    
		    for (Artiste z : listeAZ) {
				if ((!z.getPhotos().isEmpty())&&(!z.getBiographies().isEmpty())&&(!z.getVideos().isEmpty())){
					listeArtisteAZ.add(z);
				}
				
				//model.addAttribute("listeArtisteAZ", listeArtisteAZ);
		    }
		}

		model.addAttribute("listeArtisteAZ", listeArtisteAZ);
		model.addAttribute("tabloArt", tabloArt);
		
		return "front_end/fe_artiste/menuPrincipalArtiste";
	}
		
	
	/*------------------------------------------------------------------------------------*/
	
	
	

	@RequestMapping(value = "/recherche_alphabetique")
	public String recherchealphabetique(String lettre, Model model, HttpServletRequest request) {

		/*------------------------------------------------------------------------------------*/
		
		// Donnees visiteurs ////////////////////////////////
				Visiteur vvi=voirVisiteur(request);
				visiteurRepository.save(vvi);
			// //////////////////////////////////

		 List<Artiste>listeArtisteAZ=new ArrayList<>();

		 List<Artiste> listeAZ = artisteRepository.findByNomcompletartisteStartingWith(lettre);
		    
		    
		    for (Artiste z : listeAZ) {
				if ((!z.getPhotos().isEmpty())&&(!z.getBiographies().isEmpty())&&(!z.getVideos().isEmpty())){
					listeArtisteAZ.add(z);
				}
				
				model.addAttribute("listeArtisteAZ", listeArtisteAZ);
		    }
				
		/*------------------------ -----------------------------------------------------------*/

		return "front_end/fe_artiste/listeArtisteDeAaZ";

	}
	

	//controller pour afficher tous les artistes ayant au moins 1 photo 
	
	@RequestMapping(value = "/requetes_indispensables")
	public String requetesindispensables(Model model) {

		//List<Photo> listeArtistes= photoRepository.listeArtisteAvecAuMoinsUnePhoto();
		List<Artiste> listeArtistes= artisteRepository.findAllByOrderByNomcompletartiste();
		List<Artiste>tabloArtiste3CNR=new ArrayList<>();
		List<Artiste>tabloArtisteCondPhotoNR=new ArrayList<>();
		List<Artiste>tabloArtisteCondVideoNR=new ArrayList<>();
		List<Artiste>tabloArtisteCondBioNR=new ArrayList<>();
		List<Artiste>tabloArtisteNonVide=new ArrayList<>();
		
		List<Artiste>tabloArtiste1Concert3CNR=new ArrayList<>();
		
		//artistes qui à au moins 1 concert mais ne remplit pas l'un des 3 conditions (photo, video, biographie)    
				for (Artiste lxx : listeArtistes) {
					if ((lxx.getPhotos().isEmpty()||lxx.getBiographies().isEmpty()||lxx.getVideos().isEmpty())&&(!(lxx.getEvenements().isEmpty()))) {
						tabloArtiste1Concert3CNR.add(lxx);
					}
				}
				model.addAttribute("tabloArtiste1Concert3CNR", tabloArtiste1Concert3CNR);
		
		
		//liste des artistes qui ne remplit pas l'une des 3 conditions(photo, biographie, video)    
		for (Artiste lxx : listeArtistes) {
			if (lxx.getPhotos().isEmpty()||lxx.getBiographies().isEmpty()||lxx.getVideos().isEmpty()) {
				tabloArtiste3CNR.add(lxx);
			}
		}
		model.addAttribute("tabloArtiste3CNR", tabloArtiste3CNR);
		
		//liste des artistes sans aucune photo   
				for (Artiste lxx : listeArtistes) {
					if (lxx.getPhotos().isEmpty()) {
						tabloArtisteCondPhotoNR.add(lxx);
					}
				}
		model.addAttribute("tabloArtisteCondPhotoNR", tabloArtisteCondPhotoNR);
				
		//liste des artistes sans aucune Video
		for (Artiste lxx : listeArtistes) {
			if (lxx.getVideos().isEmpty()) {
				tabloArtisteCondVideoNR.add(lxx);
				}
			}
		model.addAttribute("tabloArtisteCondVideoNR", tabloArtisteCondVideoNR);
		
		
		//liste des artistes sans aucune Biographie 
		for (Artiste lxx : listeArtistes) {
			if (lxx.getBiographies().isEmpty()) {
				tabloArtisteCondBioNR.add(lxx);
				}
			}
		model.addAttribute("tabloArtisteCondBioNR", tabloArtisteCondBioNR);
		
		//liste des artiste qui remplissent les 3 conditions (photo, biographie, video)
		  for (Artiste laa : listeArtistes) { 
			  if ((!laa.getPhotos().isEmpty())&&(!laa.getBiographies().isEmpty())&&(!laa.getVideos().isEmpty())) {
		  tabloArtisteNonVide.add(laa); 
			  } 
		}
		
		/*
		 * for (Artiste laa : listeArtistes) { if (laa.getPhotos().size()>1) {
		 * tabloArtisteNonVide.add(laa); } }
		 */
		
		
		model.addAttribute("tabloArtisteNonVide", tabloArtisteNonVide);
		model.addAttribute("listeArtistes", listeArtistes);

		return "requetesindispensables/requetes";

	}

}
