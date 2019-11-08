package com.group.touchefinale.controllers;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.group.touchefinale.dao.ArtisteRepository;
import com.group.touchefinale.dao.EvenementRepository;
import com.group.touchefinale.dao.SalleRepository;
import com.group.touchefinale.entities.Artiste;
import com.group.touchefinale.entities.Evenement;
import com.group.touchefinale.entities.Salle;

@Controller
public class EvenementController {

	@Autowired
	private EvenementRepository evenementRepository;

	@Autowired
	private SalleRepository salleRepository;

	@Autowired
	private ArtisteRepository artisteRepository;

	@RequestMapping(value = "/formulaire_evenement", method = RequestMethod.GET)
	public String formulaire_evenement(Model model) {

		List<Artiste> listeArtistes = artisteRepository.findAllByOrderByNomcompletartiste();
		model.addAttribute("listeArtistes", listeArtistes);

		List<Salle> listeSalles = salleRepository.findAllByOrderByNomsalle();
		model.addAttribute("listeSalles", listeSalles);

		model.addAttribute("evenement", new Evenement());

		return "evenement/formulaireEvenement";
	}

	/*------------------------------------------------------------------------------------*/

	@RequestMapping(value = "/validation_formulaire_evenement", method = RequestMethod.POST)
	public String validation_formulaire_evenement(Evenement evenement) {

		evenementRepository.save(evenement);

		return "evenement/pageMessageConfirmationEvenement";
	}

	/*------------------------------------------------------------------------------------*/

	@RequestMapping(value = "/validation_modification_formulaire_evenement", method = RequestMethod.POST)
	public String validation_modification_formulaire_evenement(Evenement evenement) {

		evenementRepository.save(evenement);

		return "evenement/pageMessageConfirmationEvenement";
	}

	/*------------------------------------------------------------------------------------*/

	/*
	 * @RequestMapping(value="/suite_validation_formulaire_evenement",
	 * method=RequestMethod.POST) public String
	 * suite_validation_formulaire_evenement(@ModelAttribute("artistesuite") Artiste
	 * artistesuite, HttpServletRequest request) {
	 * 
	 * String nombio=request.getParameter("nombiographie"); String
	 * descbio=request.getParameter("descriptionbiographie"); String
	 * idbio=request.getParameter("idbiographie"); Long intidbio=(long)
	 * Integer.parseInt(idbio);
	 * 
	 * String idart=request.getParameter("idartiste");
	 * 
	 * Long intidart=Long.parseLong(idart);
	 * 
	 * Biographie biobio=biographieRepository.save(new Biographie(intidbio, nombio,
	 * descbio, new Artiste(intidart)));
	 * 
	 * 
	 * Biographie xbio=biographieRepository.getOne(biobio.getIdbiographie());
	 * 
	 * artistesuite=artisteRepository.getOne(intidart);
	 * artistesuite.getBiographies().add(xbio);
	 * 
	 * artisteRepository.save(artistesuite);
	 * 
	 * return "biographie/pageMessageConfirmationBiographie"; }
	 */

	/*------------------------------------------------------------------------------------*/

	@RequestMapping(value = "/liste_evenement")
	public String liste_evenement(Model model,
			@RequestParam(name="pageRP", defaultValue = "0")int page,
			@RequestParam(name="motcleRP1", defaultValue = "2017-01-01")
			@DateTimeFormat(pattern="yyyy-MM-dd")
			Date motcle1,
			@RequestParam(name="motcleRP2", defaultValue = "2025-01-01")
			@DateTimeFormat(pattern="yyyy-MM-dd")
			Date motcle2) {
		
		Calendar c = Calendar.getInstance(); 
		c.setTime(motcle2); 
		c.add(Calendar.DATE, 1);
		motcle2 = c.getTime();
		
		Page<Evenement>listeDesEvenements=evenementRepository.chercherDateEvenement(motcle1, motcle2,new PageRequest(page, 10));
		int pagesCount=listeDesEvenements.getTotalPages();
		int[]pages=new int[pagesCount];
		
		for(int i=0;i<pagesCount;i++) pages[i]=i;
		
		model.addAttribute("pages", pages);
		model.addAttribute("pageCourante", page);
		model.addAttribute("motcle1", motcle1);
		model.addAttribute("motcle2", motcle2);
		model.addAttribute("listeDesEvenements", listeDesEvenements);

	
		return "evenement/listeEvenement";
	}

	@RequestMapping(value = "/supprimerevenement")
	public String supprimerevenement(Long id) {
		evenementRepository.deleteById(id);

		return "redirect:liste_evenement";
	}

	@RequestMapping(value = "/editerevenement")
	public String editerevenement(Long id, Model model) {

		Evenement evenement = evenementRepository.getOne(id);
		model.addAttribute("evenement", evenement);

		System.out.println("***** ******* **** " + evenement.getIdevenement());

		return "evenement/modificationFormulaireEvenement";

	}

	/*------------------------------------------------------------------------------------*/

	@RequestMapping(value = "/mise_a_jour_evenement", method = RequestMethod.POST)
	public String mise_a_jour_evenement(Evenement evenement) {

		evenementRepository.save(evenement);

		return "redirect:liste_evenement";
	}

	/*------------------------------------------------------------------------------------*/

	@RequestMapping(value = "/voirevenement")
	public String voirevenement(@RequestParam(value = "id") Long id, Model model) {

		Evenement evenement = evenementRepository.getOne(id);

		model.addAttribute("evenement", evenement);

		System.out.println("***** ******* **** " + evenement.getIdevenement());

		System.out.println("***** model event******* **** " + evenement);


		return "evenement/voirFormulaireEvenement";

	}

}
