package br.com.luchiari.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.luchiari.dto.CampanhaDto;
import br.com.luchiari.model.Campanha;
import br.com.luchiari.response.Response;
import br.com.luchiari.service.CampanhaService;

@RestController
@RequestMapping("/api/v1")
public class CampanhasController {
	@Autowired
	private CampanhaService campanhaService;

	@GetMapping("/campanhas")
	@ResponseBody
	public List<Campanha> getCampanha() {
		return campanhaService.listarCampanhas();
	}

	@GetMapping("/campanhas/{id}")
	@ResponseBody
	public ResponseEntity<Response<Campanha>> getCampanha(@PathVariable(value = "id") long id) throws Exception {
		Campanha campanha = campanhaService.buscar(id);		
		Response<Campanha> response = new Response<Campanha>();
		response.setData(campanha);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@PostMapping("/campanhas")
	@ResponseBody
	public ResponseEntity<Response<Campanha>> createCampanha(@Valid @RequestBody CampanhaDto campanhaDto,
			BindingResult result) {

		Response<Campanha> response = new Response<Campanha>();
		
		campanhaService.reoganizaVigencia(campanhaDto);

		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		Campanha novaCampanha = campanhaService.salvar(campanhaDto);
		response.setData(novaCampanha);
		return ResponseEntity.status(HttpStatus.OK).body(response);
		
	}

	@DeleteMapping("/campanhas/{id}")
	public ResponseEntity<Response<List<Campanha>>> deleteCampanha(@PathVariable(value = "id") int idCampanha) {

		Response<List<Campanha>> response = new Response<List<Campanha>>();
		List<Campanha> campanhas = campanhaService.apagarCampanha(idCampanha);

		response.setData(campanhas);
		return ResponseEntity.status(HttpStatus.OK).body(response);

	}

	@PutMapping("/campanhas/{id}")
	public ResponseEntity<Response<Campanha>> updateCampanha(@PathVariable(value = "id") Long idCampanha,
			@RequestBody CampanhaDto campanhaDto, BindingResult result) {

		Response<Campanha> response = new Response<Campanha>();

		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		Campanha novaCampanha = campanhaService.alterar(campanhaDto, idCampanha);
		response.setData(novaCampanha);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@GetMapping("/campanhas/timeCoracao/{idTimeCoracao}")
	@ResponseBody
	public ResponseEntity<Response<List<Campanha>>> getCampanhasTimeCoracao(
			@PathVariable(value = "idTimeCoracao") long idTimecoracao) {
		List<Campanha> campanhas = campanhaService.buscarPorIdTimeCoracao(idTimecoracao);
		Response<List<Campanha>> response = new Response<List<Campanha>>();
		response.setData(campanhas);
		return ResponseEntity.status(HttpStatus.OK).body(response);

	}

	

}
