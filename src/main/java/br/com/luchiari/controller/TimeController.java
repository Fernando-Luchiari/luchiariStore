package br.com.luchiari.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.luchiari.dto.TimeDTO;
import br.com.luchiari.model.Time;
import br.com.luchiari.response.Response;
import br.com.luchiari.service.TimeService;

@RestController
@RequestMapping("/api/v1")
public class TimeController {

	@Autowired
	private TimeService timeService;

	
	@GetMapping("/times")
	@ResponseBody
	public List<Time> getTimes() {
		return timeService.listarTimes();
	}
	
	@PostMapping("/time")
	@ResponseBody
	public ResponseEntity<Response<TimeDTO>> createTime(@Valid @RequestBody TimeDTO timeDto, BindingResult result) {

		Response<TimeDTO> response = new Response<TimeDTO>();
		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		TimeDTO time = timeService.salvar(timeDto);
		response.setData(time);
		return ResponseEntity.status(HttpStatus.OK).body(response);

	}

	@GetMapping("/time/{idTime}")
	@ResponseBody
	public ResponseEntity<Response<Time>> getTimePorId(@PathVariable(value = "idTime") long idTime){
		Response<Time> response = new Response<Time>();
		
		Time time= timeService.buscarPorId(idTime);
		response.setData(time);
		return ResponseEntity.status(HttpStatus.OK).body(response);
		
	}

}
