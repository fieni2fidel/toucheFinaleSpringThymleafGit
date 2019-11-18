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
import com.group.touchefinale.dao.LieuRepository;
import com.group.touchefinale.dao.SalleRepository;
import com.group.touchefinale.entities.Artiste;
import com.group.touchefinale.entities.Biographie;
import com.group.touchefinale.entities.Lieu;
import com.group.touchefinale.entities.Salle;

@Controller
public class SalleController {
	
	@Autowired
	private BiographieRepository biographieRepository;
	
	@Autowired
	private SalleRepository salleRepository;
	
	@Autowired
	private LieuRepository lieuRepository;
	
	@RequestMapping(value="/formulaire_salle", method=RequestMethod.GET)
	public String formulaire_biographie(Model model) {
		model.addAttribute("salle", new Salle());
		
		List<Lieu>listeDesLieuxParPays=lieuRepository.findAllByOrderByPayslieu();
		
		List<Lieu>ListeDesLieuxParVille=lieuRepository.findAllByOrderByVillelieu();
			 
		model.addAttribute("ListeDesLieuxParVille", ListeDesLieuxParVille);
		model.addAttribute("ListeDesLieuxParPays", listeDesLieuxParPays);
		
		return "salle/formulaireSalle";
		}
	
	/*------------------------------------------------------------------------------------*/

	
	/*verifier et valider le formulaire avec condition --- si on doit l'associer a un artiste ou non*/
	@RequestMapping(value="/validation_formulaire_salle", method=RequestMethod.POST)
	public String validation_formulaire_salle(Salle salle, Model model) {
	 salleRepository.save(salle);
		return "salle/pageMessageConfirmationSalle";
	}

	/*------------------------------------------------------------------------------------*/
	
	/*verifier et valider le formulaire*/
	@RequestMapping(value="/validation_modification_formulaire_salle", method=RequestMethod.POST)
	public String validation_modification_formulaire_salle(Salle salle, Model model) {
	
	salleRepository.save(salle);

		return "salle/pageMessageConfirmationSalle";
	}

	/*------------------------------------------------------------------------------------*/

	
	/*verifier et valider le formulaire  avec le nom de l'artiste*/
	@RequestMapping(value="/suite_validation_formulaire_salle", method=RequestMethod.POST)
	public String suite_validation_formulaire_salle(@ModelAttribute("lieusuite") Lieu lieusuite,
			HttpServletRequest request) {
	
	String nomsalle=request.getParameter("nomsalle");
	
	String nbreplxsalle=request.getParameter("nombredeplacesalle");
	Long longnbreplxsalle=Long.parseLong(nbreplxsalle);
	
	String idsalle=request.getParameter("idsalle");
	Long longidsalle=Long.parseLong(idsalle);
	
	String idlieu=request.getParameter("idlieu");
	Long longlieu=Long.parseLong(idlieu);

	Salle salsal=salleRepository.save(new Salle(longidsalle, nomsalle, longnbreplxsalle, new Lieu(longlieu)));
	
	Salle xsalle=salleRepository.getOne(salsal.getIdsalle());
	
	lieusuite=lieuRepository.getOne(longlieu);
	lieusuite.getSalles().add(xsalle);
	
	System.out.println("**************** ******** "+lieusuite);
	
	lieuRepository.save(lieusuite);	
		/* return "redirect:liste_biographie"; */
		 return "salle/pageMessageConfirmationSalle"; 
	}
	
	
	/*------------------------------------------------------------------------------------*/
	
	@RequestMapping(value="/liste_salle")
	public String liste_salle(Model model,
			@RequestParam(name="pageRP", defaultValue = "0")int page,
			@RequestParam(name="motcleRP", defaultValue = "")String motcle) {
		
		Page<Salle>listeDesSalles=salleRepository.chercherNomSalle("%"+motcle+"%",new PageRequest(page, 10));
		
		int pagesCount=listeDesSalles.getTotalPages();
		
		int[]pages=new int[pagesCount];
		
		for(int i=0;i<pagesCount;i++) pages[i]=i;
		
		model.addAttribute("pages", pages);
		model.addAttribute("pageCourante", page);
		model.addAttribute("motcle", motcle);		
		model.addAttribute("listeDesSalles", listeDesSalles);

		return "salle/listeSalle";
	}

	
	@RequestMapping(value = "/supprimersalle")
		public String supprimersalle(Long id) {
		salleRepository.deleteById(id);
		
		
		return "redirect:liste_salle";
	}
	
	@RequestMapping(value = "/editersalle")
	public String editersallee(Long id, Model model) {
	
		Salle salle=salleRepository.getOne(id);
		model.addAttribute("salle", salle);
		
		System.out.println("***** ******* **** "+salle.getIdsalle());
	
	return "salle/modificationFormulaireSalle";
	
}
	/*------------------------------------------------------------------------------------*/
	
	/*mise a jour de lartiste*/
	@RequestMapping(value="/mise_a_jour_salle", method=RequestMethod.POST)
	public String mise_a_jour_salle(Salle salle) {
			  	 
	salleRepository.save(salle);
		
		return "redirect:liste_salle";
	}
	
}
