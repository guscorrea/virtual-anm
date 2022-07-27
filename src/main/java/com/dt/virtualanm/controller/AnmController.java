package com.dt.virtualanm.controller;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dt.virtualanm.model.AnmRequest;
import com.dt.virtualanm.persistence.entity.Anm;
import com.dt.virtualanm.service.AnmService;

@RestController
public class AnmController {

	private final AnmService anmService;

	@Autowired
	public AnmController(AnmService anmService) {
		this.anmService = anmService;
	}

	@GetMapping("/v1/anm")
	public ResponseEntity<List<Anm>> listAnm() {
		List<Anm> anmList = anmService.getAllAnms();
		return new ResponseEntity<>(anmList, HttpStatus.OK);
	}

	@GetMapping("/v1/anm/{id}")
	public ResponseEntity<Anm> getAnm(@PathVariable("id") UUID id) {
		Anm anm = anmService.getAnm(id);
		return new ResponseEntity<>(anm, HttpStatus.OK);
	}

	@PostMapping("/v1/anm")
	public ResponseEntity<Anm> createAnm(@RequestBody @Valid AnmRequest anmRequest) {
		Anm anm = anmService.saveAnm(anmRequest);
		return new ResponseEntity<>(anm, HttpStatus.OK);
	}

	@PutMapping("/v1/anm/{id}")
	public ResponseEntity<Anm> updateAnm(@PathVariable("id") UUID id, @RequestBody @Valid AnmRequest anmRequest) {
		Anm updatedAnm = anmService.updateAnm(id, anmRequest);
		return new ResponseEntity<>(updatedAnm, HttpStatus.OK);
	}

	@DeleteMapping("/v1/anm/{id}")
	public ResponseEntity<Void> createAnm(@PathVariable("id") UUID id) {
		anmService.deleteAnm(id);
		return ResponseEntity.noContent().build();
	}

}
