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
    
    //Buscar objeto por código
    @GetMapping("/codigo/{codigo}")
    public ResponseEntity<Objeto> buscarPorCodigo(@PathVariable String codigo) {
    	return ResponseEntity.ok( objetoService.buscarPorCodigo(codigo));
    }

    //Buscar objetos de um determinado apartamento
    @GetMapping("/apartamento/{apartamento}")
    public ResponseEntity<List<Objeto>> buscarPorApartamento(@PathVariable String apartamento) {
        List<Objeto> objetos = objetoService.buscarPorApartamento(apartamento);
        return objetos.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(objetos);
    }

    //Buscar objetos não entregues (pendentes) de um apartamento
    @GetMapping("/apartamento/{apartamento}/pendentes")
    public ResponseEntity<List<Objeto>> buscarNaoEntreguesPorApartamento(@PathVariable String apartamento) {
        List<Objeto> objetos = objetoService.buscarNaoEntreguesPorApartamento(apartamento);
        return objetos.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(objetos);
    }

    //Buscar objetos entregues de um apartamento (ordenados por dataRetirada DESC)
    @GetMapping("/apartamento/{apartamento}/entregues")
    public ResponseEntity<List<Objeto>> buscarEntreguesPorApartamento(@PathVariable String apartamento) {
        List<Objeto> objetos = objetoService.buscarEntreguesPorApartamento(apartamento);
        return objetos.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(objetos);
    }
    
}
