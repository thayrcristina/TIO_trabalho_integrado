package com.projeto.TIO.repository;

import org.springframework.data.repository.CrudRepository;

import com.projeto.TIO.models.Eventos;


public interface EventoRepository extends CrudRepository<Eventos, String> {
	Eventos findByCodigo(long codigo);

	

}
