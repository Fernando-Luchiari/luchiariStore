package br.com.luchiari.luchiariStore;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.luchiari.dto.CampanhaDTO;
import br.com.luchiari.model.Campanha;
import br.com.luchiari.repositories.CampanhaRepository;
import br.com.luchiari.service.CampanhaService;

@RunWith(MockitoJUnitRunner.class)
public class CampanhaServiceTest {

	@InjectMocks
	CampanhaService campanhaService;

	@Mock
	CampanhaRepository campanhaRepository;

	@Test
	public void testAlterar() {
		Campanha campanha = new Campanha(1L, "Teste", 1L, "30/07/2019", "20/08/2019", "30/07/2019");
		CampanhaDTO campanhaDTO = new CampanhaDTO("Teste", 1L, new Date(), new Date());

		when(campanhaRepository.findById(any(long.class))).thenReturn(Optional.of(campanha));

		when(campanhaRepository.save(any(Campanha.class))).thenReturn(campanha);

		CampanhaDTO campanhaSalvo = campanhaService.alterar(campanhaDTO, 1L);

		assertTrue(campanhaSalvo.getNomeCampanha().equals(campanhaDTO.getNomeCampanha()));
	}

	@Test
	public void testBuscarPorId() throws Exception {
		Campanha campanha = new Campanha(1L, "Teste", 1L, "30/07/2019", "20/08/2019", "30/07/2019");
		CampanhaDTO campanhaDTO = new CampanhaDTO("Teste", 1L, new Date(), new Date());

		when(campanhaRepository.findById(any(long.class))).thenReturn(Optional.of(campanha));

		CampanhaDTO campanhaSalvo = campanhaService.buscar(1L);

		assertTrue(campanhaSalvo.getNomeCampanha().equals(campanhaDTO.getNomeCampanha()));
	}

	@Test
	public void testBuscarPorIdNulo() {
		when(campanhaRepository.findById(any(long.class))).thenReturn(Optional.ofNullable(null));
		CampanhaDTO campanhaSalvo = campanhaService.buscar(1L);

		assertNull(campanhaSalvo);
	}
}
