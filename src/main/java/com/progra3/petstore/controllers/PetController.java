package com.progra3.petstore.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.progra3.petstore.entities.Pet;
import com.progra3.petstore.services.PetService;

@RestController
@RequestMapping("/pets")
public class PetController {
	
	@Autowired
	PetService service;
	
	@GetMapping()
	public List<Pet> findAll(){
		return service.listAll();
	}
	
	@GetMapping("/{id}")
	public Pet findPet(@PathVariable Long id) {
		return service.findById(id);
	}
	
	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping()
	public String createPet(@RequestBody Pet pet) {
		String mensaje = "Mascota agendada";
		service.createPet(pet);
		return mensaje;
	}
	
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	@PutMapping("/{id}")
	public String updatePet(@PathVariable Long id, Pet pet) {
		String mensaje = "Mascota Modificada";
		service.updatePet(id, pet);
		return mensaje;
	}
	
	@DeleteMapping("/{id}")
	public String deletePet(@PathVariable Long id) {
		String mensaje = "Mascota Eliminada";
		service.deletePet(id);
		return mensaje;
	}
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(
			MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return errors;
	}

}
