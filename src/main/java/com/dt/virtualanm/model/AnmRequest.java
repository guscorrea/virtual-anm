package com.dt.virtualanm.model;

import javax.validation.constraints.NotBlank;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "ANM request")
public class AnmRequest {

	@NotBlank
	@Schema(description = "The name of the ANM resource", required = true, example = "ANM #1")
	private String name;

	@Schema(description = "Additional information for ANM resource", example = "Additional info")
	private String anmInfo;

	@Schema(description = "PXO Valve open (true) or closed (false) status", defaultValue = "false")
	private Boolean pxoValveIsOpen;

	@Schema(description = "XO Valve open (true) or closed (false) status", defaultValue = "false")
	private Boolean xoValveIsOpen;

	@Schema(description = "W1 Valve open (true) or closed (false) status", defaultValue = "true")
	private Boolean w1ValveIsOpen;

	@Schema(description = "W2 Valve open (true) or closed (false) status", defaultValue = "true")
	private Boolean w2ValveIsOpen;

	@Schema(description = "M1 Valve open (true) or closed (false) status", defaultValue = "true")
	private Boolean m1ValveIsOpen;

	@Schema(description = "M2 Valve open (true) or closed (false) status", defaultValue = "true")
	private Boolean m2ValveIsOpen;

}
