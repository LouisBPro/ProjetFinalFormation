package fr.projetFormation.Hashi.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, value = HttpStatus.BAD_REQUEST, reason = "donnees incorrectes")
public class ClientException extends RuntimeException{
	public ClientException() {
	}

	public ClientException(String message) {
		super(message);
	}


}
