package com.dt.virtualanm.controller;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "ANM")
public class AnmController {

	private final AnmService anmService;

	@Autowired
	public AnmController(AnmService anmService) {
		this.anmService = anmService;
	}

	@GetMapping("/v1/anm")
	@Operation(summary = "Retrieves all ANMs.", description = "Retrieves all ANM resources in a list.", responses = {
			@ApiResponse(responseCode = "200", description = "The list of ANMs was retrieved.", content = {
					@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
							array = @ArraySchema(schema = @Schema(implementation = Anm.class))) }) })
	public ResponseEntity<List<Anm>> listAnm() {
		List<Anm> anmList = anmService.getAllAnms();
		return new ResponseEntity<>(anmList, HttpStatus.OK);
	}

	@GetMapping("/v1/anm/{id}")
	@Operation(summary = "Retrieves a ANM.", description = "Retrieves a ANM resource with a given UUID.", responses = {
			@ApiResponse(responseCode = "200", description = "The ANM was retrieved.",
					content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Anm.class)) }),
			@ApiResponse(responseCode = "400", description = "The request failed validation.", content = {
					@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(type = "string", example = "Invalid UUID string")) }),
			@ApiResponse(responseCode = "404", description = "The ANM was not found in the DB.", content = {
					@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(type = "string",
							example = "Anm with id c5f2f64c-b02d-4635-8a34-c3d4cc2d955b not found in the database.")) }) })
	public ResponseEntity<Anm> getAnm(@PathVariable("id") UUID id) {
		Anm anm = anmService.getAnm(id);
		return new ResponseEntity<>(anm, HttpStatus.OK);
	}

	@PostMapping("/v1/anm")
	@Operation(summary = "Creates a ANM resource",
			description = "Sends a post request, validates input data, and saves the generated resource into the Scylla database.", responses = {
			@ApiResponse(responseCode = "200", description = "ANM resource was created",
					content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Anm.class)) }),
			@ApiResponse(responseCode = "400", description = "The request failed validation.", content = {
					@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
							schema = @Schema(type = "string", example = "Field name: must not be null")) }),
			@ApiResponse(responseCode = "500", description = "Unexpected error occurred",
					content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE) }) })
	public ResponseEntity<Anm> createAnm(@RequestBody @Valid AnmRequest anmRequest) {
		Anm anm = anmService.saveAnm(anmRequest);
		return new ResponseEntity<>(anm, HttpStatus.OK);
	}

	@PutMapping("/v1/anm/{id}")
	@Operation(summary = "Updates a ANM resource",
			description = "Sends a put request, validates input data, checks if the current resource exists, and saves the updated resource into the "
					+ "Scylla database.", responses = {
			@ApiResponse(responseCode = "200", description = "ANM resource was updated",
					content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Anm.class)) }),
			@ApiResponse(responseCode = "400", description = "The request failed validation.", content = {
					@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
							schema = @Schema(type = "string", example = "Field name: must not be null")) }),
			@ApiResponse(responseCode = "404", description = "The ANM was not found in the DB.", content = {
					@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(type = "string",
							example = "Anm with id c5f2f64c-b02d-4635-8a34-c3d4cc2d955b not found in the database.")) }),
			@ApiResponse(responseCode = "500", description = "Unexpected error occurred",
					content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE) }) })
	public ResponseEntity<Anm> updateAnm(@PathVariable("id") UUID id, @RequestBody @Valid AnmRequest anmRequest) {
		Anm updatedAnm = anmService.updateAnm(id, anmRequest);
		return new ResponseEntity<>(updatedAnm, HttpStatus.OK);
	}

	@DeleteMapping("/v1/anm/{id}")
	@Operation(summary = "Deletes a ANM resource", description = "Deletes a ANM resource with given UUID.",
			responses = { @ApiResponse(responseCode = "204", description = "The ANM was deleted.") })
	public ResponseEntity<Void> createAnm(@PathVariable("id") UUID id) {
		anmService.deleteAnm(id);
		return ResponseEntity.noContent().build();
	}

}
