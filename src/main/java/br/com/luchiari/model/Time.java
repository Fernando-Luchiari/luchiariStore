package br.com.luchiari.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Component
@Table(name = "time")
public class Time  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2898026923692131943L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long idTime;
	
	@Column(name = "nome_time", nullable = false)
	public String nomeTime;
	
	public Time() {
		
	}
	
	public Time(long idTime, String nomeTime) {
		super();
		this.idTime = idTime;
		this.nomeTime = nomeTime;
	}

	public long getIdTime() {
		return idTime;
	}

	public void setIdTime(long idTime) {
		this.idTime = idTime;
	}

	public String getNomeTime() {
		return nomeTime;
	}

	public void setNomeTime(String nomeTime) {
		this.nomeTime = nomeTime;
	}
	
	
	
}
