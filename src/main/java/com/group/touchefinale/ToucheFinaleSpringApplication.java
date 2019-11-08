package com.group.touchefinale;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.group.touchefinale.dao.ArtisteRepository;
import com.group.touchefinale.dao.BiographieRepository;
import com.group.touchefinale.dao.TarifRepository;
import com.group.touchefinale.entities.Artiste;
import com.group.touchefinale.entities.Biographie;



@SpringBootApplication
public class ToucheFinaleSpringApplication {

	static TarifRepository tarifRepository;
	
	public static void main(String[] args) throws ParseException {
		ApplicationContext ctx=SpringApplication.run(ToucheFinaleSpringApplication.class, args);
		ArtisteRepository artisteRepository=ctx.getBean(ArtisteRepository.class);
		DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		/*
		 * artisteRepository.save(new Artiste("Chantal Taiba", df.parse("1985-05-10"),
		 * "Ivoirienne", "chantaltof1", "chantaltoflarge"));
		 */
		 
		/*-------------------------------------------------------------------------------------*/
		 
		/*
		 * BiographieRepository
		 * biographieRepository=ctx.getBean(BiographieRepository.class);
		 * biographieRepository.save(new Biographie("Biograpie de Akon",
		 * "Akon, de son vrai nom" +
		 * " Alioune Badara Thiam, est un chanteur et producteur de RnB américano-sénégalais"
		 * +
		 * " né le 16 avril 1973 à Saint-Louis dans le Missouri. Son premier single Locked Up "
		 * + "et son premier album Trouble sont sortis en 2004."));
		 * 
		 * biographieRepository.save(new Biographie("Biograpie de Aya Nakamura",
		 * "Aya Nakamura, de son " +
		 * "vrai nom Aya Danioko, est une chanteuse de RNB. Elle est née le 10 mai 1995, "
		 * +
		 * "à Bamako au Mali. Elle arrive en France avec ses parents qui emménagent à Aulnay "
		 * + "sous Bois en région parisienne" ));
		 */
		 
		 
		 /*-------------------------------------------------------------------------------------*/
		 
		/*
		 * EvenementRepository
		 * evenementRepository=ctx.getBean(EvenementRepository.class); DateFormat
		 * dfe=new SimpleDateFormat("yyyy-MM-dd"); DateFormat hfe=new
		 * SimpleDateFormat("HH:mm");
		 * 
		 * evenementRepository.save(new Evenement("concert", dfe.parse("2020-01-01"),
		 * (Time) hfe.parse("10:00")));
		 */
		 
		 /*-------------------------------------------------------------------------------------*/
		 
		 
		 /*-------------------------------------------------------------------------------------*/
		 
		 
		 /*-------------------------------------------------------------------------------------*/
		 
		 
		 /*-------------------------------------------------------------------------------------*/
		 
		 
		 /*-------------------------------------------------------------------------------------*/
		 
		 
		 /*-------------------------------------------------------------------------------------*/
		 
		 
		 /*-------------------------------------------------------------------------------------*/
		 
		 
		 /*-------------------------------------------------------------------------------------*/
		
		
	}

}
