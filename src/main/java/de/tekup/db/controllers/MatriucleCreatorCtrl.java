package de.tekup.db.controllers;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import de.tekup.db.entities.Matricule;
import de.tekup.db.entities.MatriculeCreator;
import de.tekup.db.services.MatriculeCreatorService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class MatriucleCreatorCtrl {
	
	private MatriculeCreatorService creatorService;
	
	@PostMapping("/creator/add")
	public MatriculeCreator addMatriculeCreator(@RequestBody MatriculeCreator creator) {
		return creatorService.addCreator(creator);
	}
	
	@PostMapping("/creator/{idCreator}/add/matricule")
	public Matricule addMatriculeByCreator(@PathVariable int idCreator, @RequestBody Matricule matricule) {
		return creatorService.addMatricule(idCreator, matricule);
	}
	
	@GetMapping("/creator/{idCreator}/get/matricule")
	public List<Matricule> getMatriculesByCreator(@PathVariable int idCreator) {
		return creatorService.getMatriculesByCreatorId(idCreator);
	}
	
	@ExceptionHandler({NoSuchElementException.class,NumberFormatException.class})
	public ResponseEntity<String> handleNoSuchElementSndNumberFormat(RuntimeException e) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
							 .body("Error in finding Creator : "+e.getMessage());
							 
	}
}
