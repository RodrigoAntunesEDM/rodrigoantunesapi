package br.edu.infnet.rodrigoantunesapi.model.domain.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;

import br.edu.infnet.rodrigoantunesapi.exceptions.PorteiroInvalidoException;
import br.edu.infnet.rodrigoantunesapi.exceptions.PorteiroNaoEncontradoException;
import br.edu.infnet.rodrigoantunesapi.interfaces.CrudService;
import br.edu.infnet.rodrigoantunesapi.model.domain.Porteiro;


@Service
public class PorteiroService  implements CrudService<Porteiro, Integer>{

	private final Map<Integer, Porteiro> mapa = new ConcurrentHashMap<Integer, Porteiro>();
	private final AtomicInteger nextId = new AtomicInteger(1);
	
	
	private void validarPorteiro(Porteiro porteiro) {
		//RN - Porteiro Null
		if(porteiro == null) {
			throw new IllegalArgumentException("O porteiro não pode ser nulo.");
		}
		
		//RN - Nome do porteiro obrigatório
		if(porteiro.getNome() == null || porteiro.getNome().trim().isEmpty()) {
			throw new PorteiroInvalidoException("O nome do porteiro é obrigatório.");
		}
		
		//RN - CPF obrigatório
		if(porteiro.getCpf() == null || porteiro.getCpf().trim().isEmpty()) {
			throw new PorteiroInvalidoException("O CPF do porteiro é obrigatório.");
		}
				
	}
	
	@Override
	public Porteiro buscarPorId(Integer id) {
		if(id == null || id <= 0) {
			throw new IllegalArgumentException("O ID utilizado deve ser  maior que zero");
		}
		
		Porteiro porteiro = mapa.get(id);
		
		if(porteiro == null) {
			throw new PorteiroNaoEncontradoException("O porteiro com o ID ["+id+"] não foi encontrado!");
		}
		
		return porteiro;
	}
	
	
	@Override
	public Porteiro salvar(Porteiro porteiro) {
		
		validarPorteiro(porteiro);
		
		porteiro.setId(nextId.getAndIncrement());

		mapa.put(porteiro.getId(), porteiro);
		
		return porteiro;
	}


	@Override
	public Porteiro atualizar(Integer id, Porteiro porteiro) {
		Porteiro porteiroEncontrado=buscarPorId(id);
		
		validarPorteiro(porteiro);
		
		mapa.put(porteiroEncontrado.getId(), porteiro);
		return buscarPorId(porteiroEncontrado.getId());
	}

	@Override
	public List<Porteiro> listarTodos() {
		return new ArrayList<Porteiro>(mapa.values());
	}

	@Override
	public void excluir(Integer id) {
		Porteiro porteiro = buscarPorId(id);
		mapa.remove(porteiro.getId());
		
	}

	public Porteiro inativar (Integer id) {
		Porteiro porteiro = buscarPorId(id);
		  
		porteiro.setAtivo(false); 
		mapa.put(id, porteiro);   

	    return porteiro;
	}
	
	public Porteiro buscarPorCpf(String cpf) {
	    return mapa.values().stream()
	            .filter(m -> cpf.equals(m.getCpf()))
	            .findFirst()
	            .orElse(null);    
	}
	
}
