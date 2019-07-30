package br.com.luchiari.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.luchiari.dto.CampanhaDTO;
import br.com.luchiari.model.Campanha;
import br.com.luchiari.repositories.CampanhaRepository;

@Service
public class CampanhaService {
	@Autowired
	private CampanhaRepository campanhaRepository;

	public CampanhaDTO salvar(CampanhaDTO campanhaDto) {
		Campanha campanha = new Campanha();
		campanha.setNomeCampanha(campanhaDto.getNomeCampanha());
		campanha.setInicioVigencia(campanhaDto.converteInicioVigencia());
		campanha.setFimVigencia(campanhaDto.converteFimVigencia());
		campanha.setIdTimeCoracao(campanhaDto.getIdTimeCoracao());
		campanha.setDataCriacao(new Date());
		reorganizaVigencia(campanhaDto);
		campanhaRepository.save(campanha);
		return campanhaDto;
	}

	public List<CampanhaDTO> listarCampanhas() {
		List<CampanhaDTO> responseCampanha = new ArrayList<CampanhaDTO>();
		campanhaRepository.findAllByFimVigenciaGreaterThanEqual(new Date())
				.forEach(campanha -> responseCampanha.add(new CampanhaDTO(campanha.getNomeCampanha(),
						campanha.getIdTimeCoracao(), campanha.getInicioVigencia(), campanha.getFimVigencia())));
		return responseCampanha;

	}

	public CampanhaDTO buscar(long id) throws Exception {
		Campanha campanha = campanhaRepository.findById(id).isEmpty() ? null : campanhaRepository.findById(id).get();
		CampanhaDTO campanhaDto = null;
		if (campanha != null) {
			campanhaDto = new CampanhaDTO(campanhaRepository.findById(id).get().getNomeCampanha(),
					campanhaRepository.findById(id).get().getIdTimeCoracao(),
					campanhaRepository.findById(id).get().getInicioVigencia(),
					campanhaRepository.findById(id).get().getFimVigencia());
		}
		return campanhaDto;
	}

	public List<Campanha> buscarPorIdTimeCoracao(long idTime) {
		List<Campanha> campanhas = campanhaRepository.findAllByIdTimeCoracao(idTime);
		return campanhas;
	}

	public List<CampanhaDTO> apagarCampanha(long idCampanha) {
		try {
			campanhaRepository.deleteById(idCampanha);
		} catch (Exception e) {
			return null;
		}
		List<CampanhaDTO> responseCampanha = new ArrayList<CampanhaDTO>();
		campanhaRepository.findAllByFimVigenciaGreaterThanEqual(new Date())
				.forEach(campanha -> responseCampanha.add(new CampanhaDTO(campanha.getNomeCampanha(),
						campanha.getIdTimeCoracao(), campanha.getInicioVigencia(), campanha.getFimVigencia())));
		return responseCampanha;
	}

	public CampanhaDTO alterar(CampanhaDTO campanhaDto, long idCampanha) {
		Campanha campanhaAlterada = campanhaRepository.findById(idCampanha).isEmpty() ? null
				: campanhaRepository.findById(idCampanha).get();

		if (campanhaAlterada != null) {
			campanhaAlterada.setNomeCampanha(campanhaDto.getNomeCampanha());
			campanhaAlterada.setInicioVigencia(campanhaDto.converteInicioVigencia());
			campanhaAlterada.setFimVigencia(campanhaDto.converteFimVigencia());
			campanhaAlterada.setIdTimeCoracao(campanhaDto.getIdTimeCoracao());
			campanhaRepository.save(campanhaAlterada);
			return campanhaDto;
		}
		return null;
	}

	public void reorganizaVigencia(CampanhaDTO novaCampanha) {

		Calendar c = Calendar.getInstance();
		c.setTime(novaCampanha.converteFimVigencia());
		c.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH) + 1);

		Campanha campanha = campanhaRepository
				.findTop1ByFimVigenciaLessThanEqualOrderByDataCriacaoDesc(novaCampanha.converteFimVigencia());
		if (campanha != null) {
			campanha.setFimVigencia(c.getTime());
			reordenaCampanhasAntigas(campanha);
			campanhaRepository.save(campanha);
		}
	}

	public void reordenaCampanhasAntigas(Campanha campanha) {
		Campanha oldestCampanha = campanhaRepository
				.findTop1ByFimVigenciaLessThanEqualAndIdCampanhaNotInAndDataCriacaoLessThanEqualOrderByDataCriacaoDesc(
						campanha.getFimVigencia(), campanha.getIdCampanha(), campanha.getDataCriacao());
		if (oldestCampanha != null) {
			Calendar c = Calendar.getInstance();
			c.setTime(campanha.getFimVigencia());
			c.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH) + 1);
			oldestCampanha.setFimVigencia(c.getTime());
			reordenaCampanhasAntigas(oldestCampanha);
		} else {
			return;
		}
	}
}
