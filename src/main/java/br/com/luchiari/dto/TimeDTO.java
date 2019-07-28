package br.com.luchiari.dto;

import javax.validation.constraints.NotNull;

public class TimeDTO {
	
	private String nomeTime;
	
	public TimeDTO() {
		
	}
	
	public TimeDTO( String nomeTime) {
		super();
		
		this.nomeTime = nomeTime;
	}
	
	@NotNull(message = "O nome do time é obrigatório")
	public String getNomeTime() {
		return nomeTime;
	}
	public void setNomeTime(String nomeTime) {
		this.nomeTime = nomeTime;
	}
	
	
	
}
