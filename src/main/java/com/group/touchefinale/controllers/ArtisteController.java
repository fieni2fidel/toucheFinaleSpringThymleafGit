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

import com.group.touchefinale.dao.ArtisteRepository;
import com.group.touchefinale.dao.BiographieRepository;
import com.group.touchefinale.dao.EvenementRepository;
import com.group.touchefinale.dao.PhotoRepository;
import com.group.touchefinale.dao.VideoRepository;
import com.group.touchefinale.entities.Artiste;
import com.group.touchefinale.entities.Evenement;

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

	/*------------------------------------------------------------------------------------*/

	@RequestMapping(value = "/liste_artistes")
	public String liste_artistes(Model model, @RequestParam(name = "pageRP", defaultValue = "0") int page,
			@RequestParam(name = "motcleRP", defaultValue = "") String motcle) {

		Page<Artiste> listDesArtistes = artisteRepository.chercherEtudiants("%" + motcle + "%",
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

	/*------------------------------------------------------------------------------------*/

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
	public String debutmajusculeartiste(Long id, Model model) {

		 Artiste artistefrontend = artisteRepository.getOne(id); 
		
		List<Evenement> listeDateArtistesAVenir= evenementRepository.dateArtistesAVenir(id, new Date());
		Collections.sort(listeDateArtistesAVenir);
		Collections.reverse(listeDateArtistesAVenir);
		model.addAttribute("listeDateArtistesAVenir", listeDateArtistesAVenir);
		
		model.addAttribute("artistefrontend", artistefrontend);
		
		List<Evenement> listeDateArtistesTermine= evenementRepository.dateArtistesTermine(id, new Date());
		Collections.sort(listeDateArtistesTermine);
		
		model.addAttribute("listeDateArtistesTermine",listeDateArtistesTermine);

		return "front_end/fe_artiste/meiway";

	}

	/*------------------------------------------------------------------------------------*/
	@RequestMapping(value = "/artiste")
	public String listedebutnom(Model model, String debutnom) {

		/*
		 * List<Artiste> artistes = artisteRepository.findAllOrderByNomCompletartiste();
		 * String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"; int index = 0; for (int i =
		 * 0; i < alphabet.length(); i++) { // Ecrire le nom de la lettre et le divider
		 * for (int j = index; j < artistes.size(); j++) { if
		 * (artistes.get(j).getNomcompletartiste().toLowerCase() .startsWith("" +
		 * alphabet.toLowerCase().charAt(i))) { //ecrerice le nom de l'artiste }else {
		 * index = j; break; } } }
		 */
		List<Artiste> listeA = artisteRepository.findByNomcompletartisteStartingWith("a");
		model.addAttribute("listeA", listeA);

		List<Artiste> listeB = artisteRepository.findByNomcompletartisteStartingWith("b");
		model.addAttribute("listeB", listeB);

		List<Artiste> listeC = artisteRepository.findByNomcompletartisteStartingWith("c");
		model.addAttribute("listeC", listeC);

		List<Artiste> listeD = artisteRepository.findByNomcompletartisteStartingWith("d");
		model.addAttribute("listeD", listeD);

		List<Artiste> listeE = artisteRepository.findByNomcompletartisteStartingWith("e");
		model.addAttribute("listeE", listeE);

		List<Artiste> listeF = artisteRepository.findByNomcompletartisteStartingWith("f");
		model.addAttribute("listeF", listeF);

		List<Artiste> listeG = artisteRepository.findByNomcompletartisteStartingWith("g");
		model.addAttribute("listeG", listeG);

		List<Artiste> listeH = artisteRepository.findByNomcompletartisteStartingWith("h");
		model.addAttribute("listeH", listeH);

		List<Artiste> listeJ = artisteRepository.findByNomcompletartisteStartingWith("j");
		model.addAttribute("listeJ", listeJ);

		List<Artiste> listeI = artisteRepository.findByNomcompletartisteStartingWith("i");
		model.addAttribute("listeI", listeI);

		List<Artiste> listeK = artisteRepository.findByNomcompletartisteStartingWith("k");
		model.addAttribute("listeK", listeK);

		List<Artiste> listeL = artisteRepository.findByNomcompletartisteStartingWith("l");
		model.addAttribute("listeL", listeL);

		List<Artiste> listeM = artisteRepository.findByNomcompletartisteStartingWith("m");
		model.addAttribute("listeM", listeM);

		List<Artiste> listeN = artisteRepository.findByNomcompletartisteStartingWith("n");
		model.addAttribute("listeN", listeN);

		List<Artiste> listeO = artisteRepository.findByNomcompletartisteStartingWith("o");
		model.addAttribute("listeO", listeO);

		List<Artiste> listeP = artisteRepository.findByNomcompletartisteStartingWith("p");
		model.addAttribute("listeP", listeP);

		List<Artiste> listeQ = artisteRepository.findByNomcompletartisteStartingWith("q");
		model.addAttribute("listeQ", listeQ);

		List<Artiste> listeR = artisteRepository.findByNomcompletartisteStartingWith("r");
		model.addAttribute("listeR", listeR);

		List<Artiste> listeS = artisteRepository.findByNomcompletartisteStartingWith("s");
		model.addAttribute("listeS", listeS);

		List<Artiste> listeT = artisteRepository.findByNomcompletartisteStartingWith("t");
		model.addAttribute("listeT", listeT);

		List<Artiste> listeU = artisteRepository.findByNomcompletartisteStartingWith("u");
		model.addAttribute("listeU", listeU);

		List<Artiste> listeV = artisteRepository.findByNomcompletartisteStartingWith("v");
		model.addAttribute("listeV", listeV);

		List<Artiste> listeW = artisteRepository.findByNomcompletartisteStartingWith("w");
		model.addAttribute("listeW", listeW);

		List<Artiste> listeX = artisteRepository.findByNomcompletartisteStartingWith("x");
		model.addAttribute("listeX", listeX);

		List<Artiste> listeY = artisteRepository.findByNomcompletartisteStartingWith("y");
		model.addAttribute("listeY", listeY);

		List<Artiste> listeZ = artisteRepository.findByNomcompletartisteStartingWith("z");
		model.addAttribute("listeZ", listeZ);

		return "front_end/fe_artiste/menuPrincipalArtiste";
	}

}
