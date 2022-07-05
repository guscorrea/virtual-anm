package com.dt.virtualanm.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dt.virtualanm.config.MqttConfig;
import com.dt.virtualanm.exception.AnmNotFoundException;
import com.dt.virtualanm.model.AnmRequest;
import com.dt.virtualanm.model.ComponentTopics;
import com.dt.virtualanm.persistence.AnmRepository;
import com.dt.virtualanm.persistence.entity.Anm;

@Service
public class AnmService {

	@Resource
	private MqttConfig mqttConfig;

	private final AnmRepository anmRepository;

	@Autowired
	public AnmService(AnmRepository anmRepository) {
		this.anmRepository = anmRepository;
	}

	public List<Anm> getAllAnms() {
		return anmRepository.findAll();
	}

	public Anm getAnm(UUID id) {
		Anm anm = anmRepository.find(id);
		if (Objects.isNull(anm)) {
			throw new AnmNotFoundException("Anm with id " + id.toString() + " not found in the database.");
		}
		return anm;
	}

	public Anm saveAnm(AnmRequest anmRequest) {
		System.out.println("Creating ANM with name " + anmRequest.getName());
		Anm anm = Anm.builder()
				.anmId(UUID.randomUUID())
				.name(anmRequest.getName())
				.anmInfo(anmRequest.getAnmInfo())
				.creationDateTime(LocalDateTime.now())
				.build();
		addTopics(anm);
		return anmRepository.save(anm);
	}

	public Anm updateAnm(UUID id, AnmRequest anmRequest) {
		Anm anm = getAnm(id);
		System.out.println("Updating ANM with id " + id);
		anm.setName(anmRequest.getName());
		anm.setAnmInfo(StringUtils.defaultIfEmpty(anmRequest.getAnmInfo(), anm.getAnmInfo()));
		anm.setPxoValveIsOpen(Objects.isNull(anmRequest.getPxoValveIsOpen()) ? anm.isPxoValveIsOpen() : anmRequest.getPxoValveIsOpen());
		anm.setXoValveIsOpen(Objects.isNull(anmRequest.getXoValveIsOpen()) ? anm.isXoValveIsOpen() : anmRequest.getXoValveIsOpen());
		anm.setM1ValveIsOpen(Objects.isNull(anmRequest.getM1ValveIsOpen()) ? anm.isM1ValveIsOpen() : anmRequest.getM1ValveIsOpen());
		anm.setM2ValveIsOpen(Objects.isNull(anmRequest.getM2ValveIsOpen()) ? anm.isM2ValveIsOpen() : anmRequest.getM2ValveIsOpen());
		anm.setW1ValveIsOpen(Objects.isNull(anmRequest.getW1ValveIsOpen()) ? anm.isW1ValveIsOpen() : anmRequest.getW1ValveIsOpen());
		anm.setW2ValveIsOpen(Objects.isNull(anmRequest.getW2ValveIsOpen()) ? anm.isW2ValveIsOpen() : anmRequest.getW2ValveIsOpen());
		return anmRepository.save(anm);
	}

	public void deleteAnm(UUID id) {
		System.out.println("Deleting ANM with id " + id);
		anmRepository.delete(id);
		removeDefaultTopics(id);
	}

	private void addTopics(Anm anm) {
		ComponentTopics newComponentTopics = new ComponentTopics(anm.getAnmId().toString());

		String temperatureTopic = newComponentTopics.getTemperatureTopicName();
		System.out.println("Adding new topic: " + temperatureTopic);
		mqttConfig.adapter.addTopic(temperatureTopic, 2);

		String pressureTopic = newComponentTopics.getPressureTopicName();
		System.out.println("Adding new topic: " + pressureTopic);
		mqttConfig.adapter.addTopic(pressureTopic, 2);

		String customTopic = newComponentTopics.getCustomTopicName();
		System.out.println("Adding new topic: " + customTopic);
		mqttConfig.adapter.addTopic(customTopic, 2);

	}

	private void removeDefaultTopics(UUID id) {
		ComponentTopics componentTopics = new ComponentTopics(id.toString());
		mqttConfig.adapter.removeTopic(componentTopics.getTemperatureTopicName(),
				componentTopics.getPressureTopicName(),
				componentTopics.getCustomTopicName());
	}

}
