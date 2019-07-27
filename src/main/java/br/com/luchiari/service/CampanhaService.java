package br.com.luchiari.service;

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

	public Campanha salvar(CampanhaDto campanhaDto) {

		Campanha campanha = new Campanha();

		campanha.setNomeCampanha(campanhaDto.getNomeCampanha());
		campanha.setInicioVigencia(campanhaDto.getInicioVigencia());
		campanha.setFimVigencia(campanhaDto.getFimVigencia());
		campanha.setIdTimeCoracao(campanhaDto.getIdTimeCoracao());
		campanha.setDataCriacao(new Date());
		return campanhaRepository.save(campanha);
	}

	public List<Campanha> listarCampanhas() {
		return campanhaRepository.findAll();
	}

	public Campanha buscar(long id) throws Exception {
		Campanha campanha = campanhaRepository.findById(id).get();

		if (campanha == null) {
			throw new Exception("Não existe esta campanha cadastrada");
		}
		return campanha;
	}

	public List<Campanha> buscarPorIdTimeCoracao(long idTime) {
		List<Campanha> campanhas = campanhaRepository.findAllByIdTimeCoracao(idTime);
		return campanhas;
	}

	public List<Campanha> apagarCampanha(long idCampanha) {
		campanhaRepository.deleteById(idCampanha);
		return campanhaRepository.findAll();
	}

	public Campanha alterar(CampanhaDto campanhaDto, long idCampanha) {
		Campanha campanhaAlterada = campanhaRepository.findById(idCampanha).get();

		campanhaAlterada.setNomeCampanha(campanhaDto.getNomeCampanha());
		campanhaAlterada.setInicioVigencia(campanhaDto.getInicioVigencia());
		campanhaAlterada.setFimVigencia(campanhaDto.getFimVigencia());
		campanhaAlterada.setIdTimeCoracao(campanhaDto.getIdTimeCoracao());
		return campanhaRepository.save(campanhaAlterada);
	}

	public void reoganizaVigencia(CampanhaDto novaCampanha) {

		Campanha campanha = campanhaRepository
				.findTop1ByFimVigenciaLessThanEqualOrderByDataCriacaoDesc(novaCampanha.getFimVigencia());
		if (campanha != null) {
			System.out.println("ultima campanha adicionada que impacta com a nova" + campanha.getIdCampanha());

			campanha = campanhaRepository
					.findTop1ByFimVigenciaLessThanEqualAndIdCampanhaNotInOrderByDataCriacaoDesc(campanha.getFimVigencia(),campanha.getIdCampanha());

			if (campanha != null)
				System.out.println(
						"ultima campanha adicionada que impacta ja adiconada na base" + campanha.getIdCampanha());
		}
		// List<Campanha> cp = campanhas.stream().filter(c ->
		// novaCampanha.getInicioVigencia() == c.getInicioVigencia()).filter(c ->
		// novaCampanha.getFimVigencia().get(Calendar.DAY_OF_YEAR) >=
		// c.getFimVigencia().get(Calendar.DAY_OF_YEAR)).collect(Collectors.toList());
	}

}
