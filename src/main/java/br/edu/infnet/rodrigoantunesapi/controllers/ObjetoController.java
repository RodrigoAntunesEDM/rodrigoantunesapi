package br.edu.infnet.rodrigoantunesapi.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.infnet.rodrigoantunesapi.model.domain.Objeto;
import br.edu.infnet.rodrigoantunesapi.model.service.ObjetoService;


@RestController
@RequestMapping("/api/objetos")


public class ObjetoController {

	private final ObjetoService objetoService;
	
	public ObjetoController(ObjetoService objetoService) {
		this.objetoService = objetoService;
	}
	
	

	@PostMapping
	public ResponseEntity< Objeto> salvar (@RequestBody Objeto objeto) {	
		return ResponseEntity.status(HttpStatus.CREATED).body( objetoService.salvar(objeto));
	}
	
    @PutMapping("/{id}")
    public ResponseEntity< Objeto> atualizar(@PathVariable Integer id, @RequestBody Objeto objeto) {
    	return 	ResponseEntity.ok( objetoService.atualizar(id, objeto));
    }

    @GetMapping("/{id}")
    public ResponseEntity< Objeto> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok( objetoService.buscarPorId(id));
    }
    
	@GetMapping
	public ResponseEntity<List<Objeto>> listarTodos() {
		List<Objeto> lista = objetoService.listarTodos();
		
		if (lista.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.ok(lista);
	}
	
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {
        objetoService.excluir(id);
        
        return ResponseEntity.noContent().build();
    }
    
}
