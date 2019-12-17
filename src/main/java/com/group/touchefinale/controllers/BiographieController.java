package com.group.touchefinale.controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

import com.group.touchefinale.dao.ArtisteRepository;
import com.group.touchefinale.dao.BiographieRepository;
import com.group.touchefinale.dao.SalleRepository;
import com.group.touchefinale.entities.Artiste;
import com.group.touchefinale.entities.Biographie;
import com.group.touchefinale.entities.Evenement;
import com.group.touchefinale.entities.Salle;

@Controller
public class BiographieController {
	
	@Autowired
	private BiographieRepository biographieRepository;
	
	@Autowired
	private ArtisteRepository artisteRepository;
	
	@Autowired
	private SalleRepository salleRepository;
	
	@RequestMapping(value="/formulaire_biographie", method=RequestMethod.GET)
	public String formulaire_biographie(Model model) {
		model.addAttribute("biographie", new Biographie());
		
		return "biographie/formulaireBiographie";
		}
	
	/*verifier et valider le formulaire avec condition --- si on doit l'associer a un artiste ou non*/
	@RequestMapping(value="/validation_formulaire_biographie", method=RequestMethod.POST)
	public String validation_formulaire_biographie(Biographie biographie, HttpServletRequest request,Model model) {
	
	String name=request.getParameter("nomalier");	
	System.out.println("**************** ******** "+name);
	
	model.addAttribute("biographie", biographie);	
	
	if ((name.equals("artiste"))) {
		
		//request.getParameter("salle");
		
		List<Artiste>listeArtistes=artisteRepository.findAllByOrderByNomcompletartiste();	 
		model.addAttribute("listeArtistes", listeArtistes);
		model.addAttribute("artiste", new Artiste());
		
		biographieRepository.save(biographie);

		return "biographie/formulaireBiographieSuiteArtiste";
	}else {
		
		List<Salle>listeSalles=salleRepository.findAllByOrderByNomsalle();
		model.addAttribute("listeSalles", listeSalles);
		model.addAttribute("salle", new Salle());
		
		biographieRepository.save(biographie);
		
		return "biographie/formulaireBiographieSuiteSalle";
	}

}

	/*verifier et valider le formulaire  avec le nom de l'artiste*/
	@RequestMapping(value="/artiste_suite_validation_formulaire_biographie", method=RequestMethod.POST)
	public String artiste_suite_validation_formulaire_biographie(Biographie biographie) {
	
	biographieRepository.save(biographie);
	
		/* return "redirect:liste_biographie"; */
		 return "biographie/pageMessageConfirmationBiographie"; 
	}
	

	/*verifier et valider le formulaire*/
	@RequestMapping(value="/salle_suite_validation_formulaire_biographie", method=RequestMethod.POST)
	public String salle_suite_validation_formulaire_biographie(Biographie biographie) {
	
	
	biographieRepository.save(biographie);

		return "biographie/pageMessageConfirmationBiographie";
	}
	

	/*verifier et valider le formulaire*/
	@RequestMapping(value="/validation_modification_formulaire_biographie", method=RequestMethod.POST)
	public String validation_modification_formulaire_biographie(Biographie biographie) {
	
	
	biographieRepository.save(biographie);

		return "biographie/pageMessageConfirmationBiographie";
	}
	

	@RequestMapping(value="/liste_biographie")
	public String liste_artistes(Model model,
			@RequestParam(name="pageRP", defaultValue = "0")int page,
			@RequestParam(name="motcleRP", defaultValue = "")String motcle) {
		
		Page<Biographie>listeDesBiographies=biographieRepository.chercherBiographies("%"+motcle+"%",new PageRequest(page, 10));
		int pagesCount=listeDesBiographies.getTotalPages();
		int[]pages=new int[pagesCount];
		
		for(int i=0;i<pagesCount;i++) pages[i]=i;
		
		model.addAttribute("pages", pages);
		model.addAttribute("pageCourante", page);
		model.addAttribute("motcle", motcle);
		model.addAttribute("listeDesBiographie", listeDesBiographies);

		return "biographie/listeBiographie";
	}

	
	@RequestMapping(value = "/supprimerbiographie")
		public String supprimerbiographie(Long id) {
		biographieRepository.deleteById(id);
		
		return "redirect:liste_biographie";
	}
	
	@RequestMapping(value = "/editerbiographie")
	public String editerbiographie(Long id, Model model) {
	
		Biographie biographie=biographieRepository.getOne(id);
		model.addAttribute("biographie", biographie);
		
		System.out.println("***** ******* **** "+biographie.getIdbiographie());
	
	return "biographie/modificationFormulaireBiographie";
	
}
	/*------------------------------------------------------------------------------------*/
	
	/*mise a jour de lartiste*/
	@RequestMapping(value="/mise_a_jour_biographie", method=RequestMethod.POST)
	public String mise_a_jour_biographie(Biographie biographie) {
			  	 
	biographieRepository.save(biographie);
		
		return "redirect:liste_biographie";
	}
	
}


