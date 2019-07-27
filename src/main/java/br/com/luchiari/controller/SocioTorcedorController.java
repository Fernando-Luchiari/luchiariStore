package br.com.luchiari.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.luchiari.model.SocioTorcedor;

public class SocioTorcedorController {

	private static List<SocioTorcedor> socios = new ArrayList<SocioTorcedor>();
	
	@GetMapping("/socios/{id}")
	@ResponseBody
	public SocioTorcedor getCampanha(@PathVariable(value = "id") int idSocio){
		SocioTorcedor socio = socios.stream().filter(c -> idSocio == c.getIdSocio()).findAny().get();
		return socio;
	}
	
	@PostMapping("/socioTorcedor")
	@ResponseBody
	public Map<String, String> createCampanha(@RequestBody SocioTorcedor socioTorcedor){
		
		socios.add(socioTorcedor);
		Map<String, String> response = new HashMap<>();
	    response.put("message","Sócio adicionado!");
	    return response;
	}
	
	@DeleteMapping("/socioTorcedor/{id}")
	public Map<String, String> deleteCampanha(@PathVariable(value = "id") int idSocio){
		
		SocioTorcedor socio = socios.stream().filter(c -> idSocio == c.getIdSocio()).findAny().get();
		socios.remove(socio);
		
		Map<String, String> response = new HashMap<>();
	    response.put("message","Sócio apagado!");
	    return response;
	}
	
	@PutMapping("/socios/{id}")
	public Map<String,String> updateCampanha(@PathVariable(value = "id") int idSocio, @RequestBody SocioTorcedor socio){
		SocioTorcedor cp = socios.stream().filter(c -> idSocio == c.getIdSocio()).findAny().get();
		//cp.setFimVigencia(campanha.getFimVigencia());
		socios.remove(cp);
		socios.add(socio);
		Map<String, String> response = new HashMap<>();
	    response.put("message","Sócio atualizado");
	    return response;
	}
	
	
}
