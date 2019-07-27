package br.com.luchiari.model;

public class Time {
	public int idTime;
	public String nomeTime;
	
	public Time(int idTime, String nomeTime) {
		super();
		this.idTime = idTime;
		this.nomeTime = nomeTime;
	}

	public int getIdTime() {
		return idTime;
	}

	public void setIdTime(int idTime) {
		this.idTime = idTime;
	}

	public String getNomeTime() {
		return nomeTime;
	}

	public void setNomeTime(String nomeTime) {
		this.nomeTime = nomeTime;
	}
	
	
	
}
