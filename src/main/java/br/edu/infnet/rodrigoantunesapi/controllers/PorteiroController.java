package br.edu.infnet.rodrigoantunesapi.controllers;

import java.util.List;

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
import br.edu.infnet.rodrigoantunesapi.model.domain.service.PorteiroService;

@RestController
@RequestMapping("/api/porteiros")

public class PorteiroController {

	private final PorteiroService porteiroService;

	public PorteiroController(PorteiroService porteiroService) {
		this.porteiroService = porteiroService;
	}
	
	@PostMapping
	public Porteiro salvar(@RequestBody Porteiro porteiro) {
		return porteiroService.salvar(porteiro);
	}

	@GetMapping
	public List<Porteiro> listarTodos() {
		return porteiroService.listarTodos();
	}
	
    @GetMapping("/{id}")
    public Porteiro buscarPorId(@PathVariable Integer id) {
        return porteiroService.buscarPorId(id);
    }
	
    @PutMapping("/{id}")
    public Porteiro atualizar(@PathVariable Integer id, @RequestBody Porteiro porteiro) {
        return porteiroService.atualizar(id, porteiro);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Integer id) {
        porteiroService.excluir(id);
    }
	
    @PatchMapping("/{id}/inativar")
    public Porteiro inativar(@PathVariable Integer id) {
        return porteiroService.inativar(id);
    }
	
}
