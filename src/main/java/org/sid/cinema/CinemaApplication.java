package org.sid.cinema;

import org.sid.cinema.service.ICinemaIniitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CinemaApplication implements CommandLineRunner {
	
	@Autowired
		private ICinemaIniitService cinemaIniitService;
		public static void main(String[] args) {
		SpringApplication.run(CinemaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
cinemaIniitService.initVilles();
cinemaIniitService.initCinemas();
cinemaIniitService.initiSalles();
cinemaIniitService.initiPlaces();
cinemaIniitService.initSeance();
cinemaIniitService.initCategories();
cinemaIniitService.initFilms();
cinemaIniitService.initProjections();
cinemaIniitService.initTickets();

		
	}

}
