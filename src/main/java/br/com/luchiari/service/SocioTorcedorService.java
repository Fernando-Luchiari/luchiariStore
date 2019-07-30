package br.com.luchiari.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import br.com.luchiari.dto.CampanhaDto;
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
		Time time = new Time();
		try {
			time = obterTime(socioTorcedorDto.getTimeCoracao());
		} catch (Exception e) {
			System.out.println("Nenhum time encontrado!");
		}
		socioTorcedor.setTimeCoracao(time);
		try {
			socioTorcedor.setCampanhas(obterCampanhas(socioTorcedorDto.getTimeCoracao()));
		} catch (Exception e1) {
			System.out.println("Nenhuma campanha encontrada");
		}
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
		return new Time(node.get("data").get("idTime").asLong(), node.get("data").get("nomeTime").asText());

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
		List<Campanha> responseCampanha = new ArrayList<Campanha>();
		ObjectNode node = mapper.readValue(previsaoJson, ObjectNode.class);
		node.get("data")
				.forEach(campanha -> responseCampanha
						.add(new Campanha(campanha.get("idCampanha").asLong(), campanha.get("nomeCampanha").asText(),
								campanha.get("idTimeCoracao").asLong(), campanha.get("inicioVigencia").asText(),
								campanha.get("fimVigencia").asText(), campanha.get("dataCriacao").asText())));

		return responseCampanha;
	}
}
