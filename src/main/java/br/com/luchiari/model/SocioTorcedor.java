package br.com.luchiari.model;

import java.util.ArrayList;
import java.util.Calendar;

public class SocioTorcedor {
	
	private int idSocio;
	private String Nome;
	private String Sobrenome;
	private Calendar dataNascimento;
	private int idTimeCoracao;
	private String email;
	private ArrayList<Campanha> campanhas;
	
		
	public int getIdSocio() {
		return idSocio;
	}
	public void setIdSocio(int idSocio) {
		this.idSocio = idSocio;
	}
	public String getNome() {
		return Nome;
	}
	public void setNome(String nome) {
		Nome = nome;
	}
	public String getSobrenome() {
		return Sobrenome;
	}
	public void setSobrenome(String sobrenome) {
		Sobrenome = sobrenome;
	}
	public Calendar getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Calendar dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public int getIdTimeCoracao() {
		return idTimeCoracao;
	}
	public void setIdTimeCoracao(int idTimeCoracao) {
		this.idTimeCoracao = idTimeCoracao;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public ArrayList<Campanha> getCampanhas() {
		return campanhas;
	}
	public void setCampanhas(ArrayList<Campanha> campanhas) {
		this.campanhas = campanhas;
	}

}
