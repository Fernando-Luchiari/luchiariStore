package br.com.luchiari.luchiariStore;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

import static org.mockito.Mockito.mock;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.luchiari.dto.TimeDTO;
import br.com.luchiari.model.Time;
import br.com.luchiari.repositories.TimeRepository;
import br.com.luchiari.service.TimeService;

@RunWith(MockitoJUnitRunner.class)
public class TimeServiceTest {
	
	@InjectMocks
	TimeService timeService;
	
	@Mock
	TimeRepository timeRepository;
	
	@Test
	public void testSalvar() {
		Time time = new Time(1L,"Corinthians");
		TimeDTO timeDTO = new TimeDTO("Corinthians");
		when(timeRepository.save(any(Time.class))).thenReturn(time);
		
		TimeDTO timeSalvo = timeService.salvar(timeDTO);
		
		assertTrue(timeSalvo.getNomeTime().equals(time.getNomeTime()));
	}
	
	@Test
	public void testBuscarPorId() {
		Time time = new Time(1L,"Corinthians");
		when(timeRepository.findById(any(long.class))).thenReturn(Optional.of(time));
		
		Time timeSalvo = timeService.buscarPorId(1L);
		
		assertTrue(timeSalvo.getNomeTime().equals(time.getNomeTime()));
	}

}
