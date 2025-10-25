package br.edu.infnet.rodrigoantunesapi.exceptions.handler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.edu.infnet.rodrigoantunesapi.exceptions.MoradorInvalidoException;
import br.edu.infnet.rodrigoantunesapi.exceptions.MoradorNaoEncontradoException;
import br.edu.infnet.rodrigoantunesapi.exceptions.ObjetoInvalidoException;
import br.edu.infnet.rodrigoantunesapi.exceptions.ObjetoNaoEncontradoException;
import br.edu.infnet.rodrigoantunesapi.exceptions.PorteiroInvalidoException;
import br.edu.infnet.rodrigoantunesapi.exceptions.PorteiroNaoEncontradoException;


@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
		
		Map<String, String> mapa = new HashMap<String, String>();
		
		e.getBindingResult().getAllErrors().forEach((erro) -> {
			String nomeCampo = ((FieldError) erro).getField();
			String mensagemErro = erro.getDefaultMessage();
			mapa.put(nomeCampo, mensagemErro);
		});

		return new ResponseEntity<Map<String,String>>(mapa, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(MoradorInvalidoException.class)
	public ResponseEntity<Map<String, String>> handleMoradorInvalidoException(MoradorInvalidoException e){
		Map<String, String> mapa = new HashMap<String, String>();

		mapa.put("timestamp", LocalDateTime.now().toString());
		mapa.put("status", HttpStatus.BAD_REQUEST.toString());
		mapa.put("error", e.getMessage());
		mapa.put("detail", "Dados do morador inválidos. Verifique!");
		
		return new ResponseEntity<Map<String,String>>(mapa, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MoradorNaoEncontradoException.class)
	public ResponseEntity<Map<String, String>> handleMoradorNaoEncontratoException(MoradorNaoEncontradoException e){
		Map<String, String> mapa = new HashMap<String, String>();

		mapa.put("timestamp", LocalDateTime.now().toString());
		mapa.put("status", HttpStatus.NOT_FOUND.toString());
		mapa.put("error", e.getMessage());
		mapa.put("detail", "Morador não encontrado!");
		
		return new ResponseEntity<Map<String,String>>(mapa, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(PorteiroInvalidoException.class)
	public ResponseEntity<Map<String, String>> handlePorteiroInvalidoException(PorteiroInvalidoException e){
		Map<String, String> mapa = new HashMap<String, String>();

		mapa.put("timestamp", LocalDateTime.now().toString());
		mapa.put("status", HttpStatus.BAD_REQUEST.toString());
		mapa.put("error", e.getMessage());
		mapa.put("detail", "Dados do porteiro inválidos. Verifique!");
		
		return new ResponseEntity<Map<String,String>>(mapa, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(PorteiroNaoEncontradoException.class)
	public ResponseEntity<Map<String, String>> handlePorteiroNaoEncontratoException(PorteiroNaoEncontradoException e){
		Map<String, String> mapa = new HashMap<String, String>();

		mapa.put("timestamp", LocalDateTime.now().toString());
		mapa.put("status", HttpStatus.NOT_FOUND.toString());
		mapa.put("error", e.getMessage());
		mapa.put("detail", "Porteiro não encontrado!");
		
		return new ResponseEntity<Map<String,String>>(mapa, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(ObjetoInvalidoException.class)
	public ResponseEntity<Map<String, String>> handleObjetoInvalidoException(ObjetoInvalidoException e){
		Map<String, String> mapa = new HashMap<String, String>();

		mapa.put("timestamp", LocalDateTime.now().toString());
		mapa.put("status", HttpStatus.BAD_REQUEST.toString());
		mapa.put("error", e.getMessage());
		mapa.put("detail", "Dados do objeto inválidos. Verifique!");
		
		return new ResponseEntity<Map<String,String>>(mapa, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ObjetoNaoEncontradoException.class)
	public ResponseEntity<Map<String, String>> handleObjetoNaoEncontratoException(ObjetoNaoEncontradoException e){
		Map<String, String> mapa = new HashMap<String, String>();

		mapa.put("timestamp", LocalDateTime.now().toString());
		mapa.put("status", HttpStatus.NOT_FOUND.toString());
		mapa.put("error", e.getMessage());
		mapa.put("detail", "Objeto não encontrado!");
		
		return new ResponseEntity<Map<String,String>>(mapa, HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<Map<String, String>> handleIllegalArgumentException(IllegalArgumentException e){
		Map<String, String> mapa = new HashMap<String, String>();

		mapa.put("timestamp", LocalDateTime.now().toString());
		mapa.put("status", HttpStatus.BAD_REQUEST.toString());
		mapa.put("error", e.getMessage());
		mapa.put("detail", "Algum argumento inválido foi fornecido para essa operação!");
		
		return new ResponseEntity<Map<String,String>>(mapa, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<Map<String, String>> handleDataIntegrityViolationException(DataIntegrityViolationException e){
		Map<String, String> mapa = new HashMap<String, String>();

		mapa.put("timestamp", LocalDateTime.now().toString());
		mapa.put("status", HttpStatus.CONFLICT.toString());
		mapa.put("error", e.getMessage());
		mapa.put("detail", "Recurso já existente");
		
		return new ResponseEntity<Map<String,String>>(mapa, HttpStatus.CONFLICT);
	}

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<Map<String, String>> handleRuntimeException(RuntimeException e){
		Map<String, String> mapa = new HashMap<String, String>();

		mapa.put("timestamp", LocalDateTime.now().toString());
		mapa.put("status", HttpStatus.INTERNAL_SERVER_ERROR.toString());
		mapa.put("error", e.getMessage());
		mapa.put("detail", "Ocorreu um erro interno no servidor!");
		
		return new ResponseEntity<Map<String,String>>(mapa, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
