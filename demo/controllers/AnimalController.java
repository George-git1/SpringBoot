package com.example.demo.controllers;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Animal;
import com.example.demo.services.AnimalService;

@RestController
@RequestMapping("/animal") // localhost:8080/animal
public class AnimalController {

	private AnimalService animalService;

	@Autowired
	public AnimalController(AnimalService animalService) {
		this.animalService = animalService;
	}

	@GetMapping
	public ResponseEntity<List<Animal>> getAnimals() {
		List<Animal> data = this.animalService.getAnimals();
		
		return new ResponseEntity<List<Animal>>(data, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getAnimalById(@PathVariable("id") int id) {
		Animal data = this.animalService.getAnimalById(id);
		
		if (data != null) {
			return new ResponseEntity<Animal>(data, HttpStatus.OK);
		} else {
			return new ResponseEntity<Animal>(data, HttpStatus.NOT_FOUND);
		}

		
	}

	@GetMapping("/type")
	public ResponseEntity<List<Animal>> getAnimalsByType(@RequestParam("type") String type) {
		List<Animal> data = this.animalService.getAnimalsByType(type);
		
		if (data != null) {
			return new ResponseEntity<List<Animal>>(data, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<Animal>>(data, HttpStatus.NOT_FOUND);
		}
		
	
	}

	@PutMapping("/{id}")
	public ResponseEntity<Animal> updateAnimal(@PathVariable("id") int id, @Valid @RequestBody Animal animal) {
		Animal data = this.animalService.updateAnimal(id, animal);
		
	
		if (data != null) {
			return new ResponseEntity<Animal>(data, HttpStatus.OK);
		} else {
			return new ResponseEntity<Animal>(data, HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping
	public ResponseEntity<Animal> createAnimal(@Valid @RequestBody Animal animal) {
		Animal data = this.animalService.createAnimal(animal);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(URI.create("localhost:8080/" + data.getId()));
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		
		if (!data.equals(null)) {
			return new ResponseEntity<Animal>(data, headers, HttpStatus.CREATED);
		} 
			return new ResponseEntity<Animal>(data, HttpStatus.BAD_REQUEST);
		}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteAnimal(@PathVariable("id") int id) {
	this.animalService.deleteAnimal(id);
	
	return new ResponseEntity<>(HttpStatus.OK);
}
}
