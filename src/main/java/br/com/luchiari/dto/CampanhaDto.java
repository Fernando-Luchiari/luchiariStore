package br.com.luchiari.dto;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class CampanhaDto {
	
	
	private String nomeCampanha;
	private long idTimeCoracao;
	
	
	private String vigencia;
	
	public CampanhaDto() {
		
	}
	
	public CampanhaDto(String nomeCampanha, long idTimeCOracao, Date inicioVigencia, Date fimVigencia) {
		this.nomeCampanha = nomeCampanha;
		this.idTimeCoracao = idTimeCOracao;
		setVigencia(inicioVigencia, fimVigencia);
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
	
	@NotNull(message = "A vigência é obrigatória")
	@Length(min = 23, max = 23, message = "A vigencia não está preenchida corretamente")
	public String getVigencia() {
		return vigencia;
	}

	public void setVigencia(String vigencia) {
		this.vigencia = vigencia;
	}
	
	private void setVigencia(Date inicioVigencia, Date fimVigencia) {
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String strDateInicio = dateFormat.format(inicioVigencia);
		String strDateFim = dateFormat.format(fimVigencia);
		String vigencia = strDateInicio + " a " + strDateFim;
		setVigencia(vigencia);
	}
	
	public Boolean validaDataInicio() {
		Date dataInicio = converteInicioVigencia();
		return dataInicio.compareTo(new Date())>=0?true:false;
	}
	
	public Boolean validaDataFim() {
		Date dataFim = converteFimVigencia();
		return dataFim.compareTo(new Date())>=0?true:false;
	}
	
	public Date converteInicioVigencia() {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		String inicioVigencia = this.vigencia.substring(0,10);
		Date data = new Date();
		try {
			data = formato.parse(inicioVigencia);
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return data;		
	}
	
	public Date converteFimVigencia() {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		String fimVigencia = this.vigencia.substring(12, vigencia.length());
		Date data = new Date();
		try {
			data = formato.parse(fimVigencia);
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return data;
		
	}
	
}
