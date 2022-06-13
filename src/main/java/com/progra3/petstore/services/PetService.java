package com.progra3.petstore.services;

import java.util.List;

import com.progra3.petstore.entities.Pet;

public interface PetService {
	
	public List<Pet> listAll();
	public Pet findById(Long id);
	public void createPet(Pet pet);
	public void updatePet(Long id, Pet pet);
	public void deletePet(Long id);

}
