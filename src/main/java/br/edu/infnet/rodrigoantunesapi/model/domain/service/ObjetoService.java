package br.edu.infnet.rodrigoantunesapi.model.domain.service;

import org.springframework.stereotype.Service;

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
	
	@Override
	public Objeto salvar(Objeto objeto) {
		
		objeto.setId(nextId.getAndIncrement());

		mapa.put(objeto.getId(), objeto);
		
		return objeto;
	}

	@Override
	public Objeto buscarPorId(Integer id) {
		
		return mapa.get(id);
		
	}

	@Override
	public List<Objeto> listarTodos() {
		return new ArrayList<Objeto>(mapa.values());
	}

	@Override
	public void excluir(Integer id) {
		mapa.remove(id);
	}

	@Override
	public Objeto atualizar(Integer id, Objeto objeto) {
		
		mapa.put(id, objeto);
		
		return mapa.get(id);
	}

}
