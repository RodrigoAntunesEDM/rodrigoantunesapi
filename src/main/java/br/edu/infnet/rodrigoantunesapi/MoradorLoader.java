package br.edu.infnet.rodrigoantunesapi;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import br.edu.infnet.rodrigoantunesapi.model.domain.Contato;
//import br.edu.infnet.rodrigoantunesapi.model.domain.Contato.TipoContato;
import br.edu.infnet.rodrigoantunesapi.model.service.MoradorService;
import br.edu.infnet.rodrigoantunesapi.model.domain.Morador;
import br.edu.infnet.rodrigoantunesapi.model.domain.TipoContato;

@Component
@Order(1)

public class MoradorLoader implements ApplicationRunner{

	private final MoradorService moradorService;
	
	
	public MoradorLoader(MoradorService moradorService) {
		this.moradorService = moradorService;
	}



	@Override
	public void run(ApplicationArguments args) throws Exception {
		// Ler o arquivo
		
		System.out.println("Entrou no loader de Morador");
		
		FileReader arquivo = new FileReader("moradores.txt");
		BufferedReader leitura = new BufferedReader(arquivo);
		
		String linha = leitura.readLine();
		String[] campos = null;
		

		while(linha != null) {
			campos = linha.split(";");
			
			Morador morador = new Morador();
			List<Contato> listacontatos = new ArrayList<Contato>();
			Contato contato =new Contato();
			
			morador.setCpf(campos[0]);
			morador.setNome(campos[1]);
			morador.setApartamento(campos[2]);
			
			contato.setTipoContato(TipoContato.valueOf(campos[3]));
			contato.setContato(campos[4]);
			listacontatos.add(contato);
			
			morador.setContato(listacontatos);
			
			morador.setAtivo(Boolean.valueOf(campos[5]));
			
			moradorService.salvar(morador);
			
			linha = leitura.readLine();		
			
		}
		leitura.close();
		
	}

}
