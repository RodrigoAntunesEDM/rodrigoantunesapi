package br.edu.infnet.rodrigoantunesapi.model.domain.service;

import org.springframework.stereotype.Service;

import br.edu.infnet.rodrigoantunesapi.exceptions.ObjetoInvalidoException;
import br.edu.infnet.rodrigoantunesapi.exceptions.ObjetoNaoEncontradoException;
import br.edu.infnet.rodrigoantunesapi.interfaces.CrudService;
import br.edu.infnet.rodrigoantunesapi.model.domain.Objeto;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;


@Service
public class ObjetoService  implements CrudService<Objeto, Integer>{

	private final Map<Integer, Objeto> mapa = new ConcurrentHashMap<Integer, Objeto>();
	private final AtomicInteger nextId = new AtomicInteger(1);
	
	
	private void validarObjeto(Objeto objeto) {
		//RN - Objeto Null
		if(objeto  == null) {
			throw new IllegalArgumentException("O objeto da entrega não pode ser nulo.");
		}
		
		//RN - Código da entrega do fornecedor
		if(objeto.getCodigo() == null || objeto.getCodigo().trim().isEmpty()) {
			throw new ObjetoInvalidoException("O código identificador da entrega é obrigatório.");
		}
		
		//RN - Data de entrada
		if(objeto.getDataEntrada() == null) {
			throw new ObjetoInvalidoException("A data de entrada da entrega é obrigatória.");
		}
		
		//RN - Porteiro
		if(objeto.getPorteiro() == null) {
			throw new ObjetoInvalidoException("O porteiro que recebeu a entrega é obrigatório.");
		}
		
		//RN - Apartamento
		if(objeto.getApartamento() == null || objeto.getApartamento().trim().isEmpty()) {
			throw new ObjetoInvalidoException("O apartemento da entrega é obrigatório.");
		}
				
	}
	
	@Override
	public Objeto buscarPorId(Integer id) {
		if(id == null || id <= 0) {
			throw new IllegalArgumentException("O ID utilizado deve ser  maior que zero");
		}
		
		Objeto objeto = mapa.get(id);
		
		if(objeto == null) {
			throw new ObjetoNaoEncontradoException("O objeto com o ID ["+id+"] não foi encontrado!");
		}
		return objeto;
			
	}
	
	
	@Override
	public Objeto salvar(Objeto objeto) {
		
		validarObjeto(objeto);
		
		objeto.setId(nextId.getAndIncrement());

		mapa.put(objeto.getId(), objeto);
		
		return objeto;
	}

	@Override
	public Objeto atualizar(Integer id, Objeto objeto) {
		Objeto objetoAtualizado = buscarPorId(id);
		
		validarObjeto(objeto);
		
		mapa.put(objetoAtualizado.getId(), objeto);
		return buscarPorId(objetoAtualizado.getId());
	}
	
	@Override
	public List<Objeto> listarTodos() {
		return new ArrayList<Objeto>(mapa.values());
	}

	@Override
	public void excluir(Integer id) {
		Objeto objeto = buscarPorId(id);
		
		mapa.remove(objeto.getId());
	}



}
