package com.dt.virtualanm.controller;

import java.time.format.DateTimeParseException;
import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.dt.virtualanm.exception.AnmNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(AnmNotFoundException.class)
	public ResponseEntity<List<String>> handleNotFoundException(AnmNotFoundException e) {
		return new ResponseEntity<>(toListMessages(e.getMessage()), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(DateTimeParseException.class)
	public ResponseEntity<List<String>> handleDateTimeParseException(DateTimeParseException e) {
		return new ResponseEntity<>(toListMessages(e.getMessage()), HttpStatus.BAD_REQUEST);
	}

	private List<String> toListMessages(String message) {
		return Collections.singletonList(message);
	}

}
