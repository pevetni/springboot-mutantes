package com.mercado.libre.springboot.app.services.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mercado.libre.springboot.app.models.Resultado;
import com.mercado.libre.springboot.app.models.Stats;
import com.mercado.libre.springboot.app.repository.IMutantesRepository;
import com.mercado.libre.springboot.app.services.IServiceMutante;

@Service
public class ServiceMutanteImpl implements IServiceMutante{
	
	@Autowired
	IMutantesRepository repoMutantes;

	@Override
	public Boolean isMutant(String[] dna) {
		
		if(findSecuenceVertical(dna) || findSecuenceHorizontal(dna) || findSecuenceOblicuo(dna)) {
			this.registrarMutante(dna);
			return true;
		}
		this.registrarHumano(dna);
		return false;
	}
	
	
	private boolean findSecuenceHorizontal(String[] dna) {
		int horizontal = 0;
		for (int i = 0; i < dna.length; i++) {		// El primer índice recorre las filas.
			horizontal = 0;
			for (int j = 0; j < dna[i].length(); j++) {	// El segundo índice recorre las columnas.
				if(j==0) {
					horizontal++;
				}else if(dna[i].charAt(j-1)==dna[i].charAt(j)) { //aca invierto la forma en que se compara con el caracter anterior
					horizontal++; 
				}else if(horizontal==4) {
					return true;
				}
			}
		}
			
		return false;
	}
	
	private boolean findSecuenceVertical(String[] dna) {
		int vertical = 0;
		for (int i = 0; i < dna.length; i++) {		// El primer índice recorre las filas.
			vertical = 0;
			for (int j = 0; j < dna[i].length(); j++){	// El segundo índice recorre las columnas.
				if(j==0) {
					vertical++;
				}else if(dna[j].charAt(i)==dna[j-1].charAt(i)) { //aca invierto la forma en que se compara con el caracter anterior
					vertical++;
				}else if(vertical==4) {
					return true;
				}
			}
		}
		return false;
	}
	
	private boolean findSecuenceOblicuo(String[] dna) {
		String cadena = "";
		boolean mutante = false;
		for(int j=dna[0].length()-1; j>=0; j--){
            for(int k=0; k<dna.length; k++){
                if((j + k) < dna[0].length()){
                    cadena = cadena + dna[k].charAt(j + k);
                } else {
                    break;
                }
            }
            mutante = validarDiagonal(cadena);
        }
		if(!mutante)
	        for(int i=1; i<dna.length; i++){
	            for(int j=i, k=0; j<dna.length && k<dna[0].length(); j++, k++){
	                cadena = cadena + dna[j].charAt(k);
	            }
	            mutante = validarDiagonal(cadena);
	        }
		
		return mutante;
	}
	
	private boolean validarDiagonal(String cadena) {
		int diagonal=0;
		if(cadena.length()>=4)
			for(int i=0;i<cadena.length();i++) {
				if(i==0) {
					diagonal++;
				}else if(cadena.charAt(i)==cadena.charAt(i-1)) { //aca invierto la forma en que se compara con el caracter anterior
					diagonal++;
				}else if(diagonal>=4) {
					return true;
				}
			}
		return false;
	}
	
	private void registrarMutante(String[] dna) {
		Resultado res = new Resultado();
		res.setDna(Arrays.stream(dna)
	            .collect(Collectors.joining()));
		Optional<Resultado> resBD = repoMutantes.findById(res.getDna());
		if(!resBD.isPresent()) {
			res.setMutantes(1L);
			repoMutantes.save(res);
		}		
	}
	
	private void registrarHumano(String[] dna) {
		Resultado res = new Resultado();
		res.setDna(Arrays.stream(dna)
	            .collect(Collectors.joining()));
		Optional<Resultado> resBD = repoMutantes.findById(res.getDna());
		if(!resBD.isPresent()) {
			res.setHumanos(1L);
			repoMutantes.save(res);
		}		
	}


	@Override
	public Stats stats() {
		List<Resultado> respList = repoMutantes.findAll();
		Stats stats = new Stats();
		for(Resultado res:respList) {
			stats.setCount_human_dna(stats.getCount_human_dna()+res.getHumanos());
			stats.setCount_mutant_dna(stats.getCount_mutant_dna()+res.getMutantes());
		}
		
		if(stats.getCount_mutant_dna()>0 && stats.getCount_human_dna()>0)
			stats.setRadio(stats.getCount_mutant_dna().doubleValue()/stats.getCount_human_dna().doubleValue());
		
		return stats;
	}

}
