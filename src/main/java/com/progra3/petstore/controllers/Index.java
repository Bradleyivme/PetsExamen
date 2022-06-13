package com.progra3.petstore.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Index {

	@GetMapping
	private String index() {
		return "Bienvenido Examen Final de Programacion";
	}
}
