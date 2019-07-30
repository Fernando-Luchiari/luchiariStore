package br.com.luchiari.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

public class SocioTorcedorDTO {

	private String nomeCompleto;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private Date dataNascimento;

	private long timeCoracao;

	private String email;

	@NotNull(message = "O e-mail é obrigatório")
	public String getEmail() {
		return this.email;
	}

	@NotNull(message = "O nome é obrigatório")
	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	@NotNull(message = "A data de nascimento é obrigatória")
	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	@NotNull(message = "O time de coração é obrigatório")
	public long getTimeCoracao() {
		return timeCoracao;
	}

	public void setTimeCoracao(long timeCoracao) {
		this.timeCoracao = timeCoracao;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
