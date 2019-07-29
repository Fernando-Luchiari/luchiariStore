package br.com.luchiari.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;



@Entity
@Component
@Table(name = "campanha")
public class Campanha implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6184393980989587305L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idCampanha;
	
	@Column(name = "nome_campanha", nullable = false)
	private String nomeCampanha;
	
	@Column(name = "id_time_coracao", nullable = false)
	private long idTimeCoracao;
	
	@JsonSerialize(using = DateSerializer.class)
	@Column(name = "inicio_vigencia", nullable = true)
	private Date inicioVigencia;
	
	@JsonSerialize(using = DateSerializer.class)
	@Column(name = "fim_vigencia", nullable = true)
	private Date fimVigencia;
	
	@JsonSerialize(using = DateSerializer.class)
	@Column(name = "data_criacao", nullable = true)
	private Date dataCriacao;
	
	
	public long getIdCampanha() {
		return idCampanha;
	}
	public void setIdCampanha(long idCampanha) {
		this.idCampanha = idCampanha;
	}
	
	public void setIdTimeCOracao(long idTimeCoracao) {
		this.idTimeCoracao = idTimeCoracao;
	}
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	public Date getInicioVigencia() {
		return inicioVigencia;
	}
	public void setInicioVigencia(Date inicioVigencia) {
		this.inicioVigencia = inicioVigencia;
	}
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	public Date getFimVigencia() {
		return fimVigencia;
	}
	public void setFimVigencia(Date fimVigencia) {
		this.fimVigencia = fimVigencia;
	}
	public String getNomeCampanha() {
		return nomeCampanha;
	}
	public void setNomeCampanha(String nomeCampanha) {
		this.nomeCampanha = nomeCampanha;
	}
	public long getIdTimeCoracao() {
		return idTimeCoracao;
	}
	public void setIdTimeCoracao(long idTimeCoracao) {
		this.idTimeCoracao = idTimeCoracao;
	}
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
	public Date getDataCriacao() {
		return dataCriacao;
	}
	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	
}
