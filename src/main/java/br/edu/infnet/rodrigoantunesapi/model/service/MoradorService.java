package br.edu.infnet.rodrigoantunesapi.model.service;

import java.util.List;
import org.springframework.stereotype.Service;

import br.edu.infnet.rodrigoantunesapi.exceptions.MoradorInvalidoException;
import br.edu.infnet.rodrigoantunesapi.exceptions.MoradorNaoEncontradoException;
import br.edu.infnet.rodrigoantunesapi.model.domain.Morador;
import br.edu.infnet.rodrigoantunesapi.model.repository.MoradorRepository;


@Service
public class MoradorService implements CrudService<Morador, Integer>{

	private final MoradorRepository moradorRepository;
	
	
	public MoradorService(MoradorRepository moradorRepository) {
		this.moradorRepository = moradorRepository;
	}


	private void validarMorador(Morador morador) {
		//RN - Morador Null
		if(morador == null) {
			throw new IllegalArgumentException("O morador não pode ser nulo.");
		}
		
		//RN - Nome do morador obrigatório
		if(morador.getNome() == null || morador.getNome().trim().isEmpty()) {
			throw new MoradorInvalidoException("O nome do morador é obrigatório.");
		}
		
		//RN - CPF obrigatório
		if(morador.getCpf() == null || morador.getCpf().trim().isEmpty()) {
			throw new MoradorInvalidoException("O CPF do morador é obrigatório.");
		}
		
		//RN - A Apto é obrigatório
		if(morador.getApartamento() == null || morador.getApartamento().trim().isEmpty()) {
			throw new MoradorInvalidoException("O apartamento do morador é obrigatório.");
		}
				
		//RN - O contato é obrigatório		
		if ((morador.getContato() == null || morador.getContato().isEmpty()) 
				|| morador.getContato().get(0).getTipoContato()==null ){
			throw new MoradorInvalidoException("O tipo e contato do morador é obrigatório.");
		}
	}
	
	
	@Override
	public Morador buscarPorId(Integer id) {
		
		if(id == null || id <= 0) {
			throw new IllegalArgumentException("O ID utilizado deve ser  maior que zero");
		}
		
		return moradorRepository.findById(id).orElseThrow(() -> new MoradorNaoEncontradoException("O morador com o ID [\"+id+\"] não foi encontrado!"));
	
	}
	
	@Override
	public Morador salvar(Morador morador) {
		
		validarMorador(morador);
		
		if (morador.getContato() != null) {
	        morador.getContato().forEach(c -> c.setMorador(morador));
	    }
		
		moradorRepository.save(morador);
				
		return morador;
	}



	@Override
	public Morador atualizar(Integer id, Morador morador) {
		
		buscarPorId(id);
		validarMorador(morador);
		
		morador.setId(id);
		
		
	    if (morador.getContato() != null) {
	        morador.getContato().forEach(c -> c.setMorador(morador));
	    }
		
		return	moradorRepository.save(morador);
	}

	@Override
	public List<Morador> listarTodos() {
		return moradorRepository.findAll();
	}

	@Override
	public void excluir(Integer id) {
		Morador morador = buscarPorId(id);
		
		moradorRepository.delete(morador);
	}
	
	public Morador inativar (Integer id) {
		 Morador morador = buscarPorId(id);

		 morador.setAtivo(false); 
		 
		 return moradorRepository.save(morador);
	}
	
	
	public Morador buscarPorCpf(String cpf) {
	    if (cpf == null || cpf.trim().isEmpty()) {
	        throw new IllegalArgumentException("O CPF não pode ser nulo ou vazio.");
	    }

	    return moradorRepository.findByCpf(cpf)
	            .orElseThrow(() -> new MoradorNaoEncontradoException("Morador com CPF " + cpf + " não encontrado."));
	}
	
	
}