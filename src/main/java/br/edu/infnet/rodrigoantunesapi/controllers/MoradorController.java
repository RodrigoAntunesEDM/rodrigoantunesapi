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

import br.edu.infnet.rodrigoantunesapi.model.domain.Morador;
import br.edu.infnet.rodrigoantunesapi.model.service.MoradorService;

@RestController
@RequestMapping("/api/moradores")

public class MoradorController {

	private final MoradorService moradorService;

	public MoradorController(MoradorService moradorService) {
		this.moradorService = moradorService;
	}
	
	@PostMapping
	public ResponseEntity< Morador> salvar(@RequestBody Morador morador) {
		return ResponseEntity.status(HttpStatus.CREATED).body( moradorService.salvar(morador));
	}

    @PutMapping("/{id}")
    public ResponseEntity < Morador> atualizar(@PathVariable Integer id, @RequestBody Morador morador) {
        return ResponseEntity.ok( moradorService.atualizar(id, morador));
    }
    
    @GetMapping
	public ResponseEntity< List<Morador> > listarTodos() {
		
    	List<Morador> lista = moradorService.listarTodos();
    	
		if (lista.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
    	
    	return ResponseEntity.ok(lista) ;
	}
	

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {
        moradorService.excluir(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity <Morador> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok( moradorService.buscarPorId(id));
    }
    
    @PatchMapping("/{id}/inativar")
    public ResponseEntity< Morador>  inativar(@PathVariable Integer id) {
        return ResponseEntity.ok( moradorService.inativar(id));
    }
}
