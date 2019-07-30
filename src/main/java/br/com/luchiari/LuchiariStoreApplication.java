package br.com.luchiari;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import br.com.luchiari.dto.TimeDTO;

@SpringBootApplication
public class LuchiariStoreApplication {
	
	
	public static String apiExternasUri="http://localhost:9000/api/v1";

	public static void main(String[] args) {
		SpringApplication.run(LuchiariStoreApplication.class, args);
		LuchiariStoreApplication.popularTime();	
	}
	
	public static void popularTime() {
		
		List<TimeDTO> times = new ArrayList<TimeDTO>();
		times.add( new TimeDTO("Corinthians"));
		times.add(new TimeDTO("Santos"));
		times.add(new TimeDTO("Sao Paulo"));
		times.forEach(time -> cadastraTime(time));	
	}
	
	public static void cadastraTime(TimeDTO time) {
		final String uri = apiExternasUri + "/time";
		RestTemplate restTemplate = new RestTemplate();
		try{
			ResponseEntity<String> result = restTemplate.postForEntity(uri, time, String.class);
			System.out.println("status cadastro time " + result.getStatusCodeValue());
		} catch (HttpClientErrorException e) {
			System.out.println("erro ao carregar time!");
		}
	}

}
