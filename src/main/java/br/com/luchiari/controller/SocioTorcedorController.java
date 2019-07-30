package br.com.luchiari.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.luchiari.dto.SocioTorcedorDTO;
import br.com.luchiari.model.SocioTorcedor;
import br.com.luchiari.response.Response;
import br.com.luchiari.service.SocioTorcedorService;

@RestController
@RequestMapping("/api/v1")
public class SocioTorcedorController {
	@Autowired
	private SocioTorcedorService socioTorcedorService;
	
	
	@PostMapping("/socioTorcedor")
	@ResponseBody
	public ResponseEntity<Response<SocioTorcedorDTO>> createSocioTorcedor(@Valid @RequestBody SocioTorcedorDTO socioTorcedor,
			BindingResult result){
		
		SocioTorcedor validaSocio = socioTorcedorService.validaEmail(socioTorcedor);
		
		Response<SocioTorcedorDTO> response = new Response<SocioTorcedorDTO>();
		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}else if(validaSocio != null) {
			response.getErrors().add("Já existe um sócio cadastrado com esse email");
			return ResponseEntity.badRequest().body(response);
		}
		
		response.setData(socioTorcedorService.salvar(socioTorcedor));
		return ResponseEntity.status(HttpStatus.OK).body(response);
		
		
	}
	
	
	
	
}
