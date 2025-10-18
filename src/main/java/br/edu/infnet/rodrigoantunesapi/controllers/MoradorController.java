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

import br.edu.infnet.rodrigoantunesapi.model.domain.Morador;
import br.edu.infnet.rodrigoantunesapi.model.domain.service.MoradorService;

@RestController
@RequestMapping("/api/moradores")

public class MoradorController {

	private final MoradorService moradorService;

	public MoradorController(MoradorService moradorService) {
		this.moradorService = moradorService;
	}
	
	@PostMapping
	public Morador salvar(@RequestBody Morador morador) {
		return moradorService.salvar(morador);
	}

	@GetMapping
	public List<Morador> listarTodos() {
		return moradorService.listarTodos();
	}
	
    @GetMapping("/{id}")
    public Morador buscarPorId(@PathVariable Integer id) {
        return moradorService.buscarPorId(id);
    }
	
    @PutMapping("/{id}")
    public Morador atualizar(@PathVariable Integer id, @RequestBody Morador morador) {
        return moradorService.atualizar(id, morador);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Integer id) {
        moradorService.excluir(id);
    }
    
    @PatchMapping("/{id}/inativar")
    public Morador  inativar(@PathVariable Integer id) {
        return moradorService.inativar(id);
    }
}
