package com.mercado.libre.springboot.app.services;

import com.mercado.libre.springboot.app.models.Stats;

public interface IServiceMutante {
	public Boolean isMutant(String[] dna);
	
	public Stats stats();
}
