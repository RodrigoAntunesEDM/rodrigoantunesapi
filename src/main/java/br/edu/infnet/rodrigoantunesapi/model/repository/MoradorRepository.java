package br.edu.infnet.rodrigoantunesapi.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.infnet.rodrigoantunesapi.model.domain.Morador;

public interface MoradorRepository extends JpaRepository<Morador, Integer>{

}
