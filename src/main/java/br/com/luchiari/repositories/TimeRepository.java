package br.com.luchiari.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.luchiari.model.Time;

public interface TimeRepository extends JpaRepository<Time, Long>{

}
