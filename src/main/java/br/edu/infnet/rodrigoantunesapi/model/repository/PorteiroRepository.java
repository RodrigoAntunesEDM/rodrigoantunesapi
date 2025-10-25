package br.edu.infnet.rodrigoantunesapi.model.repository;

import org.springframework.stereotype.Repository;

import br.edu.infnet.rodrigoantunesapi.model.domain.Porteiro;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface PorteiroRepository extends JpaRepository<Porteiro, Integer> {
	Optional<Porteiro> findByCpf(String cpf);
}
