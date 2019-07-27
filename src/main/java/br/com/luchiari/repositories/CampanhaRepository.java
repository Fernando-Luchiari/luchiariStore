package br.com.luchiari.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.luchiari.model.Campanha;

@Repository
public interface CampanhaRepository extends JpaRepository<Campanha, Long> {
	
	List<Campanha> findAllByIdTimeCoracao(long idTime);
	
	
	Campanha findTop1ByFimVigenciaLessThanEqualOrderByDataCriacaoDesc(Date fimVigencia);  
}
