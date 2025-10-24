package br.edu.infnet.rodrigoantunesapi.model.service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;

import br.edu.infnet.rodrigoantunesapi.exceptions.MoradorInvalidoException;
import br.edu.infnet.rodrigoantunesapi.exceptions.MoradorNaoEncontradoException;
import br.edu.infnet.rodrigoantunesapi.model.domain.Morador;
import br.edu.infnet.rodrigoantunesapi.model.repository.MoradorRepository;


@Service
public class MoradorService implements CrudService<Morador, Integer>{

	private final Map<Integer, Morador> mapa = new ConcurrentHashMap<Integer, Morador>();
	private final AtomicInteger nextId = new AtomicInteger(1);
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
		if(
		  (morador.getContato().getTipoContato() == null) || 
		  (morador.getContato().getContato()== null || morador.getContato().getContato().trim().isEmpty())) {
			throw new MoradorInvalidoException("O tipo e contato do morador é obrigatório.");
		}
	}
	
	
	@Override
	public Morador buscarPorId(Integer id) {
		
		if(id == null || id <= 0) {
			throw new IllegalArgumentException("O ID utilizado deve ser  maior que zero");
		}
		
		//Morador morador = mapa.get(id);
		
		//if(morador == null) {
		//	throw new MoradorNaoEncontradoException("O morador com o ID ["+id+"] não foi encontrado!");
		//}
		
		//return morador;
				
		return moradorRepository.findById(id).orElseThrow(() -> new MoradorNaoEncontradoException("O morador com o ID [\"+id+\"] não foi encontrado!"));
	
	}
	
	@Override
	public Morador salvar(Morador morador) {
		
		validarMorador(morador);
		
		moradorRepository.save(morador);
		
		//Manter te alterar o buscaporCPF
		morador.setId(nextId.getAndIncrement());
		mapa.put(morador.getId(), morador);
		
		
		return morador;
	}



	@Override
	public Morador atualizar(Integer id, Morador morador) {
		
		Morador moradorEncontrado = buscarPorId(id);
		validarMorador(morador);
		
		morador.setId(id);
		
		mapa.put(moradorEncontrado.getId(), morador);
		
		//return buscarPorId(moradorEncontrado.getId());
		
		return	moradorRepository.save(morador);
	}

	@Override
	public List<Morador> listarTodos() {
		//return new ArrayList<Morador>(mapa.values());
		return moradorRepository.findAll();
	}

	@Override
	public void excluir(Integer id) {
		Morador morador = buscarPorId(id);
		
		mapa.remove(morador.getId());
		
		moradorRepository.delete(morador);
	}
	
	public Morador inativar (Integer id) {
		 Morador morador = buscarPorId(id);

		 morador.setAtivo(false); 
		 mapa.put(id, morador);   
		 
		// return morador;
		 
		 return moradorRepository.save(morador);
	}
	
	public Morador buscarPorCpf (String cpf) {
	    return mapa.values().stream()
	            .filter(m -> cpf.equals(m.getCpf()))
	            .findFirst()
	            .orElse(null);     
	}
}