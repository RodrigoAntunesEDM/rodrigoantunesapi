package br.edu.infnet.rodrigoantunesapi;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import br.edu.infnet.rodrigoantunesapi.model.domain.Objeto;
import br.edu.infnet.rodrigoantunesapi.model.domain.service.ObjetoService;

@Component
public class ObjetoLoader implements ApplicationRunner{

	//Criando estrutura do construtor
	
	private final ObjetoService objetoService;
	
	public ObjetoLoader(ObjetoService objetoService) {
		this.objetoService = objetoService;
	}


	@Override
	public void run(ApplicationArguments args) throws Exception {

		FileReader arquivo = new FileReader("registros.txt");
		BufferedReader leitura = new BufferedReader(arquivo);
		
		String linha = leitura.readLine();
		String[] campos = null;
		
		DateTimeFormatter formatador = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
;

		while(linha != null) {
		
			campos = linha.split(";");
			
			Objeto objeto = new Objeto();
			
			objeto.setCodigo(campos[0]);
			objeto.setDataEntrada(LocalDateTime.parse(campos[1], formatador));
			objeto.setPorteiro(campos[2]);
			objeto.setApartamento(campos[3]);
			
			if (campos[4] != null && !campos[4].isBlank()) {
				objeto.setDataRetirada(LocalDateTime.parse(campos[4], formatador));
			}
			
			objeto.setRetirante(campos[5]);
			objeto.setRetirado(Boolean.valueOf(campos[6]));
			objeto.setExcluido(Boolean.valueOf(campos[7]));
			
			objetoService.salvar(objeto);
			
			
			linha = leitura.readLine();
		}
		
		
		Collection<Objeto> objetos = objetoService.listarTodos();
		
		objetos.forEach(System.out::println);
		
		leitura.close();
		
	}

}

