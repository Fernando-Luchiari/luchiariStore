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
	
	private String regexVigencia = "([0-9]{2}/[0-9]{2}/[0-9]{4})\\s[a]\\s([0-9]{2}/[0-9]{2}/[0-9]{4})";

	@GetMapping("/campanhas")
	@ResponseBody
	public List<CampanhaDto> getCampanha() {
		return campanhaService.listarCampanhas();
	}

	@GetMapping("/campanhas/{id}")
	@ResponseBody
	public ResponseEntity<Response<CampanhaDto>> getCampanha(@PathVariable(value = "id") long id) throws Exception {
		CampanhaDto campanhaDto = campanhaService.buscar(id);		
		Response<CampanhaDto> response = new Response<CampanhaDto>();
		response.setData(campanhaDto);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@PostMapping("/campanhas")
	@ResponseBody
	public ResponseEntity<Response<CampanhaDto>> createCampanha(@Valid @RequestBody CampanhaDto campanhaDto,
			BindingResult result) {

		Response<CampanhaDto> response = new Response<CampanhaDto>();
		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}else if(!campanhaDto.getVigencia().matches(regexVigencia)) {
			response.getErrors().add("Data de Vigência fora do formato dd/MM/yyyy a dd/MM/yyyy");
			return ResponseEntity.badRequest().body(response);
		}else if(!campanhaDto.validaDataInicio() && !campanhaDto.validaDataFim()) {
			response.getErrors().add("Data de Vigência menor que a data atual");
			return ResponseEntity.badRequest().body(response);
		}

		CampanhaDto novaCampanhaDto = campanhaService.salvar(campanhaDto);
		response.setData(novaCampanhaDto);
		return ResponseEntity.status(HttpStatus.OK).body(response);
		
	}

	@DeleteMapping("/campanhas/{id}")
	public ResponseEntity<Response<List<CampanhaDto>>> deleteCampanha(@PathVariable(value = "id") int idCampanha) {

		Response<List<CampanhaDto>> response = new Response<List<CampanhaDto>>();
		List<CampanhaDto> campanhas = campanhaService.apagarCampanha(idCampanha);

		if(campanhas == null) {
			response.getErrors().add("Nenhuma campanha com esse id foi encontrada");
			return ResponseEntity.badRequest().body(response);
		}
		
		response.setData(campanhas);
		return ResponseEntity.status(HttpStatus.OK).body(response);

	}

	@PutMapping("/campanhas/{id}")
	public ResponseEntity<Response<CampanhaDto>> updateCampanha(@PathVariable(value = "id") Long idCampanha,
			@Valid @RequestBody CampanhaDto campanhaDto, BindingResult result) {

		Response<CampanhaDto> response = new Response<CampanhaDto>();

		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}else if(!campanhaDto.getVigencia().matches(regexVigencia)) {
			response.getErrors().add("Data de Vigência fora do formato dd/MM/yyyy a dd/MM/yyyy");
			return ResponseEntity.badRequest().body(response);
		}else if(!campanhaDto.validaDataInicio() && !campanhaDto.validaDataFim()) {
			response.getErrors().add("Data de Vigência menor que a data atual");
			return ResponseEntity.badRequest().body(response);
		}

		CampanhaDto novaCampanha = campanhaService.alterar(campanhaDto, idCampanha);
		if(novaCampanha == null) {
			response.getErrors().add("Nenhuma campanha com esse id foi encontrada");
			return ResponseEntity.badRequest().body(response);
		}		
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
