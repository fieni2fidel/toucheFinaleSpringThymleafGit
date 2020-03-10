package com.group.touchefinale.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.group.touchefinale.dao.ArtisteRepository;
import com.group.touchefinale.dao.BiographieRepository;
import com.group.touchefinale.dao.LieuRepository;
import com.group.touchefinale.dao.SalleRepository;
import com.group.touchefinale.entities.Artiste;
import com.group.touchefinale.entities.Biographie;
import com.group.touchefinale.entities.Lieu;
import com.group.touchefinale.entities.Video;

@Controller
public class LieuController {
	
	@Autowired
	private LieuRepository lieuRepository;
	
	@Autowired
	private SalleRepository salleRepository;
		
	@RequestMapping(value="/formulaire_lieu", method=RequestMethod.GET)
	public String formulaire_lieu(Model model) {
		model.addAttribute("lieu", new Lieu());
		
		
		return "lieu/formulaireLieu";
		}
	
	
	/*verifier et valider le formulaire*/
	@RequestMapping(value="/validation_formulaire_lieu", method=RequestMethod.POST)
	public String validation_formulaire_lieu(Lieu lieu) {
		
	lieuRepository.save(lieu);

		return "lieu/pageMessageConfirmationLieu";
	}

	@RequestMapping(value="/liste_lieu")
	public String liste_lieu(Model model,
			@RequestParam(name="pageRP", defaultValue = "0")int page,
			@RequestParam(name="motcleRP", defaultValue = "")String motcle) {
		
		Page<Lieu>listeDesLieux=lieuRepository.chercherLieux("%"+motcle+"%",new PageRequest(page, 10));
		
		
		
		int pagesCount=listeDesLieux.getTotalPages();
		int[]pages=new int[pagesCount];
		
		for(int i=0;i<pagesCount;i++) pages[i]=i;
		
		for (Lieu li : listeDesLieux) {
			li.setSalles(salleRepository.findAllByLieu(li));
		}
		
		model.addAttribute("pages", pages);
		model.addAttribute("pageCourante", page);
		model.addAttribute("motcle", motcle);
		model.addAttribute("listDesLieux", listeDesLieux);
		
		
		return "lieu/listeLieu";
	}

	
	@RequestMapping(value = "/supprimerlieu")
		public String supprimerlieu(Long id) {
		lieuRepository.deleteById(id);	
		
		return "redirect:liste_lieu";
	}
	
	@RequestMapping(value = "/editerlieu")
	public String editerlieu(Long id, Model model) {
		
		Lieu lieu=lieuRepository.getOne(id);
		model.addAttribute("lieu", lieu);
	
	return "lieu/modificationFormulaireLieu";
	
}
	/*------------------------------------------------------------------------------------*/
	
	/*mise a jour du lieu*/
	@RequestMapping(value="/mise_a_jour_lieu", method=RequestMethod.POST)
	public String mise_a_jour_lieu(Lieu lieu) {
			  	 
	lieuRepository.save(lieu);
		
		return "redirect:liste_lieu";
	}

	/*------------------------------------------------------------------------------------*/
	
	
}


