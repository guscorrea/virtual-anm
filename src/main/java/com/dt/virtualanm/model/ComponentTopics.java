package com.dt.virtualanm.model;

import org.springframework.beans.factory.annotation.Autowired;

import com.dt.virtualanm.model.enums.MeasurementType;
import lombok.Data;

@Data
public class ComponentTopics {

	private static final String ANM_TOPIC = "anm.";

	private static final String PRESSURE = "." + MeasurementType.pressure;

	private static final String TEMPERATURE = "." + MeasurementType.temperature;

	private static final String CUSTOM = "." + MeasurementType.custom;

	private final String componentId;

	private final String baseTopicName;

	private final String pressureTopicName;

	private final String temperatureTopicName;

	private final String customTopicName;

	@Autowired
	public ComponentTopics(String componentId) {
		this.componentId = componentId;
		this.baseTopicName = ANM_TOPIC + componentId;
		this.pressureTopicName = ANM_TOPIC + componentId + PRESSURE;
		this.temperatureTopicName = ANM_TOPIC + componentId + TEMPERATURE;
		this.customTopicName = ANM_TOPIC + componentId + CUSTOM;
	}

}
