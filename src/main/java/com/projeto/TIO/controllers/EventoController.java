package com.projeto.TIO.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.projeto.TIO.models.Convidados;
import com.projeto.TIO.models.Eventos;
import com.projeto.TIO.repository.ConvidadoRepository;
import com.projeto.TIO.repository.EventoRepository;

@Controller
public class EventoController {

	@Autowired
	private EventoRepository er;

	@Autowired
	private ConvidadoRepository cr;

	@RequestMapping(value = "/cadastrarEvento", method = RequestMethod.GET)
	public String form() {
		return "evento/formEvento";

	}

	@RequestMapping(value = "/cadastrarEvento", method = RequestMethod.POST)
	public String form(@Valid Eventos eventos, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique os campos!");
			return "evento/formEvento";
		}

		er.save(eventos);
		attributes.addFlashAttribute("mensagem", "Evento cadastrado com sucesso!");
		return "evento/formEvento";
		
	}

	@RequestMapping("/eventos")
	public ModelAndView listaEventos() {
		ModelAndView mv = new ModelAndView("index");
		Iterable<Eventos> eventos = er.findAll();
		mv.addObject("eventos", eventos);
		return mv;

	}

	@RequestMapping(value = "/{codigo}", method = RequestMethod.GET)
	public ModelAndView detalhesEvento(@PathVariable("codigo") long codigo) {
		Eventos eventos = er.findByCodigo(codigo);
		ModelAndView mv = new ModelAndView("evento/detalhesEventos");
		mv.addObject("eventos", eventos);
		Iterable<Convidados> convidados = cr.findByEventos(eventos);
		mv.addObject("convidados", convidados);
		return mv;

	}
	@RequestMapping("/deletar")
  public String deletarEvento(long codigo){
		Eventos eventos = er.findByCodigo(codigo);
		er.delete(eventos);
		return "redirect:/eventos";
	}
	
	@RequestMapping(value = "/{codigo}", method = RequestMethod.POST)
	public String detalhesEventos(@PathVariable("codigo") long codigo, @Valid Convidados convidados,
			BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique os campos!");
			return "redirect:/{codigo}";
		}
		Eventos eventos = er.findByCodigo(codigo);
		convidados.setEventos(eventos);
		cr.save(convidados);
		attributes.addFlashAttribute("mensagem", "Convidado adicionado com sucesso!");
		return "redirect:/{codigo}";
	}
	
	@RequestMapping("/deletarConvidados")
	public String deletarConvidados(String rg){
		Convidados convidados = cr.findByRg(rg);
		cr.delete(convidados);
		
		Eventos eventos = convidados.getEventos();
		long codigoLong = eventos.getCodigo();
		String codigo = "" + codigoLong;
		return "redirect:/" + codigo;
	}
}