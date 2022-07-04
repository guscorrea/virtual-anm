package com.dt.virtualanm.persistence.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
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
	private LocalDateTime creationDateTime;

}
