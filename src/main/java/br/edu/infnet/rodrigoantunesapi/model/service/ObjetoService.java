package br.edu.infnet.rodrigoantunesapi.model.service;

import org.springframework.stereotype.Service;

import br.edu.infnet.rodrigoantunesapi.exceptions.ObjetoInvalidoException;
import br.edu.infnet.rodrigoantunesapi.exceptions.ObjetoNaoEncontradoException;
import br.edu.infnet.rodrigoantunesapi.model.domain.Morador;
import br.edu.infnet.rodrigoantunesapi.model.domain.Objeto;
import br.edu.infnet.rodrigoantunesapi.model.domain.Porteiro;
import br.edu.infnet.rodrigoantunesapi.model.repository.ObjetoRepository;

import java.util.List;


@Service
public class ObjetoService  implements CrudService<Objeto, Integer>{

	private final PorteiroService porteiroService;
	private final MoradorService moradorService ;
	private final ObjetoRepository objetoRepository;
	
	Porteiro porteiro;
	Morador morador;
	
	public ObjetoService(PorteiroService porteiroService, MoradorService moradorService, ObjetoRepository objetoRepository) {
		this.porteiroService = porteiroService;
		this.moradorService = moradorService;
		this.objetoRepository= objetoRepository;
	}

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
		
		return objetoRepository.findById(id).orElseThrow(() -> new ObjetoNaoEncontradoException("O objeto com o ID ["+id+"] não foi encontrado!"));
	}
	
	
	@Override
	public Objeto salvar(Objeto objeto) {
		
		validarObjeto(objeto);
		
		
		porteiro=null;
		porteiro=objeto.getPorteiro();
		//Se nao tem nome é uma inclusao via Postman recebe apenas o CPF		
		if (porteiro.getNome()==null ||  porteiro.getNome().trim().isEmpty()) {
			if (porteiro.getCpf()!=null) {
				objeto.setPorteiro(porteiroService.buscarPorCpf(porteiro.getCpf()));	
			}
					
		}
	
		morador=null;
		morador=objeto.getMorador();
	
		//Se nao tem nome é uma inclusao via Postman recebe apenas o CPF
		if (morador!=null) {
			if (morador.getNome()==null ||  morador.getNome().trim().isEmpty()) {
				if (morador.getCpf()!=null) {
					objeto.setMorador(moradorService.buscarPorCpf(morador.getCpf()));	
				}
			}
		}
		
		return objetoRepository.save(objeto);
	}

	@Override
	public Objeto atualizar(Integer id, Objeto objeto) {

		buscarPorId(id);
		
		validarObjeto(objeto);
		
		objeto.setId(id);
		
		porteiro=null;
		porteiro=objeto.getPorteiro();
		//Se nao tem nome é uma inclusao via Postman recebe apenas o CPF		
		if (porteiro.getNome()==null ||  porteiro.getNome().trim().isEmpty()) {
			objeto.setPorteiro(porteiroService.buscarPorCpf(porteiro.getCpf()));			
		}
		
		morador=null;
		morador=objeto.getMorador();
		//Se nao tem nome é uma inclusao via Postman recebe apenas o CPF
		if (morador!=null) {
			if (morador.getNome()==null ||  morador.getNome().trim().isEmpty()) {
				objeto.setMorador(moradorService.buscarPorCpf(morador.getCpf()));			
			}
		}

		return objetoRepository.save(objeto);
		
	}
	
	@Override
	public List<Objeto> listarTodos() {
		return objetoRepository.findAll();
	}

	@Override
	public void excluir(Integer id) {
		Objeto objeto = buscarPorId(id);
				
		objetoRepository.delete(objeto);
		
	}
	
	// Buscar objeto por código
    public Objeto buscarPorCodigo(String codigo) {
        return objetoRepository.findByCodigo(codigo).orElseThrow(() -> new ObjetoNaoEncontradoException("O objeto com o ID ["+codigo+"] não foi encontrado!"));
    }

    // Buscar objetos de um determinado apartamento
    public List<Objeto> buscarPorApartamento(String apartamento) {
        return objetoRepository.findByApartamento(apartamento);
    }

    // Buscar objetos não entregues de um apartamento
    public List<Objeto> buscarNaoEntreguesPorApartamento(String apartamento) {
        return objetoRepository.findByApartamentoAndRetiradoFalse(apartamento);
    }

    //Buscar objetos entregues de um apartamento (ordenados por data de retirada DESC)
    public List<Objeto> buscarEntreguesPorApartamento(String apartamento) {
        return objetoRepository.findByApartamentoAndRetiradoTrueOrderByDataRetiradaDesc(apartamento);
    }

}