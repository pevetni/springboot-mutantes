package com.mercado.libre.springboot.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mercado.libre.springboot.app.models.Request;
import com.mercado.libre.springboot.app.services.IServiceMutante;

@RestController
public class MutantesController {
	
	@Autowired
	IServiceMutante serviceMutante;

	@PostMapping("/mutant/")
	public ResponseEntity<?> isMutant(@RequestBody Request req) {
		if(this.serviceMutante.isMutant(req.getDna())) 
			return new ResponseEntity<>(true,HttpStatus.OK);

		return new ResponseEntity<>(false,HttpStatus.FORBIDDEN);
	}
	
	@GetMapping("/stats")
	public ResponseEntity<?> stats() {
		return new ResponseEntity<>(this.serviceMutante.stats(),HttpStatus.OK);
	}
}
