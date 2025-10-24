package br.edu.infnet.rodrigoantunesapi.model.service;

import java.util.List;

public interface CrudService<T, ID> {

	T salvar(T entidade);
	T buscarPorId(ID id);
	T atualizar (ID id, T entidade);
	List<T> listarTodos();
	void excluir(ID id);
}