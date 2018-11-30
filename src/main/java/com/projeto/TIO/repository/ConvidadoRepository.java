package com.projeto.TIO.repository;

import org.springframework.data.repository.CrudRepository;

import com.projeto.TIO.models.Convidados;
import com.projeto.TIO.models.Eventos;

public interface ConvidadoRepository extends CrudRepository<Convidados, String>{
	Iterable<Convidados> findByEventos(Eventos eventos);
	Convidados findByRg(String rg);
}
