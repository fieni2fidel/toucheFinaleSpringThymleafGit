package com.group.touchefinale.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

import com.group.touchefinale.dao.ArtisteRepository;
import com.group.touchefinale.dao.BiographieRepository;
import com.group.touchefinale.dao.PhotoRepository;
import com.group.touchefinale.dao.VideoRepository;
import com.group.touchefinale.entities.Artiste;
import com.group.touchefinale.entities.Biographie;
import com.group.touchefinale.entities.Photo;
import com.group.touchefinale.entities.Video;

@Controller
public class PageArtisteController {

	@Autowired
	private PhotoRepository photoRepository;
	
	@Autowired
	private BiographieRepository biographieRepository;
	
	@Autowired
	private VideoRepository videoRepository;
	
	@Autowired
	private ArtisteRepository artisteRepository;

	/*afficher formulaire ETAPE I*/
	@RequestMapping(value="/formulaire_page_artiste_etape_1", method=RequestMethod.GET)
	public String formulaire_page_artiste_etape_1(Model model) {
		
		List<Artiste>listeArtistes=artisteRepository.findAll();
		model.addAttribute("listeArtistes", listeArtistes);
		
		List<Photo>listePhotos=photoRepository.findAll();
		model.addAttribute("listePhotos", listePhotos);
		
		List<Video>listeVideos=videoRepository.findAll();
		model.addAttribute("listeVideos", listeVideos);
		
		model.addAttribute("artiste", new Artiste());
		
		return "pageArtiste/formulairePageArtiste";
	}
	
	
	/*verifier et valider le formulaire ETAPE I*/
	@RequestMapping(value="/validation_formulaire_page_artiste_etape_1", method=RequestMethod.POST)
	public String validation_formulaire_page_artiste_etape_1(Artiste artiste, Biographie biographie, Model model1) {
		
		artiste=artisteRepository.getOne(artiste.getIdartiste());
		model1.addAttribute("model1artiste", artiste);
		System.out.println("les donnees de artiste 1 : "+artiste+" "+artiste.getIdartiste());
		 
		return "redirect:/formulaire_page_artiste_etape_2";
	}
	

	/* affichage formulaire II*/
	@RequestMapping(value="/formulaire_page_artiste_etape_2")
	public String validation_II_formulaire_page_artiste(Model model2 ) {
		
		List<Biographie>listeBiographies=biographieRepository.findAll();
		 model2.addAttribute("listeBiographies", listeBiographies); 
		/* model1.addAttribute("listeBiographies", artiste.getBiographies()); */
		
		 model2.addAttribute("biographie", new Biographie());
		
		return "PageArtiste/pageArtisteFormulaireEtapeII";
	}

}

