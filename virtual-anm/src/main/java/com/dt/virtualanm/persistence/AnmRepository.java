package com.dt.virtualanm.persistence;

import static com.datastax.driver.core.DataType.text;
import static com.datastax.driver.core.DataType.timestamp;
import static com.datastax.driver.core.DataType.uuid;
import static com.datastax.driver.core.querybuilder.QueryBuilder.select;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.schemabuilder.SchemaBuilder;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import com.dt.virtualanm.persistence.entity.Anm;

@Repository
public class AnmRepository {

	private Mapper<Anm> mapper;

	private Session session;

	private static final String TABLE = "anm";

	public AnmRepository(MappingManager mappingManager) {
		createTable(mappingManager.getSession());
		this.mapper = mappingManager.mapper(Anm.class);
		this.session = mappingManager.getSession();
	}

	private void createTable(Session session) {
		session.execute(
				SchemaBuilder.createTable(TABLE)
						.ifNotExists()
						.addPartitionKey("anm_id", uuid())
						.addColumn("name", text())
						.addColumn("anm_info", text())
						.addColumn("creation_date_time", timestamp()));
	}

	public Anm find(UUID id) {
		return mapper.get(id);
	}

	public List<Anm> findAll() {
		final ResultSet result = session.execute(select().all().from(TABLE));
		return mapper.map(result).all();
	}

	public void delete(UUID id) {
		mapper.delete(id);
	}

	public Anm save(Anm anm) {
		mapper.save(anm);
		return anm;
	}

}
