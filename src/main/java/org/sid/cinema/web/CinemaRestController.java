package org.sid.cinema.web;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;
import javax.websocket.server.PathParam;

import org.sid.cinema.dao.FilmRepository;
import org.sid.cinema.dao.TicketRepository;
import org.sid.cinema.entities.Film;
import org.sid.cinema.entities.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.Data;

//creer une API rest avec sprint data rest

//DEUX MANIERE DE CREER DES API REST SOIT EN UTILISANT UN RESTCONTROLLER OUBIENT SPRINT DATA RESTIT 
@RestController
public class CinemaRestController {
	/*
	 private FilmRepository filmRepository;
	@GetMapping("/listFilms")
	public List<Film> films(){
		return filmRepository.findAll();
	}*/
	
	// COMMENT CONSULTER UNE IMAGE
	@Autowired
	private FilmRepository filmRepository;
	@Autowired
	private TicketRepository ticketRepository;
	
	@GetMapping(path="/imageFilm/{id}",produces=MediaType.IMAGE_JPEG_VALUE)
	public byte [] image (@PathVariable(name="id")Long id) throws Exception{
		
		Film f=filmRepository.findById(id).get();
		String photoName=f.getPhoto();
		File file =new File (System.getProperty("user.home")+"/cinema/images/"+photoName);
		Path path=Paths.get(file.toURI());
		return Files.readAllBytes(path);
	}
	
	
	///une methode qui permet de  payer un ticket
	
	@PostMapping(path="/payerTickets")
	@Transactional
	public List<Ticket> payerTickets( @RequestBody TicketForm ticketForm) {
		List<Ticket>listTickets =new ArrayList<Ticket>();
		
		ticketForm.getTickets().forEach(idTicket-> {
			System.out.println(idTicket);
			Ticket ticket= ticketRepository.findById(idTicket).get();
			ticket.setNomClient(ticketForm.getNomClient());
			ticket.setReservee(true);
			ticketRepository.save(ticket);
			listTickets.add(ticket);
			
			
		});
		return listTickets;
		
		
	}

	

}
//annotation data  veut dire les getters et les setters 
@Data 
class TicketForm {
	private String nomClient;
	private int codePayement;
	private List<Long>tickets =new ArrayList<>();
	
}
