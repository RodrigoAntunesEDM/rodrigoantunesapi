package br.edu.infnet.rodrigoantunesapi.model.service;

import org.springframework.stereotype.Service;

import br.edu.infnet.rodrigoantunesapi.model.domain.Endereco;
import br.edu.infnet.rodrigoantunesapi.model.repository.EnderecoRepository;

@Service
public class EnderecoService {


    private final EnderecoRepository enderecoRepository;
    
    public EnderecoService(EnderecoRepository enderecoRepository) {
		this.enderecoRepository = enderecoRepository;
	}

	public Endereco obterOuCriar(Endereco endereco) {
        return enderecoRepository.findByCep(endereco.getCep())
                .orElseGet(() -> enderecoRepository.save(endereco));
    }
}
