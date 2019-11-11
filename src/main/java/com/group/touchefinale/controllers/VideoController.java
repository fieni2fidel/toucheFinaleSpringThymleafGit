package com.group.touchefinale.controllers;


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
import com.group.touchefinale.dao.VideoRepository;
import com.group.touchefinale.entities.Artiste;
import com.group.touchefinale.entities.Biographie;
import com.group.touchefinale.entities.Video;

@Controller
public class VideoController {

	@Autowired
	private VideoRepository videoRepository;

	@Autowired
	private ArtisteRepository artisteRepository;

	Video video;

	@Value("${x}")
	private String imageDir;

	// Artiste artiste=new Artiste();

	@RequestMapping(value = "/formulaire_video", method = RequestMethod.GET)
	public String formulaire_video(Model model) {
		model.addAttribute("video", new Video());

		List<Artiste> listeArtistes = artisteRepository.findAllByOrderByNomcompletartiste();

		model.addAttribute("listeArtistes", listeArtistes);
		model.addAttribute("artiste", new Artiste());

		return "video/formulaireVideo";
	}

	/*------------------------------------------------------------------------------------*/

	/* verifier et valider le formulaire */
	@RequestMapping(value = "/validation_formulaire_video", method = RequestMethod.POST)
	public String validation_formulaire_video(Video video) {

		  videoRepository.save(video);		 

		return "video/pageMessageConfirmationvideo";

	}

	/*------------------------------------------------------------------------------------*/

	/* verifier et valider le formulaire */
	
	  @RequestMapping(value = "/validation_modification_formulaire_video", method =
	  RequestMethod.POST) public String
	  validation_modification_formulaire_video(Video video) {
	  
	  videoRepository.save(video);
	  
	  return "video/pageMessageConfirmationvideo"; }
	 
	@RequestMapping(value = "/liste_videos")
	public String liste_videos(Model model,
			@RequestParam(name="pageRP", defaultValue = "0")int page,
			@RequestParam(name="motcleRP", defaultValue = "")String motcle) {
		
		Page<Video>listeDesVideos=videoRepository.chercherVideo("%"+motcle+"%",new PageRequest(page, 10));
		int pagesCount=listeDesVideos.getTotalPages();
		int[]pages=new int[pagesCount];
		
		for(int i=0;i<pagesCount;i++) pages[i]=i;
		
		model.addAttribute("pages", pages);
		model.addAttribute("pageCourante", page);
		model.addAttribute("motcle", motcle);		
		
		model.addAttribute("listeDesVideos", listeDesVideos);

		return "video/listeVideos";
	}

	@RequestMapping(value = "/supprimervideo")
	public String supprimervideo(Long id) {
		videoRepository.deleteById(id);

		return "redirect:liste_videos";
	}

	@RequestMapping(value = "/editervideo")
	public String editervideo(Long id, Model model) {

		Video video = videoRepository.getOne(id);
		model.addAttribute("video", video);

		return "video/modificationFormulaireVideo";

	}
	/*------------------------------------------------------------------------------------*/

	/* mise a jour de lartiste */
	@RequestMapping(value = "/mise_a_jour_video", method = RequestMethod.POST)
	public String mise_a_jour_video(Video video) {

		videoRepository.save(video);

		return "redirect:liste_videos";
	}

	/*------------------------------------------------------------------------------------*/

}
