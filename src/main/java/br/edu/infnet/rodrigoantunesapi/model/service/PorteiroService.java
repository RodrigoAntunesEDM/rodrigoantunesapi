package br.edu.infnet.rodrigoantunesapi.model.service;

import java.util.List;
import org.springframework.stereotype.Service;

import br.edu.infnet.rodrigoantunesapi.clients.ViaCepFeignClient;
import br.edu.infnet.rodrigoantunesapi.exceptions.PorteiroInvalidoException;
import br.edu.infnet.rodrigoantunesapi.exceptions.PorteiroNaoEncontradoException;
import br.edu.infnet.rodrigoantunesapi.model.domain.Endereco;
import br.edu.infnet.rodrigoantunesapi.model.domain.Porteiro;
import br.edu.infnet.rodrigoantunesapi.model.repository.PorteiroRepository;


@Service
public class PorteiroService  implements CrudService<Porteiro, Integer>{

	//private final Map<Integer, Porteiro> mapa = new ConcurrentHashMap<Integer, Porteiro>();
	//private final AtomicInteger nextId = new AtomicInteger(1);
	
	private final PorteiroRepository porteiroRepository;
	private final ViaCepFeignClient cepFeignClient;
	private final EnderecoService enderecoService;

		
	public PorteiroService(PorteiroRepository porteiroRepository, ViaCepFeignClient cepFeignClient,
			EnderecoService enderecoService) {
		this.porteiroRepository = porteiroRepository;
		this.cepFeignClient = cepFeignClient;
		this.enderecoService = enderecoService;
	}


	private void validarPorteiro(Porteiro porteiro) {
		//RN - Porteiro Null
		if(porteiro == null) {
			throw new IllegalArgumentException("O porteiro não pode ser nulo.");
		}
		
		//RN - Nome do porteiro obrigatório
		if(porteiro.getNome() == null || porteiro.getNome().trim().isEmpty()) {
			throw new PorteiroInvalidoException("O nome do porteiro é obrigatório.");
		}
		
		//RN - CPF obrigatório
		if(porteiro.getCpf() == null || porteiro.getCpf().trim().isEmpty()) {
			throw new PorteiroInvalidoException("O CPF do porteiro é obrigatório.");
		}
				
	}
	

	@Override
	public Porteiro buscarPorId(Integer id) {
		if(id == null || id <= 0) {
			throw new IllegalArgumentException("O ID utilizado deve ser  maior que zero");
		}
		
		return porteiroRepository.findById(id).orElseThrow(() -> new PorteiroNaoEncontradoException("O porteiro com o ID [" + id + "] não foi encontrado!"));
	}
	
	
	@Override
	public Porteiro salvar(Porteiro porteiro) {
		
		Endereco endereco;
		
		validarPorteiro(porteiro);
		
		//Busca o endereco
		endereco=porteiro.getEndereco();
		
		if(endereco!=null) {
			
			if (endereco.getCep()!=null) {
				if (endereco.getLogradouro()==null ||  endereco.getLogradouro().trim().isEmpty())
				{
					Endereco enderecoViaCep;
					enderecoViaCep= cepFeignClient.findByCep(endereco.getCep());	
					
					if (enderecoViaCep!= null && enderecoViaCep.getLogradouro()!=null) {
						//Verifica de tem no banco
						Endereco enderecoRepository;
						enderecoRepository=enderecoService.obterOuCriar(enderecoViaCep);
							
				        porteiro.setEndereco(enderecoRepository);
					} else
					{
						porteiro.setEndereco(null);
					}
				}
					
			}
		}
		
		porteiroRepository.save(porteiro);
		
		//Temporario ate fazer a busca por CPF
		//porteiro.setId(nextId.getAndIncrement());
		//mapa.put(porteiro.getId(), porteiro);
		
		
		return porteiro;
	}


	@Override
	public Porteiro atualizar(Integer id, Porteiro porteiro) {
		buscarPorId(id);

		validarPorteiro(porteiro);
		
		porteiro.setId(id);
		
		//Busca o endereco
		Endereco endereco;
		endereco=porteiro.getEndereco();
		
		if(endereco!=null) {
			if (endereco.getCep()!=null) {
				if (endereco.getLogradouro()==null ||  endereco.getLogradouro().trim().isEmpty())
				{
					Endereco enderecoViaCep;
					enderecoViaCep= cepFeignClient.findByCep(endereco.getCep());	
					
					if (enderecoViaCep!= null && enderecoViaCep.getLogradouro()!=null ) {
						
						//Verifica de tem no banco
						Endereco enderecoRepository;
						enderecoRepository=enderecoService.obterOuCriar(enderecoViaCep);
						
						porteiro.setEndereco(enderecoRepository);
					} else {
						porteiro.setEndereco(null);
					}
				}
			}
		}
		
		//Remover no futuro 
		//mapa.put(porteiroEncontrado.getId(), porteiro);
		//return buscarPorId(porteiroEncontrado.getId());
		
		return porteiroRepository.save(porteiro);
		
	}

	@Override
	public List<Porteiro> listarTodos() {
		//return new ArrayList<Porteiro>(mapa.values());
		return porteiroRepository.findAll();
	}

	@Override
	public void excluir(Integer id) {
		Porteiro porteiro = buscarPorId(id);
		//mapa.remove(porteiro.getId());
		
		porteiroRepository.delete(porteiro);
		
	}

	public Porteiro inativar (Integer id) {
		Porteiro porteiro = buscarPorId(id);
		  
		porteiro.setAtivo(false); 
		
		//mapa.put(id, porteiro);   
	    //return porteiro;
		
		return porteiroRepository.save(porteiro);
	}

	
	public Porteiro buscarPorCpf(String cpf) {
	    if (cpf == null || cpf.trim().isEmpty()) {
	        throw new IllegalArgumentException("O CPF não pode ser nulo ou vazio.");
	    }

	    return porteiroRepository.findByCpf(cpf)
	            .orElseThrow(() -> new PorteiroNaoEncontradoException("Porteiro com CPF " + cpf + " não encontrado."));
	}
}
