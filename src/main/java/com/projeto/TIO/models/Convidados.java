package com.projeto.TIO.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Convidados {
	
	@Id
	@NotEmpty
	private String rg;
	@NotEmpty
	private String nomeconvidado;
	
	@ManyToOne
	private Eventos eventos; 
	
	
	public String getRg() {
		return rg;
	}
	public void setRg(String rg) {
		this.rg = rg;
	}
	public String getNomeconvidado() {
		return nomeconvidado;
	}
	public void setNomeconvidado(String nomeconvidado) {
		this.nomeconvidado = nomeconvidado;
	}
	public Eventos getEventos() {
		return eventos;
	}
	public void setEventos(Eventos eventos) {
		this.eventos = eventos;
	}
	
	
}
