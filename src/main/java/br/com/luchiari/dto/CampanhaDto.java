package br.com.luchiari.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

public class CampanhaDto {
	
	private long idCampanha;
	private String nomeCampanha;
	private long idTimeCoracao;
	
	@JsonFormat(pattern="dd-MM-yyyy")
	private Date inicioVigencia;
	
	@JsonFormat(pattern="dd-MM-yyyy")
	private Date fimVigencia;
	
	
	

	public long getIdCampanha() {
		return idCampanha;
	}

	public void setIdCampanha(long idCampanha) {
		this.idCampanha = idCampanha;
	}
	@NotNull(message = "O nome da campanha é obrigatório")
	public String getNomeCampanha() {
		return nomeCampanha;
	}

	public void setNomeCampanha(String nomeCampanha) {
		this.nomeCampanha = nomeCampanha;
	}
	
	@NotNull(message = "O id do time de coração é obrigatório")
	public long getIdTimeCoracao() {
		return idTimeCoracao;
	}
	
	public void setIdTimeCoracao(long idTimeCoracao) {
		this.idTimeCoracao = idTimeCoracao;
	}
	@NotNull(message = "Data de início de vigência é obrigatória")
	public Date getInicioVigencia() {
		return inicioVigencia;
	}

	public void setInicioVigencia(Date inicioVigencia) {
		this.inicioVigencia = inicioVigencia;
	}
	@NotNull(message = "Data de fim de vigência é obrigatória")
	public Date getFimVigencia() {
		return fimVigencia;
	}

	public void setFimVigencia(Date fimVigencia) {
		this.fimVigencia = fimVigencia;
	}

	
	
	
}
