package br.edu.infnet.rodrigoantunesapi.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.infnet.rodrigoantunesapi.model.domain.Porteiro;
import br.edu.infnet.rodrigoantunesapi.model.service.PorteiroService;

@RestController
@RequestMapping("/api/porteiros")

public class PorteiroController {

	private final PorteiroService porteiroService;

	public  PorteiroController(PorteiroService porteiroService) {
		this.porteiroService = porteiroService;
	}
	
	@PostMapping
	public ResponseEntity< Porteiro> salvar(@RequestBody Porteiro porteiro) {
		return ResponseEntity.status(HttpStatus.CREATED).body( porteiroService.salvar(porteiro));
	}
	
    @PutMapping("/{id}")
    public ResponseEntity< Porteiro> atualizar(@PathVariable Integer id, @RequestBody Porteiro porteiro) {
        return ResponseEntity.ok(porteiroService.atualizar(id, porteiro));
    }

	@GetMapping
	public ResponseEntity< List<Porteiro>> listarTodos() {
		List<Porteiro> lista = porteiroService.listarTodos();
		
		if (lista.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.ok(lista);
	}
	
    @GetMapping("/{id}")
    public ResponseEntity< Porteiro> buscarPorId(@PathVariable Integer id) {    	
    	return ResponseEntity.ok(porteiroService.buscarPorId(id));
	}
    

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {
        porteiroService.excluir(id);
        return ResponseEntity.noContent().build();
    }
	
    @PatchMapping("/{id}/inativar")
    public ResponseEntity< Porteiro> inativar(@PathVariable Integer id) {
        return ResponseEntity.ok(porteiroService.inativar(id));
    }
	
}
