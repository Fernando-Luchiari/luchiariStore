package br.com.luchiari.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Component
@Table(name = "socioTorcedor")
public class SocioTorcedor implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2282234407223179143L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idSocio;
	
	@Column(name = "nomeCompleto_torcedor", nullable = false)
	private String nomeCompleto;
	
	
	
	@Column(name = "data_nascimento", nullable = false)
	private Date dataNascimento;
	
	@ManyToOne
	@JoinColumn(name = "idTime")
	private Time timeCoracao;
	
	@Column(name = "email", nullable = false)
	private String email;
	
	@ManyToMany
	@JoinColumn(name="id_socio", nullable = true)
	private List<Campanha> campanhas;
	
		
	public int getIdSocio() {
		return idSocio;
	}
	public void setIdSocio(int idSocio) {
		this.idSocio = idSocio;
	}
	public String getNomeCompleto() {
		return nomeCompleto;
	}
	public void setNome(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}
	
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public Time getTimeCoracao() {
		return timeCoracao;
	}
	public void setTimeCoracao(Time timeCoracao) {
		this.timeCoracao = timeCoracao;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<Campanha> getCampanhas() {
		return campanhas;
	}
	public void setCampanhas(List<Campanha> campanhas) {
		this.campanhas = campanhas;
	}

}
