package com.dt.virtualanm.persistence.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "anm")
public class Anm {

	@PartitionKey
	private UUID anmId;

	@Column
	private String name;

	@Column
	private String anmInfo;

	@Column
	@Builder.Default
	private boolean pxoValveIsOpen = false;

	@Column
	@Builder.Default
	private boolean xoValveIsOpen = false;

	@Column(name = "w1_valve_is_open")
	@Builder.Default
	private boolean w1ValveIsOpen = true;

	@Column(name = "w2_valve_is_open")
	@Builder.Default
	private boolean w2ValveIsOpen = true;

	@Column(name = "m1_valve_is_open")
	@Builder.Default
	private boolean m1ValveIsOpen = true;

	@Column(name = "m2_valve_is_open")
	@Builder.Default
	private boolean m2ValveIsOpen = true;

	@Column
	private LocalDateTime creationDateTime;

}
