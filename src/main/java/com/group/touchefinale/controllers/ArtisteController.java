package com.group.touchefinale.controllers;

import java.util.Collections;
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
import com.group.touchefinale.dao.EvenementRepository;
import com.group.touchefinale.dao.PhotoRepository;
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
		for (Artiste artiste: listDesArtistes) {
			artiste.setPhotos(photoRepository.findAllByArtiste(artiste));
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
		System.out.println("***** ******* **** " + artiste.getIdartiste());
		System.out.println("***** model event******* **** " + artiste);

		return "artiste/voirFormulaireArtiste";

	}

	/*------------------------------------------------------------------------------------*/

	@RequestMapping(value = "/artiste/{motcleArtiste}")
	public String artiste(Model model, Long id, @PathVariable("motcleArtiste") String motcleArtiste) {

		List<Artiste> nomArtiste = artisteRepository.findByNomcompletartisteLike("%" + motcleArtiste + "%");
		Artiste artistefrontend = new Artiste();
		for (Artiste x : nomArtiste) {
			artistefrontend = artisteRepository.getOne(x.getIdartiste());
			Collections.sort((List<Evenement>) artistefrontend.getEvenements());
			model.addAttribute("artistefrontend", artistefrontend);
		}
		System.out.println("********  " + nomArtiste);

		model.addAttribute("nomArtiste", nomArtiste);
		return "front_end/fe_artiste/" + motcleArtiste;
	}

}
