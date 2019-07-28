package br.com.luchiari.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.luchiari.dto.CampanhaDto;
import br.com.luchiari.model.Campanha;
import br.com.luchiari.repositories.CampanhaRepository;

@Service
public class CampanhaService {
	@Autowired
	private CampanhaRepository campanhaRepository;

	public CampanhaDto salvar(CampanhaDto campanhaDto) {

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

	public List<CampanhaDto> listarCampanhas() {
		List<CampanhaDto> responseCampanha = new ArrayList<CampanhaDto>();
		campanhaRepository.findAllByFimVigenciaGreaterThanEqual(new Date())
				.forEach(campanha -> responseCampanha.add(new CampanhaDto(campanha.getNomeCampanha(),
						campanha.getIdTimeCoracao(), campanha.getInicioVigencia(), campanha.getFimVigencia())));
		return responseCampanha;

	}

	public CampanhaDto buscar(long id) throws Exception {
		Campanha campanha = campanhaRepository.findById(id).isEmpty() ? null : campanhaRepository.findById(id).get();
		CampanhaDto campanhaDto = null;
		if (campanha != null) {
			campanhaDto = new CampanhaDto(campanhaRepository.findById(id).get().getNomeCampanha(),
					campanhaRepository.findById(id).get().getIdTimeCoracao(),
					campanhaRepository.findById(id).get().getInicioVigencia(),
					campanhaRepository.findById(id).get().getFimVigencia());
		} else if (campanhaDto == null) {
			throw new Exception("Não existe esta campanha cadastrada");
		}
		return campanhaDto;
	}

	public List<Campanha> buscarPorIdTimeCoracao(long idTime) {
		List<Campanha> campanhas = campanhaRepository.findAllByIdTimeCoracao(idTime);
		return campanhas;
	}

	public List<CampanhaDto> apagarCampanha(long idCampanha) {
		campanhaRepository.deleteById(idCampanha);

		List<CampanhaDto> responseCampanha = new ArrayList<CampanhaDto>();
		campanhaRepository.findAllByFimVigenciaGreaterThanEqual(new Date())
				.forEach(campanha -> responseCampanha.add(new CampanhaDto(campanha.getNomeCampanha(),
						campanha.getIdTimeCoracao(), campanha.getInicioVigencia(), campanha.getFimVigencia())));
		return responseCampanha;
	}

	public CampanhaDto alterar(CampanhaDto campanhaDto, long idCampanha) {
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

	public void reorganizaVigencia(CampanhaDto novaCampanha) {
		
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
		}else {
			return;
		}
	}
}
