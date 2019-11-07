package com.mercado.libre.springboot.app.models;

public class Stats {
	private Long count_mutant_dna;
	private Long count_human_dna;
	private Double radio;
	
	public Stats() {
		this.count_mutant_dna = 0L;
		this.count_human_dna = 0L;
		this.radio = 0.0;
	}
	
	public Long getCount_mutant_dna() {
		return count_mutant_dna;
	}
	public void setCount_mutant_dna(Long count_mutant_dna) {
		this.count_mutant_dna = count_mutant_dna;
	}
	public Long getCount_human_dna() {
		return count_human_dna;
	}
	public void setCount_human_dna(Long count_human_dna) {
		this.count_human_dna = count_human_dna;
	}
	public Double getRadio() {
		return radio;
	}
	public void setRadio(Double radio) {
		this.radio = radio;
	}
	
}
