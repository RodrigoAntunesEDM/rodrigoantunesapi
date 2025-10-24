package br.edu.infnet.rodrigoantunesapi;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import br.edu.infnet.rodrigoantunesapi.model.domain.Endereco;
import br.edu.infnet.rodrigoantunesapi.model.domain.Porteiro;
import br.edu.infnet.rodrigoantunesapi.model.service.PorteiroService;


@Component
@Order(2)
public class PorteiroLoader implements ApplicationRunner{

	private final PorteiroService porteiroService;
		
	
	public PorteiroLoader(PorteiroService porteiroService) {
		this.porteiroService = porteiroService;
	}


	@Override
	public void run(ApplicationArguments args) throws Exception {
		// Ler o arquivo
		
		System.out.println("Entrou no loader de Porteiro");
		
		FileReader arquivo = new FileReader("porteiros.txt");
		BufferedReader leitura = new BufferedReader(arquivo);
		
		String linha = leitura.readLine();
		String[] campos = null;

		
		DateTimeFormatter formatador = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		while(linha != null) {
			campos = linha.split(";");
			
			Porteiro porteiro = new Porteiro();
			Endereco endereco = new Endereco();
			
			porteiro.setCpf(campos[0]);
			porteiro.setNome(campos[1]);
			porteiro.setMatricula(campos[2]);
			porteiro.setDataAdmissao(LocalDate.parse(campos[3], formatador));;
			porteiro.setAtivo(Boolean.valueOf(campos[4]));
			
			endereco.setCep(campos[5]);
			porteiro.setEndereco(endereco);			

			
			porteiroService.salvar(porteiro);
			
			linha = leitura.readLine();		
			
		}
		leitura.close();
	}
		
}
