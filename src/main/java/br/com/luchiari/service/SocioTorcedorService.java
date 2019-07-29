package br.com.luchiari.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import br.com.luchiari.dto.SocioTorcedorDto;
import br.com.luchiari.model.Campanha;
import br.com.luchiari.model.SocioTorcedor;
import br.com.luchiari.model.Time;
import br.com.luchiari.repositories.SocioTorcedorRepository;

@Service
public class SocioTorcedorService {
	@Autowired
	private SocioTorcedorRepository socioTorcedorRepository;
	
	@Value("${apisExternasUri}")
	String apiExternasUri;

	@Autowired
	private ObjectMapper mapper;
	
	public SocioTorcedorDto salvar(SocioTorcedorDto socioTorcedorDto) {
		SocioTorcedor socioTorcedor = new SocioTorcedor();

		socioTorcedor.setNome(socioTorcedorDto.getNomeCompleto());
		socioTorcedor.setDataNascimento(socioTorcedorDto.getDataNascimento());
		socioTorcedor.setEmail(socioTorcedorDto.getEmail());
		Time time =new Time();
		try {
			time = obterTime(socioTorcedorDto.getTimeCoracao());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		socioTorcedor.setTimeCoracao(time);
		//socioTorcedor.setsocioTorcedorDto.getEmail());
		socioTorcedorRepository.save(socioTorcedor);
		
		try {
			List<Campanha> campanhas = obterCampanhas(socioTorcedorDto.getTimeCoracao());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return socioTorcedorDto;
	}

	public SocioTorcedor validaEmail(SocioTorcedorDto socioTorcedorDto) {
		return socioTorcedorRepository.findAllByEmailEquals(socioTorcedorDto.getEmail());

	}
	
	public Time obterTime(long idTime) throws Exception {
		final String uri = apiExternasUri + "/time/" + idTime;
		RestTemplate restTemplate = new RestTemplate();

		String previsaoJson = "";

		try {
			previsaoJson = restTemplate.getForObject(uri, String.class);
		} catch (HttpClientErrorException hcee) {
			return null;
		}

		ObjectNode node = mapper.readValue(previsaoJson, ObjectNode.class);
		return new Time(node.get("data").get("idTime").asLong(),node.get("data").get("nomeTime").asText());
		
	}
	
	public List<Campanha> obterCampanhas(long idTime) throws Exception {
		final String uri = apiExternasUri + "/campanhas/timeCoracao/" + idTime;
		RestTemplate restTemplate = new RestTemplate();

		String previsaoJson = "";
		try {
			previsaoJson = restTemplate.getForObject(uri, String.class);
		} catch (HttpClientErrorException hcee) {
			return null;
		}
		ObjectNode node = mapper.readValue(previsaoJson, ObjectNode.class);
		node.get("data");
		//node.get("data").get("idTime").asLong(),node.get("data").get("nomeTime").asText());
		return null;
	}
}
