package com.dt.virtualanm.persistence.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "anm")
@Schema(description = "ANM resource")
public class Anm {

	@PartitionKey
	@Schema(description = "The virtual ANM unique identifier", example = "ccf9e52b-e2e4-45d8-8884-0721d3246a53")
	private UUID anmId;

	@Column
	@Schema(description = "The name of the ANM resource", required = true, example = "ANM #1")
	private String name;

	@Column
	@Schema(description = "Additional information for ANM resource", example = "Additional info")
	private String anmInfo;

	@Column
	@Builder.Default
	@Schema(description = "PXO Valve open (true) or closed (false) status", defaultValue = "false")
	private boolean pxoValveIsOpen = false;

	@Column
	@Builder.Default
	@Schema(description = "XO Valve open (true) or closed (false) status", defaultValue = "false")
	private boolean xoValveIsOpen = false;

	@Column(name = "w1_valve_is_open")
	@Builder.Default
	@Schema(description = "W1 Valve open (true) or closed (false) status", defaultValue = "true")
	private boolean w1ValveIsOpen = true;

	@Column(name = "w2_valve_is_open")
	@Builder.Default
	@Schema(description = "W2 Valve open (true) or closed (false) status", defaultValue = "true")
	private boolean w2ValveIsOpen = true;

	@Column(name = "m1_valve_is_open")
	@Builder.Default
	@Schema(description = "M1 Valve open (true) or closed (false) status", defaultValue = "true")
	private boolean m1ValveIsOpen = true;

	@Column(name = "m2_valve_is_open")
	@Builder.Default
	@Schema(description = "M2 Valve open (true) or closed (false) status", defaultValue = "true")
	private boolean m2ValveIsOpen = true;

	@Column
	@Schema(description = "Resource creation date and time")
	private LocalDateTime creationDateTime;

}
