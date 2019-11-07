package com.mercado.libre.springboot.app.models;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Document(collection = "mutantes_humanos")
@JsonPropertyOrder({"dna", "mutantes", "humanos", "radio"})
public class Resultado implements Serializable{

	@Id
	private String dna;
	
	private Long mutantes;
	private Long humanos;
	
	
	public Resultado() {
		this.mutantes = 0L;
		this.humanos = 0L;
	}
	
	public String getDna() {
		return dna;
	}
	public void setDna(String dna) {
		this.dna = dna;
	}
	public Long getMutantes() {
		return mutantes;
	}
	public void setMutantes(Long mutantes) {
		this.mutantes = mutantes;
	}
	public Long getHumanos() {
		return humanos;
	}
	public void setHumanos(Long humanos) {
		this.humanos = humanos;
	}
	
	private static final long serialVersionUID = 5059958510964272586L;
}
