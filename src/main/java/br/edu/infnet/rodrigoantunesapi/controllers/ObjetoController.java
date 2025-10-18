package br.edu.infnet.rodrigoantunesapi.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.infnet.rodrigoantunesapi.model.domain.Objeto;
import br.edu.infnet.rodrigoantunesapi.model.domain.service.ObjetoService;


@RestController
@RequestMapping("/api/objetos")


public class ObjetoController {

	private final ObjetoService objetoService;
	
	public ObjetoController(ObjetoService objetoService) {
		this.objetoService = objetoService;
	}
	
	
	@GetMapping
	public List<Objeto> listarTodos() {
		return objetoService.listarTodos();
	}
	
	@PostMapping
	public Objeto salvar (@RequestBody Objeto objeto) {	
		return objetoService.salvar(objeto);
	}
	
    @GetMapping("/{id}")
    public Objeto buscarPorId(@PathVariable Integer id) {
        return objetoService.buscarPorId(id);
    }
    
	
    @PutMapping("/{id}")
    public Objeto atualizar(@PathVariable Integer id, @RequestBody Objeto objeto) {
    	return 	objetoService.atualizar(id, objeto);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Integer id) {
        objetoService.excluir(id);
    }
    
}
