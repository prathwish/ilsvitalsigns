package com.example.ilsvitalsign_be.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.ilsvitalsign_be.util.ErrorResponse;

@ControllerAdvice
public class ilsvitalsignExceptionHandler {
	static Logger logger = LoggerFactory.getLogger(ilsvitalsignExceptionHandler.class);

	/**
	 * @name : generalException
	 *
	 * @param e
	 *
	 * @returns : ResponseEntity<ApplErrorCollection>
	 *
	 * @description : This method is used to handle generic Exceptions.
	 *
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> generalException(Exception ex) {
		ErrorResponse error = new ErrorResponse(false, ex.getLocalizedMessage());
		return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
