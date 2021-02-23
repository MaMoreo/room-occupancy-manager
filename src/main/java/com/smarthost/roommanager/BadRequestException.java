package com.smarthost.roommanager;

import javax.money.UnknownCurrencyException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends UnknownCurrencyException {

	public BadRequestException(String message) {
		super(message);
	}

}
