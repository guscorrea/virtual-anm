package com.dt.virtualanm.model;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class AnmRequest {

	@NotBlank
	public String name;

	public String anmInfo;

}
