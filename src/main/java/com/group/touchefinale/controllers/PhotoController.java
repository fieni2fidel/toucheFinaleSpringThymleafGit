package com.group.touchefinale.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import com.group.touchefinale.dao.PhotoRepository;
import com.group.touchefinale.dao.SalleRepository;
import com.group.touchefinale.entities.Artiste;
import com.group.touchefinale.entities.Photo;
import com.group.touchefinale.entities.Salle;

@Controller
public class PhotoController {
	
	
	@Autowired
	private PhotoRepository photoRepository;
	
	@Autowired
	private SalleRepository salleRepository;
	
	@Autowired
	private ArtisteRepository artisteRepository;
	
	@Value("${x}")
	private String imageDir;
	
	@Value("${y}")
	private String photoDir;
	
	//Artiste artiste=new Artiste();
	
	@RequestMapping(value="/formulaire_photo", method=RequestMethod.GET)
	public String formulaire_photo(Model model) {
		model.addAttribute("photo", new Photo());
		
		List<Artiste>listeArtistes=artisteRepository.
		findAllByOrderByNomcompletartiste(); model.addAttribute("listeArtistes",
		listeArtistes); model.addAttribute("artiste", new Artiste());
		
		return "photo/formulairePhoto";
		}
	
	/*------------------------------------------------------------------------------------*/
	
	
	//verifier et valider le formulaire
	
	  @RequestMapping(value="/validation_formulaire_photo", method=RequestMethod.POST) 
	  public String validation_formulaire_photo(Photo photo, MultipartFile photoimage, 
			  									HttpServletRequest httpServletRequest, Model model) 
			  									throws Exception, IOException{
		  
		/*
		 * String name=httpServletRequest.getParameter("nom");
		 * System.out.println("**************** ******** "+name);
		 */
		  
		if (!(photoimage.isEmpty())) {
			//Thumbnails
			photo.setUrlphoto(photoimage.getOriginalFilename());
			//photoimage.transferTo(new File(photoDir+photo.getIdphoto()+"xl."+photo.getUrlphoto().substring(photo.getUrlphoto().lastIndexOf('.')+1).trim()));
		}
	  
	 photoRepository.save(photo);
	 
	 	System.out.println(photo.getUrlphoto());
		System.out.println(photo.getIdphoto());
		System.out.println("chemin de la photo : ********** "+imageDir+photo.getIdphoto()+photo.getUrlphoto());
		System.out.println("chemin de la photo : ********** "+imageDir+photo.getIdphoto()+"."+photo.getUrlphoto().substring(photo.getUrlphoto().lastIndexOf('.')+1).trim());
		
		photo.setUrlphoto(imageDir+photo.getIdphoto()+"."+photo.getUrlphoto().substring(photo.getUrlphoto().lastIndexOf('.')+1).trim());
		photoimage.transferTo(new File(imageDir+photo.getIdphoto()+"."+photo.getUrlphoto().substring(photo.getUrlphoto().lastIndexOf('.')+1).trim()));
		
		
		
		photoRepository.save(photo);
		
		//model.addAttribute("photo", photo);
		
		/*
		 * if ((name.equals("artiste"))) {
		 * 
		 * List<Artiste>listeArtistes=artisteRepository.
		 * findAllByOrderByNomcompletartiste(); model.addAttribute("listeArtistes",
		 * listeArtistes); model.addAttribute("artiste", new Artiste());
		 * 
		 * photoRepository.save(photo);
		 * 
		 * return "photo/formulairePhotoSuiteArtiste"; }else {
		 * 
		 * List<Salle>listeSalles=salleRepository.findAllByOrderByNomsalle();
		 * model.addAttribute("listeSalles", listeSalles); model.addAttribute("salle",
		 * new Salle());
		 * 
		 * photoRepository.save(photo);
		 
			
			return "photo/formulairePhotoSuiteSalle";

	   }
	   */
	  return "photo/pageMessageConfirmationPhoto";
	          //photo/pageMessageConfirmationphoto
	}
	  

	  /*verifier et valider le formulaire  avec le nom de l'artiste*/
	/*
	 * @RequestMapping(value="/artiste_suite_validation_formulaire_photo",
	 * method=RequestMethod.POST) public String
	 * artiste_suite_validation_formulaire_photo(Photo photo) {
	 * 
	 * 
	 * photoRepository.save(photo);
	 * 
	 * 
	 * return "photo/pageMessageConfirmationphoto"; }
	 */
		
		
		  
		  /*verifier et valider le formulaire  avec le nom de la salle*/
	/*
	 * @RequestMapping(value="/salle_suite_validation_formulaire_photo",
	 * method=RequestMethod.POST) public String
	 * salle_suite_validation_formulaire_photo(Photo photo) {
	 * 
	 * 
	 * photoRepository.save(photo);
	 * 
	 * 
	 * return "photo/pageMessageConfirmationphoto"; }
	 */
			

	  @RequestMapping(value = {"/artiste/getphotoimage","/getphotoimage"}, produces = MediaType.IMAGE_JPEG_VALUE)
	  @ResponseBody
	  public byte[] getphotoimage(@RequestParam("id") Long idphoto) throws Exception{
		Photo photo= photoRepository.getOne(idphoto);
		File f=new File(photo.getUrlphoto());
			return IOUtils.toByteArray(new FileInputStream(f));
		}
	  
	  //-------------------------------------------------------------------------------
	  
	  
	   @RequestMapping(value="/liste_photo") public String liste_photo(Model model,
			   @RequestParam(name="pageRP", defaultValue = "0")int page,
				@RequestParam(name="motcleRP", defaultValue = "")String motcle) {
			
			Page<Photo>listeDesPhotos=photoRepository.chercherPhotos("%"+motcle+"%",new PageRequest(page, 10));
			int pagesCount=listeDesPhotos.getTotalPages();
			int[]pages=new int[pagesCount];
			
			for(int i=0;i<pagesCount;i++) pages[i]=i;
			
			model.addAttribute("pages", pages);
			model.addAttribute("pageCourante", page);
			model.addAttribute("motcle", motcle);
				  model.addAttribute("listePhoto", listeDesPhotos);
				 
				  return "photo/listePhoto";
				  }
	 //--------------------------------------------------------------------------------
	   
	   @RequestMapping(value = "/supprimerphoto") public String
		  supprimerphoto(Long id) { 
		  
		   photoRepository.deleteById(id);
		  Photo photo;
		 
		   
		  return "redirect:liste_photo"; }
	   
	   //--------------------------------------------------------------------------------
	   
	   @RequestMapping(value = "/editerphoto") public String editerartiste(Long id, Model model) {
				  
				  Photo photo=photoRepository.getOne(id); model.addAttribute("photo",
				  photo);
				  
				  return "photo/modificationFormulairePhoto";
				 
	   }

		  @RequestMapping(value="/update_photo", method=RequestMethod.POST) 
		  public String update_photo(Photo photo, MultipartFile photoimagemodif) throws IllegalStateException, IOException{
			  
			 /* List<Photo>ldp=photoRepository.findAll();
		
			  if (photoimagemodif.isEmpty()) {
				  
				  System.out.println("le file est vide, ici alpha 1 repondez, je repete le file est vide!!! ");
				  photo.setUrlphoto(photoimagemodif.getOriginalFilename());
				 
				  System.out.println("o*o*o*o*o*o* "+photo.getUrlphoto());
		
				  
				 System.out.println("image de mise a jour one : ********** "+imageDir+photo.getIdphoto()+"."+photo.getUrlphoto().substring(photo.getUrlphoto().lastIndexOf('.')+1).trim());  
				
				 System.out.println("image de mise a jour two : ********** "+imageDir+photo.getUrlphoto());  
				 
				 for (Photo x : ldp) {
					if (photo.getIdphoto().equals(x.getIdphoto())) {
						System.out.println("----------- "+x.getUrlphoto());
						photo.setUrlphoto(x.getUrlphoto());
						System.out.println("o*o*o*o*o*o* "+photoimagemodif);
						//photoimagemodif.transferTo(new File(x.getUrlphoto()));
						
					}
				}*/
				 	photoRepository.save(photo);
					
			 /* }else {
				
				  System.out.println("le file n'est pas vide, ici alpha 1 repondez, je repete le file n'est pas vide!!! ");
				  photo.setUrlphoto(photoimagemodif.getOriginalFilename());
				  
				  System.out.println(photo.getUrlphoto());
				  
				  System.out.println("image de mise a jour : ********** "+imageDir+photo.getIdphoto()+"."+photo.getUrlphoto().substring(photo.getUrlphoto().lastIndexOf('.')+1).trim());  
				
				  photoRepository.save(photo);*/

				 // photo.setUrlphoto(imageDir+photo.getIdphoto()+"."+photo.getUrlphoto().substring(photo.getUrlphoto().lastIndexOf('.')+1).trim());
				  //String filephotoimagex=imageDir+photo.getIdphoto()+"."+photo.getUrlphoto().substring(photo.getUrlphoto().lastIndexOf('.')+1).trim();
				  
			/*
			 * File fpn=new File(filephotoimagex); if (fpn.exists()) { fpn.delete();
			 * 
			 * System.out.println(" photo supprimée " + photoimagemodif); }
			 * 
			 * photoimagemodif.transferTo(new
			 * File(imageDir+photo.getIdphoto()+"."+photo.getUrlphoto().substring(photo.
			 * getUrlphoto().lastIndexOf('.')+1).trim()));
			 * 
			 * photoRepository.save(photo);
			 
			 
			} */
			  
			
		  return "redirect:liste_photo";
		  
		  }

}
		
	
	

