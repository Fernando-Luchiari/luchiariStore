package br.com.luchiari.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.luchiari.model.SocioTorcedor;

public interface SocioTorcedorRepository  extends JpaRepository<SocioTorcedor, Long>{

	SocioTorcedor findAllByEmailEquals(String email);
	
}
